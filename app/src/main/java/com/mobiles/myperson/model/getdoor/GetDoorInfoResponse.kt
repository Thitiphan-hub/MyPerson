package com.mobiles.myperson.model.getdoor

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GetDoorInfoResponse(
    @SerializedName("DoorId")
    val DoorId: Int,
    @SerializedName("DoorShowName")
    val DoorShowName: String,
    @SerializedName("Floor")
    val Floor: String,
    @SerializedName("BuildingId")
    val BuildingId: Int,
    @SerializedName("IsActive")
    val IsActive: Boolean
) : Parcelable