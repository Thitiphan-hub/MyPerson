package com.mobiles.myperson.model.authen

import com.google.gson.annotations.SerializedName

data class AuthenRequest(
    @SerializedName("UserName")
    var UserName: String,
    @SerializedName("Password")
    var Password: String,
    @SerializedName("AppCode")
    val AppCode: String,
    @SerializedName("AppVersion")
    val AppVersion: String,
    @SerializedName("Build")
    val Build: String,
    @SerializedName("DeviceID")
    val DeviceID: String,
    @SerializedName("OS")
    val OS: String,

    )
