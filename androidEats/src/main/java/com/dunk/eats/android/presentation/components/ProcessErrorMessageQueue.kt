package com.dunk.eats.android.presentation.components

import androidx.compose.runtime.Composable
import com.dunk.eats.domain.model.ErrorMessage
import com.dunk.eats.domain.util.Queue

@Composable
fun DisplayQueueAsDialog(
    dialogQueue: Queue<ErrorMessage>?,
    onRemoveMessageAtHead: () -> Unit,
) {
    dialogQueue?.peek()?.let { obj ->
        AppDialog(
            onDismiss = obj.onDismiss,
            title = obj.title,
            description = obj.description,
            negativeAction = obj.negativeAction,
            positiveAction = obj.positiveAction,
            onRemoveMessageAtHead = onRemoveMessageAtHead
        )
    }
}