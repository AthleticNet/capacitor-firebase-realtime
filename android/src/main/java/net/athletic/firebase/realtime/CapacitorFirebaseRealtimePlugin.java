package net.athletic.firebase.realtime;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import java.util.Map;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONException;

import com.google.gson.Gson;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseError;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

/*
Examples here:
 - https://github.com/hbmartin/cordova-plugin-firebase-realtime-database/blob/master/src/android/FirebaseDatabasePlugin.java
 - https://github.com/firebase/quickstart-android/blob/afa250e1c45ec45f633cfd94878ed3a655d1525e/auth/app/src/main/java/com/google/firebase/quickstart/auth/java/CustomAuthActivity.java#L70-L71
 - https://github.com/capacitor-community/flipper/blob/master/android/src/main/java/com/getcapacitor/community/flipper/Flipper.java
*/

@CapacitorPlugin(name = "CapacitorFirebaseRealtime")
public class CapacitorFirebaseRealtimePlugin extends Plugin {

    private CapacitorFirebaseRealtime implementation = new CapacitorFirebaseRealtime();

    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", implementation.echo(value));
        call.resolve(ret);
    }

    private FirebaseAuth fireAuth;
    private FirebaseDatabase fireDb;
    private FirebaseUser currentUser = null;

    private static final String TAG = "AnetFirebaseRealtime";

    @Override
    public void load() {
        super.load();

        fireAuth = FirebaseAuth.getInstance();
        fireDb = FirebaseDatabase.getInstance();
        currentUser = fireAuth.getCurrentUser();

        Log.d(TAG, "loaded");

        fireDb.setPersistenceEnabled(true);
    }

    @PluginMethod()
    public void initialize(PluginCall call) {
        JSObject ret = new JSObject();
        int signedInUserId = call.getInt("signedInUserId");
        currentUser = fireAuth.getCurrentUser();
        int fireUserId = currentUser != null ? Integer.parseInt(currentUser.getUid()) : 0;
        boolean fireDBIsSignedIn = fireUserId == signedInUserId;

        if (fireUserId != signedInUserId && fireUserId > 0) {
            Log.d(TAG, "userId mismatch");
            signOut();
        }

        Log.d(TAG, "init; signedIn: " + fireDBIsSignedIn);

        ret.put("signedIn", fireDBIsSignedIn);
        call.resolve(ret);
    }

    @PluginMethod()
    public void signInWithCustomToken(PluginCall call) {
        String token = call.getString("token");

        if (currentUser == null) {
            fireAuth.signInWithCustomToken(token)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithCustomToken:success");
                                currentUser = fireAuth.getCurrentUser();
                                call.resolve();
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithCustomToken:failure", task.getException());
                                call.reject("signInWithCustomToken:failure", task.getException());
                            }
                        }
                    });
        }
    }

    @PluginMethod()
    public void signOut(PluginCall call) {
        Log.d(TAG, "signout triggered");
        signOut();
        call.resolve();
    }

    private void signOut() {
        fireAuth.signOut();
        currentUser = null;
        Log.d(TAG, "signout");
    }

    @PluginMethod()
    public void setValue(PluginCall call) {
        String path = call.getString("path");
        JSONObject value = call.getObject("value");
        Log.d(TAG, "userId: " + currentUser.getUid() + ", setValue path: " + path + ", value: " + value.toString());

        DatabaseReference ref = fireDb.getReference(path);

        try {
            ref.setValue(jsonObjectToMap(value), getCompletionListener(call));
        } catch (JSONException e) {
            call.reject(e.getMessage());
        }
    }

    @PluginMethod()
    public void updateChildren(PluginCall call) {
        String path = call.getString("path");
        JSObject data = call.getObject("data");
        Log.d(TAG, "userId: " + currentUser.getUid() + ", updateChildren path: " + path + ", update: " + data.toString());

        DatabaseReference ref = fireDb.getReference(path);

        try {
            ref.updateChildren(jsonObjectToMap(data), getCompletionListener(call));
            call.resolve();
        } catch (JSONException e) {
            call.reject(e.getMessage());
        }
    }

    private DatabaseReference.CompletionListener getCompletionListener(final PluginCall call) {
        return new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError == null) {
                    call.resolve();
                } else {
                    call.reject(databaseError.getMessage());
                }
            }
        };
    }

    private static Map<String, Object> jsonObjectToMap(JSONObject json) throws JSONException {
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<String, Object>();
        map = (Map<String, Object>) gson.fromJson(json.toString(), map.getClass());
        return map;
    }

//
//  @Override
//  protected void handleOnStart() {
//    super.handleOnStart();
//    Log.d(TAG, "handleOnStart");
//    checkCurrentUser();
//  }
//
//  @Override
//  protected void handleOnRestart() {
//    super.handleOnStart();
//    Log.d(TAG, "handleOnRestart");
//    checkCurrentUser();
//  }
//
//  @Override
//  protected void handleOnResume() {
//    super.handleOnStart();
//    Log.d(TAG, "handleOnResume");
//    checkCurrentUser();
//  }
//
//  @Override
//  protected void handleOnPause() {
//    super.handleOnStart();
//    Log.d(TAG, "handleOnPause");
//    checkCurrentUser();
//  }
//
//  @Override
//  protected void handleOnStop() {
//    super.handleOnStart();
//    Log.d(TAG, "handleOnStop");
//    checkCurrentUser();
//  }
//
//  @Override
//  protected void handleOnDestroy() {
//    super.handleOnStart();
//    Log.d(TAG, "handleOnDestroy");
//    checkCurrentUser();
//  }
//
//  private void checkCurrentUser(){
//    if(currentUser != null){
//      Log.d(TAG, "currentUser: " + currentUser.getUid());
//    }
//    else {
//      Log.d(TAG, "currentUser: NULL");
//    }
//  }

}
