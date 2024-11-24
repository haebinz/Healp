package net.flow9.thisiskotlin.nomooo

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import net.flow9.thisiskotlin.nomooo.databinding.ActivityMainBinding
import net.flow9.thisiskotlin.nomooo.databinding.ActivityWeightBinding

class WeightActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWeightBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeightBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        val isMale = intent.getBooleanExtra("gender", true)      // 성별 정보 가져오기

// NumberPicker 설정
        binding.numberPickerWeight.minValue = 0         // 최소값 설정
        binding.numberPickerWeight.maxValue = 150       // 최대값 설정
        binding.numberPickerWeight.value = 75           // 시작값 설정
        binding.numberPickerWeight.wrapSelectorWheel = true // 반복 스크롤 가능


        // 선택된 몸무게가 변경될 때마다 이벤트 처리
        binding.numberPickerWeight.setOnValueChangedListener { picker, oldVal, newVal ->
            // 선택된 몸무게가 newVal로 업데이트됨
            binding.textView.text = "선택된 체중: ${newVal}kg"
        }

        /// "계속" 버튼 클릭 시 SharedPreferences에 저장 추가
        binding.buttonContinue3.setOnClickListener {
            val weight = binding.numberPickerWeight.value
            saveWeightToPreferences(weight)  // 체중 값을 SharedPreferences에 저장
            val intent = Intent(this, ExerciseActivity::class.java)
            intent.putExtra("weight", weight)               // 체중 정보 전달
            intent.putExtra("gender", isMale)               // 성별 정보 다시 전달
            startActivity(intent)
        }


        // imageButtonBack3 클릭 시 SexActivity로 이동
        binding.imageButtonBack3.setOnClickListener {
            val intent = Intent(this, SexActivity::class.java)
            startActivity(intent)
            finish()  // 현재 Activity 종료
        }


            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }
    // **체중 저장 메서드 추가**
    private fun saveWeightToPreferences(weight: Int) {
        val sharedPreferences = getSharedPreferences("UserProfile", MODE_PRIVATE)
        sharedPreferences.edit().putInt("weight", weight).apply() // 체중 값 저장
    }
    }