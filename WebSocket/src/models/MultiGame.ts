export default interface MultiGame {
    gameId: string;
    player1: string | null;
    player2: string | null;
    choice1: number;
    choice2: number;
    gameStatus: number;
    winner: string;
}