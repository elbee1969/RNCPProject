import { Address } from "./address";
import { Role } from "./Role";

export class User {
    id: number;
    username: string;
    email: string;
    password: string;
    lastname: string;
    firstname: string;
    address: Address;
    role: Role;

}