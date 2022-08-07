package com.dunk.eats.android.presentation.recipe_browse

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dunk.eats.domain.model.Recipe
import com.dunk.eats.interactors.recipe_list.SearchRecipes
import com.dunk.eats.presentation.recipe_browse.RecipeBrowseEvents
import com.dunk.eats.presentation.recipe_browse.RecipeBrowseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RecipeBrowseViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val searchRecipes: SearchRecipes
) : ViewModel() {

    val state: MutableState<RecipeBrowseState> = mutableStateOf(RecipeBrowseState())

    fun onTriggerEvent(event: RecipeBrowseEvents){
        when(event){
            RecipeBrowseEvents.LoadCategories -> {
                loadRecipes()
            }

            RecipeBrowseEvents.NextPage -> {
                nextPage()
            }
            RecipeBrowseEvents.NewSearch -> {
                newSearch()
            }
            is RecipeBrowseEvents.OnUpdateQuery  -> {
                state.value = state.value.copy(query =  event.query)
            }
            else -> {
                handleError("Invalid event")
            }
        }
    }

    private fun newSearch() {
        state.value = state.value.copy(page = 1, recipes = listOf() )
        loadRecipes()
    }

    private fun nextPage() {
        //update the state and increment the page
        state.value = state.value.copy(page = state.value.page + 1)
        loadRecipes()
    }

    private fun loadRecipes(){
        searchRecipes.execute(
            page = state.value.page,
            query = state.value.query,
        ).onEach { dataState ->
            state.value= state.value.copy(isLoading = dataState.isLoading)

            dataState.data?.let { recipes ->
                appendRecipes(recipes)
            }

            dataState.message?.let { message ->
                handleError(message)
            }
        }.launchIn(viewModelScope)
    }

    private fun appendRecipes(recipes: List<Recipe>){
        val curr = ArrayList(state.value.recipes)
        curr.addAll(recipes)
        state.value = state.value.copy(recipes = curr)
    }

    private fun handleError(errorMessage: String) {

    }

}