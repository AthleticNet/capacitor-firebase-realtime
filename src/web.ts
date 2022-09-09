import { WebPlugin } from '@capacitor/core';

import type { CapacitorFirebaseRealtimePlugin } from './definitions';

export class CapacitorFirebaseRealtimeWeb extends WebPlugin implements CapacitorFirebaseRealtimePlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
