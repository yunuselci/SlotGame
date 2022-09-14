export interface Login {
  token: string;
  user: {
    username: string,
    credits: number,
    loan: number,
    withdraw: number
  };
}
