package com.example.chatapp.presentation.ui.chat.adapter.viewholder

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.databinding.MessageReceiverItemLayoutBinding
import com.example.chatapp.databinding.MessageSenderItemLayoutBinding
import com.example.chatapp.domain.model.ChatDomain
import com.example.chatapp.util.extensions.long.getStringDate

class MessageSenderViewHolder(private val binding: MessageSenderItemLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(chatDomain: ChatDomain) {
        with(binding) {
            messageTextView.text = chatDomain.message
            timeTextView.text = chatDomain.time?.getStringDate()
            timeTextView.isVisible = chatDomain.time != null
        }
    }
}

class MessageReceiverViewHolder(private val binding: MessageReceiverItemLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(chatDomain: ChatDomain) {
        with(binding) {
            messageTextView.text = chatDomain.message
            timeTextView.text = chatDomain.time?.getStringDate()
            timeTextView.isVisible = chatDomain.time != null
        }
    }
}