import XCTest
import Capacitor
@testable import CapacitorFirebaseRealtimePlugin

class CapacitorFirebaseRealtimePluginTests: XCTestCase {
    func testPluginRegistration() {
        let plugin = CapacitorFirebaseRealtimePlugin()

        XCTAssertEqual(plugin.identifier, "CapacitorFirebaseRealtimePlugin")
        XCTAssertEqual(plugin.jsName, "CapacitorFirebaseRealtime")
        XCTAssertEqual(
            plugin.pluginMethods.map { $0.name },
            ["signOut", "initialize", "signInWithCustomToken", "updateChildren"]
        )
        XCTAssertTrue(plugin.pluginMethods.allSatisfy { $0.returnType == CAPPluginReturnPromise })
    }
}
