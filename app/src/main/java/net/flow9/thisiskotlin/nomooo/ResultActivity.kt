package net.flow9.thisiskotlin.nomooo

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import net.flow9.thisiskotlin.nomooo.databinding.ActivityResultBinding
import net.flow9.thisiskotlin.nomooo.databinding.ActivityResultBinding.*

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        val isMale = intent.getBooleanExtra("gender", true)
        val weight = intent.getIntExtra("weight", 0)
        val activityLevel = intent.getIntExtra("activityLevel", 0)
        val climateAdjustment = intent.getIntExtra("climateAdjustment", 0)

        val result = calculateWaterIntake(isMale, weight, activityLevel, climateAdjustment)
        binding.textViewResult.text = "$result ml"

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // buttonContinue6 클릭 시 activity_finish로 이동하는 코드
        binding.buttonContinue6.setOnClickListener {
            val intent = Intent(this, FinishActivity::class.java)
            startActivity(intent)
        }
        // imageButtonBack6 클릭 시 WeatherActivity로 이동
        binding.imageButtonBack6.setOnClickListener {
            val intent = Intent(this, WeatherActivity::class.java)
            startActivity(intent)
            finish()  // 현재 Activity 종료
        }
    }
    private fun calculateWaterIntake(isMale: Boolean, weight: Int, activityLevel: Int, climateAdjustment: Int): Int {
        val baseIntake = if (isMale) weight * 35 else weight * 30
        val result = baseIntake + activityLevel + climateAdjustment

        // SharedPreferences에 저장
        val sharedPreferences = getSharedPreferences("WaterIntake", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("calculatedResult", result)
        editor.apply()

        return result
    }

}