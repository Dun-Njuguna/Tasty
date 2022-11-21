//
//  RecipeBrowseScreen.swift
//  iosEats
//
//  Created by Duncan K on 20/11/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct RecipeBrowseScreen: View {
    
    private let networkModule: NetworkModule
    private let cacheModule: CachingModule
    private let searchRecipesModule: SearchRecipesModule
    
    @ObservedObject var viewModel: RecipeBrowseViewModel
    
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
        self.viewModel = RecipeBrowseViewModel(
            searchRecipes: searchRecipesModule.searchRecipes,
            foodCategories: CategoryTypes()
        )
    }
    
    var body: some View {
        VStack(alignment: .leading){
            SearchAppBar()
            Spacer(minLength: 8)
            List{
                getTitle(title: "Top Categories")
                topCategories()
                getTitle(title: "All Categories")
                allCategories()
            }
        }
    }
    
    func getTitle(title:String) -> some View {
        Text(title)
            .padding(EdgeInsets.init(top: 10, leading: 0, bottom: 10, trailing: 0))
    }
    
    func topCategories() -> some View  {
        ForEach(viewModel.getTopCategories(), id: \.self.id){category in
            Text("\(category.name)")
                .padding(EdgeInsets.init(top: 0, leading: 10, bottom: 0, trailing: 10))
        }
    }
    
    func allCategories() -> some View {
       ForEach(viewModel.state.categories, id: \.self.id){category in
            Text("\(category.name)")
                .padding(EdgeInsets.init(top: 0, leading: 10, bottom: 0, trailing: 10))
        }
    }
}
