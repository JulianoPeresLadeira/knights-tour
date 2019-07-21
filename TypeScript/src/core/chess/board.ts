import { BoardSettings } from "../business/settings/board-settings";
import { Position } from "../business/chess/position";
import { Move } from "../business/chess/move";
import { InvalidPositionException } from "../business/exception/invalid-position";

export class Board {

    public settings: BoardSettings;
    protected knightPosition: Position;

    constructor (settings: BoardSettings) {
        this.settings = settings;
        this.knightPosition = new Position(0,0);
    }

    public setKnightPosition(position: Position): void {

        if (!this.isLegalPosition(position)) {
            throw new InvalidPositionException(`Invalid position ${position.toString()}.`);
        }

        this.knightPosition = position;
    }

    public getKnightPosition(): Position {
        return this.knightPosition;
    }
    
    public isLegalPosition(position: Position): boolean {
        return (position.column >= 0 && position.column < this.settings.totalColumns) &&
        (position.row >= 0 && position.row < this.settings.totalRows);
    }

    public isValidMove(move: Move): boolean {
        return this.isLegalPosition(new Position(this.getKnightPosition().row + move.deltaRow, this.getKnightPosition().column + move.deltaColumn));
    }
    
}