export interface CapacitorFirebaseRealtimePlugin {
  signOut(): Promise<null>;
  initialize(options: { signedInUserId: number }): Promise<{ signedIn: boolean }>;
  signInWithCustomToken(options: { token: string }): Promise<null>;
  updateChildren(options: { path: string, data: any }): Promise<null>;
}
