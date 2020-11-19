import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Address } from '../model/address';
import { first } from "rxjs/operators";
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-user-update',
  templateUrl: './user-update.component.html',
  styleUrls: ['./user-update.component.css']
})
export class UserUpdateComponent implements OnInit {
  address: any;
  editForm: FormGroup;
  id: any;
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
      id: [''],
      num: ['', Validators.required],
      street: ['', Validators.required],
      town: ['', Validators.required],
      country: ['', Validators.required]
    });
    this.userService.getAddressById(+this.id)
      .subscribe(addressData => {
        this.address = addressData;
        this.numero = addressData.num;
        console.log("address data : " + JSON.stringify(addressData));
        console.log("address num Jst : " + JSON.stringify(addressData.num));
        console.log("address num : " + addressData.num);
        console.log("form values : " + this.editForm.setValue(this.address.result));
        this.editForm.setValue(addressData.result);
    });
}

  onSubmit() {
    console.log("this.editForm.value " + JSON.stringify(this.editForm.value));
    this.userService.updateAddress(this.editForm.value)
      .pipe(first())
      .subscribe(
        data => {
          if (data.status === 200) {
            console.log('Address updated successfully.');
            this.router.navigate(['/profile']);
          } else {
            console.log(data.message);
          }
        },
        error => {
          console.log(error);
        });
  }

  backToProfil() {
    this.router.navigate(['/profile']);
  }

}
