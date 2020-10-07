import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { User } from '../model/user';
import { ModalService } from '../services/modal.service';

import { UserService } from '../services/user.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {
  config: any;
  collection = [];
  usersInfos: Observable<any>;
  users: any;
  taille: number;
  bodyText: string;

  constructor(private userService: UserService,
              private route: ActivatedRoute,
              private router: Router,
              private modalService: ModalService) {
    this.config = {
      currentPage: 1,
      itemsPerPage: 10,
      totalItems: this.taille
   }
    // an example array of 150 items to be paged
    route.queryParams.subscribe(
      users => this.config.currentPage = users['page'] ? users['page'] : 1);

    for (let i = 1; i <= this.taille; i++) {
      this.collection.push(this.users);
    }
  }
  ngOnInit() {
    this.reloadData();
    this.bodyText = 'Cette action est irréversible ...';
  }
reloadData(){
    this.userService.getAll()
      .subscribe(data => {
        this.users = data;
        this.taille = Object.keys(data).length;
      });
  }
  pageChange(newPage: number) {
    this.router.navigate(['/users/callback'], { queryParams: { page: newPage } });
}
  deleteUser(id: number) {
    this.userService.delete(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }

  userDetails(id: number) {
    this.router.navigate(['/details', id]);
  }

  openModal(id: string) {
    this.modalService.open(id);
  }

  closeModal(id: string) {
    this.modalService.close(id);
  }
}