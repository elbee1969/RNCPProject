import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AlertService } from '../services/alert.service';
import { AuthService } from '../services/auth.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  form: any = {};
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';

  constructor(private userService: UserService,
              private alertService: AlertService,
              private router: Router) { }

  ngOnInit() {
  }

  onSubmit() {
    this.userService.register(this.form).subscribe(
      data => {
        console.log("data : " + data);
        this.isSuccessful = true;
        this.isSignUpFailed = false;
        this.router.navigate(['/']);
        console.log("Enregistrement réussi");
      },
      err => {
        this.alertService.error(err);
        this.isSignUpFailed = true;
      }
    );
  }
}