package dev.abdullah.calculator

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.abdullah.calculator.ui.theme.CalculatorTheme
import dev.abdullah.calculator.ui.theme.LightGray
import dev.abdullah.calculator.ui.theme.MediumGray
import dev.abdullah.calculator.ui.theme.Orange
import org.jetbrains.annotations.TestOnly

//@Preview
@Composable
private fun TestOnly() {
    CalculatorTheme {
        Scaffold { innerPadding ->
            Calculator(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .background(MediumGray),
                enableUndo = true,
                enableRedo = true,
                state = CalculatorState()
            ) { }
        }
    }

}

@Composable
fun Calculator(
    modifier: Modifier = Modifier,
    state: CalculatorState,
    buttonSpacing: Dp = 8.dp,
    enableUndo: Boolean,
    enableRedo: Boolean,
    scrollState: ScrollState = rememberScrollState(),
    onAction: (CalculatorAction) -> Unit
) {
    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {

            ///------------------------------------------------------------
            LaunchedEffect(state.expression) {
                scrollState.animateScrollTo(scrollState.maxValue)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(scrollState)
                    .padding(vertical = 32.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = state.expression,
                    textAlign = TextAlign.End,
                    fontWeight = FontWeight.Light,
                    color = Color.White,
                    fontSize = 60.sp,
                    modifier = Modifier.padding(end = 16.dp),
                )
            }
            ///------------------------------------------------------------



            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    modifier = Modifier
                        .background(LightGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = "AC",
                    onClick = { onAction(CalculatorAction.Clear) }
                )
                CalculatorButton(
                    modifier = Modifier
                        .background(LightGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = "Del",
                    onClick = { onAction(CalculatorAction.Delete) }
                )

                CalculatorButton(
                    modifier = Modifier
                        .background(LightGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = "↺",
                    isEnable = enableUndo,
                    onClick = { onAction(CalculatorAction.Control(HistoryControl.Undo)) }
                )

                CalculatorButton(
                    modifier = Modifier
                        .background(LightGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = "↻",
                    isEnable = enableRedo,
                    onClick = { onAction(CalculatorAction.Control(HistoryControl.Redo)) }
                )


                CalculatorButton(
                    modifier = Modifier
                        .background(Orange)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = "/",
                    onClick = { onAction(CalculatorAction.Operation(CalculatorOperation.Divide)) }
                )

            }


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {

                CalculatorButton(
                    modifier = Modifier
                        .background(Color.DarkGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = "7",
                    onClick = { onAction(CalculatorAction.Number(7)) }
                )
                CalculatorButton(
                    modifier = Modifier
                        .background(Color.DarkGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = "8",
                    onClick = { onAction(CalculatorAction.Number(8)) }
                )
                CalculatorButton(
                    modifier = Modifier
                        .background(Color.DarkGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = "9",
                    onClick = { onAction(CalculatorAction.Number(9)) }
                )
                CalculatorButton(
                    modifier = Modifier
                        .background(Orange)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = "(",
                    onClick = { onAction(CalculatorAction.Bracket(Brackets.Open)) }
                )

                CalculatorButton(
                    modifier = Modifier
                        .background(Orange)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = "*",
                    onClick = { onAction(CalculatorAction.Operation(CalculatorOperation.Multiply)) }
                )

            }


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {

                CalculatorButton(
                    modifier = Modifier
                        .background(Color.DarkGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = "4",
                    onClick = { onAction(CalculatorAction.Number(4)) }
                )
                CalculatorButton(
                    modifier = Modifier
                        .background(Color.DarkGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = "5",
                    onClick = { onAction(CalculatorAction.Number(5)) }
                )
                CalculatorButton(
                    modifier = Modifier
                        .background(Color.DarkGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = "6",
                    onClick = { onAction(CalculatorAction.Number(6)) }
                )

                CalculatorButton(
                    modifier = Modifier
                        .background(Orange)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = ")",
                    onClick = { onAction(CalculatorAction.Bracket(Brackets.Close)) }
                )


                CalculatorButton(
                    modifier = Modifier
                        .background(Orange)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = "-",
                    onClick = { onAction(CalculatorAction.Operation(CalculatorOperation.Subtract)) }
                )

            }


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {

                CalculatorButton(
                    modifier = Modifier
                        .background(Color.DarkGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = "1",
                    onClick = { onAction(CalculatorAction.Number(1)) }
                )
                CalculatorButton(
                    modifier = Modifier
                        .background(Color.DarkGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = "2",
                    onClick = { onAction(CalculatorAction.Number(2)) }
                )
                CalculatorButton(
                    modifier = Modifier
                        .background(Color.DarkGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = "3",
                    onClick = { onAction(CalculatorAction.Number(3)) }
                )
                CalculatorButton(
                    modifier = Modifier
                        .background(Orange)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = "^",
                    onClick = { onAction(CalculatorAction.Operation(CalculatorOperation.Power)) }
                )
                CalculatorButton(
                    modifier = Modifier
                        .background(Orange)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = "+",
                    onClick = { onAction(CalculatorAction.Operation(CalculatorOperation.Add)) }
                )

            }


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    modifier = Modifier
                        .background(Color.DarkGray)
                        .aspectRatio(2f)
                        .weight(2f),
                    symbol = "0",
                    onClick = { onAction(CalculatorAction.Number(0)) }
                )
                CalculatorButton(
                    modifier = Modifier
                        .background(Color.DarkGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    symbol = ".",
                    onClick = { onAction(CalculatorAction.Decimal) }
                )




                CalculatorButton(
                    modifier = Modifier
                        .background(LightGray)
                        .aspectRatio(2f)
                        .weight(2f),
                    symbol = "=",
                    onClick = { onAction(CalculatorAction.Calculate) }
                )

            }


        }
    }
}

