export class User {
    id : number;
    firstName : string;
    lastName: string;
    emailAddress : string;
    password : string;
    roles : string[];

    constructor(id : number, firstName : string, lastName: string, emailAddress : string, password : string, roles : string[]) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
        this.roles = roles;
    }
}
