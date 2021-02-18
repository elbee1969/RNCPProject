import { Status } from "./status";

export class Order {
    id: number;
    image: typeof Image;
    name: string;
    quantity: number;
    weight: number;
    price: number;
    totalWeight: number;
    totalPrice: number;
    timeToPrint: string;
    orderStatus: Status;
    customUserId: number;
}