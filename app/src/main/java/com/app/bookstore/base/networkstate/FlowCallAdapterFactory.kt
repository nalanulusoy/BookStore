package com.app.bookstore.base.networkstate

import com.app.bookstore.base.networkstate.errorhandling.ApiResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type


/**
 * Created by Nalan Ulusoy on 07,Temmuz,2022
 */
class FlowCallAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? = when (getRawType(returnType)) {
        Flow::class.java -> {
            val observableType = getParameterUpperBound(0, returnType as ParameterizedType)
            require(observableType is ParameterizedType) { "resource must be parameterized" }
            val rawObservableType = getRawType(observableType)
            require(rawObservableType == ApiResponse::class.java) { "type must be a resource" }
            val bodyType = getParameterUpperBound(0, observableType)
            FlowCallAdapter(bodyType)
        }

        Call::class.java -> {
            val callType = getParameterUpperBound(0, returnType as ParameterizedType)
            when (getRawType(callType)) {
                ApiResponse::class.java -> {
                    val resultType = getParameterUpperBound(0, callType as ParameterizedType)
                    ResultAdapter(resultType)
                }
                else -> null
            }
        }

        else -> null
    }

    companion object {
        @JvmStatic
        fun create() = FlowCallAdapterFactory()
    }
}