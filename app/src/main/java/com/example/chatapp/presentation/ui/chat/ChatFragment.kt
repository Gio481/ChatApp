package com.example.chatapp.presentation.ui.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.chatapp.data.service.broadcast.BroadcastService
import com.example.chatapp.databinding.FragmentChatBinding
import com.example.chatapp.presentation.base.BaseFragment
import com.example.chatapp.presentation.ui.chat.chat_user.ChatUser
import com.example.chatapp.presentation.ui.chat.viewmodel.ChatViewModel
import kotlin.reflect.KClass

class ChatFragment(private val user: ChatUser) : BaseFragment<FragmentChatBinding, ChatViewModel>() {

    override val bindingInflater: (inflater: LayoutInflater, container: ViewGroup?, attachRoot: Boolean) -> FragmentChatBinding
        get() = FragmentChatBinding::inflate

    override fun getViewModelClass(): KClass<ChatViewModel> = ChatViewModel::class

    override val broadcastService: BroadcastService = BroadcastService {}

    override fun onBindViewModel(viewModel: ChatViewModel) {}
}