import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpHeaders, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { TokenStorageService } from './token-storage.service';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class UploadFileService {

  private baseUrl = 'http://localhost:9090/api/image';


  constructor(private http: HttpClient, private router: Router, private tokenStorage: TokenStorageService) { }

  upload(file: File): Observable<HttpEvent<any>> {
    console.log("file : " + JSON.stringify(file));
    const formData: FormData = new FormData();
    formData.append('file', file);

    const req = new HttpRequest('POST', `${this.baseUrl}/upload`, formData, {
      reportProgress: true,
      responseType: 'json'
    });
     return this.http.request(req);
  }

  getImages(): Observable<any> {
    return this.http.get(`${this.baseUrl}/images`);
  }
  getOwnedImages(): Observable<any> {
    return this.http.get(`${this.baseUrl}/ownedimages`);
  }
  getChoosedImages(): Observable<any> {
    return this.http.get(`${this.baseUrl}/choosedimages`);
  }
  getCurrentFile(id) {
    return this.http.get(`${this.baseUrl}/image/${id}`);
  }
  updateImage(image: typeof Image, id: number): Observable<any> {
    console.log("in update image id : " + id);
    console.log("in update image : " + JSON.stringify(image));
    return this.http.patch<any>(`${this.baseUrl}/update/${id}`, image, httpOptions);
  }
  updateImageV(image: typeof Image, id: number): Observable<any> {

    return this.http.patch<any>(`${this.baseUrl}/updatevalidated/${id}`, image, httpOptions);
  }
  showCurrentImage(id: number){
    return this.http.get(`${this.baseUrl}/image/${id}`, {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + this.tokenStorage.getToken(),
        'Content-Type': 'application/vnd.ms-pki.stl'
      })
    })
  }

  delete(id){
    return this.http.delete(`${this.baseUrl}/deleteImage/${id}`);
  }
}