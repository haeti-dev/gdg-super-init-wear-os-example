package com.haeti.wearsample.presentation

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

class GyroScopeManager(context: Context) {
	// 센서 관리자 접근
	private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
	// 자이로스코프 센서 접근
	private val gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

	fun registerGyroscopeSensor() {
		// 센서 리스너 등록
		sensorManager.registerListener(
			object : SensorEventListener {
				override fun onSensorChanged(event: SensorEvent) {
					val rotationX = event.values[0]
					val rotationY = event.values[1]
					val rotationZ = event.values[2]
					// 회전 값 처리
				}

				override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
					// 정확도 변경 처리
				}
			},
			gyroscopeSensor,
			SensorManager.SENSOR_DELAY_NORMAL
		)
	}
}
