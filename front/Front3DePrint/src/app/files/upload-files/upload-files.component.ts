import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { UploadFileService } from 'src/app/services/upload-file.service';
import { HttpEventType, HttpResponse } from '@angular/common/http';

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

  constructor(private uploadService: UploadFileService) { 
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
    console.log("current file : " + this.currentFile);
    

    this.uploadService.upload(this.currentFile).subscribe(
      event => {
        console.log("event req : " + event);
        if (event.type === HttpEventType.UploadProgress) {
          this.progress = Math.round(100 * event.loaded / event.total);
        } else if (event instanceof HttpResponse) {
          this.message = event.body.message;
          this.fileInfos = this.uploadService.getFiles();
        }
      },
      err => {
        this.progress = 0;
        this.message = 'Could not upload the file! : ' + err;
        this.currentFile = undefined;
      });

    this.selectedFiles = undefined;
  }

  ngOnInit() {
    this.fileInfos = this.uploadService.getFiles();
  }

}