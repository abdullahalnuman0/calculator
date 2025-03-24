package dev.abdullah.calculator

sealed class Brackets(val symbol: Char) {
    data object Open : Brackets('(')
    data object Close : Brackets(')')
}