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
    var clickedRecipe: Recipe? = nil
    
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
            loadNextPage()
        case is RecipeBrowseEvents.NewSearch :
            newSearch()
        case is RecipeBrowseEvents.OnUpdateQuery :
            onUpdateQuery(query: (stateEvent as!  RecipeBrowseEvents.OnUpdateQuery).query)
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
    
    private func onUpdateQuery(query:String){
        updateState(query: query)
    }
    
    private func newSearch(){
        resetSearchState()
        loadRecipes()
    }
    
    private func resetSearchState(){
        let currentState = (self.state.copy() as! RecipeBrowseState)
        var foodCategory = currentState.selectedFoodCategories
        if(foodCategory?.name != currentState.query){
            foodCategory = nil
        }
        self.state = self.state.doCopy(
            isLoading: currentState.isLoading,
            page: 1,
            query: currentState.query,
            categories: currentState.categories,
            selectedFoodCategories: currentState.selectedFoodCategories,
            recipes: [],
            bottomRecipe: currentState.bottomRecipe,
            errorQueue: currentState.errorQueue
        )
    }
    
    private func loadRecipes() {
        let currentState = (self.state.copy() as! RecipeBrowseState)
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
        var currentState = (self.state.copy()) as! RecipeBrowseState
        var currentRecipes = currentState.recipes
        currentRecipes.append(contentsOf: recipes)
        self.state = self.state.doCopy(
            isLoading: currentState.isLoading,
            page: currentState.page,
            query: currentState.query,
            categories: currentState.categories,
            selectedFoodCategories: currentState.selectedFoodCategories,
            recipes: currentRecipes ,
            bottomRecipe: currentState.bottomRecipe,
            errorQueue: currentState.errorQueue
        )
        
        currentState = self.state.copy() as! RecipeBrowseState
        self.updateBottomRecipe(recipe: currentState.recipes[currentState.recipes.count - 1])
    }
    
    func updateBottomRecipe(recipe:Recipe) {
        updateState(bottomRecipe: recipe)
    }
    
    func shouldLoadNextPage(recipe:Recipe) -> Bool {
        let currentState = (self.state.copy()) as! RecipeBrowseState
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
    
    func loadNextPage(){
        let currentState = (self.state.copy() as! RecipeBrowseState)
        updateState(page: Int(currentState.page + 1))
        loadRecipes()
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
            recipes: currentState.recipes,
            bottomRecipe: bottomRecipe ?? currentState.bottomRecipe,
            errorQueue: currentState.errorQueue
        )
    }
    
    func displayErrorMessageByUIComponentType( _ errorMessage: ErrorMessage?){
        //Todo handle errpr message
    }
    
}
