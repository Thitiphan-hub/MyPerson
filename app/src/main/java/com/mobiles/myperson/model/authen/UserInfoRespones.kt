package com.mobiles.myperson.model.authen

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserInfoRespones(
    val BirthDate: String,
    val Branch: String,
    val CompanyCode: String,
    val CompanyID: Int,
    val CompanyName: String,
    val CompanyNameEng: String,
    val CostCenter: String,
    val DivisionCode: String,
    val DivisionID: Int,
    val DivisionName: String,
    val Email: String,
    val EmpCode: String,
    val EmpID: String,
    val EmpPic: String,
    val EmpSex: String,
    val EncryptPassword: String,
    val EncryptUserName: String,
    val Expr1: String,
    val FirstName: String,
    @SerializedName("FullName")
    val FullName: String,
    val HiringDate: String,
    val IsVerify: Boolean,
    val LastName: String,
    val LastNameEN: String,
    val LastNameTH: String,
    val LeaderName: String,
    val Mobile: String,
    val NameEN: String,
    val NameTH: String,
    val NickName: String,
    val PhoneExt: String,
    val PositionCode: String,
    val PositionID: Int,
    val PositionName: String,
    val SectionCode: String,
    val SectionID: Int,
    val SectionName: String,
    val Status: String,
    val TitleEN: String,
    val TitleName: String,
    val TitleTH: String,
    val Token: String,
    val UnitCode: String,
    val UnitID: Int,
    val UnitName: String,
    val UserGUID: String,
    val UserID: Int,
    val UserName: String
) : Parcelable