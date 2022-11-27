//
//  SearchRecipeList.swift
//  iosEats
//
//  Created by Duncan K on 26/11/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct SearchRecipeList: View {
    
    private let cacheModule: CachingModule
    @ObservedObject var viewModel: RecipeBrowseViewModel
    
    @State var showingDetailSheet:Bool = false
    private var gridItemLayout = [GridItem(.adaptive(minimum: 150))]
    
    init(
        recipeBrowseViewModel: RecipeBrowseViewModel,
        cacheModule: CachingModule
    ) {
        self.viewModel = recipeBrowseViewModel
        self.cacheModule = cacheModule
    }
    
    var body: some View {
        ZStack{
            ScrollView{
                LazyVGrid(columns: gridItemLayout, spacing: 8) {
                    ForEach(viewModel.state.recipes, id: \.self.id){recipe in
                        RecipeChip(recipe: recipe, onClick: {
                            viewModel.clickedRecipe = recipe
                            showingDetailSheet = true
                        })
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
                                viewModel.onTriggerEvent(stateEvent: RecipeBrowseEvents.NextPage())
                            }
                        })
                    }
                }
            }
            if(viewModel.state.isLoading){
                ProgressView("Loading...")
            }
        }
    }
}
