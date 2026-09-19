# capacitor-firebase-realtime

Capacitor Firebase Realtime plugin

## Install

```bash
npm install @athletic/capacitor-firebase-realtime
npx cap sync
```

## iOS

Both Swift Package Manager and CocoaPods are supported.

- **Swift Package Manager** (default for Capacitor 6+ apps): `npx cap sync ios` adds the
  `AthleticCapacitorFirebaseRealtime` package to your app's `CapApp-SPM` package automatically.
  Depends on `firebase-ios-sdk` 11.6 through 12.x.
- **CocoaPods**: `npx cap sync ios` adds the `AthleticCapacitorFirebaseRealtime` pod to your
  Podfile. It depends on the `FirebaseAuth` and `FirebaseDatabase` pods and is built as a static
  framework.

Your app must include its own `GoogleService-Info.plist`; the plugin calls
`FirebaseApp.configure()` on load if no default Firebase app exists yet.

## API

<docgen-index>

* [`signOut()`](#signout)
* [`initialize(...)`](#initialize)
* [`signInWithCustomToken(...)`](#signinwithcustomtoken)
* [`updateChildren(...)`](#updatechildren)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### signOut()

```typescript
signOut() => Promise<null>
```

**Returns:** <code>Promise&lt;null&gt;</code>

--------------------


### initialize(...)

```typescript
initialize(options: { signedInUserId: number; }) => Promise<{ signedIn: boolean; }>
```

| Param         | Type                                     |
| ------------- | ---------------------------------------- |
| **`options`** | <code>{ signedInUserId: number; }</code> |

**Returns:** <code>Promise&lt;{ signedIn: boolean; }&gt;</code>

--------------------


### signInWithCustomToken(...)

```typescript
signInWithCustomToken(options: { token: string; }) => Promise<null>
```

| Param         | Type                            |
| ------------- | ------------------------------- |
| **`options`** | <code>{ token: string; }</code> |

**Returns:** <code>Promise&lt;null&gt;</code>

--------------------


### updateChildren(...)

```typescript
updateChildren(options: { path: string; data: any; }) => Promise<null>
```

| Param         | Type                                      |
| ------------- | ----------------------------------------- |
| **`options`** | <code>{ path: string; data: any; }</code> |

**Returns:** <code>Promise&lt;null&gt;</code>

--------------------

</docgen-api>
