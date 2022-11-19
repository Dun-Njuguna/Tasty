package com.dunk.eats.android.presentation.recipe_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dunk.eats.datasource.network.recipeService.RecipeService
import com.dunk.eats.domain.model.Recipe
import com.dunk.eats.domain.util.DatetimeUtil
import com.dunk.eats.interactors.recipe_detail.GetRecipe
import com.dunk.eats.presentation.recipe_detail.RecipeDetailEvents
import com.dunk.eats.presentation.recipe_detail.RecipeDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalStdlibApi
@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
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
             else -> {
                 handleError("Invalid Event")
             }
         }
    }


    private fun getRecipe(recipeId:Int){
        getRecipe.execute(
            recipeId = recipeId
        ).onEach { dataState ->
            state.value = state.value.copy(dataState.isLoading)
            dataState.data?.let {
                state.value = state.value.copy(recipe = it)
            }

            dataState.message?.let { message ->
                handleError(message)
            }

        }.launchIn(viewModelScope)
    }

    private fun handleError(errorMessage: String) {
        val queue = state.value.errorQueue
        queue.add(errorMessage)
        state.value = state.value.copy(errorQueue = queue)
    }

}