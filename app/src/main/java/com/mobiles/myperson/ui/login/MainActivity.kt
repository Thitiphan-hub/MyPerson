package com.mobiles.myperson.ui.login
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mobiles.myperson.*
import com.mobiles.myperson.databinding.ActivityMainBinding
import com.mobiles.myperson.ui.getdoor.ChooseOpenDoorActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: ActivityMainBinding

//    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider.NewInstanceFactory().create(LoginViewModel::class.java)

        binding.loginButtonLogin.setOnClickListener {

            val username = binding.loginEdittextUsername.text.toString()
            val password = binding.loginEdittextPassword.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                showToast("Username or Password is Empty")
                return@setOnClickListener

            }

            if (password.length < 4) {
                showToast("Form Input Password invalid")
                return@setOnClickListener

            }

            viewModel.fetchLogin(username, password, "apintranet", "1", "", "4458323266", "Android")
                .observe(this, {

                    // มี Check username กับ password ด้วย แต่ปัญหาตอนนี้ ยังเข้าได้อยู่ ถึงแม้รหัสจะถูก Block

                    val ic = Intent(this, ChooseOpenDoorActivity::class.java)
                    startActivity(ic)
                    finish()
                    Toast.makeText(this, it.Message, Toast.LENGTH_SHORT).show()
                })

            viewModel.liveDataShowLoading.observe(this, Observer {
                if (it) {
                    viewLoading.visibility = View.VISIBLE
                } else {
                    viewLoading.visibility = View.GONE
                }
            })

            viewModel.liveDataAuthenError.observe(this, Observer {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            })

        }

        binding.loginScrollview.apply {
            isVerticalScrollBarEnabled = false
            isHorizontalScrollBarEnabled = false
        }
    }
}


