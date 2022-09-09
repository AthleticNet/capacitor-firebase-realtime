import { registerPlugin } from '@capacitor/core';

import type { CapacitorFirebaseRealtimePlugin } from './definitions';

const CapacitorFirebaseRealtime = registerPlugin<CapacitorFirebaseRealtimePlugin>('CapacitorFirebaseRealtime', {
  web: () => import('./web').then(m => new m.CapacitorFirebaseRealtimeWeb()),
});

export * from './definitions';
export { CapacitorFirebaseRealtime };
