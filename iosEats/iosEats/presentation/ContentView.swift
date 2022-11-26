//
//  ContentView.swift
//  iosEats
//
//  Created by Duncan K on 20/11/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ContentView: View {
    private let networkModule: NetworkModule
    private let cacheModule: CachingModule
    
    init(
        networkModule: NetworkModule,
        cacheModule: CachingModule
    ){
        self.networkModule = networkModule
        self.cacheModule = cacheModule
    }
    
    var body: some View {
        TabView{
            RecipeListScreen(
                networkModule: networkModule,
                cacheModule: cacheModule)
                .tabItem{
                    Image(systemName: "house")
                    Text("Home")
                }
            RecipeBrowseScreen(
                networkModule: networkModule,
                cacheModule: cacheModule)
                .tabItem{
                    Image(systemName: "magnifyingglass.circle")
                    Text("Browse")
                }
        }
    }
}

