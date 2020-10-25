import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class UploadFileService {

  private baseUrl = 'http://localhost:9090/api/private';


  constructor(private http: HttpClient, private router: Router) { }

  upload(file: File): Observable<HttpEvent<any>> {
    console.log("file : " + file);
    const formData: FormData = new FormData();
    formData.append('file', file);

    const req = new HttpRequest('POST', `${this.baseUrl}/upload`, formData, {
      reportProgress: true,
      responseType: 'json'
    });
     return this.http.request(req);
  }

  getFiles(): Observable<any> {
    return this.http.get(`${this.baseUrl}/images`);
  }
  getOwnedFiles(): Observable<any> {
    return this.http.get(`${this.baseUrl}/ownedImages`);
  }
  getCurrentFile(id) {
    return this.http.get(`${this.baseUrl}/image/${id}`);
  }

  delete(id){
    return this.http.delete(`${this.baseUrl}/files/${id}`);
  }
}