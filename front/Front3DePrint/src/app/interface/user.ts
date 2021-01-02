import { Address } from "../model/address";


export interface User {
    id: number;
    username: string;
    email: string;
    password: string;
    lastName: string;
    firstName: string;
    address: Address;

}
