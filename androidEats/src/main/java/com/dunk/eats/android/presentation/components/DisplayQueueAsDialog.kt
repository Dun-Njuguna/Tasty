package com.dunk.eats.android.presentation.components

import androidx.compose.runtime.Composable
import com.dunk.eats.domain.util.Queue

@Composable
fun DisplayQueueAsDialog(
    dialogQueue: Queue<String>?
) {
    dialogQueue?.peek()?.let { message ->
        AppDialog(title = "Error", description = message)
    }
}