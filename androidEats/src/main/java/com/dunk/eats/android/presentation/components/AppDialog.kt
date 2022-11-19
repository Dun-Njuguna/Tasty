package com.dunk.eats.android.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dunk.eats.domain.model.NegativeAction
import com.dunk.eats.domain.model.PositiveAction

@Composable
fun AppDialog(
    modifier: Modifier = Modifier,
    onDismiss: (() -> Unit)?,
    title: String,
    description: String? = null,
    positiveAction: PositiveAction?,
    negativeAction: NegativeAction?,
    onRemoveMessageAtHead: () -> Unit,
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = {
            onDismiss?.invoke()
            onRemoveMessageAtHead()
        },
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.h3
            )
        },
        text = {
            description?.let {
                Text(
                    text = description,
                    style = MaterialTheme.typography.body2
                )
            }
        },
        buttons = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement =  Arrangement.End
            ){
                negativeAction?.let {
                    Button(
                        modifier = Modifier.padding(end = 8.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.onError),
                        onClick = {
                            onRemoveMessageAtHead()
                        }
                    ) {
                        negativeAction.onNegativeAction()
                        Text(
                            text = negativeAction.negativeBtnTxt,
                            style = MaterialTheme.typography.button
                        )
                    }
                }
                positiveAction?.let {
                    Button(
                        modifier = Modifier.padding(end = 8.dp),
                        onClick = {
                            onRemoveMessageAtHead()
                        }
                    ) {
                        positiveAction.onPositiveAction()
                        Text(
                            text = positiveAction.positiveBtnTxt,
                            style = MaterialTheme.typography.button
                        )
                    }
                }
            }
        }
    )
}