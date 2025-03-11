package com.haeti.wearsample.presentation

import android.content.Context
import androidx.health.services.client.HealthServices
import androidx.health.services.client.MeasureCallback
import androidx.health.services.client.data.Availability
import androidx.health.services.client.data.DataPointContainer
import androidx.health.services.client.data.DataType
import androidx.health.services.client.data.DataTypeAvailability
import androidx.health.services.client.data.DeltaDataType
import androidx.health.services.client.data.SampleDataPoint
import androidx.health.services.client.getCapabilities
import androidx.health.services.client.unregisterMeasureCallback
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.runBlocking

class HealthServicesManager(context: Context) {
	private val measureClient = HealthServices.getClient(context).measureClient

	// 심박수 측정 기능 확인
	suspend fun hasHeartRateCapability() = runCatching {
		val capabilities = measureClient.getCapabilities()
		(DataType.HEART_RATE_BPM in capabilities.supportedDataTypesMeasure)
	}.getOrDefault(false)

	// 심박수 데이터를 Flow로 수신
	fun heartRateMeasureFlow(): Flow<MeasureMessage> = callbackFlow {
		val callback = object : MeasureCallback {
			// 데이터 가용성 변경 시 호출
			override fun onAvailabilityChanged(dataType: DeltaDataType<*, *>, availability: Availability) {
				if (availability is DataTypeAvailability) {
					trySendBlocking(MeasureMessage.MeasureAvailability(availability))
				}
			}

			// 데이터 수신 시 호출
			override fun onDataReceived(data: DataPointContainer) {
				val heartRateBpm = data.getData(DataType.HEART_RATE_BPM)
				trySendBlocking(MeasureMessage.MeasureData(heartRateBpm))
			}
		}

		// 콜백 등록
		measureClient.registerMeasureCallback(DataType.HEART_RATE_BPM, callback)

		// Flow 수집이 중단되면 콜백 해제
		awaitClose {
			runBlocking {
				measureClient.unregisterMeasureCallback(DataType.HEART_RATE_BPM, callback)
			}
		}
	}
}

// 심박수 데이터 래핑 클래스
sealed class MeasureMessage {
	class MeasureAvailability(val availability: DataTypeAvailability) : MeasureMessage()
	class MeasureData(val data: List<SampleDataPoint<Double>>) : MeasureMessage()
}
