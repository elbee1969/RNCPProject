import { Component, ErrorHandler, OnInit } from '@angular/core';
import { Router } from '@angular/router';
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
              private router: Router) { }

  ngOnInit() {
  }

  onSubmit() {
    this.userService.register(this.form).subscribe(
      data => {
        this.isSuccessful = true;
        this.isSignUpFailed = false;
        setTimeout(() => {
          this.router.navigate(['login']);
        }, 20000);
      
      },
      err => {
        this.errorMessage = ErrorHandlerService.catch(err);
        this.isSignUpFailed = true;
      }
    );
  }
}