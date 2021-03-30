import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { TokenStorageService } from 'src/app/services/token-storage.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  title = 'Front3DePrint';
  private roles: string[];
  public isCollapsed = true;

  showAdminBoard: boolean = false;
  isLoggedIn: boolean;
  greeting = {};
  showUserBoard: boolean = false;
  username: any;
  @Output() public sidenavToggle = new EventEmitter();
  
  constructor(private tokenStorageService: TokenStorageService, private router: Router, private authService: AuthService) { }

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
  public onToggleSidenav = () => { 
    this.sidenavToggle.emit();
  }
   logout() {
    this.tokenStorageService.signOut();
    // window.location.reload();
    this.router.navigate(['/home']);
  }
}
