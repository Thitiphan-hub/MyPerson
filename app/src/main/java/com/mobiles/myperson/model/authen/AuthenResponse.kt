package com.mobiles.myperson.model.authen

data class AuthenResponse(
    var success: Boolean,
    var data: UserInfoRespones,
    var Message: String
)
