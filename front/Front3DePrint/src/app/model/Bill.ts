import { Order } from "./Order";
import { Status } from "./status";

export class Bill {
    id: number;
    customUserid: number;
    billRef: string;
    orderDate: Date;
    order: Order[];
    totalPriceHT: number;
    totalPriceTTC: number;
    status: Status;

}