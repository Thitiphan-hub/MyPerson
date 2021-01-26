package com.mobiles.myperson.ui.login
import android.os.Build
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobiles.myperson.model.authen.AuthenRequest
import com.mobiles.myperson.model.authen.AuthenResponse
import com.mobiles.myperson.model.authen.UserInfoRespones
import com.mobiles.myperson.network.authen.ApiClient
import com.mobiles.myperson.network.authen.AuthenApi
import retrofit2.Call
import retrofit2.Response

class LoginViewModel  : ViewModel() {

    var liveDataUserInfoRespones: MutableLiveData<AuthenResponse> = MutableLiveData()
    var liveDataShowLoading: MutableLiveData<Boolean> = MutableLiveData()
    val liveDataAuthenError: MutableLiveData<String> = MutableLiveData()

    fun fetchLogin(UserName: String, Password: String, AppCode: String,
        AppVersion: String, Build: String, DeviceID: String, OS: String): LiveData<AuthenResponse> {
        val apiClient = ApiClient()
        val retrofit = apiClient.buildRetrofit()
        val authenRepo = retrofit.create(AuthenApi::class.java)

        liveDataShowLoading.value = true
        val body = AuthenRequest(UserName,Password,AppCode,AppVersion, Build,DeviceID,OS)
        authenRepo.login(body).enqueue(object : retrofit2.Callback<AuthenResponse> {
            override fun onResponse(call: Call<AuthenResponse>, response: Response<AuthenResponse>) {

                liveDataShowLoading.value = false
                if (response.isSuccessful) {
                        liveDataUserInfoRespones.value = response.body()
                } else {
                    Log.e("login", response.message())
                    liveDataAuthenError.value = "Email or Password Invalid"
                }
            }
            override fun onFailure(call: Call<AuthenResponse>, t: Throwable) {
                Log.e("Login", t.message!!)
                liveDataShowLoading.value = false
                liveDataAuthenError.value = "Email or Password Invalid"
            }
        })

        return liveDataUserInfoRespones

    }


}