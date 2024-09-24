import { Component, OnInit } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { DataService } from '../services/data.service';


@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css'
})
export class MenuComponent implements OnInit {
  
  authenticated! : boolean;
  username! : string;
  showUserMenu : boolean = false;

  constructor(private dataService : DataService, private router : Router) {}

  ngOnInit(): void {
    this.dataService.getAuthenticated.subscribe((data) => this.authenticated = data);
    this.dataService.getUsername.subscribe((data) => this.username = data);
  }

  handleLogout() : void {
    window.localStorage.clear();
    this.dataService.setAuthenticated(false);
    this.showUserMenu = false;
    this.router.navigate(['/login']);
  }

  toggleUserMenu() : void {
    this.showUserMenu = !this.showUserMenu;
  }

  handleProfileClick() : void {
    this.showUserMenu = false;
  }

}
