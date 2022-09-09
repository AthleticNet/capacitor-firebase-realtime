# capacitor-firebase-realtime

Capacitor Firebase Realtime plugin

## Install

```bash
npm install capacitor-firebase-realtime
npx cap sync
```

## API

<docgen-index>

* [`echo(...)`](#echo)
* [`signOut()`](#signout)
* [`init(...)`](#init)
* [`signInWithCustomToken(...)`](#signinwithcustomtoken)
* [`updateChildren(...)`](#updatechildren)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### echo(...)

```typescript
echo(options: { value: string; }) => Promise<{ value: string; }>
```

| Param         | Type                            |
| ------------- | ------------------------------- |
| **`options`** | <code>{ value: string; }</code> |

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### signOut()

```typescript
signOut() => Promise<null>
```

**Returns:** <code>Promise&lt;null&gt;</code>

--------------------


### init(...)

```typescript
init(options: { signedInUserId: number; }) => Promise<{ signedIn: boolean; }>
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
