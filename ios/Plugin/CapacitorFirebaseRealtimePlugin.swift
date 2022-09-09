import Foundation
import Capacitor
import FirebaseAuth
import FirebaseCore
import FirebaseDatabase

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(CapacitorFirebaseRealtimePlugin)
public class CapacitorFirebaseRealtimePlugin: CAPPlugin {
    private let implementation = CapacitorFirebaseRealtime()
    
    private    var ref: DatabaseReference!
    
    override public func load() {
        if (FirebaseApp.app() == nil) {
            FirebaseApp.configure()
        }
        ref = Database.database().reference()
    }
    
    @objc func signOut(_ call: CAPPluginCall) {
        do {
            try Auth.auth().signOut()
            call.resolve()
        } catch let signOutError as NSError {
            print("Error signing out: %@", signOutError)
            call.reject(signOutError.localizedDescription, nil, signOutError)
        }
    }
    
    @objc func initialize(_ call: CAPPluginCall) {
        let signedInUserId = call.getInt("signedInUserId") ?? 0
        let currentUser = Auth.auth().currentUser;
        var firebaseUserId = Int(currentUser?.uid ?? "0") ?? 0
        if firebaseUserId > 0 && signedInUserId != firebaseUserId {
            print("CapFire userId mismatch")
            signOut(call)
            firebaseUserId = 0
        }
        call.resolve(["signedIn": firebaseUserId])
    }
    
    @objc func signInWithCustomToken(_ call: CAPPluginCall) {
        let token = call.getString("token") ?? ""
        
        Auth.auth().signIn(withCustomToken: token) { user, error in
            if let error = error {
                print("Error signIn withCustomToken: \(error).")
                call.reject("Error signIn withCustomToken: \(error).")
            } else {
                call.resolve()
            }
        }
    }
    
    @objc func updateChildren(_ call: CAPPluginCall) {
        let path = call.getString("path") ?? ""
        let data = call.getAny("data")
        ref.child(path).updateChildValues(data as! [AnyHashable : Any]) {
            (error:Error?, ref:DatabaseReference) in
            if let error = error {
                print("Data could not be saved: \(error).")
                call.reject("Data could not be saved: \(error).")
            } else {
                call.resolve()
            }
        }
        
    }
}
