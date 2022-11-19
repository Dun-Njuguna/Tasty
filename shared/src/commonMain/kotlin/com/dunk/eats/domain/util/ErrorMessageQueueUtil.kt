package com.dunk.eats.domain.util

import com.dunk.eats.domain.model.ErrorMessage

class ErrorMessageQueueUtil {
    fun isErrorUnique(
        queue: Queue<ErrorMessage>,
        message: ErrorMessage
    ): Boolean {
        for (item in queue.items){
            if (item.id == message.id) {
                return true
            }
        }
        return false
    }
}