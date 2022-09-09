import { WebPlugin } from '@capacitor/core';

import type { CapacitorFirebaseRealtimePlugin } from './definitions';

export class CapacitorFirebaseRealtimeWeb extends WebPlugin implements CapacitorFirebaseRealtimePlugin {
  async  signOut(): Promise<null> {
    return null;
  }
  async initialize(options: { signedInUserId: number }): Promise<{ signedIn: boolean }>{
    JSON.stringify(options);
    return { signedIn: false };
  }
  async signInWithCustomToken(options: { token: string }): Promise<null>{
    JSON.stringify(options);
    return null;
  }
  async updateChildren(options: { path: string, data: any }): Promise<null>{
    throw('CapacitorFirebaseRealtimeWeb.updateChildren: Web version has not been implemented ' + JSON.stringify(options));
  }

}
