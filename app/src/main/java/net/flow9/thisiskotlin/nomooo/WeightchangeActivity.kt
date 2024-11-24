package net.flow9.thisiskotlin.nomooo

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import net.flow9.thisiskotlin.nomooo.databinding.ActivityWeightchangeBinding

class WeightchangeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWeightchangeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // ViewBinding 초기화
        binding = ActivityWeightchangeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // NumberPicker 설정
        binding.numberPickerWeight2.minValue = 0         // 최소값 설정
        binding.numberPickerWeight2.maxValue = 150       // 최대값 설정
        binding.numberPickerWeight2.value = 75           // 기본값 설정
        binding.numberPickerWeight2.wrapSelectorWheel = true // 반복 스크롤 가능

        // WindowInsets 처리
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // "계속" 버튼 클릭 시 이벤트 처리
        binding.buttonContinue8.setOnClickListener {
            // 선택된 체중 값 가져오기
            val selectedWeight = binding.numberPickerWeight2.value

            // Intent를 사용하여 ProfileActivity로 데이터 전달
            val intent = Intent(this, ProfileActivity::class.java).apply {
                putExtra("selectedWeight", selectedWeight)
            }
            startActivity(intent)
            finish() // 현재 액티비티 종료
        }

        // "뒤로가기" 버튼 클릭 시 ProfileActivity로 이동
        binding.imageButtonBack10.setOnClickListener {
            finish()
        }
    }
}