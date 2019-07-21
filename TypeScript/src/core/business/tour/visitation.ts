import { Position } from "../chess/position";

export class Visitation {

    public position: Position;
    public movementIndex: number;

    constructor(position: Position, movementIndex: number) {
        this.position = position;
        this.movementIndex = movementIndex;
    }

    public toString(): string {
        return this.position.toString() + ' - ' + this.movementIndex;
    }
}