import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor() { }

  private username = new BehaviorSubject<string>('');
  private authenticated = new BehaviorSubject<boolean>(false);

  getUsername = this.username.asObservable();
  getAuthenticated = this.authenticated.asObservable();

  setUsername(username : string) {
    this.username.next(username);
  }

  setAuthenticated(authenticated : boolean) {
    this.authenticated.next(authenticated);
  }
}
