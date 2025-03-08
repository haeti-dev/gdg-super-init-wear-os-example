package com.haeti.wearsample.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition
import androidx.wear.compose.ui.tooling.preview.WearPreviewDevices
import androidx.wear.compose.ui.tooling.preview.WearPreviewFontScales

@Composable
fun ScaffoldExample() {
	val listState = rememberScalingLazyListState()

	Scaffold(
		timeText = { TimeText() },
		vignette = { Vignette(vignettePosition = VignettePosition.TopAndBottom) },
		positionIndicator = {
			PositionIndicator(scalingLazyListState = listState)
		}
	) {
		ScalingLazyColumn(state = listState) {
			items(10) { index ->
				Chip(
					modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
					label = { Text("항목 $index") },
					onClick = { /* 클릭 처리 */ }
				)
			}
		}
	}
}

@WearPreviewDevices
@WearPreviewFontScales
@Composable
fun WearAppPreview() {
	ScaffoldExample()
}
