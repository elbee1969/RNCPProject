import { Component, OnInit, Input, ViewContainerRef, ViewChild, ComponentFactoryResolver } from '@angular/core';
import { UploadFileService } from 'src/app/services/upload-file.service';
import { ActivatedRoute, Router } from '@angular/router';
import { DomSanitizer } from '@angular/platform-browser';
import { FormControl } from '@angular/forms';
import * as THREE from 'three';

import { TokenStorageService } from 'src/app/services/token-storage.service';

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
   
  constructor(private uploadService: UploadFileService,
    private route: ActivatedRoute,
    private token: TokenStorageService, 
    private router: Router,
    ) {}
    
    
    ngOnInit() {
      this.user = this.token.getUser().authorities[0];
      this.id = this.route.snapshot.params['id']
      this.uploadService.getCurrentFile(this.id).subscribe(response => {
        this.image = response;
        this.imageName = this.image.name;
        }) ,
        error => {
          console.log(error);
        }

  }
  backToImageslist() {
    if (this.user === "ROLE_ADMIN") {
    this.router.navigate(['/files']);
    } else {
      this.router.navigate(['/upload']);  
    }
  }
  deleteFile() {
 /*   this.uploadService.delete(this.fileInfos)
      .subscribe(
        response => {
          console.log(response);
          this.router.navigate(['/files']);
        },
        error => {
          console.log(error);
        });*/
  }
  acceptPrice(){
    this.router.navigate(['/user']);
  }
}
