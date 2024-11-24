package net.flow9.thisiskotlin.nomooo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import net.flow9.thisiskotlin.nomooo.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val sharedPreferences = getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
        val userName = sharedPreferences.getString("userName", "이름 없음")
        val userGender = sharedPreferences.getString("gender", "성별 선택 안 됨")
        val weight = sharedPreferences.getInt("weight", -1)
        val weightText = if (weight != -1) "체중: ${weight}kg" else "체중:"
        val selectedExercise = sharedPreferences.getString("selectedExercise", "거의 운동하지 않음")
        val selectedWeather = sharedPreferences.getString("selectedWeather", "날씨 선택 안 됨") // 저장된 날씨 값
        val selectedGender = intent.getStringExtra("selectedGender")
        if (selectedGender != null) {
            with(sharedPreferences.edit()) {
                putString("gender", selectedGender) // 선택된 성별 데이터를 저장
                apply()
            }
            // **즉시 UI 업데이트**
            binding.btnchangesex.text = "성별: $selectedGender"
        }
        // WeightchangeActivity에서 전달된 데이터 처리 부분 추가
        val selectedWeight = intent.getIntExtra("selectedWeight", -1)
        if (selectedWeight != -1) {
            with(sharedPreferences.edit()) {
                putInt("weight", selectedWeight) // 선택된 체중 저장
                apply()
            }
        }

        // 버튼 텍스트 설정
        binding.btnName.text = "이름: $userName"
        binding.btnchangesex.text = "성별: $userGender"
        binding.btnchangeweight.text = weightText
        binding.btnchangeexcersize.text = "활동수준: $selectedExercise"
        binding.btnchangeweather.text = "날씨: $selectedWeather" // 날씨 텍스트 업데이트

        // WindowInsets 처리
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnName.setOnClickListener {
            showNameChangeDialog()
        }

        binding.imageButtonBack8.setOnClickListener {
            finish() // 현재 Activity 종료
        }
        binding.btnchangesex.setOnClickListener {
            val intent = Intent(this, SexchangeActivity::class.java)
            startActivity(intent)
        }
        binding.btnchangeweight.setOnClickListener {
            val intent = Intent(this, WeightchangeActivity::class.java)
            startActivity(intent)
        }
        binding.btnchangeexcersize.setOnClickListener {
            val intent = Intent(this, ExercisechangeActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_EXERCISE_CHANGE)
        }
        binding.btnchangeweather.setOnClickListener {
            val intent = Intent(this, WeahterchangeActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_WEATHER_CHANGE)
        }
        // UI 업데이트 수정
        val updatedWeightText =
            if (selectedWeight != -1) "체중: ${selectedWeight}kg"
            else if (weight != -1) "체중: ${weight}kg"
            else "체중:"
        binding.btnchangeweight.text = updatedWeightText

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_EXERCISE_CHANGE && resultCode == RESULT_OK) {
            val selectedExercise = data?.getStringExtra("selectedExercise")
            if (selectedExercise != null) {
                val sharedPreferences = getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
                with(sharedPreferences.edit()) {
                    putString("selectedExercise", selectedExercise) // 선택된 운동 수준 저장
                    apply()
                }
                binding.btnchangeexcersize.text = "활동수준: $selectedExercise" // UI 업데이트
            }
        }
        if (requestCode == REQUEST_CODE_WEATHER_CHANGE && resultCode == RESULT_OK) {
            val selectedWeather = data?.getStringExtra("selectedWeather")
            if (selectedWeather != null) {
                val sharedPreferences = getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
                with(sharedPreferences.edit()) {
                    putString("selectedWeather", selectedWeather) // 선택된 날씨 저장
                    apply()
                }
                binding.btnchangeweather.text = "날씨: $selectedWeather" // UI 업데이트
            }
        }

    }
    private fun showNameChangeDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("이름 변경")

        val input = EditText(this)
        input.hint = "새 이름 입력"
        builder.setView(input)

        builder.setPositiveButton("저장") { dialog, _ ->
            val newName = input.text.toString()

            if (newName.isNotBlank()) {
                val sharedPreferences = getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
                with(sharedPreferences.edit()) {
                    putString("userName", newName)
                    apply()
                }

                // UI 업데이트
                binding.btnName.text = "이름: $newName"
            }

            dialog.dismiss()
        }

        builder.setNegativeButton("취소") { dialog, _ ->
            dialog.dismiss()
        }

        builder.create().show()
    }
    companion object {
        const val REQUEST_CODE_EXERCISE_CHANGE = 1001
        const val REQUEST_CODE_WEATHER_CHANGE = 1002// **REQUEST_CODE 추가**
    }
}