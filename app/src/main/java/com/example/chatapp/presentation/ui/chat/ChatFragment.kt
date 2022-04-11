package com.example.chatapp.presentation.ui.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.chatapp.databinding.FragmentChatBinding
import com.example.chatapp.presentation.base.BaseFragment
import com.example.chatapp.presentation.ui.chat.viewmodel.ChatViewModel
import kotlin.reflect.KClass

class ChatFragment : BaseFragment<FragmentChatBinding, ChatViewModel>() {

    override val bindingInflater: (inflater: LayoutInflater, container: ViewGroup?, attachRoot: Boolean) -> FragmentChatBinding
        get() = FragmentChatBinding::inflate

    override fun getViewModelClass(): KClass<ChatViewModel> = ChatViewModel::class

    override fun onBindViewModel(viewModel: ChatViewModel) {}
}