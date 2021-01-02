import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Address } from '../model/address';
import { delayWhen, first, map, retryWhen, shareReplay, tap } from "rxjs/operators";
import { UserService } from '../services/user.service';
import { HttpXhrBackend } from '@angular/common/http';

@Component({
  selector: 'app-user-update',
  templateUrl: './user-update.component.html',
  styleUrls: ['./user-update.component.css']
})
export class UserUpdateComponent implements OnInit {
  address: Address;
  editForm: FormGroup;
  id: number;
  numero: number;
  
  constructor(private router: Router,
              private route: ActivatedRoute,
              private userService: UserService,
              private formBuilder: FormBuilder,) { }

  ngOnInit() {
    this.id = this.route.snapshot.params['id'];
    if (!this.id) {
      console.log("Invalid action.")
      this.router.navigate(['/profile']);
      return;
    }
    console.log("this.id : " + this.id);
    this.editForm = this.formBuilder.group({
      num: ['', Validators.required],
      street: ['', Validators.required],
      town: ['', Validators.required],
      country: ['', Validators.required]
    });
    this.userService.getAddressById(this.id)
      .subscribe(data => {
        this.address = data;
        this.numero = data.num;
        console.log("address data : " + JSON.stringify(data));
        console.log("address num Jst : " + JSON.stringify(data.num));
        console.log("address num : " + data.num);
        this.editForm.setValue(data.result);
    });
}

  onSubmit() {
    console.log("this.editForm.value " + JSON.stringify(this.editForm.value));
    return this.userService.updateAddress(this.editForm.value, this.id)
      .subscribe(
        () => {
          console.log('Address updated successfully');
          this.router.navigate(['/profile']);
        },
        error => {
          console.log(error);
        });

      // .subscribe(
      //   res => console.log('HTTP response', res),
      //   err => console.log('HTTP Error', err),
      //   () => console.log('HTTP request completed.')
      // );
  }

  backToProfil() {
    this.router.navigate(['/profile']);
  }

}
