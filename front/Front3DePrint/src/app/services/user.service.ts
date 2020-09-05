import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const API_PUBLIC_URL = 'http://localhost:9090/api/public/';
const API_PRIVATE_URL = 'http://localhost:9090/api/private/';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getPublicContent(): Observable<any> {
    return this.http.get(API_PUBLIC_URL + 'all', { responseType: 'text' });
  }

  getUserBoard(): Observable<any> {
    return this.http.get(API_PRIVATE_URL + 'user', { responseType: 'text' });
  }

  getSupervisorBoard(): Observable<any> {
    return this.http.get(API_PRIVATE_URL + 'sup', { responseType: 'text' });
  }

  getAdminBoard(): Observable<any> {
    return this.http.get(API_PRIVATE_URL + 'admin', { responseType: 'text' });
  }
}