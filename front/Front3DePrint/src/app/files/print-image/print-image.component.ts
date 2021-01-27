import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { UploadFileService } from 'src/app/services/upload-file.service';
import { Image } from 'src/app/model/Image';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/model/user';
import { OrderService } from 'src/app/services/order-service';

@Component({
  selector: 'app-print-image',
  templateUrl: './print-image.component.html',
  styleUrls: ['./print-image.component.css']
})
export class PrintImageComponent implements OnInit {
  image: any;
  imageId: number;
  imageName: string;
  editForm: any;
  customUserId: number;
  user: User;
  userName: any;
  quantity: number;
  inputForm: any;
  heure: number;
  minute: number;
  seconde: number;
  obj: any = {};
  time: any;
  
  constructor(private route: ActivatedRoute,
              private orderService: OrderService,
              private router: Router,
              private uploadService: UploadFileService,
              private userService: UserService,
              private formBuilder: FormBuilder
    ) { }

  ngOnInit() {
    this.imageId = this.route.snapshot.params['id'];
    if (!this.imageId) {
      console.log("Invalid action.")
      this.router.navigate(['/adminfiles']);
      return;
    }
    console.log("id : " + this.imageId)
    
    this.uploadService.showCurrentImage(this.imageId).subscribe(result => {
    this.image = result;
    this.imageName = this.image.name;
      this.customUserId = this.image.customUserId;
    this.quantity = this.image.quantity;
      console.log("imageName : " + this.imageName);
      console.log("quantity : " + this.quantity);
    console.log ("image : " + JSON.stringify(this.image));
    
      this.userService.getOne(this.customUserId).subscribe(result => {
      this.user = result;
      console.log("user : " + JSON.stringify(this.user));
      this.userName = this.user.username;
      console.log("username : " + this.userName);
        
    })
    
    });
    this.editForm = this.formBuilder.group({
      status: ['A']
    });
    this.inputForm = this.formBuilder.group({
      customUserId: [],
      imageId: [],
      name: [],
      quantity: [],
      weight: ['', Validators.required],
      timeToPrint: ['', Validators.required],
      price: ['', Validators.required],
      status: ['I']
    });
  }

  onSubmit() {
    console.log("this.editForm.value " + JSON.stringify(this.editForm.value));
    console.log("this input form : " + JSON.stringify(this.inputForm.value));
    
    return this.uploadService.updateImageV(this.editForm.value, this.imageId)
      .subscribe(
        () => {
          console.log('Image updated successfully');
          this.create();
          this.router.navigate(['/adminfiles']);
        },
        error => {
          console.log(error);
        });

    
  }
  create(){
    return this.orderService.createOrder(this.inputForm.value)
      .subscribe(
        () => {
          console.log('Order created successfully');
          this.router.navigate(['/adminfiles']);
        },
        error => {
          console.log(error);
        });
  }

  back(){
    this.router.navigate(['/adminfiles']);
  }

  calculate(){
    const val1 = this.heure * 3600 * this.quantity;
    console.log("heure : " + this.heure);
    const val2 = this.minute * 60 * this.quantity;
    console.log("minute : " + this.minute);
    const val3 = this.seconde * this.quantity;
    console.log("seconde : " + this.seconde);
    const secs = val1 + val2 + val3;
    console.log("secondes : " + secs);
    this.secondsToTime(secs);
  }

  secondsToTime(secs) {
    var hours = Math.floor(secs / (60 * 60));

    var divisor_for_minutes = secs % (60 * 60);
    var minutes = Math.floor(divisor_for_minutes / 60);

    var divisor_for_seconds = divisor_for_minutes % 60;
    var seconds = Math.ceil(divisor_for_seconds);

    this.obj = {
      "h": hours + " heure(s)",
      "m": minutes + " minute(s)",
      "s": seconds + "seconde(s)"
    };
    console.log("time : " + this.time);
    console.log("OBJ : " + this.obj["h"] + this.obj["m"] + this.obj["s"]);
    this.time = JSON.stringify(this.obj['h']) + " " + JSON.stringify(this.obj['m']) + " " + JSON.stringify(this.obj['s']);
    this.time = this.time.replace(/"/g, "");

    console.log("time : " + this.time);
  }

}
