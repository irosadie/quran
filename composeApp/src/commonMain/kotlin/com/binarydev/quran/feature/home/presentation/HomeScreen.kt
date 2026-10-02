package com.binarydev.quran.feature.home.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    onSurah: (Int) -> Unit,
    onPage: (Int) -> Unit,
    vm: HomeViewModel = koinViewModel(),
) {
    val state by vm.state.collectAsStateWithLifecycle()
    LaunchedEffect(vm) {
        vm.effect.collect { e ->
            when (e) {
                is HomeEffect.NavigateSurah -> onSurah(e.number)
                is HomeEffect.NavigatePage -> onPage(e.page)
            }
        }
    }
    Column(Modifier.fillMaxSize()) {
        state.lastRead?.let { last ->
            Card(Modifier.fillMaxWidth().padding(12.dp).clickable { vm.onEvent(HomeEvent.OpenSurah(last.surah)) }) {
                Text("Terakhir dibaca: ${last.key}", Modifier.padding(16.dp))
            }
        }
        TextField(
            value = state.query,
            onValueChange = { vm.onEvent(HomeEvent.Search(it)) },
            placeholder = { Text("Cari surah / nomor…") },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
            singleLine = true,
        )
        if (state.loading) {
            CircularProgressIndicator(Modifier.padding(24.dp))
        } else {
            LazyColumn(
                contentPadding = PaddingValues(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(state.filtered, key = { it.number }) { s ->
                    Card(Modifier.fillMaxWidth().clickable { vm.onEvent(HomeEvent.OpenSurah(s.number)) }) {
                        Row(Modifier.fillMaxWidth().padding(14.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("${s.number}. ${s.latinName}", style = MaterialTheme.typography.titleMedium)
                            Text("${s.ayatCount} ayat", style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
        }
    }
}
