package com.chinky.family.presentation.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.chinky.family.R

object ApplicationFont {
    val robotoBold = FontFamily(Font(R.font.roboto_bold, FontWeight.Bold))
    val robotoLight = FontFamily(Font(R.font.roboto_light, FontWeight.Light))
    val robotoItalic = FontFamily(Font(R.font.roboto_italic))
    val robotoExtraLight = FontFamily(Font(R.font.roboto_extra_light, FontWeight.ExtraLight))
    val robotoThin = FontFamily(Font(R.font.roboto_semi_condensed_thin, FontWeight.Thin))
}