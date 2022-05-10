package com.example.chatapp.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.chatapp.R
import com.example.chatapp.presentation.ui.chat.ChatFragment
import com.example.chatapp.presentation.ui.chat.chat_user.ChatUser
import com.example.chatapp.util.extensions.activity.transaction

class ChatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        transaction(R.id.topFragment, ChatFragment(ChatUser.TOP))
        transaction(R.id.bottomFragment, ChatFragment(ChatUser.BOTTOM))
    }
}