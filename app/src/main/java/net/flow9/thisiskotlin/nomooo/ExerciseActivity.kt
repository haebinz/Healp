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
import net.flow9.thisiskotlin.nomooo.databinding.ActivityExerciseBinding


class ExerciseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExerciseBinding
    private var activityLevel = 0
    private var selectedExerciseText: String = "" // 선택한 버튼의 텍스트를 저장하는 변수

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val isMale = intent.getBooleanExtra("gender", true)
        val weight = intent.getIntExtra("weight", 0)

        // "계속" 버튼 클릭 이벤트
        binding.buttonContinue4.setOnClickListener {
            val intent = Intent(this, WeatherActivity::class.java)
            intent.putExtra("activityLevel", activityLevel)
            intent.putExtra("weight", weight)
            intent.putExtra("gender", isMale)
            startActivity(intent)
        }

        // 뒤로가기 버튼 클릭 이벤트
        binding.imageButtonBack4.setOnClickListener {
            if (selectedExerciseText.isEmpty()) {
                selectedExerciseText = "거의 운동하지 않음" // [수정] 기본값 설정
            }
            saveSelectedExercise() // [기존 코드 유지] 선택한 텍스트를 SharedPreferences에 저장
            val intent = Intent(this, WeightActivity::class.java)
            startActivity(intent)
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 운동 수준 버튼 클릭 이벤트
        binding.SelectExercise1.setOnClickListener { setActivityLevel(0, binding.SelectExercise1) }
        binding.SelectExercise2.setOnClickListener { setActivityLevel(200, binding.SelectExercise2) }
        binding.SelectExercise3.setOnClickListener { setActivityLevel(400, binding.SelectExercise3) }
        binding.SelectExercise4.setOnClickListener { setActivityLevel(700, binding.SelectExercise4) }
    }

    private fun setActivityLevel(level: Int, selectedButton: View) {
        activityLevel = level
        selectedExerciseText = (selectedButton as android.widget.Button).text.toString()

        // 선택한 운동 수준 저장
        saveSelectedExercise()

        // 버튼 상태 업데이트
        updateButtonStates(selectedButton)
    }

    private fun updateButtonStates(selectedButton: View) {
        binding.SelectExercise1.alpha = 0.5f
        binding.SelectExercise2.alpha = 0.5f
        binding.SelectExercise3.alpha = 0.5f
        binding.SelectExercise4.alpha = 0.5f
        selectedButton.alpha = 1.0f
    }



    private fun saveSelectedExercise() {
        val sharedPreferences = getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
        sharedPreferences.edit()
            .putString("selectedExercise", selectedExerciseText)
            .apply()

        // 디버깅용 로그
        Log.d("ExerciseActivity", "Selected exercise saved: $selectedExerciseText")
    }
}


