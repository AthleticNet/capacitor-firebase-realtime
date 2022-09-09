export interface CapacitorFirebaseRealtimePlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  signOut(): Promise<null>;
  initialize(options: { signedInUserId: number }): Promise<{ signedIn: boolean }>;
  signInWithCustomToken(options: { token: string }): Promise<null>;
  updateChildren(options: { path: string, data: any }): Promise<null>;
}
