import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-fourofour',
  templateUrl: './fourofour.component.html',
  styleUrls: ['./fourofour.component.css']
})
export class FourofourComponent implements OnInit {

  constructor( private router: Router) { }

  ngOnInit(): void {
  }
  return() {
      this.router.navigate(['/home']);
}
}
