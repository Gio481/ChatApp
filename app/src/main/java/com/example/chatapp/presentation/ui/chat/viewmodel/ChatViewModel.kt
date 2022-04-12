package com.example.chatapp.presentation.ui.chat.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatapp.R
import com.example.chatapp.domain.model.ChatDomain
import com.example.chatapp.domain.usecase.GetMessagesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class ChatViewModel(private val useCase: GetMessagesUseCase) : ViewModel() {

    private val _chatLiveData: MutableLiveData<List<ChatDomain>> = MutableLiveData()
    val chatLiveData: LiveData<List<ChatDomain>> = _chatLiveData

    private val _errorLiveData: MutableLiveData<Int> = MutableLiveData()
    val errorLiveData: LiveData<Int> = _errorLiveData

    fun getAllMessages() {
        viewModelScope.launch(Dispatchers.IO) {
            _chatLiveData.postValue(useCase.getAllMessages())
        }
    }

    fun sendMessage(user: String, message: String, action: (message: ChatDomain) -> Unit) {
        if (message.isNotBlank()) {
            val userMessage = ChatDomain(user = user, message = message, time = getTime())
            action(userMessage)
        }
    }

    fun insertMessage(user: String, message: String) {
        viewModelScope.launch {
            if (message.isNotBlank()) {
                useCase.insertMessage(ChatDomain(user = user, message = message, time = getTime()))
            } else {
                _errorLiveData.postValue(R.string.blank_message_error_text)
            }
        }
    }


    @SuppressLint("SimpleDateFormat")
    private fun getTime(): String {
        val sdf = SimpleDateFormat("MMM dd, HH:mm")
        return sdf.format(Calendar.getInstance().time)
    }
}