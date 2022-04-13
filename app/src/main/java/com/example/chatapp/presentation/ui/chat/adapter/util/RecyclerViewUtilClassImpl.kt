package com.example.chatapp.presentation.ui.chat.adapter.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.databinding.MessageReceiverItemLayoutBinding
import com.example.chatapp.databinding.MessageSenderItemLayoutBinding
import com.example.chatapp.domain.model.ChatDomain
import com.example.chatapp.presentation.ui.chat.adapter.viewholder.MessageReceiverViewHolder
import com.example.chatapp.presentation.ui.chat.adapter.viewholder.MessageSenderViewHolder

class RecyclerViewUtilClassImpl : RecyclerViewUtilClass {

    override fun getVewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == MESSAGE_SENDER) {
            MessageSenderViewHolder(MessageSenderItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
        } else {
            MessageReceiverViewHolder(MessageReceiverItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun getItemViewType(user: String, list: List<ChatDomain>, position: Int): Int {
        return if (user == list[position].user) {
            MESSAGE_SENDER
        } else {
            MESSAGE_RECEIVER
        }
    }

    override fun onBind(holder: RecyclerView.ViewHolder, list: List<ChatDomain>, position: Int) {
        when (holder) {
            is MessageSenderViewHolder -> holder.onBind(list[position])
            is MessageReceiverViewHolder -> holder.onBind(list[position])
        }
    }

    companion object {
        private const val MESSAGE_SENDER = 0
        private const val MESSAGE_RECEIVER = 1
    }
}