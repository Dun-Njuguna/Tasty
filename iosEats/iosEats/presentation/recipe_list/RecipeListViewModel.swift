//
//  RecipeListViewModel.swift
//  iosEats
//
//  Created by Duncan K on 19/11/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

class RecipeListViewModel: ObservableObject {
    
    let searchRecipes: SearchRecipes
    let foodCategories: CategoryTypes
    
    @Published var state: RecipeListState = RecipeListState()
    
    init(
        searchRecipes: SearchRecipes,
        foodCategories: CategoryTypes
    ){
        self.searchRecipes = searchRecipes
        self.foodCategories = foodCategories
        onTriggerEvent(stateEvent: RecipeListEvents.LoadRecipes())
    }
    
    func onTriggerEvent(stateEvent: RecipeListEvents){
        switch stateEvent {
        case is RecipeListEvents.LoadRecipes :
            loadRecipes()
        case is RecipeListEvents.NewSearch :
            doNothing()
        case is RecipeListEvents.NextPage :
            nextPage()
        case is RecipeListEvents.OnUpdateQuery :
            doNothing()
        case is RecipeListEvents.OnSelectCategory :
            doNothing()
        case is RecipeListEvents.RemoveHeadMessageFromQueue :
            doNothing()
        default:
            doNothing()
        }
    }
    
    private func nextPage(){
        let currentState = (self.state.copy() as! RecipeListState)
        updateState(page: Int(currentState.page + 1))
        loadRecipes()
    }
    
    private func loadRecipes() {
        let currentState = (self.state.copy() as! RecipeListState)
        do{
            try searchRecipes.execute(
                page: Int32(currentState.page),
                query: currentState.query
            ).collectFlow(coroutineScope: nil, callback: { dataState in
                if dataState != nil {
                    let data = dataState?.data
                    let message = dataState?.errorMessage
                    let loading = dataState?.isLoading ?? false
                    
                    self.updateState(isLoading: loading)
                    
                    if data != nil {
                        self.appendRecipes(recipes: data as! [Recipe])
                    }
                    if message != nil {
                        self.displayErrorMessageByUIComponentType(message)
                    }
                }
            })
        }catch{
            print("\(error)")
        }
    }
    
    func appendRecipes(recipes: [Recipe]){
        var currentState = (self.state.copy()) as! RecipeListState
        var currentRecipes = currentState.recipes
        currentRecipes.append(contentsOf: recipes)
        self.state = self.state.doCopy(
            isLoading: currentState.isLoading,
            page: currentState.page,
            query: currentState.query,
            recipes: currentRecipes,
            topCategories: currentState.topCategories,
            newCategories: currentState.newCategories,
            selectedFoodCategories: currentState.selectedFoodCategories,
            bottomRecipe: currentState.bottomRecipe,
            errorQueue: currentState.errorQueue
        )
        
        currentState = self.state.copy() as! RecipeListState
        self.updateBottomRecipe(recipe: currentState.recipes[currentState.recipes.count - 1])
        
    }
    
    func updateBottomRecipe(recipe:Recipe) {
        updateState(bottomRecipe: recipe)
    }
    
    func shouldLoadNextPage(recipe:Recipe) -> Bool {
        let currentState = (self.state.copy()) as! RecipeListState
        if (recipe.id == currentState.bottomRecipe?.id) {
            if (SearchRecipes.Companion().RECIPE_PAGINATION_PAGE_SIZE *
                currentState.page <= currentState.recipes.count){
                if(!currentState.isLoading){
                    return true
                }
            }
        }
        return false
    }
    
    func displayErrorMessageByUIComponentType( _ errorMessage: ErrorMessage?){
        //Todo handle errpr message
    }
    
    func doNothing(){
        
    }
    
    func updateState(
        isLoading: Bool? = nil,
        page:Int? = nil,
        query: String? = nil,
        bottomRecipe: Recipe? = nil,
        queue: Queue<ErrorMessage>? = nil
    ){
        let currentState = (self.state.copy() as! RecipeListState)
        self.state = self.state.doCopy(
            isLoading: isLoading ?? currentState.isLoading,
            page: Int32(page ?? Int(currentState.page)),
            query: query ?? currentState.query,
            recipes: currentState.recipes,
            topCategories: currentState.topCategories,
            newCategories: currentState.newCategories,
            selectedFoodCategories: currentState.selectedFoodCategories,
            bottomRecipe: bottomRecipe ?? currentState.bottomRecipe,
            errorQueue: currentState.errorQueue
        )
    }
    
}
