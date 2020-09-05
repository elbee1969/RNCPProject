import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { identifierModuleUrl } from '@angular/compiler';
import { TokenStorageService } from './token-storage.service';

const AUTH_API = 'http://localhost:9090/oauth/token/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  setToken: any;
  oauthResponse: any;
  handleError: any;

  constructor(private http: HttpClient, private tokenStorage: TokenStorageService) { }

  /*login(credentials): Observable<any> {
    return this.http.post(AUTH_API, {
      username: credentials.username,
      password: credentials.password
    }, httpOptions);
  }*/

  login(credentials): Observable<any> {
    console.log("in login profile");
    const body = 'grant_type=password&username=' + credentials.username + '&password=' + credentials.password + '&client_id=3D-ePrint-app';
    return this.http.post('http://localhost:9090/oauth/token', body, {
      headers: new HttpHeaders({
        "Content-type": "application/x-www-form-urlencoded"
      })
    });
  }
 data = "";


getProfile() {
  console.log("in get profile");
  /*const xhr = new XMLHttpRequest();
  xhr.withCredentials = true;
  xhr.addEventListener("readystatechange", function () {
    if (this.readyState === 4) {
      console.log("responsetext : "+this.responseText);
    }
  });


  xhr.open("GET", "http://localhost:9090/api/public/login");
  xhr.setRequestHeader("Authorization", this.tokenStorage.getToken());

  xhr.send(this.data);*/

    return this.http.get<any>('localhost:9090/api/public/login', {
      headers:  new HttpHeaders({
        'Authorization': 'Bearer ' + this.tokenStorage.getToken()
        
      })
    })
  }



  register(user): Observable<any> {
    return this.http.post(AUTH_API + 'signup', {
      username: user.username,
      email: user.email,
      password: user.password
    }, httpOptions);
  }
}