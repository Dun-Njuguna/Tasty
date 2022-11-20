package com.dunk.eats.android.presentation.recipe_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dunk.eats.domain.model.ErrorMessage
import com.dunk.eats.domain.model.PositiveAction
import com.dunk.eats.domain.model.UIComponentType
import com.dunk.eats.domain.util.ErrorMessageQueueUtil
import com.dunk.eats.domain.util.Queue
import com.dunk.eats.interactors.recipe_detail.GetRecipe
import com.dunk.eats.presentation.recipe_detail.RecipeDetailEvents
import com.dunk.eats.presentation.recipe_detail.RecipeDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.*
import javax.inject.Inject

@ExperimentalStdlibApi
@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getRecipe: GetRecipe
) : ViewModel() {

    val state: MutableState<RecipeDetailState> = mutableStateOf(RecipeDetailState())

    init {
        savedStateHandle.get<Int>("recipeId")?.let { recipeId ->
            onTriggerEvent(RecipeDetailEvents.GetRecipe(recipeId))
        }
    }

    fun onTriggerEvent(event: RecipeDetailEvents){
         when(event){
             is RecipeDetailEvents.GetRecipe -> {
                 getRecipe(recipeId = event.recipeId)
             }
             is RecipeDetailEvents.RemoveHeadMessageFromQueue -> {
                 removeMessageAtHead()
             }
             else -> {
                 addErrorToQueue(
                     ErrorMessage.Builder()
                         .id(UUID.randomUUID().toString())
                         .title("Error")
                         .uiComponentType(UIComponentType.Dialog)
                         .description("Invalid event")
                         .positive(PositiveAction(positiveBtnTxt = "Ok", onPositiveAction = {}))
                 )
             }
         }
    }

    private fun getRecipe(recipeId:Int){
        getRecipe.execute(
            recipeId = recipeId
        ).collectFlow(viewModelScope) { dataState ->
            state.value = state.value.copy(dataState.isLoading)
            dataState.data?.let {
                state.value = state.value.copy(recipe = it)
            }

            dataState.errorMessage?.let { message ->
                addErrorToQueue(
                    ErrorMessage.Builder()
                        .id(UUID.randomUUID().toString())
                        .title(message.title)
                        .uiComponentType(UIComponentType.Dialog)
                        .description(message.description ?: "Unknown error")
                        .positive(PositiveAction(positiveBtnTxt = "Ok", onPositiveAction = {}))
                )
            }

        }
    }

    private fun addErrorToQueue(error: ErrorMessage.Builder) {
        if (!ErrorMessageQueueUtil().isErrorUnique(
                queue = state.value.errorQueue,
                message = error.build()
            )) {
            val queue = state.value.errorQueue
            queue.add(error.build())
            state.value = state.value.copy(errorQueue = queue)
        }
    }

    private fun removeMessageAtHead() {
        try {
            val queue = state.value.errorQueue
            queue.remove()
            // Forcing the app to recompose and remove the dialog since the queue is empty
            state.value = state.value.copy(errorQueue = Queue(mutableListOf()))
            state.value = state.value.copy(errorQueue = queue)
        }catch (e: Exception){
            // Nothing to remove queue is empty
        }
    }

}