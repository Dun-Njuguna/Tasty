package com.dunk.eats.android.presentation.components

import androidx.compose.runtime.Composable
import com.dunk.eats.domain.model.ErrorMessage
import com.dunk.eats.domain.util.Queue

@Composable
fun DisplayQueueAsDialog(
    dialogQueue: Queue<ErrorMessage>?
) {
    dialogQueue?.peek()?.let { message ->
        AppDialog(title = message.title, description = message.description)
    }
}