//
//  RecipeChip.swift
//  iosEats
//
//  Created by Duncan K on 27/11/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared
import SDWebImageSwiftUI

struct RecipeChip: View {
    let recipe:Recipe
    
    let cardHeight: CGFloat = 135
    let imageHeight: CGFloat = 116
    let cornerRadius: CGFloat = 8
    
    private let  onClick: () -> Void
    
    init(
        recipe: Recipe,
        onClick: @escaping () -> Void
    ) {
        self.recipe = recipe
        self.onClick = onClick
    }
    
    var body: some View {
        ZStack {
            RoundedRectangle(
                cornerRadius: cornerRadius,
                style: .continuous
            )
            .fill(Color.black)
            
            VStack(spacing: 0) {
                WebImage(url: URL(string: "\(recipe.featuredImage)"))
                    .resizable()
                    .indicator(.activity)
                    .padding(.bottom, 10)
                    .aspectRatio(contentMode: .fill)
                    .frame(minWidth: 0, maxWidth: .infinity, minHeight: cardHeight, maxHeight: cardHeight)
                    .cornerRadius(cornerRadius, corners: [.topLeft, .bottomRight])
                    .gesture(
                        TapGesture()
                            .onEnded { _ in
                               onClick()
                            }
                    )
                
                LazyVStack(alignment: .leading) {
                    Text("\(recipe.title)")
                        .font(.custom("Avenir", size: 14))
                }
                .padding(.all,10)
                
            }
            .frame(minWidth: 0, maxWidth: .infinity, minHeight: cardHeight)
            .cornerRadius(cornerRadius)
        }
        .shadow(color: .gray, radius: 2, x: 0.8, y: 0.8)
    }
}

