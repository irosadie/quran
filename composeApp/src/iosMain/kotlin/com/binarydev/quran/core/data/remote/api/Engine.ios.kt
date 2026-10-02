package com.binarydev.quran.core.data.remote.api

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.darwin.Darwin

actual fun engine(): HttpClientEngineFactory<*> = Darwin
