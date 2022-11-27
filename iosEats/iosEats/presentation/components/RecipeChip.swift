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
    let cardHeight: CGFloat
    let cornerRadius: CGFloat
    let displayDetaildData:Bool
    let dateTimeUtil: DatetimeUtil?
    
    private let  onClick: () -> Void
    
    init(
        recipe: Recipe,
        cardHeight: CGFloat = 135,
        cornerRadius: CGFloat = 8,
        displayDetaildData:Bool = false,
        dateTimeUtil: DatetimeUtil? = nil,
        onClick: @escaping () -> Void
    ) {
        self.recipe = recipe
        self.onClick = onClick
        self.cardHeight = cardHeight
        self.cornerRadius = cornerRadius
        self.displayDetaildData = displayDetaildData
        self.dateTimeUtil = dateTimeUtil
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
                
                HStack(alignment: .lastTextBaseline) {
                    Text("\(getTitle())")
                        .font(.custom("Avenir", size: 16))
                        .frame(maxWidth: .infinity, alignment: .leading)
                    Text("\(recipe.rating)")
                        .font(.custom("Avenir", size: 14))
                        .frame(maxWidth: .infinity, alignment: .trailing)
                }
                .padding(.all,10)
                
            }
            .frame(minWidth: 0, maxWidth: .infinity, minHeight: cardHeight)
            .cornerRadius(cornerRadius)
        }
        .shadow(color: .gray, radius: 2, x: 0.8, y: 0.8)
        .contentShape(Rectangle())
        .onTapGesture {
            onClick()
        }
    }
    
    private func getTitle() -> String{
       if displayDetaildData && dateTimeUtil != nil {
           return "Update on \(dateTimeUtil!.humanizeDatetime(date: recipe.dateUpdated)) by \(recipe.publisher)"
        }
        else {
           return recipe.title
        }
    }
}

