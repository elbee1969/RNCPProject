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
  address: Address;
  editForm: FormGroup;
  id: any;
  
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
    this.editForm = this.formBuilder.group({
      id: [''],
      num: ['', Validators.required],
      street: ['', Validators.required],
      town: ['', Validators.required],
      country: ['', Validators.required]
    });
    this.userService.getAddressById(this.id)
      .subscribe(adressData => {
        console.log("address data : " + JSON.stringify(adressData));
        this.editForm.setValue(adressData.result);
    });
}

  onSubmit() {
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
