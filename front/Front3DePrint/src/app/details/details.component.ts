import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})
export class DetailsComponent implements OnInit {
  user: any;
  id: any;
  address: any;

  constructor(private router: Router, private route: ActivatedRoute,private userService: UserService) { }

  ngOnInit() {

    this.id = this.route.snapshot.params['id'];
 
    this.userService.getOne(this.id).subscribe(
      data => {
        this.user = data;
        this.address = this.user.address;
        console.log("data : " + JSON.stringify(this.user));
      },
      err => {
        this.user = JSON.parse(err.error).message;
      }
    );
  }
  


  

  backToUserlist() {
    this.router.navigate(['/users']);
  }
}
