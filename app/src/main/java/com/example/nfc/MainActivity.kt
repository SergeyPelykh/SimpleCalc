package com.example.nfc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlin.text.StringBuilder

class MainActivity : AppCompatActivity() {
    lateinit var displayExamin:TextView
    lateinit var displayResult:TextView
    lateinit var button1: Button
    lateinit var button2: Button
    lateinit var button3: Button
    lateinit var button4: Button
    lateinit var button5: Button
    lateinit var button6: Button
    lateinit var button7: Button
    lateinit var button8: Button
    lateinit var button9: Button
    lateinit var button0: Button
    lateinit var buttonMinus: Button
    lateinit var buttonPlus: Button
    lateinit var buttonMultipl: Button
    lateinit var buttonDivide: Button
    lateinit var buttonClear: Button
    lateinit var buttonEvaluateExpression: Button
    var operationWasEntered: Boolean = false
    var lastSighIsOperate:Boolean = false
    var examin:Int = 0
    var sbExamin:StringBuilder = StringBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        displayResult = findViewById(R.id.display_result)
        displayResult.text = "0"
        displayExamin = findViewById(R.id.display)
        displayExamin.setText(examin.toString())


        button0 = findViewById(R.id.zero)
        button0.setOnClickListener {
            if(!operationWasEntered && (sbExamin.isNotEmpty())) {
                sbExamin.append("0")
                calculateExpression()
            }

        }

        button1 = findViewById(R.id.one)
        button1.setOnClickListener {
            sbExamin.append("1")
            calculateExpression()
        }

        button2 = findViewById(R.id.two)
        button2.setOnClickListener {
            sbExamin.append("2")
            calculateExpression()
        }

        button3 = findViewById(R.id.three)
        button3.setOnClickListener {
            sbExamin.append("3")
            calculateExpression()
        }

        button4 = findViewById(R.id.four)
        button4.setOnClickListener {
            sbExamin.append("4")
            calculateExpression()
        }

        button5 = findViewById(R.id.five)
        button5.setOnClickListener {
            sbExamin.append("5")
            calculateExpression()
        }

        button6 = findViewById(R.id.six)
        button6.setOnClickListener {
            sbExamin.append("6")
            calculateExpression()
        }

        button7 = findViewById(R.id.seven)
        button7.setOnClickListener {
            sbExamin.append("7")
            calculateExpression()
        }

        button8 = findViewById(R.id.eight)
        button8.setOnClickListener {
            sbExamin.append("8")
            calculateExpression()
        }

        button9 = findViewById(R.id.nine)
        button9.setOnClickListener {
            sbExamin.append("9")
            calculateExpression()
        }
        buttonPlus = findViewById(R.id.plus)
        buttonPlus.setOnClickListener {
            toAddSign("+")
        }

        buttonMinus = findViewById(R.id.minus)
        buttonMinus.setOnClickListener{
            toAddSign("-")
        }

        buttonMultipl = findViewById(R.id.mulpl)
        buttonMultipl.setOnClickListener {
            toAddSign("x")
        }
        buttonDivide = findViewById(R.id.divide)
        buttonDivide.setOnClickListener {
            toAddSign("/")
        }

        buttonClear = findViewById(R.id.clear)
        buttonClear.setOnClickListener {
            sbExamin.clear()
            operationWasEntered = false
            lastSighIsOperate = false
            displayResult.setText("")
            displayExamin.setText("")
        }
        buttonEvaluateExpression = findViewById(R.id.equal)
        buttonEvaluateExpression.setOnClickListener {


        }

    }

    private fun toAddSign(sign: String) {
        if (operationWasEntered) {
            sbExamin.deleteCharAt(sbExamin.length - 1)
            sbExamin.append(sign)
        } else {
            if (lastSighIsOperate) {
                displayResult.setText(parseExpression(sbExamin.toString()).toString())
            }
            lastSighIsOperate = true
            sbExamin.append(sign)
            operationWasEntered = true
        }
        displayExamin.setText(sbExamin.toString())
    }

    private fun calculateExpression() {
        operationWasEntered = false
        val expr:String = sbExamin.toString()
        if (expr.length > 9){
            displayExamin.textSize =resources.getDimension(R.dimen.font_medium)
        }
        displayExamin.setText(expr)
        displayResult.setText(parseExpression(expr).toString())
    }

    fun parseExpression(expression:String):Int{
        var pointer = 1
        var startPointer = 0
        var operationSign:String = "!"
        var firstMember:Int = 0
        var secondMember:Int = 0
        if (expression.isEmpty()){
            return 0
        }
        if (expression.length == 1){
            return Integer.parseInt(expression)
        }
        while (pointer < expression.length){
            when(expression[pointer].toString()){
                "x"-> {
                    secondMember = Integer.parseInt(expression.substring(startPointer, pointer))
                    firstMember = calculate(operationSign, firstMember, secondMember)
                    operationSign = "x"
                    startPointer = ++pointer
                    pointer += 1
                }
                "/"-> {
                    secondMember = Integer.parseInt(expression.substring(startPointer, pointer))
                    firstMember = calculate(operationSign, firstMember, secondMember)
                    operationSign = "/"
                    startPointer = ++pointer
                    pointer += 1
                }
                "-"-> {
                    secondMember = Integer.parseInt(expression.substring(startPointer, pointer))
                    firstMember = calculate(operationSign, firstMember, secondMember)
                    operationSign = "-"
                    startPointer = ++pointer
                    pointer += 1
                }
                "+"-> {
                    secondMember = Integer.parseInt(expression.substring(startPointer, pointer))
                    firstMember = calculate(operationSign, firstMember, secondMember)
                    operationSign = "+"
                    startPointer = ++pointer
                    pointer += 1
                }
                else->pointer++
            }
        }


        return calculate(operationSign, firstMember, Integer.parseInt(expression.substring(startPointer, expression.length)))
    }

    fun calculate(sign:String, firstOperand: Int, secondOperand:Int):Int{
        return when (sign){
            "x" -> firstOperand*secondOperand
            "-" -> firstOperand-secondOperand
            "+" -> firstOperand+secondOperand
            "/" -> firstOperand/secondOperand
            "!" ->  secondOperand
            else -> 0
        }
    }
}