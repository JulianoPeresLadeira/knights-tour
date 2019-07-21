import { BoardSettings } from "./core/business/settings/board-settings";
import { KnightsTour } from "./core/business/tour/knights-tour";

const settings: BoardSettings = new BoardSettings({totalRows: 6, totalColumns: 6});
const tour: KnightsTour = new KnightsTour(settings);

tour.start();
