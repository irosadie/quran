package com.binarydev.quran.core.data.remote.api

import io.ktor.client.engine.HttpClientEngineFactory

/** expect/actual engine per platform (OkHttp / Darwin / Java) — ringan & native. */
expect fun engine(): HttpClientEngineFactory<*>
