package com.example.chatapp.presentation.ui.chat.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.databinding.MessageReceiverItemLayoutBinding
import com.example.chatapp.databinding.MessageSenderItemLayoutBinding
import com.example.chatapp.domain.model.ChatDomain

class MessageSenderViewHolder(private val binding: MessageSenderItemLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(chatDomain: ChatDomain) {
        with(binding) {
            messageTextView.text = chatDomain.message
            timeTextView.text = chatDomain.time
        }
    }
}

class MessageReceiverViewHolder(private val binding: MessageReceiverItemLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(chatDomain: ChatDomain) {
        with(binding) {
            messageTextView.text = chatDomain.message
            timeTextView.text = chatDomain.time
        }
    }
}