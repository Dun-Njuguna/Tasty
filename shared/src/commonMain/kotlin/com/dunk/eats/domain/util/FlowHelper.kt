package com.dunk.eats.domain.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun <T> Flow<T>.asFlowHelper(): FlowHelper<T> = FlowHelper(this)

class FlowHelper<T>(private val origin: Flow<T>): Flow<T> by origin {
    fun collectFlow(
        coroutineScope: CoroutineScope? = null,
        callback: (T) -> Unit,
    ){
        onEach {
            callback(it)
        }.launchIn(coroutineScope?: CoroutineScope(Dispatchers.Main))
    }
}