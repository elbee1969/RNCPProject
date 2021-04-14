import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { User } from '../model/user';
import { TokenStorageService } from './token-storage.service';


const AUTH_API = 'http://localhost:9090/oauth/token/';


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  setToken: any;
  oauthResponse: any;
  handleError: any;
  private currentUserSubject: BehaviorSubject<User>;
    public currentUser: Observable<User>;

  constructor(private http: HttpClient, private tokenStorageService: TokenStorageService ) { 

    this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('currentUser')));
    this.currentUser = this.currentUserSubject.asObservable();
  }
    
  public get currentUserValue(): User {
    return this.currentUserSubject.value;
  }

 isAuthenticated(): boolean {
    if (this.tokenStorageService.getToken()) {
      return true;
    }
    return false;
  }
  login(credentials): Observable<any> {
    console.log("in login profile");
    const body = 'grant_type=password&username=' + credentials.username + '&password=' + credentials.password + '&client_id=3D-ePrint-app';
    return this.http.post('http://localhost:9090/oauth/token', body, {
      headers: new HttpHeaders({
        "Content-type": "application/x-www-form-urlencoded"
      })
    });
  }

}