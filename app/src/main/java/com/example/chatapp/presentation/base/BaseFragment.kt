package com.example.chatapp.presentation.base

import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.example.chatapp.data.service.broadcast.BroadcastService
import org.koin.androidx.viewmodel.ext.android.getViewModel
import kotlin.reflect.KClass

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel> : Fragment() {
    private var _binding: VB? = null
    protected val binding get() = _binding!!
    abstract val broadcastService: BroadcastService
    abstract val bindingInflater: (inflater: LayoutInflater, container: ViewGroup?, attachRoot: Boolean) -> VB

    private lateinit var viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = bindingInflater(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel(clazz = getViewModelClass())
        registerReceiver(broadcastService)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBindViewModel(viewModel)
    }

    abstract fun getViewModelClass(): KClass<VM>
    abstract fun onBindViewModel(viewModel: VM)

    private fun registerReceiver(receiver: BroadcastService) {
        val intentFilter = IntentFilter(receiver.actionName)
        requireContext().registerReceiver(receiver, intentFilter)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        context?.unregisterReceiver(broadcastService)
    }
}