package net.flow9.thisiskotlin.nomooo

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import net.flow9.thisiskotlin.nomooo.databinding.ActivityWeahterchangeBinding

class WeahterchangeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWeahterchangeBinding
    private var climateAdjustment = 0
    private var selectedWeatherText: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeahterchangeBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.imageButtonBack12.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            finish()
        }
        // 버튼 클릭 이벤트 설정
        binding.btnWeatherHot2.setOnClickListener {
            setClimateAdjustment(
                500,
                binding.btnWeatherHot2,
                "더움"
            )
        }
        binding.btnWeatherWarm2.setOnClickListener {
            setClimateAdjustment(
                200,
                binding.btnWeatherWarm2,
                "온화"
            )
        }
        binding.btnWeatherCold2.setOnClickListener {
            setClimateAdjustment(
                0,
                binding.btnWeatherCold2,
                "추움"
            )
        }
        binding.buttonContinue10.setOnClickListener {
            val intent = Intent()
            intent.putExtra("selectedWeather", selectedWeatherText) // **선택된 텍스트 전달**
            setResult(RESULT_OK, intent) // **결과 설정**
            finish() // **현재 Activity 종료**
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }

    private fun setClimateAdjustment(adjustment: Int, selectedButton: View, buttonText: String) {
        climateAdjustment = adjustment
        selectedWeatherText = buttonText

        // 버튼 상태 시각적 업데이트
        binding.btnWeatherHot2.alpha = 0.5f
        binding.btnWeatherWarm2.alpha = 0.5f
        binding.btnWeatherCold2.alpha = 0.5f
        selectedButton.alpha = 1.0f
    }
}