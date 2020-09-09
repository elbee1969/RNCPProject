import { Component } from '@angular/core';
import { TokenStorageService } from './services/token-storage.service';
import { HttpClient } from '@angular/common/http';
const isLoggedIn = false;
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Front3DePrint';
  private roles: string[];


  showAdminBoard = false;
  showSupervisorBoard = false;
  isLoggedIn: boolean;
  greeting = {};
  showUserBoard: boolean = false;
  username: any;

  constructor(private tokenStorageService: TokenStorageService) { 

  }

  ngOnInit() {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      this.username = this.tokenStorageService.getUser().user_name
      const user = this.tokenStorageService.getUser();
      console.log("user : "+ JSON.stringify(user));
      this.roles = this.tokenStorageService.getUser().authorities;
      console.log("role : " + this.roles);
      this.showUserBoard = this.roles.includes('ROLE_USER');
      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      this.showSupervisorBoard = this.roles.includes('ROLE_SUPERVISOR');
    }
  }

  
  logout() {
    this.tokenStorageService.signOut();
    window.location.reload();
  }
        
}
