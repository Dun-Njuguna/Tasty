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
    
    private var gridItemLayout = [GridItem(.adaptive(minimum: 150))]
    
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
            
            ScrollView {
                VStack(alignment: .leading, spacing: 8) {
                    getTitle(title: "Top Categories")
                    topCategories()
                    getTitle(title: "All Categories")
                    allCategories()
                }
                .padding(.horizontal, 8)
            }
            if(viewModel.state.isLoading){
                ProgressView("Loading...")
            }
        }
        .navigate(to: SearchRecipeList(recipeBrowseViewModel: viewModel), title: viewModel.state.query, when: $isShowingSearchView)
    }
    
    func getTitle(title:String) -> some View {
        Text(title)
            .padding(EdgeInsets.init(top: 10, leading: 0, bottom: 10, trailing: 0))
    }
    
    func topCategories() -> some View  {
        return LazyVGrid(columns: gridItemLayout, spacing: 8) {
            ForEach(viewModel.getTopCategories(), id: \.self.id){category in
                FoodCategoryChip(category: category){
                    viewModel.onTriggerEvent(stateEvent: RecipeBrowseEvents.OnUpdateQuery(query: category.name))
                    viewModel.onTriggerEvent(stateEvent: RecipeBrowseEvents.NewSearch())
                    isShowingSearchView = true
                }
            }
        }
    }
    
    func allCategories() -> some View {
        LazyVGrid(columns: gridItemLayout, spacing: 8) {
            ForEach(viewModel.state.categories, id: \.self.id){category in
                FoodCategoryChip(category: category){
                    viewModel.onTriggerEvent(stateEvent: RecipeBrowseEvents.OnUpdateQuery(query: category.name))
                    viewModel.onTriggerEvent(stateEvent: RecipeBrowseEvents.NewSearch())
                    isShowingSearchView = true
                }
            }
        }
    }
}
