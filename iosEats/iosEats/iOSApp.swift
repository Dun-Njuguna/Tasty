import SwiftUI
import shared

@main
struct iOSApp: App {
    private let networkModule = NetworkModule()
    private let cachingModule = CachingModule()
    
    
	var body: some Scene {
		WindowGroup {
			RecipeListScreen(
                networkModule: self.networkModule,
                cacheModule: self.cachingModule
            )
		}
	}
}
