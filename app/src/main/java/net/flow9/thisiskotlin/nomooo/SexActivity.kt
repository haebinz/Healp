package net.flow9.thisiskotlin.nomooo

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import net.flow9.thisiskotlin.nomooo.databinding.ActivitySexBinding

class SexActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySexBinding
    private var isMale = true // 성별 상태 변수

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySexBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 남성 버튼 클릭 시 처리
        binding.imageButtonMale2.setOnClickListener {
            isMale = true
            binding.imageButtonMale2.alpha = 1.0f           // 남성 버튼 활성화
            binding.imageButtonFemale.alpha = 0.5f         // 여성 버튼 비활성화
            saveGenderToPreferences("남성") // **SharedPreferences에 "남성" 저장**
        }

        // 여성 버튼 클릭 시 처리
        binding.imageButtonFemale.setOnClickListener {
            isMale = false
            binding.imageButtonFemale.alpha = 1.0f         // 여성 버튼 활성화
            binding.imageButtonMale2.alpha = 0.5f         // 남성 버튼 비활성화
            saveGenderToPreferences("여성") // **SharedPreferences에 "여성" 저장**
        }

        // "계속" 버튼 클릭 시 WeightActivity로 이동
        binding.buttonContinue2.setOnClickListener {
            val intent = Intent(this, WeightActivity::class.java)
            startActivity(intent)  // WeightActivity로 이동
        }

        // 뒤로가기 버튼 클릭 시 NameActivity로 이동
        binding.imageButtonBack2.setOnClickListener {
            val intent = Intent(this, NameActivity::class.java)
            startActivity(intent)
            finish()  // 현재 Activity 종료
        }
    }

    // SharedPreferences에 성별 저장하는 메서드
    private fun saveGenderToPreferences(gender: String) {
        val sharedPreferences = getSharedPreferences("UserProfile", MODE_PRIVATE)
        sharedPreferences.edit().putString("gender", gender).apply() // gender 값을 저장
    }
}