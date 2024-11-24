package net.flow9.thisiskotlin.nomooo

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import net.flow9.thisiskotlin.nomooo.databinding.ActivityExerciseBinding
import net.flow9.thisiskotlin.nomooo.databinding.ActivityExercisechangeBinding

class ExercisechangeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExercisechangeBinding
    private var activityLevel = 0
    private var selectedExerciseText: String = "" // 선택한 버튼의 텍스트를 저장하는 변수
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExercisechangeBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // imageButtonBack11 클릭 시 ProfileActivity로 이동
        binding.imageButtonBack11.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            finish() // 현재 액티비티 종료
        }
        // 운동 수준 버튼 클릭 이벤트
        binding.selectExercise.setOnClickListener { setActivityLevel(0, binding.selectExercise) }
        binding.selectExercise2.setOnClickListener {
            setActivityLevel(
                200,
                binding.selectExercise2
            )
        }
        binding.selectExercise3.setOnClickListener {
            setActivityLevel(
                400,
                binding.selectExercise3
            )
        }
        binding.selectExercise4.setOnClickListener {
            setActivityLevel(
                700,
                binding.selectExercise4
            )
        }

        binding.buttonContinue9.setOnClickListener {
            val intent = Intent()
            intent.putExtra("selectedExercise", selectedExerciseText) // 선택된 텍스트 전달
            setResult(RESULT_OK, intent) // 결과 전달
            finish() // 현재 Activity 종료
        }
    }

    private fun setActivityLevel(level: Int, selectedButton: View) {
        activityLevel = level
        selectedExerciseText = (selectedButton as android.widget.Button).text.toString()


        // 버튼 상태 업데이트
        updateButtonStates(selectedButton)
    }

    private fun updateButtonStates(selectedButton: View) {
        binding.selectExercise.alpha = 0.5f
        binding.selectExercise2.alpha = 0.5f
        binding.selectExercise3.alpha = 0.5f
        binding.selectExercise4.alpha = 0.5f
        selectedButton.alpha = 1.0f
    }
}