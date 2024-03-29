import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Address } from '../model/address';
import { User } from '../model/user';
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
  token: string;
  id: number;
  user: User;
  address: Address;

  constructor(private tokenStorageService: TokenStorageService,
              private router: Router,
              private userService: UserService) { }

  ngOnInit() {
    this.currentUser = this.tokenStorageService.getUser();
    this.id = this.currentUser.userId;
    console.log('current user all : ' + JSON.stringify(this.currentUser))
    console.log('current user id : ' + JSON.stringify(this.currentUser.userId))
    console.log('getted token : ' + this.tokenStorageService.getToken());
    this.userService.getOne(this.id).subscribe(
      (user: User) => {
        this.user = user;

        console.log("data user : " + JSON.stringify(this.user));
        console.log("data addresse : " + this.user.address.num);
        console.log("username : " + this.user.username);
      },
      err => {
        this.user = JSON.parse(err.error).message;
      }
    );

  }
  userUpdate(id: number) {
    this.router.navigate(['update', id]);
  }
}