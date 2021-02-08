import { Status } from "./status";

export class Order {
    id: number;
    image: typeof Image;
    name: string;
    quantity: number;
    weight: number;
    price: number;
    totalPrice: number;
    timeToPrint: string;
    status: Status;
    customUserId: number;
}