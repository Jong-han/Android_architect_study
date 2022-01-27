package com.jh.navermovie.data

sealed class Resource<T>(
    val data: T? = null,
    val errorMsg: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(errorMsg: String?) : Resource<T>(null, errorMsg)

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$errorMsg]"
            is Loading<T> -> "Loading"
        }
    }
}