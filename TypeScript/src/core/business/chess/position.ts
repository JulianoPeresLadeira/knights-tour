import { Move } from "./move";

export class Position {
    public row: number;
    public column: number;

    constructor(row: number, column: number) {
        this.row = row;
        this.column = column;
    }

    public toString(): string {
        return `(${this.row}, ${this.column})`
    }

    public equals(position: Position): boolean {
        return this.row == position.row && this.column == position.column;
    }

    public addMove(movement: Move): Position {
        return new Position (this.row + movement.deltaRow, this.column + movement.deltaColumn);
    }
}