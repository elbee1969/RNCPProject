import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-board-supervisor',
  templateUrl: './board-supervisor.component.html',
  styleUrls: ['./board-supervisor.component.css']
})
export class BoardSupervisorComponent implements OnInit {
  content = '';

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.userService.getSupervisorBoard().subscribe(
      data => {
        this.content = data;
      },
      err => {
        this.content = JSON.parse(err.error).message;
      }
    );
  }
}