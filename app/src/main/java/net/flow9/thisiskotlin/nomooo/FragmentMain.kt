package net.flow9.thisiskotlin.nomooo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import net.flow9.thisiskotlin.nomooo.databinding.ActivityFragmentBinding

open class FragmentMain : AppCompatActivity() {

    private lateinit var binding: ActivityFragmentBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFragmentBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)

        setFrag(0)

        binding.btnFragment1.setOnClickListener {
            setFrag(0)
        }

        binding.btnFragment2.setOnClickListener {
            setFrag(1)
        }

        binding.btnFragment3.setOnClickListener {
            setFrag(2)
        }




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setFrag(fragNum : Int) {
        val ft = supportFragmentManager.beginTransaction()
        when(fragNum)
        {
            0 ->{
                ft.replace(R.id.main_frame, Fragment1()).commit()
            }
            1 ->{
                ft.replace(R.id.main_frame, Fragment2()).commit()
            }
            2 ->{
                ft.replace(R.id.main_frame, Fragment3()).commit()
            }
        }
    }
}