import { HttpClient, HttpEvent, HttpHeaders, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiResponse } from '../model/api.response';
import { Order } from '../model/Order';

const API_ORDER_URL = 'http://localhost:9090/api/order';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient) { }

  createOrder(order: Order): Observable<ApiResponse> {
    return this.http.post<ApiResponse>(API_ORDER_URL, order, httpOptions);
  }
}
