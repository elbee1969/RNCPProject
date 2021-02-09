import { HttpClient, HttpEvent, HttpHeaders, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiResponse } from '../model/api.response';
import { Order } from '../model/Order';

const API_ORDER_URL = 'http://localhost:9090/api/orders';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  updateOrder(order: Order, id: number) {
    return this.http.patch<any>(API_ORDER_URL + `/updatevalidated/${id}`, order, httpOptions);
  }

  constructor(private http: HttpClient) { }

  createOrder(order: Order): Observable<ApiResponse> {
    return this.http.post<ApiResponse>(API_ORDER_URL, order, httpOptions);
  }

  listOrder(): Observable<Order> {
    return this.http.get<Order>(API_ORDER_URL + '/vieworders');

  }

  getOrders(id: number, status: string): Observable<Order> {
    return this.http.get<Order>(API_ORDER_URL + `/vieworders/${id}/${status}`);  
  }

  getOrder(id: number): Observable<Order> {
    return this.http.get<Order>(API_ORDER_URL + `/vieworder/${id}`);
  }

  getItemOrder(id: number): Observable<Order> {
    return this.http.get<Order>(API_ORDER_URL + `/viewitemsorder/${id}`);
  }
}
