package com.example.chatapp.presentation.ui.chat.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.domain.model.ChatDomain
import com.example.chatapp.presentation.ui.chat.adapter.util.RecyclerViewUtilClass

class ChatAdapter(private val user: String, private val util: RecyclerViewUtilClass) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val itemList: MutableList<ChatDomain> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return util.getVewHolder(parent, viewType)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun getItemViewType(position: Int): Int {
        return util.getItemViewType(user, itemList, position)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        util.onBind(holder, itemList, position)
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
}