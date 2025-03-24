package dev.abdullah.calculator

sealed class CalculatorAction {
    data class Number(val number: Int) : CalculatorAction()
    data object Clear : CalculatorAction()
    data object Delete : CalculatorAction()
    data object Decimal : CalculatorAction()
    data object Calculate : CalculatorAction()
    data class Operation(val operation: CalculatorOperation) : CalculatorAction()
    data class Control(val historyControl: HistoryControl) : CalculatorAction()
    data class Bracket(val brackets: Brackets) : CalculatorAction()
}
