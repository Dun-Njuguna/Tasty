package com.dunk.eats.datasource.cache


import com.dunk.eats.domain.model.Recipe
import com.dunk.eats.domain.util.DatetimeUtil
import com.dunk.eats.interactors.recipe_list.SearchRecipes.Companion.RECIPE_PAGINATION_PAGE_SIZE

class RecipeCacheImpl(
    recipeDatabase: RecipeDatabase,
    private val datetimeUtil: DatetimeUtil
): RecipeCache {

    private val queries:RecipeDbQueries = recipeDatabase.recipeDbQueries

    override fun insert(recipe: Recipe) {
        queries.insertRecipe(
            id = recipe.id.toLong(),
            title = recipe.title,
            publisher = recipe.publisher,
            featured_image = recipe.featuredImage,
            rating = recipe.rating.toLong(),
            source_url = recipe.sourceUrl,
            ingredients = recipe.ingredients.convertIngredientListToString(),
            description = recipe.description,
            cooking_Instructions = recipe.cookingInstructions,
            date_added = datetimeUtil.toEpochMilliseconds(recipe.dateAdded),
            date_updated = datetimeUtil.toEpochMilliseconds(recipe.dateUpdated)
        )
    }

    override fun insert(recipes: List<Recipe>) {
        for (recipe in recipes){
            insert(recipe)
        }
    }

    override fun search(query: String, page: Int): List<Recipe> {
       return  queries.searchRecipes(query = query, pageSize = RECIPE_PAGINATION_PAGE_SIZE.toLong(), offset = ((page - 1 ) * RECIPE_PAGINATION_PAGE_SIZE).toLong()).executeAsList().toRecipeList()
    }

    override fun getAll(page: Int): List<Recipe> {
        return  queries.getAllRecipes(pageSize = RECIPE_PAGINATION_PAGE_SIZE.toLong(), offset = ((page - 1 ) * RECIPE_PAGINATION_PAGE_SIZE).toLong()).executeAsList().toRecipeList()
    }

    override fun get(recipeId: Int): Recipe? {
        return try {
            queries.getRecipeById(recipeId.toLong()).executeAsOne().toRecipe()
        }catch (e:NullPointerException){
            null
        }
    }
}