import { WebPlugin } from '@capacitor/core';
import { CapacitorFirebaseRealtimePlugin } from './definitions';

export class CapacitorFirebaseRealtimeWeb extends WebPlugin implements CapacitorFirebaseRealtimePlugin {
  constructor() {
    super({
      name: 'CapacitorFirebaseRealtime',
      platforms: ['web'],
    });
  }

  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}

const CapacitorFirebaseRealtime = new CapacitorFirebaseRealtimeWeb();

export { CapacitorFirebaseRealtime };

import { registerWebPlugin } from '@capacitor/core';
registerWebPlugin(CapacitorFirebaseRealtime);
