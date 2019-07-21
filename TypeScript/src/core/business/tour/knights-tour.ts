import { BoardSettings } from "../settings/board-settings";
import { KnightMoves } from "../../chess/knight-moves";
import { Visitation } from "./visitation";
import { Position } from "../chess/position";
import { Board } from "../../chess/board";
import { VisitHistory } from "./visit-history";
import { UnsolveableBoardException } from "../exception/unsolvable-board";

export class KnightsTour {

    public board: Board;
    public targetMoveCount: number;
    public backtrackCount: number = 0;

    private visitedPositions: any = {};

    private moveHistory: VisitHistory = new VisitHistory();

    constructor(settings: BoardSettings) {
        this.board = new Board(settings);
        this.targetMoveCount = settings.totalColumns * settings.totalRows;
    }

    public start(): void {

        this.setKnightPosition(new Position(0,0));

        while(!this.isComplete()) {
            const nextVisitation = this.getNextMove();
            this.processMovement(nextVisitation);
        }

        this.concludeRun();
    }

    private getNextMoves(): Array<any> {
        const currentPosition = this.board.getKnightPosition();
        return KnightMoves
            .filter(move => this.board.isValidMove(move))
            .map((move, movementIndex) => { 
                const position: Position = currentPosition.addMove(move);
                return {position, movementIndex};
            });
    }

    private getNextMove() {
        return this.getNextMoveOfGreaterIndex(-1);
    }

    private getNextMoveOfGreaterIndex(index: number) {
        const nextMoves = this.getNextMoves();
        const nextKnightMove = nextMoves
            .filter(nextMove => nextMove.movementIndex > index)
            .find(nextVisitation => !this.moveHistory.checkVisitation(nextVisitation.position));

        return nextKnightMove;
    }

    private backtrack(): void {
        this.backtrackCount++;
        const lastVisitation = this.moveHistory.backtrack();

        if (!lastVisitation) {
            throw new UnsolveableBoardException(this.board.settings);
        }

        this.setKnightPosition(lastVisitation.position);
        const nextKnightMove = this.getNextMoveOfGreaterIndex(lastVisitation.movementIndex);
        this.processMovement(nextKnightMove);
   }

    private processMovement(nextKnightMove: any) {
        if (!nextKnightMove) {
            this.backtrack();
        } else {
            const visitation = new Visitation(nextKnightMove.position, nextKnightMove.movementIndex);
            this.moveHistory.addVisitation(visitation);
            this.setKnightPosition(visitation.position);
        }
    }

    public isComplete(): boolean {
        return this.moveHistory.tourSize() == this.targetMoveCount;
    }

    public setKnightPosition(position: Position) {
        this.visitedPositions[position.toString()] = true;
        this.board.setKnightPosition(position);
    }

    private concludeRun() {
        console.log(`The End!`);
        console.log(`Backtrack Count: ${this.backtrackCount}`);
        this.moveHistory.printTour();
        // console.log(`Position Order: ${this.moveHistory.map(visitation => visitation.position.toString())}`)
    };
}