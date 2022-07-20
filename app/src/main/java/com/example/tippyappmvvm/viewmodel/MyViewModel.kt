package com.example.tippyappmvvm.viewmodel

import androidx.lifecycle.ViewModel
import com.example.tippyappmvvm.model.CalculatorManager

class MyViewModel: ViewModel() {

    private val calculatorManager = CalculatorManager()

    fun calculateTipAmount(baseAmount:String, tipPercent:Int):String{
        return calculatorManager.calculateTipAmount(baseAmount, tipPercent)
    }
}