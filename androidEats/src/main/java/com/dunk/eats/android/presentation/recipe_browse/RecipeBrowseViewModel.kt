package com.dunk.eats.android.presentation.recipe_browse

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.dunk.eats.interactors.recipe_list.SearchRecipes
import javax.inject.Inject

class RecipeBrowseViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val searchRecipes: SearchRecipes
) : ViewModel() {

}