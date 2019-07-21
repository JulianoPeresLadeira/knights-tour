export class BoardSettings {
    
    public totalRows: number;
    public totalColumns: number;

    constructor(settings: any) {
        this.totalRows = settings.totalRows;
        this.totalColumns = settings.totalColumns;
    }
}