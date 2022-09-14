export interface Game {
  username: string,
  slots: string[],
  userCredits: number | null,
  loan: number | null,
  withdraw: number | null
}
