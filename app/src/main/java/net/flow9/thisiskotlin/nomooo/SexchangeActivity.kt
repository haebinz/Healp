package net.flow9.thisiskotlin.nomooo

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import net.flow9.thisiskotlin.nomooo.databinding.ActivitySexchangeBinding

class SexchangeActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySexchangeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 바인딩 초기화
        binding = ActivitySexchangeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // WindowInsets 처리
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // **추가된 코드**: 남성 버튼 클릭 이벤트
        binding.imageButtonMale.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("selectedGender", "남성") // 선택된 성별 데이터를 Intent로 전달
            startActivity(intent)
            finish() // 현재 Activity 종료
        }

        // **추가된 코드**: 여성 버튼 클릭 이벤트
        binding.imageButtonFemale2.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("selectedGender", "여성") // 선택된 성별 데이터를 Intent로 전달
            startActivity(intent)
            finish() // 현재 Activity 종료
        }

        // 기존 뒤로가기 버튼 클릭 이벤트 유지
        binding.imageButtonBack9.setOnClickListener {
            finish()
        }
    }
}