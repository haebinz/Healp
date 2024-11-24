package net.flow9.thisiskotlin.nomooo

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import net.flow9.thisiskotlin.nomooo.databinding.ActivityFinishBinding

class FinishActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFinishBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        // imageButtonBack7 클릭 시 ResultActivity로 이동
        binding.imageButtonBack7.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            startActivity(intent)
            finish()  // 현재 Activity 종료
        }

        // uttonContinue7 클릭 시 FragmentMain으로 이동
        binding.buttonContinue7.setOnClickListener {
            val intent = Intent(this, FragmentMain::class.java)
            startActivity(intent)
            finish()  // 현재 Activity 종료
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}