package com.example.tippyappmvvm.viewmodel

import androidx.lifecycle.ViewModel
import com.example.tippyappmvvm.model.CalculatorManager

class MyViewModel: ViewModel() {

    private val calculatorManager = CalculatorManager()
    var savedTipValue:Double? = null
    var savedTotalAmount:Double? = null

    fun calculateTipAmount(baseAmount:String, tipPercent:Int){
        val tipValue = calculatorManager.calculateTipAmount(baseAmount, tipPercent)
        savedTipValue = tipValue
    }

    fun calculateTotalAmount(baseAmount: String){
        val total = calculatorManager.calculateTotalAmount(baseAmount)
        savedTotalAmount = total
    }
}