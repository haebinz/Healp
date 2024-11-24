package net.flow9.thisiskotlin.nomooo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import net.flow9.thisiskotlin.nomooo.databinding.ActivityWeatherBinding

class WeatherActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWeatherBinding
    private var climateAdjustment = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val isMale = intent.getBooleanExtra("gender", true)      // 성별 정보 가져오기
        val weight = intent.getIntExtra("weight", 0)             // 체중 정보 가져오기
        val activityLevel = intent.getIntExtra("activityLevel", 0) // 활동 수준 정보 가져오기

        binding.buttonContinue5.setOnClickListener {
            Log.d("WeatherActivity", "buttonContinue5 clicked")
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("climateAdjustment", climateAdjustment) // 기후 정보 전달
            intent.putExtra("activityLevel", activityLevel)          // 활동 수준 정보 다시 전달
            intent.putExtra("weight", weight)                        // 체중 정보 다시 전달
            intent.putExtra("gender", isMale)
            startActivity(intent)
        }

        // 뒤로가기 버튼
        binding.imageButtonBack5.setOnClickListener {
            val intent = Intent(this, ExerciseActivity::class.java)
            startActivity(intent)
            finish()
        }

        // 버튼 클릭 이벤트 설정
        binding.btnWeatherHot.setOnClickListener { setClimateAdjustment(500, binding.btnWeatherHot, "더움") }
        binding.btnWeatherWarm.setOnClickListener { setClimateAdjustment(200, binding.btnWeatherWarm, "온화") }
        binding.btnWeatherCold.setOnClickListener { setClimateAdjustment(0, binding.btnWeatherCold, "추움") }

        // WindowInsets 처리
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setClimateAdjustment(adjustment: Int, selectedButton: View, buttonText: String) {
        climateAdjustment = adjustment

        // 버튼 상태 시각적 업데이트
        binding.btnWeatherHot.alpha = 0.5f
        binding.btnWeatherWarm.alpha = 0.5f
        binding.btnWeatherCold.alpha = 0.5f
        selectedButton.alpha = 1.0f

        // SharedPreferences에 선택한 날씨 저장
        val sharedPreferences = getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString("selectedWeather", buttonText) // 날씨 텍스트 저장
            apply()
        }
    }
}