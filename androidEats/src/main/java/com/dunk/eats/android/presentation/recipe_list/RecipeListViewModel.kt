package com.dunk.eats.android.presentation.recipe_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dunk.eats.interactors.recipe_list.SearchRecipes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val searchRecipes:SearchRecipes
) : ViewModel() {

    init {
        loadRecipes()
    }

    private fun loadRecipes(){
        searchRecipes.execute(
            page = 1 ,
            query = "Chicken"
        ).onEach { dataState ->
             println("Recipe list view model:......loading${dataState.isLoading}...message${dataState.message }...data${dataState.data}")
        }.launchIn(viewModelScope)
    }

}