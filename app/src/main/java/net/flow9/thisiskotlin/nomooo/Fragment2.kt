package net.flow9.thisiskotlin.nomooo

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.ScatterChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.ScatterData
import com.github.mikephil.charting.data.ScatterDataSet
import net.flow9.thisiskotlin.nomooo.databinding.Frag2Binding
import org.json.JSONArray
import java.util.Calendar

class Fragment2 : Fragment() {
    private var _binding: Frag2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = Frag2Binding.inflate(inflater, container, false)

        setupGraph()
        return binding.root
    }
    private fun setupGraph() {
        val sharedPreferences = context?.getSharedPreferences("WaterIntake", Context.MODE_PRIVATE)
        val lastInputAmount = sharedPreferences?.getFloat("lastInputAmount", 0f) ?: 0f
        val lastInputTime = sharedPreferences?.getLong("lastInputTime", 0L) ?: 0L
        val totalIntake = sharedPreferences?.getInt("totalIntake", 0) ?: 0

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        val startOfDayMillis = calendar.timeInMillis

        // 시간과 섭취량을 기반으로 그래프의 x, y 값 계산
        val xValue = (lastInputTime - startOfDayMillis) / (60 * 60 * 1000f) // 시간 (시간 단위)
        val yValue = lastInputAmount / 1000f // 섭취량 (리터 단위)

        // 그래프 데이터 추가
        val entries = mutableListOf<Entry>()
        entries.add(Entry(xValue, yValue))

        val dataSet = ScatterDataSet(entries, "Water Intake")
        dataSet.color = android.graphics.Color.BLUE
        dataSet.setScatterShape(ScatterChart.ScatterShape.CIRCLE)
        dataSet.scatterShapeSize = 15f // 점 크기 설정

        val scatterData = ScatterData(dataSet)
        scatterData.setValueTextSize(14f) // 그래프 내부 값 텍스트 크기 설정
        binding.scatterChart.data = scatterData

        // X축 설정
        val xAxis = binding.scatterChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.granularity = 12f
        xAxis.axisMinimum = 0f
        xAxis.axisMaximum = 24f
        xAxis.labelCount = 3
        xAxis.textSize = 14f
        xAxis.valueFormatter = XAxisValueFormatter()

        // Y축 설정
        val yAxis = binding.scatterChart.axisLeft
        yAxis.axisMinimum = 0f
        yAxis.axisMaximum = 3f
        yAxis.granularity = 1f
        yAxis.labelCount = 4
        yAxis.textSize = 14f
        binding.scatterChart.axisRight.isEnabled = false

        // 그래프 업데이트
        binding.scatterChart.invalidate()

        // 총 섭취량 업데이트
        binding.totalTextView.text = "합계: $totalIntake ml"
    }





    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    class XAxisValueFormatter : com.github.mikephil.charting.formatter.ValueFormatter() {
        private val labels = arrayOf("12AM", "12PM", "12AM")

        override fun getFormattedValue(value: Float): String {
            return when {
                value < 12 -> labels[0] // 0~12: 12AM
                value == 12f -> labels[1] // 12: 12PM
                value > 12 -> labels[2] // 12~24: 12AM
                else -> "" // 기본값
            }
        }
    }

}