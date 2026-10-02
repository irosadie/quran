package com.binarydev.quran.feature.search.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SearchScreen(onAyah: (String) -> Unit, vm: SearchViewModel = koinViewModel()) {
    val state by vm.state.collectAsStateWithLifecycle()
    Column(Modifier.fillMaxSize()) {
        TextField(
            value = state.query,
            onValueChange = { vm.onEvent(SearchEvent.Query(it)) },
            placeholder = { Text("Cari kata / ayat… (min 2 huruf)") },
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            singleLine = true,
        )
        if (state.loading) CircularProgressIndicator(Modifier.padding(24.dp))
        state.error?.let { Text(it, Modifier.padding(16.dp)) }
        LazyColumn(Modifier.fillMaxSize()) {
            items(state.results, key = { it.key }) { a ->
                Card(Modifier.fillMaxWidth().padding(horizontal = 12.dp, vertical = 4.dp).clickable { onAyah(a.key) }) {
                    Text("${a.key} • Juz ${a.juz}", Modifier.padding(top = 12.dp, start = 12.dp))
                    Text(a.textUthmani, Modifier.padding(12.dp))
                }
            }
        }
    }
}
