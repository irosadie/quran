package com.binarydev.quran.core.data.remote.api

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.okhttp.OkHttp

actual fun engine(): HttpClientEngineFactory<*> = OkHttp
