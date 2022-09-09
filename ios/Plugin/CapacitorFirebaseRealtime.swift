import Foundation

@objc public class CapacitorFirebaseRealtime: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}
