package com.miguel.supportflow.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.miguel.supportflow.R

private val SuporteFlowTypography = Typography(
    headlineSmall = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 32.sp
    ),
    titleLarge = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 26.sp
    ),
    titleMedium = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 22.sp
    ),
    titleSmall = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 20.sp
    ),
    bodyLarge = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 24.sp
    ),
    bodyMedium = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 20.sp
    ),
    bodySmall = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 16.sp
    ),
    labelLarge = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 20.sp
    ),
    labelMedium = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 16.sp
    ),
    labelSmall = TextStyle(
        fontSize = 11.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 16.sp
    )
)

private val SuporteFlowShapes = Shapes(
    small = RoundedCornerShape(10.dp),
    medium = RoundedCornerShape(14.dp),
    large = RoundedCornerShape(16.dp)
)

@Composable
fun SupportFlowTheme(content: @Composable () -> Unit) {
    val cores = lightColorScheme(
        primary = colorResource(R.color.brand_blue),
        onPrimary = colorResource(R.color.white),
        secondary = colorResource(R.color.brand_navy),
        onSecondary = colorResource(R.color.white),
        background = colorResource(R.color.screen_background),
        onBackground = colorResource(R.color.text_primary),
        surface = colorResource(R.color.surface_card),
        onSurface = colorResource(R.color.text_primary),
        surfaceVariant = colorResource(R.color.surface_card),
        onSurfaceVariant = colorResource(R.color.text_secondary),
        outline = colorResource(R.color.border_card),
        outlineVariant = colorResource(R.color.border_card),
        error = colorResource(R.color.prioridade_alta),
        surfaceContainerLowest = colorResource(R.color.surface_card),
        surfaceContainerLow = colorResource(R.color.surface_card),
        surfaceContainer = colorResource(R.color.surface_card),
        surfaceContainerHigh = colorResource(R.color.surface_card),
        surfaceContainerHighest = colorResource(R.color.surface_card)
    )

    MaterialTheme(
        colorScheme = cores,
        typography = SuporteFlowTypography,
        shapes = SuporteFlowShapes,
        content = content
    )
}
