import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../model/user';
import { map, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class UserService {

  baseUrl = "http://localhost:8080/api/v1/users";

  constructor(private http : HttpClient) { }

  authenticateUser(auth: {emailAddress: string, password: string}){
    console.log(auth.emailAddress + " " + auth.password)
  }

  getUsers() : Observable<User[]> {
    return this.http.get<GetUsers>(this.baseUrl).pipe(map(response => response.items));
  }

  createUser(user : User) : Observable<User> {
    return this.http.post<User>(this.baseUrl, user).pipe(map(response => response));
  }

}

interface GetUsers {
  items: User[];
}