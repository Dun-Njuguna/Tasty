package com.dunk.eats.android.presentation.recipe_browse

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dunk.eats.domain.model.ErrorMessage
import com.dunk.eats.domain.model.Recipe
import com.dunk.eats.domain.model.UIComponentType
import com.dunk.eats.interactors.recipe_categories.Category
import com.dunk.eats.interactors.recipe_categories.CategoryTypes
import com.dunk.eats.interactors.recipe_list.SearchRecipes
import com.dunk.eats.presentation.recipe_browse.RecipeBrowseEvents
import com.dunk.eats.presentation.recipe_browse.RecipeBrowseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class RecipeBrowseViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val searchRecipes: SearchRecipes,
    private val categoryTypes: CategoryTypes
) : ViewModel() {

    val state: MutableState<RecipeBrowseState> = mutableStateOf(RecipeBrowseState())

    init {
        onTriggerEvent(RecipeBrowseEvents.LoadCategories)
    }

    fun onTriggerEvent(event: RecipeBrowseEvents){
        when(event){
            RecipeBrowseEvents.LoadCategories -> {
                loadRecipes()
                loadPopularCategories()
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
            is RecipeBrowseEvents.OnSelectCategory  -> {
                onSelectCategory(event.category)
            }
            else -> {
                addErrorToQueue(
                    ErrorMessage.Builder()
                        .id(UUID.randomUUID().toString())
                        .title("Error")
                        .uiComponentType(UIComponentType.Dialog)
                        .description("Invalid event")
                )
            }
        }
    }

    fun getFoodCategory(categoryName:String): Category? {
        return categoryTypes.getCategory(categoryName)
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

    private fun loadRecipes(){
        searchRecipes.execute(
            page = state.value.page,
            query = state.value.query,
        ).onEach { dataState ->
            state.value= state.value.copy(isLoading = dataState.isLoading)

            dataState.data?.let { recipes ->
                appendRecipes(recipes)
            }

            dataState.errorMessage?.let { message ->
                addErrorToQueue(
                    ErrorMessage.Builder()
                        .id(UUID.randomUUID().toString())
                        .title(message.title)
                        .uiComponentType(UIComponentType.Dialog)
                        .description(message.description ?: "Unknown error")
                )
            }
        }.launchIn(viewModelScope)
    }

    private fun appendRecipes(recipes: List<Recipe>){
        val curr = ArrayList(state.value.recipes)
        curr.addAll(recipes)
        state.value = state.value.copy(recipes = curr)
    }

    private fun loadPopularCategories(){
        state.value = state.value.copy(categories = categoryTypes.getAllCategories())
    }

    private fun addErrorToQueue(error: ErrorMessage.Builder) {
        val queue = state.value.errorQueue
        queue.add(error.build())
        state.value = state.value.copy(errorQueue = queue)
    }

}