import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ErrorHandlerService {

  constructor() { }

  static catch(error: any) {
    let message = "";
    const errors = error.error.errors;
    errors.forEach((err: any) => message += err +  ".  " );
    return message;
  }
}
