import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../interface/user';
import { TokenStorageService } from './token-storage.service';

const API_PUBLIC_URL = 'http://localhost:9090/api/public/';
const API_PRIVATE_URL = 'http://localhost:9090/api/private/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json',  })
};


@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient, private tokenStorage: TokenStorageService) { }

  getPublicContent(): Observable<any> {
    return this.http.get(API_PUBLIC_URL + 'all', { responseType: 'text' });
  }

  getUserBoard(): Observable<any> {
    return this.http.get(API_PRIVATE_URL + 'user', { responseType: 'text' });
  }

  getAdminBoard(): Observable<any> {
    return this.http.get(API_PRIVATE_URL + 'admin', { responseType: 'text' });
  }

  getAll() {
    return this.http.get<User[]>(`/users`);
  }


  delete(id: number) {
    return this.http.delete(`/users/${id}`);
  }

  getProfile() {
    console.log("in get profile");
    return this.http.get<any>(API_PRIVATE_URL + 'login', {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + this.tokenStorage.getToken()

      })
    })
  }


  register(user): Observable<any> {
    console.log("in register profile : " + user.username);
    return this.http.post<any>(API_PUBLIC_URL + 'register', {
      username: user.username,
      firstname: user.firstname,
      lastname: user.lastname,
      email: user.email,
      password: user.password
    }, httpOptions);
  }
  
}