package com.dunk.eats.android.presentation.recipe_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dunk.eats.domain.model.Recipe
import com.dunk.eats.interactors.recipe_categories.Category
import com.dunk.eats.interactors.recipe_list.SearchRecipes
import com.dunk.eats.interactors.recipe_categories.CategoryUtil
import com.dunk.eats.presentation.recipe_list.RecipeListEvents
import com.dunk.eats.presentation.recipe_list.RecipeListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val searchRecipes:SearchRecipes,
    private val categoryUtil: CategoryUtil
) : ViewModel() {

     val state: MutableState<RecipeListState> = mutableStateOf(RecipeListState())

    init {
       onTriggerEvent(RecipeListEvents.LoadRecipes )
    }

    fun onTriggerEvent(event:RecipeListEvents){
        when(event){
            RecipeListEvents.LoadRecipes -> {
                loadTopCategories()
                loadNewCategories()
                loadRecipes()
            }

            RecipeListEvents.NextPage -> {
                nextPage()
            }
            RecipeListEvents.NewSearch -> {
                newSearch()
            }
            is RecipeListEvents.OnUpdateQuery  -> {
                state.value = state.value.copy(query =  event.query, selectedCategory = null)
            }
            is RecipeListEvents.OnSelectCategory  -> {
                onSelectCategory(event.category)
            }
            else -> {
                handleError("Invalid event")
            }
        }
    }

    fun getFoodCategory(categoryName:String): Category? {
        return categoryUtil.getCategory(categoryName)
    }

    private fun onSelectCategory(category: Category) {
        state.value = state.value.copy(selectedCategory = category, query = category.categoryName)
        newSearch()
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

    private fun loadNewCategories(){
        state.value = state.value.copy(newCategories = categoryUtil.getNewCategories())
    }

    private fun loadTopCategories(){
        state.value = state.value.copy(topCategories = categoryUtil.getAllCategories().subList(0, 4))
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