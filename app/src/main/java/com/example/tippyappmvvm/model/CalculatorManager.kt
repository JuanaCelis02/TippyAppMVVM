package com.example.tippyappmvvm.model

class CalculatorManager {

    fun calculateTipAmount(baseAmount: String, percentTip: Int): String {
        return "%.3f".format(baseAmount.toDouble() * percentTip / 100)
    }
}