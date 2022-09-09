export interface CapacitorFirebaseRealtimePlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
