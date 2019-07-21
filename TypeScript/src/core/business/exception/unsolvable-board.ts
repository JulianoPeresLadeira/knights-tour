import { BoardSettings } from "../settings/board-settings";

export class UnsolveableBoardException extends Error {

    constructor(boardSettings: BoardSettings) {
        const columns = boardSettings.totalColumns;
        const rows = boardSettings.totalRows;
        super(`Board of size ${rows}x${columns} cannot be solved.`);
    }
} 