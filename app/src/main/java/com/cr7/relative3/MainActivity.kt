package com.cr7.relative3

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var currentNumber: Double = 0.0
    private var btnNumber: Button? = null
    private var btnDivision: Button? = null
    private var btnMul: Button? = null
    private var btnSub: Button? = null
    private var btnAdd: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        btnNumber = findViewById(R.id.btnNumber)
        btnMul = findViewById(R.id.btnMul)
        btnDivision = findViewById(R.id.btnDivision)
        btnSub = findViewById(R.id.btnSub)
        btnAdd = findViewById(R.id.btnAdd)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val buttons = listOf(btnAdd, btnSub, btnMul, btnDivision)
        for (button in buttons) {
            button?.setOnClickListener { performOperation(it as Button) }
        }

        btnNumber?.setOnClickListener {
            val numberString = btnNumber?.text.toString()
            if (TextUtils.isEmpty(numberString)) {
                currentNumber = 0.0
                btnNumber?.text = ""
            } else {
                currentNumber = numberString.toDouble()
            }
        }
    }

    fun performOperation(button: Button) {
        val operation = button.text.toString()[0]

        val result = when (operation) {
            '+' -> currentNumber + 2.0
            '-' -> currentNumber - 2.0
            '*' -> currentNumber * 2.0
            '/' -> {
                if (2.0 != 0.0) {
                    currentNumber / 2.0
                } else {
                    Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show()
                    currentNumber
                }
            }
            else -> currentNumber
        }
        currentNumber = result
        btnNumber?.text = result.toString()
    }
}
