import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiResponse } from '../model/api.response';
import { Bill } from '../model/Bill';
import { Order } from '../model/Order';



const API_BILL_URL = 'http://localhost:9090/api/bills';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class BillServiceService {

  constructor(private http: HttpClient) { }


  createBill(id: number, status: string): Observable<Bill> {
    return this.http.get<Bill>(API_BILL_URL + `/create/${id}/${status}`);
  }

  getBill(id: number, status: string): Observable<Bill> {
    return this.http.get<Bill>(API_BILL_URL + `/bill/${id}/${status}`);
  }

  listBills(): Observable<Bill> {
    return this.http.get<Bill>(API_BILL_URL + '/viewbills');

  }
}
