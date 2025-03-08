package com.haeti.wearsample.presentation

import androidx.compose.runtime.Composable
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.TimeTextDefaults
import androidx.wear.compose.ui.tooling.preview.WearPreviewDevices
import androidx.wear.compose.ui.tooling.preview.WearPreviewFontScales

@Composable
fun TimeTextExample() {
	TimeText(
		timeSource = TimeTextDefaults.timeSource(timeFormat = "HH:mm:ss"),
		timeTextStyle = TimeTextDefaults.timeTextStyle()
	)
}

@WearPreviewDevices
@WearPreviewFontScales
@Composable
fun TimeExamplePreview() {
	TimeTextExample()
}
