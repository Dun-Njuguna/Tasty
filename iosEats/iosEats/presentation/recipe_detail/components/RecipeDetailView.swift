//
//  RecipeDetailView.swift
//  iosEats
//
//  Created by Duncan K on 27/11/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared
import SDWebImageSwiftUI

struct RecipeDetailView: View {
    
    private let recipe: Recipe
    private let dateTimeUtil: DatetimeUtil
    
    init(
        recipe: Recipe,
        dateTimeUtil: DatetimeUtil
    ) {
        self.recipe = recipe
        self.dateTimeUtil = dateTimeUtil
    }
    
    var body: some View {
        ScrollView{
            VStack(alignment: .leading){
                RecipeChip(
                    recipe: recipe,
                    cardHeight: 240,
                    cornerRadius: 0,
                    displayDetaildData: true,
                    dateTimeUtil: dateTimeUtil,
                    onClick: {}
                )
                
                Text("Ingredients")
                    .font(.custom("Avenir", size: 16))
                    .padding(EdgeInsets(top: 4, leading: 8, bottom: 2, trailing: 8))
                
                ForEach(recipe.ingredients as Array<String>, id: \.self){ ingredient in
                    let index = recipe.ingredients.firstIndex(of: ingredient)
                    if !ingredient.isEmpty && index != nil {
                        HStack(alignment: .firstTextBaseline){
                            Text("\(index!+1): ")
                            Text("\(ingredient)")
                        }
                        .padding(.bottom, 2)
                    }
                }.padding(12)
                
            }
        }
        .navigationTitle(recipe.title)
    }
}
