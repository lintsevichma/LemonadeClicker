package by.vlv.lemonadeclicker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import by.vlv.lemonadeclicker.ui.theme.LemonadeClickerTheme
import by.vlv.lemonadeclicker.ui.theme.LemonadeHeaderYellow
import by.vlv.lemonadeclicker.ui.theme.LemonadeImageBackgroundGreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeClickerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeUI()
                }
            }
        }
    }
}

@Composable
fun LemonadeUI(modifier: Modifier = Modifier) {
    var step by remember { mutableStateOf(1) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LemonadeHeader()
        Spacer(modifier = modifier.padding(50.dp))
        LemonadeImageWithText(step = step) {
            when (step) {
                1, 3 -> step++
                2 -> step = (2..3).random()
                else -> step = 1
            }
        }
        Spacer(modifier = modifier.padding(100.dp))
        LemonadeFooter()
    }
}

@Composable
fun LemonadeImageWithText(step: Int, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LemonadeImage(step = step, onClick = onClick)
        Spacer(modifier = Modifier.padding(24.dp))
        LemonadeText(step = step)
    }
}

@Composable
fun LemonadeImage(step: Int, onClick: () -> Unit) {
    val imageResource = when (step) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    Image(
        painter = painterResource(id = imageResource),
        contentDescription = step.toString(),
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .size(300.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(LemonadeImageBackgroundGreen)
            .shadow(1.dp)
            .clickable { onClick() }
    )
}

@Composable
fun LemonadeText(step: Int) {
    val textResource = when (step) {
        1 -> R.string.lemon_tree_text
        2 -> R.string.lemon_squeeze_text
        3 -> R.string.lemon_drink_text
        else -> R.string.lemon_restart_text
    }
    Row(
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(textResource),
            fontSize = 18.sp
        )
    }
}

@Composable
fun LemonadeHeader(modifier: Modifier = Modifier) {
    Row {
        Box(
            modifier = modifier
                .background(LemonadeHeaderYellow)
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(top = 36.dp, bottom = 12.dp)
        ) {
            Text(
                text = stringResource(id = R.string.lemonade_header_text),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun LemonadeFooter(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
            .padding(top = 3.dp, bottom = 2.dp)
    ) {
        Text(
            text = stringResource(R.string.lemonade_footer_text),
            fontSize = 12.sp,
            color = Color.DarkGray
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LemonadeClickerTheme {
        LemonadeUI()
    }
}