package dev.abdullah.calculator

import java.util.Stack

data class HistoryStack(
    val undo: List<CalculatorState> = emptyList(),
    val redo: List<CalculatorState> = emptyList()
)
