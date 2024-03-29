import { Component, OnInit, Input, ViewContainerRef, ViewChild, ComponentFactoryResolver } from '@angular/core';
import { UploadFileService } from 'src/app/services/upload-file.service';
import { ActivatedRoute, Router } from '@angular/router';
import { DomSanitizer } from '@angular/platform-browser';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FormControl } from '@angular/forms';
import * as THREE from 'three';

import { TokenStorageService } from 'src/app/services/token-storage.service';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-show-file',
  templateUrl: './show-file.component.html',
  styleUrls: ['./show-file.component.css']
})
export class ShowFileComponent implements OnInit {
   image: any;
   imageName: any;
  id: any;
  user: any;
  role: boolean;
  base64Data: any;
  thumbnail: any;
  imagePath: any;
  imageId: any;
  retrieveImage: string;
  userName: any;
  path: any;
  editForm: any;


   
  constructor(private uploadService: UploadFileService,
    private route: ActivatedRoute,
    private token: TokenStorageService, 
    private imageService: UploadFileService,
    private router: Router,
    private formBuilder: FormBuilder
    ) {}
    
    
    ngOnInit() {
      this.id = this.route.snapshot.params['id'];
      console.log("id in init: " + this.id);
      this.user = this.token.getUser().authorities[0];
      this.userName = this.token.getUser().user_name;

        this.editForm = this.formBuilder.group({
          status: ['C'],
          quantity: ['', Validators.required]
        });
       
      console.log("id : "+this.id);
      console.log("username : "+this.userName);
      this.uploadService.showCurrentImage(this.id).subscribe(response => {
        this.image = response;
        console.log("this image : " + JSON.stringify(this.image));
        this.imageName = this.image.name;
        console.log('image name : ' + this.imageName);
        this.imageId = this.image.id
        console.log('image id ' + this.imageId);

        this.path = ("'./assets/uploads/" + this.userName + "/"+this.imageName+"'");
        console.log("path : "+ this.path);
        }) ,
        error => {
          console.log(error);
        }
  }

  onSubmit() {
    console.log("this.editForm.value " + JSON.stringify(this.editForm.value));
    return this.uploadService.updateImageStatusAndQuantity(this.editForm.value, this.id)
      .subscribe(
        () => {
          console.log('Image updated successfully');
          this.router.navigate(['/upload']);
        },
        error => {
          console.log(error);
        });
  }

  backToImageslist() {
      this.router.navigate(['/upload']);  
  }

  deleteFile(imageId: number) {
    const val = confirm('action irreversible !');
    if (val) {
      this.uploadService.delete(imageId)
      .subscribe(
        response => {
          this.image  = JSON.stringify({ status: "O", quantity: 1 });
          console.log("reset : " +this.image);
          return this.imageService.updateImageStatusAndQuantity(this.image, imageId)
          .subscribe(
            () => {
              console.log('Image updated successfully');
              this.ngOnInit();
            },
            error => {
              console.log(error);
            });            
          console.log(response);
        },
        error => {
          console.log(error);
        });
        this.router.navigate(['/upload']);
    }  
  }
}
