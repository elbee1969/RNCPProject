import { Component, OnInit, Input, ViewContainerRef, ViewChild, ComponentFactoryResolver } from '@angular/core';
import { UploadFileService } from 'src/app/services/upload-file.service';
import { ActivatedRoute, Router } from '@angular/router';
import { DomSanitizer } from '@angular/platform-browser';
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

   
  constructor(private uploadService: UploadFileService,
    private route: ActivatedRoute,
    private token: TokenStorageService, 
    private sanitizer: DomSanitizer,
    private router: Router,
    ) {}
    
    
    ngOnInit() {
      this.user = this.token.getUser().authorities[0];
      this.userName = this.token.getUser().user_name;
      if (this.user == "ROLE_USER") {
        this.role = true;
      }
      this.id = this.route.snapshot.params['id']
      console.log("id : "+this.id);
      console.log("username : "+this.userName);
      this.uploadService.showCurrentFile(this.id).subscribe(response => {
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
  backToImageslist() {
    if (this.user === "ROLE_ADMIN") {
    this.router.navigate(['/files']);
    } else if (this.user === "ROLE_USER") {
      this.router.navigate(['/upload']);  
    }
  }
  deleteFile(imageId: number) {
    const val = confirm('action irreversible !');
    if (val) {
      this.uploadService.delete(imageId)
      .subscribe(
        response => {
          console.log(response);
          this.router.navigate(['/upload']);
        },
        error => {
          console.log(error);
        });
    }  
  }
  toQuote(){
    this.router.navigate(['/files']);
  }
}
