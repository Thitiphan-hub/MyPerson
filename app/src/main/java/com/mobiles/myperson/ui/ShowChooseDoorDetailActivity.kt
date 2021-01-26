package com.mobiles.myperson.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.mobiles.myperson.R
import com.mobiles.myperson.databinding.ActivityMainBinding
import com.mobiles.myperson.databinding.ActivityShowChooseDoorDetailBinding
import com.mobiles.myperson.model.ItemListOpenDoor
import com.mobiles.myperson.model.getdoor.GetDoorInfoResponse
import com.mobiles.myperson.ui.getdoor.ChooseOpenDoorActivity
import kotlinx.android.synthetic.main.activity_choose_open_door.*
import kotlinx.android.synthetic.main.view_item_door.*

class ShowChooseDoorDetailActivity : AppCompatActivity() {

   private lateinit var binding: ActivityShowChooseDoorDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShowChooseDoorDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val getdetail = intent.getParcelableExtra<GetDoorInfoResponse>(ChooseOpenDoorActivity.INTENT_PARCELABLE)
//        val tvShowHead = findViewById<TextView>(R.id.showName_Headoffice)
//        val tvShowFloor = findViewById<TextView>(R.id.showName_Frontdoor)
//        tvShowHead.text = getdetail?.DoorShowName
//        tvShowFloor.text = getdetail?.Floor

        setToolbar()
        setWidget()
        getImage()
    }

    private fun setToolbar() {
        val myToolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.myToolbar)
        setSupportActionBar(myToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getImage() {
        val uri = intent.getStringExtra("uri")
        val imgView = findViewById<ImageView>(R.id.image_Employee)
        binding.imageEmployee.apply {
            imgView.setImageURI(Uri.parse(uri))
        }

    }

    @SuppressLint("ResourceType")
    private fun setWidget()  {
        findViewById<Button>(R.id.button_Success).apply {
        setOnClickListener { MaterialDialog(this@ShowChooseDoorDetailActivity).show {
                    customView(R.layout.dialog_choose_success)
                    postDelayed(Runnable { val intent = Intent(this@ShowChooseDoorDetailActivity, ChooseOpenDoorActivity::class.java)
                        startActivity(intent)
                        finish()
                    }, 2000)
            dismiss()

              }

           }
        }
    }

}

//val intent = Intent(this@ShowChooseDoorDetailActivity, ChooseOpenDoorActivity::class.java)
//startActivity(intent)
