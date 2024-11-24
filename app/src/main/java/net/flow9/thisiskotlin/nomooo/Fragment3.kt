package net.flow9.thisiskotlin.nomooo

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import net.flow9.thisiskotlin.nomooo.databinding.Frag3Binding

class Fragment3 : Fragment() {
    private var _binding: Frag3Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = Frag3Binding.inflate(inflater, container, false)

        // editProfile 버튼 클릭 이벤트 처리
        binding.editProfile.setOnClickListener {
            // ProfileActivity로 이동
            val intent = Intent(requireContext(), ProfileActivity::class.java)
            startActivity(intent)
        }
        binding.settingBell.setOnClickListener{
            val intent = Intent(requireContext(), AlarmActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}