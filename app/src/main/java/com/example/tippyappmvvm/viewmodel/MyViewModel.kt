package com.example.tippyappmvvm.viewmodel

import androidx.lifecycle.ViewModel
import com.example.tippyappmvvm.model.CalculatorManager
import com.example.tippyappmvvm.model.CategoryTip

class MyViewModel: ViewModel() {

    private val calculatorManager = CalculatorManager()

    fun calculateTipAmount(baseAmount:String, tipPercent:Int):Double{
        return calculatorManager.calculateTipAmount(baseAmount, tipPercent)
    }

    fun calculateTotalAmount(baseAmount: String):Double{
        return calculatorManager.calculateTotalAmount(baseAmount)
    }

    fun addTipDescription(tipPercent: Int): CategoryTip {
        return calculatorManager.addDescriptionTip(tipPercent)
    }
}