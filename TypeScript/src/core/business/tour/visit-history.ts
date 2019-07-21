import { Position } from "../chess/position";
import { Visitation } from "./visitation";

export class VisitHistory {
    
    private visitations: Array<Visitation> = [];
    private map: Map<string, boolean> = new Map<string, boolean>();

    public checkVisitation(position: Position): boolean {
        return this.map.has(position.toString());
    }

    public addVisitation(visitation: Visitation): void {
        this.visitations.push(visitation);
        this.map.set(visitation.position.toString(), true);
    }

    public backtrack(): Visitation|undefined {
        const visitation = this.visitations.pop();
        
        if (visitation) {
            this.map.delete(visitation.position.toString());
        }

        return visitation;
    }

    public tourSize(): number {
        return this.visitations.length;
    }

    public printTour(): void {
        this.visitations.forEach(
            visitation => {
                console.log(visitation.position);
            }
        );
    }
}