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
    
    @ObservedObject var viewModel: RecipeBrowseViewModel
    private var gridItemLayout = [GridItem(.adaptive(minimum: 150))]
    
    init(recipeBrowseViewModel: RecipeBrowseViewModel) {
        self.viewModel = recipeBrowseViewModel
    }
    
    var body: some View {
        ZStack{
            ScrollView{
                LazyVGrid(columns: gridItemLayout, spacing: 8) {
                    ForEach(viewModel.state.recipes, id: \.self.id){recipe in
                        RecipeChip(recipe: recipe, onClick: {
                            
                        })
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
