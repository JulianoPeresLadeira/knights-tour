// import { BoardSettings } from "../settings/board-settings";
// import { KnightMoves } from "../../chess/knight-moves";
// import { Visitation } from "./visitation";
// import { Position } from "../chess/position";
// import { Board } from "../../chess/board";

// export class KnightsTour {

//     public board: Board;
//     public targetMoveCount: number;
//     public backtrackCount: number = 0;

//     private visitedPositions: any = {};

//     private moveHistory: Array<Visitation> = [];

//     constructor(settings: BoardSettings) {
//         this.board = new Board(settings);
//         this.targetMoveCount = settings.totalColumns * settings.totalRows;
//     }

//     public start(): void {

//         this.setKnightPosition(new Position(0,0));

//         while(!this.isComplete()) {
//             const nextMovesInBoard = this.getNextMoves();

//             const nextVisitation = nextMovesInBoard.find(nextVisitation => !this.moveHistory.some(visitedPosition => visitedPosition.position.equals(nextVisitation.position)))

//             if (!nextVisitation) {
//                 this.backtrack();
//             } else {
//                 const visitation = new Visitation(nextVisitation.position, nextVisitation.movementIndex);
//                 this.moveHistory.push(visitation);
//                 this.setKnightPosition(visitation.position);
//             }

//         }

//         this.concludeRun();
//     }

//     private getNextMoves(): Array<any> {
//         const currentPosition = this.board.getKnightPosition();
//         return KnightMoves
//             .filter(move => this.board.isValidMove(move))
//             .map((move, movementIndex) => { 
//                 const position: Position = currentPosition.addMove(move);
//                 return {position, movementIndex};
//             });
//     }

//     private backtrack(): void {
//         this.backtrackCount++;
//         const lastVisitation = this.moveHistory.pop();

//         if (!lastVisitation) {
//             throw 'Board unsolvable';
//         }

//         this.setKnightPosition(lastVisitation.position);
//         const nextMoves = this.getNextMoves();
//         const nextKnightMove = nextMoves
//             .filter(nextMove => nextMove.movementIndex > lastVisitation.movementIndex)
//             .find(nextVisitation => !this.moveHistory.some(visitedPosition => visitedPosition.position.equals(nextVisitation.position)));

//         if (!nextKnightMove) {
//             this.backtrack();
//         } else {
//             const visitation = new Visitation(nextKnightMove.position, nextKnightMove.movementIndex);
//             this.moveHistory.push(visitation);
//             this.setKnightPosition(visitation.position);
//         }
//     }

//     public isComplete(): boolean {
//         return this.moveHistory.length == this.targetMoveCount;
//     }

//     public setKnightPosition(position: Position) {
//         this.visitedPositions[position.toString()] = true;
//         this.board.setKnightPosition(position);
//     }

//     private concludeRun() {
//         console.log(`The End!`);
//         console.log(`Backtrack Count: ${this.backtrackCount}`);
//         console.log(`Position Order: ${this.moveHistory.map(visitation => visitation.position.toString())}`)
//     };
// }