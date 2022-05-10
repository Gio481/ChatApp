package com.example.chatapp.presentation.ui.chat.viewmodel

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatapp.R
import com.example.chatapp.data.service.broadcast.BroadcastService
import com.example.chatapp.domain.model.ChatDomain
import com.example.chatapp.domain.usecase.GetMessagesUseCase
import com.example.chatapp.util.Constants.MESSAGE_SENDER_KEY
import com.example.chatapp.util.extensions.long.differenceMinutesFrom
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChatViewModel(private val useCase: GetMessagesUseCase) : ViewModel() {

    private val _chatLiveData: MutableLiveData<List<ChatDomain>> = MutableLiveData()
    val chatLiveData: LiveData<List<ChatDomain>> = _chatLiveData

    private val _errorLiveData: MutableLiveData<Int> = MutableLiveData()
    val errorLiveData: LiveData<Int> = _errorLiveData

    private val _broadcastLiveData: MutableLiveData<Intent> = MutableLiveData()
    val broadcastLiveData: LiveData<Intent> = _broadcastLiveData

    private var minutesBetweenDates: Long = DEFAULT_VALUE

    fun getAllMessages() {
        viewModelScope.launch(Dispatchers.IO) {
            _chatLiveData.postValue(useCase.getAllMessages())
        }
    }

    fun insertMessage(user: String, message: String) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.insertMessage(ChatDomain(user = user,
                message = message,
                time = getTime()))
        }
    }

    fun sendMessage(user: String, message: String) {
        if (message.isNotBlank()) {
            viewModelScope.launch {
                val intent = Intent().apply {
                    action = BroadcastService.INTENT_ACTION_NAME
                    putExtra(MESSAGE_SENDER_KEY,
                        ChatDomain(user = user, message = message, time = getTime()))
                }
                _broadcastLiveData.postValue(intent)
            }
        } else {
            _errorLiveData.postValue(R.string.blank_message_error_text)
        }
    }


    private suspend fun getTime(): Long? {
        if (useCase.getAllMessages().isNotEmpty()) {
            minutesBetweenDates =
                (useCase.getTime())?.differenceMinutesFrom(System.currentTimeMillis())!!
        }
        return if (minutesBetweenDates <= MINUTES_TO_SHOW_DATE) {
            null
        } else {
            System.currentTimeMillis()
        }
    }

    companion object {
        private const val DEFAULT_VALUE = 50L
        private const val MINUTES_TO_SHOW_DATE = 10
    }
}