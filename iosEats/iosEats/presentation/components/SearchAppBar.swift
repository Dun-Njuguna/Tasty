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
    
    @State var query:String = ""
    
    var body: some View {
        VStack{
            HStack{
                Image(systemName: "magnifyingglass")
                TextField(
                    "Search...",
                    text: $query,
                    onCommit: {
                        
                    }
                )
                .onChange(
                    of: query,
                    perform: { value in
                         
                    }
                )
                
            }
            .padding(.bottom, 10)
        }
        .padding(EdgeInsets.init(top: 10, leading: 10, bottom: 10, trailing: 10))
    }
}

