declare module '@capacitor/core' {
  interface PluginRegistry {
    CapacitorFirebaseRealtime: CapacitorFirebaseRealtimePlugin;
  }
}

export interface CapacitorFirebaseRealtimePlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
