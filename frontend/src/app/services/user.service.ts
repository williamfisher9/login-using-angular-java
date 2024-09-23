import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../model/user';
import { map, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class UserService {

  baseUrl = "http://localhost:8080/api/v1/auth";

  constructor(private http : HttpClient) { }

  authenticateUser(auth: {emailAddress: string, password: string}) : Observable<LoginResponse>{
    return this.http.post<LoginResponse>(`${this.baseUrl}/login`, null, {headers: {Authorization: `Basic ${btoa(auth.emailAddress +":"+ auth.password)}`}})
    .pipe(map(response => response));
  }

  getUsers() : Observable<User[]> {
    return this.http.get<GetUsers>(this.baseUrl).pipe(map(response => response.items));
  }

  createUser(user : User) : Observable<User> {
    return this.http.post<User>(`${this.baseUrl}/register`, user).pipe(map(response => response));
  }

}

interface GetUsers {
  items: User[];
}


interface LoginResponse {
  items: any,
  status: number
}