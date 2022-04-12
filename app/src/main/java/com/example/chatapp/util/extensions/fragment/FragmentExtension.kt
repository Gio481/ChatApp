package com.example.chatapp.util.extensions.fragment

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData

fun <DATA> Fragment.observer(liveData: LiveData<DATA>, observer: (data: DATA) -> Unit) {
    liveData.observe(this.viewLifecycleOwner) { observer(it) }
}

fun Fragment.showToast(text: Int) {
    Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
}