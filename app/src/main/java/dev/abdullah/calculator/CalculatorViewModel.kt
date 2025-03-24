package dev.abdullah.calculator

import android.annotation.SuppressLint
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import net.objecthunter.exp4j.ExpressionBuilder

@SuppressLint("MutableCollectionMutableState")
class CalculatorViewModel : ViewModel() {
    private val _state = mutableStateOf<CalculatorState>(CalculatorState())
    val state: State<CalculatorState> = _state

    private val _historyStack = mutableStateOf<HistoryStack>(HistoryStack())
    val historyStack: State<HistoryStack> = _historyStack

    init {
        addHistoryOnStack()
        println("++++++++++++++++++++++++++++++++++++++++++++++++++=")
        println(historyStack.value)
        println("++++++++++++++++++++++++++++++++++++++++++++++++++=")
    }

    fun onAction(action: CalculatorAction) {

        if (state.value.syntaxError)
            _state.value = CalculatorState()

        when (action) {
            CalculatorAction.Calculate -> performCalculate()
            CalculatorAction.Clear -> _state.value = CalculatorState()
            CalculatorAction.Decimal -> enterDecimal()
            CalculatorAction.Delete -> performDelete()
            is CalculatorAction.Number -> enterNumber(action.number)
            is CalculatorAction.Operation -> enterOperation(action.operation)
            is CalculatorAction.Bracket -> enterBracket(action.brackets)
            is CalculatorAction.Control -> enterHistoryControl(action.historyControl)
        }

    }

    /* private fun enterHistoryControl(historyControl: HistoryControl) {

         val tampUndo = historyStack.value.undo
         val tampRedo = historyStack.value.redo
         var pop: CalculatorState? = null

         if (historyControl == HistoryControl.Undo) {
             pop = tampUndo.pop()
             tampRedo.push(pop)
         } else {
             pop = tampRedo.pop()
             tampUndo.push(pop)
         }

         _state.value = state.value.copy(
             expression = pop.expression,
             isDecimalExist = pop.isDecimalExist,
             syntaxError = pop.syntaxError
         )
         _historyStack.value = historyStack.value.copy(
             undo = tampUndo,
             redo = tampRedo
         )
     }
 */

    private fun enterHistoryControl(historyControl: HistoryControl) {
        val undoStack = historyStack.value.undo.toMutableList()
        val redoStack = historyStack.value.redo.toMutableList()

        var pop: CalculatorState? = null

        if (historyControl == HistoryControl.Undo && undoStack.isNotEmpty()) {

            undoStack.removeAt(undoStack.size - 1)
            redoStack.add(state.value)
            pop = undoStack.getOrNull(undoStack.size - 1)

        } else if (historyControl == HistoryControl.Redo && redoStack.isNotEmpty()) {

            redoStack.removeAt(redoStack.size - 1)
            undoStack.add(state.value)
            pop = redoStack.getOrNull(redoStack.size - 1)

        }

        pop?.let {
            println(pop)
            println(state)
            _state.value = it.copy()
            println(state)

            // নতুন Instance দিয়ে `_historyStack.value` আপডেট করো
            _historyStack.value = HistoryStack(
                undo = undoStack.toList(),
                redo = redoStack.toList()
            )
        }
//        _state.value = _state.value.copy()
        println("++++++++++++++++++++++++++++++++++++++++++++++++++=")
        println(historyStack.value)
        println(state.value)
        println("++++++++++++++++++++++++++++++++++++++++++++++++++=")
    }


    private fun addHistoryOnStack() {
        _historyStack.value = historyStack.value.copy(
            undo = historyStack.value.undo + state.value.copy(),
            redo = emptyList()
        )
    }

    private fun enterBracket(brackets: Brackets) {
        _state.value = state.value.copy(
            expression = state.value.expression + when (brackets) {
                Brackets.Open -> Brackets.Open.symbol
                Brackets.Close -> Brackets.Close.symbol
            }
        )
        addHistoryOnStack() //Store stack data
    }

    private fun enterOperation(operation: CalculatorOperation) {
        if (state.value.expression.isNotEmpty()) {
            if (state.value.expression.last() in "+-*/^%")
                _state.value = state.value.copy(
                    isDecimalExist = false,
                    expression = state.value.expression.dropLast(1) + operation.symbol
                )
            else
                _state.value = state.value.copy(
                    isDecimalExist = false,
                    expression = state.value.expression + operation.symbol
                )

            addHistoryOnStack() //Store stack data
        }
    }

    private fun enterNumber(number: Int) {

        _state.value = state.value.copy(
            expression = state.value.expression + number
        )
        addHistoryOnStack() //Store stack data
    }

    private fun performDelete() {

        if (state.value.expression.isNotEmpty()) {
            _state.value = state.value.copy(
                isDecimalExist = if (state.value.isDecimalExist && state.value.expression.last() == '.') false else state.value.isDecimalExist,
                expression = state.value.expression.dropLast(1)
            )
            addHistoryOnStack() //Store stack data
        }
    }

    private fun enterDecimal() {

        if (!state.value.isDecimalExist) {
            _state.value = state.value.copy(
                expression = state.value.expression + if (state.value.expression.isEmpty() || state.value.expression.last() in "+-*/^%") "0." else ".",
                isDecimalExist = true
            )

            addHistoryOnStack() //Store stack data
        }

    }

    private fun performCalculate() {

        try {
            val result = ExpressionBuilder(state.value.expression)
                .build()
                .evaluate()
                .format()

            _state.value = state.value.copy(
                expression = result,
                isDecimalExist = isDouble,
                syntaxError = false
            )
        } catch (e: Exception) {
            _state.value = state.value.copy(
                expression = "Syntax error !!",
                isDecimalExist = isDouble,
                syntaxError = true
            )
        }

        addHistoryOnStack() //Store stack data
    }

    private var isDouble: Boolean = false

    private fun Double.format(): String {
        if (this.toInt().toDouble() == this) {
            isDouble = false
            return this.toInt().toString()

        } else {
            isDouble = true
            return String.format("%.5f", this).toDouble().toString()
        }
    }
}
