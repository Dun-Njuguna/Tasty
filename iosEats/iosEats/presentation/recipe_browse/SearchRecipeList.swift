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
    
    init(recipeBrowseViewModel: RecipeBrowseViewModel) {
        self.viewModel = recipeBrowseViewModel
    }
    
    var body: some View {
        List{
            ForEach(viewModel.state.recipes, id: \.self.id){recipe in
                Text("\(recipe.title)")
                    .onAppear(perform:  {
                        if viewModel.shouldLoadNextPage(recipe: recipe){
                            viewModel.onTriggerEvent(stateEvent: RecipeBrowseEvents.NextPage())
                        }
                    })
            }
        }
    }
}
