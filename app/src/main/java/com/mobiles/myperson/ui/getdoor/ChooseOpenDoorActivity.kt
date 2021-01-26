package com.mobiles.myperson.ui.getdoor

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobiles.myperson.R
import com.mobiles.myperson.adaptet.ChooseOpenDoorAdapter
import com.mobiles.myperson.adaptet.onOpenClickListener
import com.mobiles.myperson.databinding.ActivityChooseOpenDoorBinding
import com.mobiles.myperson.ui.ShowChooseDoorDetailActivity
import kotlinx.android.synthetic.main.activity_choose_open_door.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbarchoose.*
import java.io.File

class ChooseOpenDoorActivity : AppCompatActivity(), onOpenClickListener {

    companion object {
        val INTENT_PARCELABLE = "OBJECT_INTENT"
    }

    private lateinit var binding: ActivityChooseOpenDoorBinding
    private lateinit var chooseDoorAdapter: ChooseOpenDoorAdapter
    private lateinit var viewModel: GetDoorViewModel
    private val REQ_CODE_IMG = 123
    private lateinit var mFilePath: File
    private lateinit var mExternalDir: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChooseOpenDoorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar()

        mExternalDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!

        viewModel = ViewModelProvider.NewInstanceFactory().create(GetDoorViewModel::class.java)

        setupWidget()

        // มีการ Feed ให้เห็นว่า กำลัง LoadData ด้วย

//    showDetail()
    }


//    private fun showDetail() {
//        val intent = Intent(this@ChooseOpenDoorActivity, ShowChooseDoorDetailActivity::class.java)
////      val chooseOpenDoorAdapter = ChooseOpenDoorAdapter(it, this)
//        val bundle = Bundle()
//        intent.putExtra(INTENT_PARCELABLE,bundle)
//    }

    private fun setToolbar() {
        val myToolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.myToolbar)
        val tvTitle: TextView = findViewById(R.id.tv_toolbar_title)
        setSupportActionBar(myToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        tvTitle.text = "เลือกประตู"

    }


    private fun setupWidget() {
        viewModel.fetchGetdoorList("").observe(this, Observer {
            val chooseOpenDoorAdapter = ChooseOpenDoorAdapter(it, this)
            door_item_recycleview.apply {
                adapter = chooseOpenDoorAdapter
                layoutManager = LinearLayoutManager(context)
                binding.swipeRefresh.isRefreshing = true
                binding.swipeRefresh.isRefreshing = false

            }

        })

    }

    private fun imageCapture() {
        val photoFile = File.createTempFile("IMG${System.currentTimeMillis()}", ".jpg", mExternalDir)
        mFilePath = photoFile
        val uri = FileProvider.getUriForFile(this, "com.mobiles.myperson", photoFile)
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
        startActivityForResult(intent, REQ_CODE_IMG)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_CODE_IMG && resultCode == Activity.RESULT_OK) {
            val uri = Uri.fromFile(mFilePath)
            val i = Intent(this@ChooseOpenDoorActivity, ShowChooseDoorDetailActivity::class.java)
            i.putExtra("uri", uri.toString())
            startActivity(i)
            finish()
        }
    }

    override fun onClick(position: Int) {

        imageCapture()
    }

}














