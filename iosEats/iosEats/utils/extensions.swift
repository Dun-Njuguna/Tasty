//
//  navigate.swift
//  iosEats
//
//  Created by Duncan K on 26/11/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI


extension View {
    
    func navigate<NewView: View>(to view: NewView, title:String, when binding: Binding<Bool>) -> some View {
        NavigationView {
            VStack() {
                self
                    .navigationBarTitle("")
                    .navigationBarHidden(true)
                
                NavigationLink(
                    destination: view
                        .navigationBarTitle(title)
                        .navigationBarHidden(false),
                    isActive: binding
                ) {
                    EmptyView()
                }
            }
        }
        .navigationViewStyle(.automatic)
    }
    
    func cornerRadius(_ radius: CGFloat, corners: UIRectCorner) -> some View {
        clipShape( RoundedCorner(radius: radius, corners: corners) )
    }
}


struct RoundedCorner: Shape {

    var radius: CGFloat = .infinity
    var corners: UIRectCorner = .allCorners

    func path(in rect: CGRect) -> Path {
        let path = UIBezierPath(roundedRect: rect, byRoundingCorners: corners, cornerRadii: CGSize(width: radius, height: radius))
        return Path(path.cgPath)
    }
}
