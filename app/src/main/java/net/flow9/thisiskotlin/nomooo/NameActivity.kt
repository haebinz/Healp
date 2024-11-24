package net.flow9.thisiskotlin.nomooo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import net.flow9.thisiskotlin.nomooo.databinding.ActivityNameBinding

class NameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // "계속" 버튼 클릭 시 이름 저장 후 화면 전환
        binding.buttonContinue.setOnClickListener {
            val name = binding.editTextName.text.toString()

            // SharedPreferences에 이름 저장
            val sharedPreferences = getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
            sharedPreferences.edit().putString("userName", name).apply()

            // 다음 화면(SexActivity)로 이동
            val intent = Intent(this, SexActivity::class.java)
            startActivity(intent)
        }

        // imageButtonBack 클릭 시 MainActivity로 이동
        binding.imageButtonBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // 현재 Activity 종료
        }
    }
}
