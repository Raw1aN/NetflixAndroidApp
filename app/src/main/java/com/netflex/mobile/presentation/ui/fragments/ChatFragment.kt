package com.netflex.mobile.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.data.*
import com.example.data.ChatServerGrpc.ChatServerStub
import com.netflex.mobile.R
import kotlinx.coroutines.channels.ReceiveChannel
import com.netflex.mobile.presentation.GrpcService
import com.netflex.mobile.presentation.ListAdapter
import com.netflex.mobile.presentation.MainActivity
import io.grpc.CallCredentials
import io.grpc.ManagedChannelBuilder
import io.grpc.stub.AbstractBlockingStub
import io.grpc.stub.StreamObserver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executor



class ChatFragment : Fragment() {

    private lateinit var chatService: ChatServerGrpc.ChatServerBlockingStub


    val channel = ManagedChannelBuilder
        .forAddress("10.0.2.2", 5226)
        .usePlaintext()
        .build()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View =  inflater.inflate(R.layout.fragment_chat, container, false)
        chatService = ChatServerGrpc.newBlockingStub(channel)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        runBlocking {
            val job = launch(Dispatchers.Default) {
                val request = JoinChannelRequest.newBuilder()
                    .setUsername("chel")
                    .setChannel("1")
                    .build()
                Log.d("GrpcChatManager", "Подключение к каналу...")
                chatService.joinChannel(request)
            }
        }
    }

}