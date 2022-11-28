//
//  FoodCategoryChip.swift
//  iosEats
//
//  Created by Duncan K on 26/11/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared
import SDWebImageSwiftUI

struct FoodCategoryChip: View {
    
    let category: FoodCategories
    
    let cardHeight: CGFloat = 174
    let imageHeight: CGFloat = 116
    let cornerRadius: CGFloat = 8
    
    private let  onClick: () -> Void
    
    init(
        category: FoodCategories,
        onClick: @escaping () -> Void
    ) {
        self.category = category
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
                WebImage(url: URL(string: "\(category.imageUrl)"))
                    .resizable()
                    .indicator(.activity)
                    .padding(.bottom, 10)
                    .aspectRatio(contentMode: .fill)
                    .frame(minWidth: 0, maxWidth: .infinity, minHeight: cardHeight, maxHeight: cardHeight)
                    .cornerRadius(cornerRadius, corners: [.topLeft, .bottomRight])
                
                LazyVStack(alignment: .leading) {
                    Text("\(category.name)")
                        .font(.custom("Avenir", size: 14))
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
    
}




