export class Move {
    public deltaRow: number;
    public deltaColumn: number;

    constructor(dRow: number, dColumnm: number) {
        this.deltaColumn = dColumnm;
        this.deltaRow = dRow;
    }

    public toString(): string {
        return `(${this.deltaRow > 0 ? `+${this.deltaRow}` : this.deltaRow}, ${this.deltaColumn > 0 ? `+${this.deltaColumn}` : this.deltaColumn})`
    }
}