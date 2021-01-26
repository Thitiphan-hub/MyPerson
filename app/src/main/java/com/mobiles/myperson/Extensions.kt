package com.mobiles.myperson

import android.content.Context
import android.text.BoringLayout.make
import android.view.View
import android.widget.Toast

fun Context.showToast(message : String){
    Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
}



