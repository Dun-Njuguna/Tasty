//
//  RecipeListScreen.swift
//  iosEats
//
//  Created by Duncan K on 19/11/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct RecipeListScreen: View {
    
    private let networkModule: NetworkModule
    private let cacheModule: CachingModule
    private let searchRecipesModule: SearchRecipesModule
    
    @ObservedObject var viewModel: RecipeListViewModel
    
    init(
        networkModule: NetworkModule,
        cacheModule: CachingModule
    ){
        self.networkModule = networkModule
        self.cacheModule = cacheModule
        self.searchRecipesModule = SearchRecipesModule(
            networkModule: self.networkModule,
            cachingModule: self.cacheModule
        )
        self.viewModel = RecipeListViewModel(
            searchRecipes: searchRecipesModule.searchRecipes,
            foodCategories: CategoryTypes()
        )
    }
    
    var body: some View {
        List{
            ForEach(viewModel.state.recipes, id: \.self.id){recipe in
                Text("\(recipe.title)")
                    .onAppear(perform:  {
                        if viewModel.shouldLoadNextPage(recipe: recipe){
                            viewModel.onTriggerEvent(stateEvent: RecipeListEvents.NextPage())
                        }
                    })
            }
        }
    }
}

//struct RecipeListScreen_Previews: PreviewProvider {
//    static var previews: some View {
//        RecipeListScreen()
//    }
//}
