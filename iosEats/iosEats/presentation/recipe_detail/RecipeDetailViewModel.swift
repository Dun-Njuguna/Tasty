//
//  RecipeDetailViewModel.swift
//  iosEats
//
//  Created by Duncan K on 27/11/2022.
//  Copyright © 2022 orgName. All rights reserved.
//


import SwiftUI
import shared

class RecipeDetailViewModel: ObservableObject {
    
    private let getRecipe: GetRecipe
    
    @Published var state: RecipeDetailState = RecipeDetailState()
    
    init(
        recipeId: Int,
        getRecipe: GetRecipe
    ) {
        self.getRecipe = getRecipe
        onTriggerEvent(stateEvent: RecipeDetailEvents.GetRecipe(recipeId: Int32(recipeId)))
    }
    
    func onTriggerEvent(stateEvent: RecipeDetailEvents){
        switch stateEvent {
            case is RecipeDetailEvents.GetRecipe:
                getRecipe(recipeId: Int((stateEvent as! RecipeDetailEvents.GetRecipe).recipeId))
            case is RecipeDetailEvents.RemoveHeadMessageFromQueue:
                removeHeadFromQueue()
            default: doNothing()
        }
    }
        
    private func getRecipe(recipeId: Int){
        do{
            self.updateState(isLoading: true)
            try self.getRecipe.execute(
                recipeId: Int32(recipeId)
            ).collectFlow(
                coroutineScope: nil,
                callback: { dataState in
                if dataState != nil {
                    let data = dataState?.data
                    let message = dataState?.errorMessage
                    let loading = dataState?.isLoading ?? false
                    self.updateState(isLoading: loading)
                    
                    if(data != nil){
                        self.updateState(recipe: data! as Recipe)
                    }
                    if(message != nil){
                        self.handleMessageByUIComponentType(message! )
                    }
                }else{
//                    self.logger.log(msg: "GetRecipe: DataState is nil")
                }
            })
        }catch{
//            logger.log(msg: "GetRecipe: ERROR: \(error)")
        }
    }
    
    private func handleMessageByUIComponentType(_ message: ErrorMessage){
        switch message.uiComponentType{
        case UIComponentType.Dialog():
            appendToQueue(message: message)
        case UIComponentType.None():
            doNothing()
//            logger.log(msg: "\(message.description)")
        default:
            doNothing()
        }
    }
    
    private func appendToQueue(message: ErrorMessage){
        let currentState = (self.state.copy() as! RecipeDetailState)
        let queue = currentState.errorQueue
        let queueUtil = ErrorMessageQueueUtil() // prevent duplicates
        if !queueUtil.isErrorUnique(queue: queue, message: message) {
           queue.add(element: message)
           updateState(queue: queue)
        }
   }

    private func removeHeadFromQueue(){
        let currentState = (self.state.copy() as! RecipeDetailState)
        let queue = currentState.errorQueue
        do {
            try queue.remove()
            updateState(queue: queue)
        }catch{
//            logger.log(msg: "\(error)")
        }
    }
    
    private func doNothing(){}
    
    private func updateState(
        isLoading: Bool? = nil,
        recipe: Recipe? = nil,
        queue: Queue<ErrorMessage>? = nil
    ){
        let currentState = (self.state.copy() as! RecipeDetailState)
        self.state = self.state.doCopy(
            isLoading: isLoading ?? currentState.isLoading,
            recipe: recipe ?? currentState.recipe,
            errorQueue: queue ?? currentState.errorQueue
        )
    }
    
}
