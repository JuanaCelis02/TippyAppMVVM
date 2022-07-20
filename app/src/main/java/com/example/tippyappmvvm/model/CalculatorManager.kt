package com.example.tippyappmvvm.model

class CalculatorManager {

    private var tipAmount:Double = 0.0

    fun calculateTipAmount(baseAmount: String, percentTip: Int): Double {
        tipAmount = baseAmount.toDouble() * percentTip / 100
        return tipAmount
    }
    fun calculateTotalAmount(baseAmount: String):Double{
        return tipAmount + baseAmount.toDouble()
    }
    fun addDescriptionTip(percentTip: Int):CategoryTip{
        val tipCategory = when(percentTip){
            in 0..9 -> CategoryTip.BAD
            in 10..14 -> CategoryTip.OKAY
            in 15..19 -> CategoryTip.GOOD
            in 20..24 ->  CategoryTip.GREAT
            else -> CategoryTip.AMAZING
        }
        return tipCategory
    }
}