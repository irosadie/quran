package com.binarydev.quran.core.data.remote.api

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.java.Java

actual fun engine(): HttpClientEngineFactory<*> = Java
