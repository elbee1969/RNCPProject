import { Component, EventEmitter, Output } from '@angular/core';
import { TokenStorageService } from './services/token-storage.service';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from './services/auth.service';
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

  @Output() public sidenavToggle = new EventEmitter();
  
  constructor(private tokenStorageService: TokenStorageService, private router: Router, private authService: AuthService) { 
    
    // window.location.reload();
  }

  ngOnInit() {
    //this.isLoggedIn = !!this.tokenStorageService.getToken();
    this.isLoggedIn = this.authService.isAuthenticated();
    if (this.isLoggedIn) {
      this.username = this.tokenStorageService.getUser().user_name
      //const user = this.tokenStorageService.getUser();
      this.roles = this.tokenStorageService.getUser().authorities;
      if (this.roles.includes('ROLE_USER')) {
        this.showUserBoard = this.roles.includes('ROLE_USER');
        // this.router.navigate(['/user']);
      } else if (this.roles.includes('ROLE_ADMIN')) {
        this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
        
      }else {
      this.router.navigate(['/home']);
      }
    }
    
  }
 
  
  logout() {
    this.tokenStorageService.signOut();
    // window.location.reload();
    this.router.navigate(['/home']);
  }
      
}
