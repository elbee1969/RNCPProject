import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../interface/user';
import { TokenStorageService } from './token-storage.service';
import { Address } from '../model/address';
import { ApiResponse } from '../model/api.response';

const API_PUBLIC_URL = 'http://localhost:9090/api/public/';
const API_PRIVATE_URL = 'http://localhost:9090/api/private';

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
    return this.http.get(API_PRIVATE_URL + '/user', { responseType: 'text' });
  }

  getAdminBoard(): Observable<any> {
    return this.http.get(API_PRIVATE_URL + '/admin');
  }

  getAll(): Observable<User>  {
    // return this.http.get<User[]>(API_PRIVATE_URL + `/users`);
    return this.http.get<User>(API_PRIVATE_URL + `/users`);
  }

  getOne(id: number): Observable<User> {
    return this.http.get<User>(API_PRIVATE_URL + `/details/${id}`);
  }


  delete(id: number): Observable<any> {
    return this.http.delete<any>(API_PRIVATE_URL + `/delete/${id}`);
  }

  getProfile() {
    console.log("in get profile");
    return this.http.get<any>(API_PRIVATE_URL + '/login', {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + this.tokenStorage.getToken(),
        'Content-Type': 'application/json'
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
  
  getAddressById(id: number): Observable<any> {
    return this.http.get<any>(API_PRIVATE_URL + `/address/${id}`);
  }

  updateAddress(address: Address): Observable<ApiResponse> {
    console.log("in update address id : " + address.id);
    console.log("in update address : " + JSON.stringify(address));
    return this.http.patch<ApiResponse>(API_PRIVATE_URL + `/update/${address.id}`, address, httpOptions);
  }
}