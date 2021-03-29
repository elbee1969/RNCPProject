import { Component, EventEmitter, Output } from '@angular/core';
import { TokenStorageService } from './services/token-storage.service';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '././services/auth.service';
const isLoggedIn = false;
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
 

  @Output() public sidenavToggle = new EventEmitter();
  
  constructor() {
  }


 
  

      
}
