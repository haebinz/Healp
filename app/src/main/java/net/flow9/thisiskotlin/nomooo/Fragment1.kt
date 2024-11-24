package net.flow9.thisiskotlin.nomooo

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import net.flow9.thisiskotlin.nomooo.databinding.ActivityFragmentBinding
import net.flow9.thisiskotlin.nomooo.databinding.Frag1Binding

class Fragment1 : Fragment() {

    private var _binding: Frag1Binding? = null
    private val binding get() = _binding!!

    private var totalIntake = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = Frag1Binding.inflate(inflater, container, false)
        val view = binding.root

        val sharedPreferences = context?.getSharedPreferences("WaterIntake", Context.MODE_PRIVATE)
        val goalIntake = sharedPreferences?.getInt("calculatedResult", 0) ?: 0

        // 이전에 저장된 totalIntake 값을 복원
        totalIntake = sharedPreferences?.getInt("totalIntake", 0) ?: 0

        // UI 업데이트
        binding.textviewresult.text = "일일 수분 섭취목표량: $goalIntake ml"
        binding.textviewremain.text = "남은 수분 섭취량: ${goalIntake - totalIntake} ml"
        binding.textView12.text = "$totalIntake ml"

        binding.btndrink.setOnClickListener {
            showInputDialog(goalIntake)
        }

        binding.btnreset.setOnClickListener {
            resetBeverageIntake(goalIntake)
        }

        return view
    }

    private fun showInputDialog(goalIntake: Int) {
        val editText = EditText(requireContext())
        editText.hint = "섭취량(ml)을 입력하세요"

        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("음료 섭취량 기록")
            .setView(editText)
            .setPositiveButton("확인") { _, _ ->
                val input = editText.text.toString()
                if (input.isNotEmpty()) {
                    try {
                        val intakeAmount = input.toInt()
                        totalIntake += intakeAmount

                        // 현재 시간을 저장
                        val currentTimeMillis = System.currentTimeMillis()

                        // SharedPreferences에 저장
                        val sharedPreferences = context?.getSharedPreferences("WaterIntake", Context.MODE_PRIVATE)
                        sharedPreferences?.edit()?.apply {
                            putInt("totalIntake", totalIntake)
                            putFloat("lastInputAmount", intakeAmount.toFloat()) // 섭취량 저장
                            putLong("lastInputTime", currentTimeMillis) // 시간 저장
                            apply()
                        }

                        binding.textView12.text = "$totalIntake ml"
                        binding.textviewremain.text = "남은 수분 섭취량: ${goalIntake - totalIntake} ml"

                        Toast.makeText(requireContext(), "$intakeAmount ml 추가되었습니다!", Toast.LENGTH_SHORT).show()
                    } catch (e: NumberFormatException) {
                        Toast.makeText(requireContext(), "숫자를 입력하세요!", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(requireContext(), "입력값이 없습니다!", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("취소", null)
            .create()

        dialog.show()
    }



    private fun resetBeverageIntake(goalIntake: Int) {
        totalIntake = 0
        binding.textView12.text = "0 ml"
        binding.textviewremain.text = "남은 수분 섭취량: $goalIntake ml"

        // SharedPreferences 초기화
        val sharedPreferences = context?.getSharedPreferences("WaterIntake", Context.MODE_PRIVATE)
        sharedPreferences?.edit()?.apply {
            putInt("totalIntake", totalIntake)
            apply()
        }

        Toast.makeText(requireContext(), "섭취량이 초기화되었습니다.", Toast.LENGTH_SHORT).show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}