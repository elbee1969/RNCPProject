import { Component } from '@angular/core';
import { TokenStorageService } from './services/token-storage.service';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
const isLoggedIn = false;
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Front3DePrint';
  private roles: string[];


  showAdminBoard: boolean = false;
  isLoggedIn: boolean;
  greeting = {};
  showUserBoard: boolean = false;
  username: any;
  
  constructor(private tokenStorageService: TokenStorageService, private router: Router) { 
    
    // window.location.reload();
  }

  ngOnInit() {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      this.username = this.tokenStorageService.getUser().user_name
      const user = this.tokenStorageService.getUser();
      this.roles = this.tokenStorageService.getUser().authorities;
      if (this.roles.includes('ROLE_USER')) {
        this.showUserBoard = this.roles.includes('ROLE_USER');
        this.router.navigate(['/user']);
      } else {
        this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
        this.router.navigate(['/admin']);
      }
    }
    // this.showUserBoard = this.roles.includes('ROLE_USER');
    
  }

  
  logout() {
    this.tokenStorageService.signOut();
    window.location.reload();
  }
        
}
