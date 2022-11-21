//
//  RecipeBrowseViewModel.swift
//  iosEats
//
//  Created by Duncan K on 20/11/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

class RecipeBrowseViewModel: ObservableObject {
    let searchRecipes: SearchRecipes
    let foodCategories: CategoryTypes
    
    
    @Published var state: RecipeBrowseState = RecipeBrowseState()
    
    init(
        searchRecipes: SearchRecipes,
        foodCategories: CategoryTypes
    ){
        self.searchRecipes = searchRecipes
        self.foodCategories = foodCategories
        onTriggerEvent(stateEvent:RecipeBrowseEvents.LoadCategories())
    }
    
    func onTriggerEvent(stateEvent: RecipeBrowseEvents){
        switch stateEvent {
        case is RecipeBrowseEvents.LoadCategories :
            loadCategories()
        case is RecipeBrowseEvents.NextPage :
            doNothing()
        case is RecipeBrowseEvents.NewSearch :
            doNothing()
        case is RecipeBrowseEvents.OnUpdateQuery :
            doNothing()
        case is RecipeBrowseEvents.OnSelectCategory :
            doNothing()
        case is RecipeBrowseEvents.LoadCategories :
            doNothing()
        case is RecipeBrowseEvents.RemoveHeadMessageFromQueue :
            doNothing()
        default:
            doNothing()
        }
    }
    
    func doNothing(){
        
    }
    
    func loadCategories(){
        let categories = foodCategories.getAllCategories()
        updateState(
            categories: categories
        )
    }
    
    func getTopCategories() -> [FoodCategories]{
        let currentState = (self.state.copy() as! RecipeBrowseState)
        return currentState.categories.filter{ $0.isPopular }
    }
    
    func updateState(
        isLoading: Bool? = nil,
        page:Int? = nil,
        query: String? = nil,
        categories: [FoodCategories]? = nil,
        selectedFoodCategory: FoodCategories? = nil,
        bottomRecipe: Recipe? = nil,
        queue: Queue<ErrorMessage>? = nil
    ){
        let currentState = (self.state.copy() as! RecipeBrowseState)
        
        self.state = self.state.doCopy(
            isLoading: isLoading ?? currentState.isLoading,
            page: Int32(page ?? Int(currentState.page)),
            query: query ?? currentState.query,
            categories: categories ?? currentState.categories,
            selectedFoodCategories: currentState.selectedFoodCategories,
            recipes: currentState.recipes, bottomRecipe: bottomRecipe ?? currentState.bottomRecipe,
            errorQueue: currentState.errorQueue
        )
    }
    
}
