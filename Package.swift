// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "AthleticCapacitorFirebaseRealtime",
    platforms: [.iOS(.v15)],
    products: [
        .library(
            name: "AthleticCapacitorFirebaseRealtime",
            targets: ["CapacitorFirebaseRealtimePlugin"])
    ],
    dependencies: [
        .package(url: "https://github.com/ionic-team/capacitor-swift-pm.git", from: "8.0.0"),
        .package(url: "https://github.com/firebase/firebase-ios-sdk.git", "11.6.0"..<"13.0.0")
    ],
    targets: [
        .target(
            name: "CapacitorFirebaseRealtimePlugin",
            dependencies: [
                .product(name: "Capacitor", package: "capacitor-swift-pm"),
                .product(name: "Cordova", package: "capacitor-swift-pm"),
                .product(name: "FirebaseAuth", package: "firebase-ios-sdk"),
                .product(name: "FirebaseCore", package: "firebase-ios-sdk"),
                .product(name: "FirebaseDatabase", package: "firebase-ios-sdk")
            ],
            path: "ios/Sources/CapacitorFirebaseRealtimePlugin"),
        .testTarget(
            name: "CapacitorFirebaseRealtimePluginTests",
            dependencies: ["CapacitorFirebaseRealtimePlugin"],
            path: "ios/Tests/CapacitorFirebaseRealtimePluginTests")
    ]
)
