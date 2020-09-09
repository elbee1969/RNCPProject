import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { TokenStorageService } from '../services/token-storage.service';

@Component({
  selector: 'app-board-user',
  templateUrl: './board-user.component.html',
  styleUrls: ['./board-user.component.css']
})
export class BoardUserComponent implements OnInit {
  content = '';
  username: any;

  constructor(private userService: UserService, private tokenStorageService: TokenStorageService) {

   }

  ngOnInit() {
    this.userService.getUserBoard().subscribe(
      data => {
        this.content = data;
        console.log('data in dashboard : ' + JSON.parse(data)[1]);
        this.username = this.tokenStorageService.getUser().user_name
      },
      err => {
        this.content = JSON.parse(err.error).message;
      }
    );
  }

}