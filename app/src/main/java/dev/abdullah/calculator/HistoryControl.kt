package dev.abdullah.calculator

sealed class HistoryControl() {
    data object Undo : HistoryControl()
    data object Redo : HistoryControl()
}
