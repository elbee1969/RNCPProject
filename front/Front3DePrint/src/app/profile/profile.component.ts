import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from '../services/token-storage.service';
import { UserService } from '../services/user.service';

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
  id: number;
  user: any;
  present: boolean;
  address: any;

  constructor(private tokenStorageService: TokenStorageService,
              private router: Router,
              private userService: UserService) { }

  ngOnInit() {
    this.currentUser = this.tokenStorageService.getUser();
    this.id = this.currentUser.userId;
    console.log('current user : ' + JSON.stringify(this.currentUser.userId))
    console.log('getted token : ' + this.tokenStorageService.getToken());
    this.token = this.tokenStorageService.getToken()
    this.roles = this.tokenStorageService.getUser().authorities;
    this.username = this.tokenStorageService.getUser().user_name
    this.userService.getOne(this.id).subscribe(
      data => {
        this.user = data;
        this.address = this.user.address;
        console.log("data user : " + JSON.stringify(this.user));
        console.log("data addresse : " + JSON.stringify(this.address));
      },
      err => {
        this.user = JSON.parse(err.error).message;
      }
    );

    if (this.address.num == 0 || (this.address.street == null || this.address.town == null || this.address.country == null)){
      this.present = true;
    }
  }
  userUpdate(id: number) {
    this.router.navigate(['update', id]);
  }
}