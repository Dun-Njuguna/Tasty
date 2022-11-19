package com.dunk.eats.android.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.dunk.eats.android.presentation.components.CircularProgressBar
import com.dunk.eats.android.presentation.components.DisplayQueueAsDialog
import com.dunk.eats.domain.model.ErrorMessage
import com.dunk.eats.domain.util.Queue

private val LightThemeColors = lightColors(
    primary = Blue600,
    primaryVariant = Blue400,
    onPrimary = Black2,
    secondary = Color.White,
    secondaryVariant = Teal300,
    error = RedErrorDark,
    onError = RedErrorLight,
    background = Grey1,
    onBackground = Color.Black,
    surface = Color.White,
)

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun AppTheme(
    displayProgressBar: Boolean,
    dialogQueue: Queue<ErrorMessage> = Queue(mutableListOf()),
    content: @Composable () -> Unit,

    ) {
    MaterialTheme(
        colors = LightThemeColors,
        typography = QuickSandTypography,
        shapes = AppShapes
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Grey1)
        ){
            DisplayQueueAsDialog(dialogQueue = dialogQueue)
            content()
            CircularProgressBar(isDisplayed = displayProgressBar, verticalBias = 0.5f)
        }
    }
}