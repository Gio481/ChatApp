package com.example.chatapp.presentation.ui.chat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.databinding.MessageReceiverItemLayoutBinding
import com.example.chatapp.databinding.MessageSenderItemLayoutBinding
import com.example.chatapp.domain.model.ChatDomain

class ChatAdapter(private val user: String) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val itemList: MutableList<ChatDomain> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == MESSAGE_SENDER) {
            MessageSenderViewHolder(MessageSenderItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
        } else {
            MessageReceiverViewHolder(MessageReceiverItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (user == itemList[position].user) {
            MESSAGE_SENDER
        } else {
            MESSAGE_RECEIVER
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MessageSenderViewHolder -> holder.onBind(itemList[position])
            is MessageReceiverViewHolder -> holder.onBind(itemList[position])
        }
    }

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

    fun setData(newList: List<ChatDomain>) {
        this.itemList.clear()
        this.itemList.addAll(newList)
        notifyDataSetChanged()
    }

    fun addSingleMessage(chatDomain: ChatDomain) {
        this.itemList.add(chatDomain)
        notifyItemInserted(itemList.size - 1)
    }

    companion object {
        private const val MESSAGE_SENDER = 0
        private const val MESSAGE_RECEIVER = 1
    }

}