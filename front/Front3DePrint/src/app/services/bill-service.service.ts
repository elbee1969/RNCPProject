import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiResponse } from '../model/api.response';
import { Bill } from '../model/Bill';
import { Order } from '../model/Order';



const API_ORDER_URL = 'http://localhost:9090/api/bill';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class BillServiceService {

  constructor(private http: HttpClient) { }


  createOrder(bill: Bill, id: number, status: string): Observable<ApiResponse> {
    return this.http.post<ApiResponse>(API_ORDER_URL + `/${id}/${status}`, bill, httpOptions);
  }
}
