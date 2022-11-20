package com.dunk.eats.interactors.recipe_categories

class CategoryTypes {
    fun getNewCategories():List<FoodCategories>{
        return listOf(
            FoodCategories.SOUP,
            FoodCategories.PIZZA,
            FoodCategories.DONUT
        )
    }

    fun getAllCategories():List<FoodCategories>{
        return listOf(
            FoodCategories.CHICKEN,
            FoodCategories.BEEF,
            FoodCategories.DESSERT,
            FoodCategories.VEGETARIAN,
            FoodCategories.MILK,
            FoodCategories.VEGAN,
            FoodCategories.ERROR
        )
    }

    fun getCategory(value: String): FoodCategories? {
        val map = FoodCategories.values().associateBy(FoodCategories::categoryName)
        return map[value]
    }
}