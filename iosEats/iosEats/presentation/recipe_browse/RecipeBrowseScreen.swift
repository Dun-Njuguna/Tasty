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
    @State var isShowingSearchView:Bool = false
    
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
        VStack(alignment: .leading, spacing: 0){
            SearchAppBar(
                query: viewModel.state.query,
                onSearch: { event in
                    viewModel.onTriggerEvent(stateEvent: event)
                    if(event == RecipeBrowseEvents.NewSearch()){
                        isShowingSearchView = true
                    }
                }
            )
            
            List{
                getTitle(title: "Top Categories")
                topCategories()
                getTitle(title: "All Categories")
                allCategories()
            }
            .padding(EdgeInsets.init(top: 0, leading: 4, bottom: 0, trailing: 4))
            .listStyle(PlainListStyle())
        }.navigate(to: SearchRecipeList(recipeBrowseViewModel: viewModel), title: viewModel.state.query, when: $isShowingSearchView)
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
