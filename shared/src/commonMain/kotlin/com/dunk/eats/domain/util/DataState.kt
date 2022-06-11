package com.dunk.eats.domain.util

class DataState<T>(
    val message:String? = null,
    val data:T? = null,
    val isLoading:Boolean = false
) {
    companion object{

        fun<T> loading() = DataState<T>(isLoading = true)

        fun<T> error(
            message:String
        ): DataState<T>{
             return DataState(
                 message = message
             )
        }

        fun<T> data(
            message:String? = null,
            data:T? = null
        ): DataState<T>{
            return  DataState(
                message = message,
                data = data
            )
        }


    }
}