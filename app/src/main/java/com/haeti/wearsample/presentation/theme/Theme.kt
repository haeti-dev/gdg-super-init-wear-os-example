package com.haeti.wearsample.presentation.theme

import androidx.compose.runtime.Composable
import androidx.wear.compose.material.MaterialTheme

@Composable
fun WearSampleTheme(
	content: @Composable () -> Unit
) {
	/**
	 * Empty theme to customize for your app.
	 * See: https://developer.android.com/jetpack/compose/designsystems/custom
	 */
	MaterialTheme(
		content = content
	)
}
