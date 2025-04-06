package com.example.agritech.ui.activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.agritech.MainActivity2
import com.example.agritech.databinding.ActivityOpeningBinding

class OpeningActivity : AppCompatActivity() {
    lateinit var binding:ActivityOpeningBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOpeningBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.signInBtn.setOnClickListener { startActivity(Intent(this,SignInActivity::class.java)) }
        binding.signUpBtn.setOnClickListener { startActivity(Intent(this, SignUpActivity::class.java)) }
        binding.skip3.setOnClickListener{
            startActivity(Intent(this,MainActivity2::class.java))
        }
    }
}