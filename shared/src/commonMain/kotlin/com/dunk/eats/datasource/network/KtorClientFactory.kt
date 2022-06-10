package com.dunk.eats.datasource.network

import com.dunk.eats.datasource.network.model.RecipeDto
import com.dunk.eats.domain.model.Recipe
import com.dunk.eats.domain.util.DatetimeUtil
import io.ktor.client.*

expect class KtorClientFactory(){
    fun build():HttpClient
}

fun RecipeDto.toRecipe(): Recipe{
    val dateTimeUtil = DatetimeUtil()
    return Recipe(
        id = pk,
        title = title,
        featuredImage = featuredImage,
        rating = rating,
        publisher = publisher,
        sourceUrl = sourceUrl,
        ingredients = ingredients,
        description = description,
        cookingInstructions = cookingInstructions,
        dateAdded = dateTimeUtil.toLocalDate(longDateAdded.toDouble()),
        dateUpdated =dateTimeUtil.toLocalDate(longDateUpdated.toDouble())
    )
}

fun List<RecipeDto>.toRecipeList():List<Recipe>{
    return map{it.toRecipe()}
}