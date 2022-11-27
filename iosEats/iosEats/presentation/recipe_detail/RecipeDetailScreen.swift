//
//  RecipeDetailScreen.swift
//  iosEats
//
//  Created by Duncan K on 27/11/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct RecipeDetailScreen: View {
    
    private let recipeCache: RecipeCache
    private let getRecipeModule: GetRecipe
    private let recipeId: Int
    private let dateTimeUtil = DatetimeUtil()
    
    @ObservedObject var viewModel: RecipeDetailViewModel
    
    init(
        recipeId: Int,
        recipeCache: RecipeCache
    ) {
        self.recipeId = recipeId
        self.recipeCache = recipeCache
        self.getRecipeModule = GetRecipe(
            recipeCache: recipeCache
        )
        
        self.viewModel = RecipeDetailViewModel(
            recipeId: recipeId,
            getRecipe: getRecipeModule
        )
    }
    
    var body: some View {
        VStack{
            if viewModel.state.recipe != nil {
                RecipeDetailView(
                    recipe: viewModel.state.recipe!,
                    dateTimeUtil: dateTimeUtil
                )
            }
            if(viewModel.state.isLoading){
                ProgressView("Loading...")
            }
        }
    }
}
