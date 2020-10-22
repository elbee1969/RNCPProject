import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from '../services/token-storage.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  currentUser: any;
  roles: any;
  username: any;
  token: string;

  constructor(private tokenStorageService: TokenStorageService, private router: Router) { }

  ngOnInit() {
    this.currentUser = this.tokenStorageService.getUser();
    console.log('current user : ' + JSON.stringify(this.currentUser.userId))
    console.log('getted token : ' + this.tokenStorageService.getToken());
    this.token = this.tokenStorageService.getToken()
    this.roles = this.tokenStorageService.getUser().authorities;
    this.username = this.tokenStorageService.getUser().user_name
  }
  userUpdate(id: number) {
    this.router.navigate(['update', id]);
  }
}