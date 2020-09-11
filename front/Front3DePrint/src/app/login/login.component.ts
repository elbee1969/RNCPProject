import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { TokenStorageService } from '../services/token-storage.service';
import * as jwt_decode from 'jwt-decode';
import { decode } from 'punycode';
import { AlertService } from '../services/alert.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  form: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  roles: string[] = [];
  oauthResponse: any;
  account: Object;
  username: any;
  info: any;

  constructor(private authService: AuthService,
              private tokenStorage: TokenStorageService,
              private alertService: AlertService,
              private router: Router) { 

                // redirect to home if already logged in
                     if (this.authService.currentUserValue) { 
                         this.router.navigate(['/']);
                     }
              }



  ngOnInit() {
    console.log('in on init');
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getUser().authorities;
      this.username = this.tokenStorage.getUser().user_name
      console.log("on init role : "+this.roles);
    }
  }

  onSubmit() {
    this.authService.login(this.form).subscribe(
      data => {
        this.tokenStorage.saveToken(data.access_token);
        this.tokenStorage.saveUser(JSON.stringify(jwt_decode(JSON.stringify(this.tokenStorage.getToken()))));
        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenStorage.getUser().authorities;
        this.username = this.tokenStorage.getUser().user_name
        console.log('roles : ' + this.roles);
        console.log('name : ' + this.username);
        this.reloadPage();

      },
      err => {
        this.alertService.error(err);
        this.isLoginFailed = true;
      }
    );
    }

  reloadPage() {
    window.location.reload();
  }
}