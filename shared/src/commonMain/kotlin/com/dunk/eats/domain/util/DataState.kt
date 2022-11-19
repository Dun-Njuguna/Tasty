package com.dunk.eats.domain.util

import com.dunk.eats.domain.model.ErrorMessage

class DataState<T>(
    val errorMessage:ErrorMessage ? = null,
    val data:T? = null,
    val isLoading:Boolean = false
) {
    companion object{

        fun<T> loading() = DataState<T>(isLoading = true)

        fun<T> error(
            message:ErrorMessage
        ): DataState<T>{
             return DataState(
                 errorMessage = message
             )
        }

        fun<T> data(
            message:ErrorMessage? = null,
            data:T? = null
        ): DataState<T>{
            return  DataState(
                errorMessage = message,
                data = data
            )
        }


    }
}