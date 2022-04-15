package com.example.chatapp.presentation.ui.chat.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatapp.R
import com.example.chatapp.domain.model.ChatDomain
import com.example.chatapp.domain.usecase.GetMessagesUseCase
import com.example.chatapp.util.extensions.calendar.getFormattedDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
            val userMessage = ChatDomain(user = user,
                message = message,
                time = Calendar.getInstance().getFormattedDate())
            insertMessage(user, message)
            action(userMessage)
        } else {
            _errorLiveData.postValue(R.string.blank_message_error_text)
        }
    }

    private fun insertMessage(user: String, message: String) {
        viewModelScope.launch {
            useCase.insertMessage(ChatDomain(user = user,
                message = message,
                time = Calendar.getInstance().getFormattedDate()))
        }
    }
}