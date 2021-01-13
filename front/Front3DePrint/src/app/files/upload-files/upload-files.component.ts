import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { UploadFileService } from 'src/app/services/upload-file.service';
import { HttpEventType, HttpResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { DomSanitizer } from '@angular/platform-browser';
import { TokenStorageService } from 'src/app/services/token-storage.service';

@Component({
  selector: 'app-Upload-files',
  templateUrl: './Upload-files.component.html',
  styleUrls: ['./Upload-files.component.css']
})
export class UploadFilesComponent implements OnInit {

  selectedFiles: FileList;
  event1;
  imgURL: any;
  currentFile: File;
  progress = 0;
  message = '';

  fileInfos: Observable<any>;
  base64Data: any;
  convertedImage: any;
  user: any;
  role: boolean;

  constructor(private uploadService: UploadFileService, 
              private router: Router,
              private domSanitizer: DomSanitizer,
              private token: TokenStorageService,) { 
  }
  ngOnInit() {
    this.fileInfos = this.uploadService.getOwnedImages();
    this.user = this.token.getUser().authorities[0];
     if (this.user == "ROLE_USER") {
      this.role = true;
    }


    
  }
  public selectFile(event) {
    this.selectedFiles = event.target.files;

    let reader = new FileReader();
    reader.readAsDataURL(event.target.files[0]);
    reader.onload = (event1) => {
      this.imgURL = reader.result;
    }
  }

  upload() {

    this.progress = 0;
    this.currentFile = this.selectedFiles.item(0);
    console.log("current file : " + JSON.stringify(this.currentFile));
    

    this.uploadService.upload(this.currentFile).subscribe(
      event => {
        console.log("event req : " + JSON.stringify(event));
        if (event.type === HttpEventType.UploadProgress) {
          this.progress = Math.round(100 * event.loaded / event.total);
        } else if (event instanceof HttpResponse) {
          this.message = event.body.message;
          this.fileInfos = this.uploadService.getOwnedImages();
          if (this.role) {
            this.router.navigate(['/upload']);
          } else {
            this.router.navigate(['/files']);
          }
        }
       
      },
      err => {
        console.log(JSON.stringify(err));
        this.message = 'Impossible à charger : ' + err.error.message;
        this.currentFile = undefined;
        this.progress = 0;
        if (this.role) {
          this.router.navigate(['/upload']);
        }
      },

      );

    this.selectedFiles = undefined;
  }



}