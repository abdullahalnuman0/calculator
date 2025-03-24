package dev.abdullah.calculator

data class CalculatorState(
    val expression: String = "",
    val isDecimalExist: Boolean = false,
    val syntaxError:Boolean = false
)
