import { HttpClient, HttpEvent, HttpHeaders, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiResponse } from '../model/api.response';
import { Order } from '../model/Order';
import { Status } from '../model/status';

const API_ORDER_URL = 'http://localhost:9090/api/orders';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  updateOrder(order: Order, id: number) {
    return this.http.patch<Order>(API_ORDER_URL + `/updateorder/${id}`, order, httpOptions);
  }

  constructor(private http: HttpClient) { }

  createOrder(order: Order): Observable<Order> {
    return this.http.post<Order>(API_ORDER_URL, order, httpOptions);
  }

  listOrders(status: String): Observable<Order> {
    return this.http.get<Order>(API_ORDER_URL + `/vieworders/${status}`);

  }

  listOrdersA(): Observable<Order> {
    return this.http.get<Order>(API_ORDER_URL + '/viewordersA');

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

  deleteOrder(id) {
    return this.http.delete(API_ORDER_URL + `/deleteorder/${id}`);
  }
}
