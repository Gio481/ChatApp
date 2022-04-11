package com.example.chatapp.data.service.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.chatapp.domain.model.ChatDomain
import com.example.chatapp.util.Constants.MESSAGE_SENDER_KEY

class BroadcastService(private val action: (data: ChatDomain) -> Unit) : BroadcastReceiver() {

    val actionName = INTENT_ACTION_NAME

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == actionName) {
            val data = intent.getParcelableExtra<ChatDomain>(MESSAGE_SENDER_KEY)
            data?.let { action.invoke(it) }
        }
    }

    companion object {
        private const val INTENT_ACTION_NAME = "messagesBroadcast"
    }
}