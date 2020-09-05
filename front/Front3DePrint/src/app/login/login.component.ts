import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { TokenStorageService } from '../services/token-storage.service';
import * as jwt_decode from 'jwt-decode';
import { decode } from 'punycode';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  form: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
    roles: string[] = [];
  oauthResponse: any;
  account: Object;
  username: any;
  info: any;

  constructor(private authService: AuthService, private tokenStorage: TokenStorageService) { }

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
        //this.authService.login(Credential);
        this.tokenStorage.saveToken(data.access_token);
        //console.log('getted token : ' + this.tokenStorage.getToken());
        //console.log("data.access token decode : " + JSON.stringify(jwt_decode(data.access_token)));
        //console.log('data JS : ' + JSON.stringify(data));
        this.tokenStorage.saveUser(JSON.stringify(jwt_decode(JSON.stringify(this.tokenStorage.getToken()))));
        //console.log('get user : ' + this.tokenStorage.getUser());
        //console.log('get user stringify : ' + JSON.stringify(this.tokenStorage.getUser()));
        //console.log('get user stringify AT decode : ' + (JSON.stringify(jwt_decode(JSON.stringify(this.tokenStorage.getUser().access_token)))));
        //this.info = JSON.parse(JSON.stringify(jwt_decode(JSON.stringify(this.tokenStorage.getUser().access_token))));

        //console.log('get user stringify AT decode : ' + (JSON.stringify(jwt_decode(JSON.stringify(this.tokenStorage.getUser().access_token)))));
        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenStorage.getUser().authorities;
        this.username = this.tokenStorage.getUser().user_name
        console.log('roles : ' + this.roles);
        //this.username = this.tokenStorage.getUser().user_name;
        console.log('name : ' + this.username);
        //console.log('roles : ' + this.info.authorities);
        this.reloadPage();

      },
      err => {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
      }
    );
    }

  reloadPage() {
    window.location.reload();
  }
}