package dev.abdullah.calculator

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorButton(
    modifier: Modifier = Modifier,
    symbol: String = "1",
    isEnable:Boolean = true,
    onClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .clip(CircleShape)
            .clickable(enabled = isEnable, onClick = onClick)
            .then(modifier),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = symbol,
            fontSize = 24.sp,
            color = Color.White,
            fontFamily = FontFamily.Serif,
        )
    }

}
