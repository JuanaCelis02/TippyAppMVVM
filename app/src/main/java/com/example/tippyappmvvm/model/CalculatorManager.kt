package com.example.tippyappmvvm.model

class CalculatorManager() {

    var tipAmount:Double = 0.0

    fun calculateTipAmount(baseAmount: String, percentTip: Int): Double {
        tipAmount = baseAmount.toDouble() * percentTip / 100
        return tipAmount
    }
    fun calculateTotalAmount(baseAmount: String):Double{
        return tipAmount + baseAmount.toDouble()
    }
}