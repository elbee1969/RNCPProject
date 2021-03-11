import { Component, ErrorHandler, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AlertService } from '../services/alert.service';
import { AuthService } from '../services/auth.service';
import { UserService } from '../services/user.service';
import { ErrorHandlerService } from '../services/error-handler.service';

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
        this.router.navigate(['login']);
        console.log("Enregistrement réussi");
      },
      err => {
       this.errorMessage = ErrorHandlerService.catch(err);
        console.log(this.errorMessage);
        this.errorMessage = this.errorMessage.slice(22);
        this.isSignUpFailed = true;
      }
    );
  }
}