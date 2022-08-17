package com.dunk.eats.datasource.cache

import com.dunk.eats.domain.model.Recipe
import com.dunk.eats.domain.util.DatetimeUtil


fun Recipe_Entity.toRecipe(): Recipe {
    val datetimeUtil = DatetimeUtil()
    return Recipe(
        id = id.toInt(),
        title = title,
        publisher = publisher,
        featuredImage = featured_image,
        rating = rating.toInt(),
        sourceUrl = source_url,
        description = description,
        cookingInstructions = cooking_Instructions,
        ingredients = ingredients.convertIngredientToList(),
        dateAdded = datetimeUtil.toLocalDate(date_added),
        dateUpdated = datetimeUtil.toLocalDate(date_updated)
    )
}

fun List<Recipe_Entity>.toRecipeList(): List<Recipe> {
    return map { it.toRecipe() }
}

fun List<String>.convertIngredientListToString(): String {
    val ingredientsString = StringBuilder()
    for (ingredient in this) {
        ingredientsString.append("$ingredient,")
    }
    return ingredientsString.toString()
}

fun String.convertIngredientToList(): List<String> {
    val list: ArrayList<String> = arrayListOf()
    for (ingredient in split(",")) {
        list.add(ingredient)
    }
    return list
}



