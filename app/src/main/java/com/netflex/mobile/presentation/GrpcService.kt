package com.netflex.mobile.presentation

import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import java.util.concurrent.TimeUnit

class GrpcService {
    fun getChannel(): ManagedChannel? {
        var channel: ManagedChannel? = null
        return try {
            channel = ManagedChannelBuilder
                .forAddress("10.0.2.2", 5225)
                .usePlaintext()
                .build()
            channel
        } catch (ex: Exception) {
            channel
        }

    }
}