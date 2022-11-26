//
//  SearchAppBar.swift
//  iosEats
//
//  Created by Duncan K on 21/11/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct SearchAppBar: View {
    
    @State var query:String
    private let onSearch: (RecipeBrowseEvents) -> Void
    
    init(
        query: String,
        onSearch: @escaping (RecipeBrowseEvents) -> Void
    ) {
        self._query = State(initialValue: query)
        self.onSearch = onSearch
    }
    
    var body: some View {
        VStack{
            Rectangle()
                .foregroundColor(Color(UIColor.lightGray))
            HStack{
                Image(systemName: "magnifyingglass")
                TextField(
                    "Search...",
                    text: $query,
                    onCommit: {
                        onSearch(RecipeBrowseEvents.NewSearch())
                    }
                )
                .onChange(
                    of: query,
                    perform: { value in
                        onSearch(RecipeBrowseEvents.OnUpdateQuery(query: value))
                    }
                )
                
            }
            .background(Color.clear)
            .padding(EdgeInsets.init(top: 0, leading: 10, bottom: 12, trailing: 10))
        }
        .background(Color(UIColor.lightGray))
        .frame(height: 50)
        .cornerRadius(10)
        .padding(EdgeInsets.init(top: 0, leading: 8, bottom: 8, trailing: 8))
    }
}

