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
    @State var showingDetailSheet:Bool = false
    
    
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
        ScrollView{
            LazyVStack(spacing: 10) {
                ForEach(viewModel.state.recipes, id: \.self.id){recipe in
                    RecipeChip(
                        recipe: recipe,
                        onClick: {
                            viewModel.clickedRecipe = recipe
                            showingDetailSheet = true
                        }
                    )
                    .sheet(
                        isPresented: $showingDetailSheet,
                        onDismiss: {
                            viewModel.clickedRecipe = nil
                            showingDetailSheet = false
                        }
                    ){
                        if viewModel.clickedRecipe != nil {
                            NavigationView {
                                RecipeDetailScreen(
                                    recipeId: Int(viewModel.clickedRecipe!.id),
                                    recipeCache: cacheModule.recipeCache
                                )
                                .cornerRadius(8, corners: [.topLeft, .topRight])
                                .navigationBarTitle(Text("\(viewModel.clickedRecipe!.title)"), displayMode: .inline)
                            }
                        }
                        
                    }
                    .onAppear(perform:  {
                        if viewModel.shouldLoadNextPage(recipe: recipe){
                            viewModel.onTriggerEvent(stateEvent: RecipeListEvents.NextPage())
                        }
                    })
                }
                if(viewModel.state.isLoading){
                    ProgressView("Loading...")
                }
            }
        }
    }
}

//struct RecipeListScreen_Previews: PreviewProvider {
//    static var previews: some View {
//        RecipeListScreen()
//    }
//}
