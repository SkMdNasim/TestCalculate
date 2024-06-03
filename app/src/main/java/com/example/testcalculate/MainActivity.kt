package com.example.testcalculate

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.testcalculate.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        binding.btnCalculate.setOnClickListener {
            try {
                val al1 : List<Int>  = binding.et1.text.toString().split(",").map{ it.toInt() }
                val al2 : List<Int> = binding.et2.text.toString().split(",").map{ it.toInt() }
                val al3 : List<Int> = binding.et3.text.toString().split(",").map{ it.toInt() }

                Log.d("al1", al1.toString())
                Log.d("al2", al2.toString())
                Log.d("al3", al3.toString())

                val intersect = (al1.intersect(al2)).intersect(al3)
                binding.tvIntersect.text = getString(R.string.intersect) +intersect.toString()

                val union : ArrayList<Int> = arrayListOf()
                union.addAll((al1 union al2) union al3)
                binding.tvUnion.text = getString(R.string.union) + union.toString()


                val sum : ArrayList<Int> = arrayListOf()
                sum.addAll(al1)
                sum.addAll(al2)
                sum.addAll(al3)

                val max: Int = sum.maxOrNull() ?: 0
                Log.d("Max", max.toString())
                binding.tvHighestNum.text = getString(R.string.highest_number) + max


                hideKeyboardFrom(this, binding.btnCalculate)
            }catch (e : Exception){
                e.printStackTrace()
            }
        }
    }

    fun hideKeyboardFrom(context: Context, view: View) {
        val imm = context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}