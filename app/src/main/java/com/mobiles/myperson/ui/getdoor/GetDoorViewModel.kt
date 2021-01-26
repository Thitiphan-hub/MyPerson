package com.mobiles.myperson.ui.getdoor

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobiles.myperson.adaptet.ChooseOpenDoorAdapter
import com.mobiles.myperson.model.getdoor.GetDoorInfoResponse
import com.mobiles.myperson.model.getdoor.GetDoorRequest
import com.mobiles.myperson.model.getdoor.GetDoorResponse
import com.mobiles.myperson.network.authen.ApiClient
import com.mobiles.myperson.network.getdoor.ApiClientDoor
import com.mobiles.myperson.network.getdoor.GetDoorApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetDoorViewModel: ViewModel() {

        private  lateinit var binding : GetDoorViewModel
        var liveDataGetDoorInfoList: MutableLiveData<ArrayList<GetDoorInfoResponse>> = MutableLiveData()
        var liveDataShowLoading: MutableLiveData<Boolean> = MutableLiveData()
        val liveDataFetchGetdoorError: MutableLiveData<String> = MutableLiveData()

        fun fetchGetdoorList(EmpCode : String): LiveData<ArrayList<GetDoorInfoResponse>> {
            val apiClientDoor = ApiClientDoor()
            val retrofit = apiClientDoor.buildRetrofit()
            val getdoorRepo = retrofit?.create(GetDoorApi::class.java)

            val door = GetDoorRequest(EmpCode)
            getdoorRepo?.getDoor(door)?.enqueue(object : Callback<GetDoorResponse?> {

                override fun onResponse(call: Call<GetDoorResponse?>, response: Response<GetDoorResponse?>
                ) {

                    liveDataShowLoading.value = false

                    Log.e("Getdoor", response.message())
                    liveDataFetchGetdoorError.value = "ดึงไม่สำเร็จ"
                    if (response.isSuccessful) {
                        liveDataGetDoorInfoList.value = response.body()!!.data

                    } else {
                        Log.e("Getdoor", response.message())
                        liveDataFetchGetdoorError.value = "ข้อมูลไม่สำเร็จ"
                    }
                }

                override fun onFailure(call: Call<GetDoorResponse?>, t: Throwable) {
                    Log.e("GetDoor", t.message!!)
                    liveDataShowLoading.value = false
                    liveDataFetchGetdoorError.value = "ดึงข้อมูลไม่สำเร็จ"

                }

            })
            return liveDataGetDoorInfoList

//                    as MutableLiveData<ArrayList<GetDoorInfoResponse>>
        }


}






