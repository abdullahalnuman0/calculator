package dev.abdullah.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.abdullah.calculator.ui.theme.CalculatorTheme
import dev.abdullah.calculator.ui.theme.MediumGray

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            CalculatorTheme {

                val viewModel: CalculatorViewModel = viewModel()
                val state = viewModel.state.value
                val historyStack = viewModel.historyStack.value

                Scaffold { innerPadding ->
                    Calculator(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .background(MediumGray)
                            .padding(8.dp),
                        state = state,
                        enableUndo = historyStack.undo.isNotEmpty(),
                        enableRedo = historyStack.redo.isNotEmpty(),
                        onAction = viewModel::onAction
                    )
                }
            }
        }
    }


}

