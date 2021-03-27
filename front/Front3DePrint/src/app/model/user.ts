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
    roles: Role[];
constructor(username : string, id : number, roles : Role[]) {
        this.username = username;
        this.id = id;
        this.roles = roles;
    }
    
    checkRole(roles: Role[]): boolean {
        return this.roles.some(role => roles.includes(role));
    }

    isAdmin() : boolean{
        return this.roles.includes(Role.ROLE_ADMIN);
    }
}