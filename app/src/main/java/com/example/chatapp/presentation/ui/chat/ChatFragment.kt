package com.example.chatapp.presentation.ui.chat

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatapp.data.service.broadcast.BroadcastService
import com.example.chatapp.databinding.FragmentChatBinding
import com.example.chatapp.presentation.base.BaseFragment
import com.example.chatapp.presentation.ui.chat.adapter.ChatAdapter
import com.example.chatapp.presentation.ui.chat.chat_user.ChatUser
import com.example.chatapp.presentation.ui.chat.viewmodel.ChatViewModel
import com.example.chatapp.util.Constants.MESSAGE_SENDER_KEY
import com.example.chatapp.util.extensions.fragment.observer
import com.example.chatapp.util.extensions.fragment.showToast
import kotlin.reflect.KClass

class ChatFragment(private val user: ChatUser) :
    BaseFragment<FragmentChatBinding, ChatViewModel>() {

    override val bindingInflater: (inflater: LayoutInflater, container: ViewGroup?, attachRoot: Boolean) -> FragmentChatBinding
        get() = FragmentChatBinding::inflate

    override fun getViewModelClass(): KClass<ChatViewModel> = ChatViewModel::class

    private val messageAdapter by lazy { ChatAdapter(user.name) }

    override val broadcastService: BroadcastService = BroadcastService()

    override fun onBindViewModel(viewModel: ChatViewModel) {
        viewModel.getAllMessages()
        setUpRecycleView()
        setListener(viewModel)
        observeMessagesLiveData(viewModel)
        observeErrorLiveData(viewModel)
        broadcastReceiverAction(viewModel)
    }

    private fun broadcastReceiverAction(viewModel: ChatViewModel) {
        broadcastService.receiverAction = {
            messageAdapter.addSingleMessage(it)
            viewModel.insertMessage(it.user, it.message)
            binding.messagesRecyclerView.scrollToPosition(messageAdapter.itemCount - 1)
        }
    }

    private fun observeMessagesLiveData(viewModel: ChatViewModel) {
        observer(viewModel.chatLiveData) {
            messageAdapter.setData(it)
        }
    }

    private fun observeErrorLiveData(viewModel: ChatViewModel) {
        observer(viewModel.errorLiveData) {
            showToast(it)
        }
    }

    private fun setListener(viewModel: ChatViewModel) {
        binding.sendMessageButton.setOnClickListener {
            sendBroadcast(viewModel)
        }
    }

    private fun sendBroadcast(viewModel: ChatViewModel) {
        viewModel.sendMessage(user.name, binding.messageEditText.text.toString()) {
            intent {
                action = broadcastService.actionName
                putExtra(MESSAGE_SENDER_KEY, it)
                requireActivity().sendBroadcast(this)
            }
            binding.messageEditText.text = null
        }
    }

    private fun intent(block: Intent.() -> Unit): Intent {
        return Intent().apply(block)
    }

    private fun setUpRecycleView() {
        with(binding.messagesRecyclerView) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = messageAdapter
        }
    }
}