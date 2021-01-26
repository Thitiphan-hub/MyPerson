package com.mobiles.myperson.network.getdoor


import com.mobiles.myperson.model.getdoor.GetDoorRequest
import com.mobiles.myperson.model.getdoor.GetDoorResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface GetDoorApi {

    @POST("GetDoorList")
    fun getDoor(@Body doors: GetDoorRequest): Call<GetDoorResponse?>


}
