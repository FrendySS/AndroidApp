import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import com.example.animeviewer.R

@Composable
fun AnimatedAppIcon(onAnimationEnd: () -> Unit) {
    var scale by remember { mutableStateOf(1f) }

    val transition = rememberInfiniteTransition()
    val animatedScale by transition.animateFloat(
        initialValue = 1f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            tween(1000, easing = LinearEasing),
            RepeatMode.Reverse
        )
    )

    LaunchedEffect(Unit) {
        delay(3000) // Задержка перед переходом к основному экрану
        onAnimationEnd()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "App Icon",
            modifier = Modifier
                .size(120.dp)
                .scale(animatedScale)
        )
    }
}
