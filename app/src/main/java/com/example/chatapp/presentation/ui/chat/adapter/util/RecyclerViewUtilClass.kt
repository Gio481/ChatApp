package com.example.chatapp.presentation.ui.chat.adapter.util

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.domain.model.ChatDomain

interface RecyclerViewUtilClass {
    fun getItemViewType(user: String, list: List<ChatDomain>, position: Int): Int
    fun getVewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    fun onBind(holder: RecyclerView.ViewHolder, list: List<ChatDomain>, position: Int)
}