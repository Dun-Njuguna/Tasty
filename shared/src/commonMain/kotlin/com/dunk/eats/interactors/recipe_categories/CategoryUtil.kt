package com.dunk.eats.interactors.recipe_categories

class CategoryUtil {
    fun getNewCategories():List<Category>{
        return listOf(
            Category.SOUP,
            Category.PIZZA,
            Category.DONUT
        )
    }

    fun getAllCategories():List<Category>{
        return listOf(
            Category.CHICKEN,
            Category.BEEF,
            Category.DESSERT,
            Category.VEGETARIAN,
            Category.MILK,
            Category.VEGAN,
            Category.ERROR
        )
    }

    fun getCategory(value: String): Category? {
        val map = Category.values().associateBy(Category::categoryName)
        return map[value]
    }
}