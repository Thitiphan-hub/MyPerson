package com.mobiles.myperson.network.authen

import com.mobiles.myperson.model.authen.AuthenRequest
import com.mobiles.myperson.model.authen.AuthenResponse
import com.mobiles.myperson.model.authen.UserInfoRespones
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenApi {

    @POST("api/Authorize/login")
    fun login(@Body user: AuthenRequest): Call<AuthenResponse>
}