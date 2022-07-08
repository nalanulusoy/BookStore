package com.app.bookstore.base.networkstate


/**
 * Created by Nalan Ulusoy on 07,Temmuz,2022
 */
import com.app.bookstore.base.networkstate.errorhandling.ApiResponse
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class ResultAdapter(
    private val type: Type
) : CallAdapter<Type, Call<ApiResponse<Type>>> {
    override fun adapt(call: Call<Type>): Call<ApiResponse<Type>> = ResultCall(call)
    override fun responseType(): Type = type
}