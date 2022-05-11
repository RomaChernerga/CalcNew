package com.example.calcnew

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.calcnew.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception


lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var isException : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        supportActionBar?.title = "Calculate"

        binding.apply {
            tvOne.setOnClickListener {appendOnExpression("1",true)}
            tvTwo.setOnClickListener {appendOnExpression("2",true)}
            tvThree.setOnClickListener {appendOnExpression("3",true)}
            tvFour.setOnClickListener {appendOnExpression("4",true)}
            tvFive.setOnClickListener {appendOnExpression("5",true)}
            tvSix.setOnClickListener {appendOnExpression("6",true)}
            tvSeven.setOnClickListener {appendOnExpression("7",true)}
            tvEight.setOnClickListener {appendOnExpression("8",true)}
            tvNine.setOnClickListener {appendOnExpression("9",true)}
            tvZero.setOnClickListener {appendOnExpression("0",true)}
            tvDot.setOnClickListener {appendOnExpression(".",true)}

            tvPlus.setOnClickListener {appendOnExpression("+",false)}
            tvMin.setOnClickListener {appendOnExpression("-",false)}
            tvMult.setOnClickListener {appendOnExpression("*",false)}
            tvOpen.setOnClickListener {appendOnExpression("(",false)}
            tvClose.setOnClickListener {appendOnExpression(")",false)}
            tvDivide.setOnClickListener {appendOnExpression("/",false)}
            tvClear.setOnClickListener { tvExpression.text = ""
                                         tvReult.text = ""  }
            tvBack.setOnClickListener {
                val string = tvExpression.text.toString()
                if(string.isNotEmpty()) {
                    tvExpression.text = string.substring(0, string.length - 1)
                }
                tvReult.text = ""
            }
            tvEqual.setOnClickListener {
                try {
                    val expression = ExpressionBuilder(tvExpression.text.toString()).build()
                    val result = expression.evaluate()
                    val longResult = result.toLong()
                    if(result == longResult.toDouble()) {
                        tvReult.text = longResult.toString()
                    } else {
                        tvReult.text = longResult.toString()
                    }
                } catch (e: Exception) {
                    Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun appendOnExpression(s: String, b: Boolean) {
       binding.apply {
           if(tvReult.text.isNotEmpty()) {
               tvExpression.text = ""
           }
           if(b) {
               tvReult.text = ""
               tvExpression.append(s)
           } else {
               tvExpression.append(tvReult.text)
               tvExpression.append(s)
               tvReult.text = ""
           }
       }
    }
}