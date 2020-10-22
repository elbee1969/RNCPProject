import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-update',
  templateUrl: './user-update.component.html',
  styleUrls: ['./user-update.component.css']
})
export class UserUpdateComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }
  backToProfil() {
    this.router.navigate(['/profile']);
  }

}
