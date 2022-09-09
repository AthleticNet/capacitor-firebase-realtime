#import <Foundation/Foundation.h>
#import <Capacitor/Capacitor.h>

// Define the plugin using the CAP_PLUGIN Macro, and
// each method the plugin supports using the CAP_PLUGIN_METHOD macro.
CAP_PLUGIN(CapacitorFirebaseRealtimePlugin, "CapacitorFirebaseRealtime",
           CAP_PLUGIN_METHOD(signOut, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(initialize, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(signInWithCustomToken, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(updateChildren, CAPPluginReturnPromise);
)
