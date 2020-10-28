import { Component, OnInit, Input } from '@angular/core';
import { UploadFileService } from 'src/app/services/upload-file.service';
import { ActivatedRoute, Router } from '@angular/router';
import { DomSanitizer } from '@angular/platform-browser';
// import { stlViewer } from 'src/stlviewer/stl_viewer.min.js';

@Component({
  selector: 'app-show-file',
  templateUrl: './show-file.component.html',
  styleUrls: ['./show-file.component.css']
})
export class ShowFileComponent implements OnInit {
  id: any;
  currentFile: any;
  image: any;
  thumbnail: any;
  base64Data: any;
  constructor(private uploadService: UploadFileService, private route: ActivatedRoute, private router: Router, private sanitizer: DomSanitizer) { }

  ngOnInit() {
      this.id = this.route.snapshot.params['id']
      this.uploadService.getCurrentFile(this.id).subscribe(response => {
        this.image = response;
        console.log('file infos : ' + JSON.stringify(response));
        console.log('file infos data : ' + this.image);
        this.base64Data = this.image.data;
        this.image = 'data:image/jpeg;base64,' + this.base64Data;

        this.thumbnail = this.sanitizer.bypassSecurityTrustUrl(this.image);
        // var stl_viewer = new stlViewer(document.getElementById("stl_cont"), { models: [{ id: 0, filename: this.image.name }] });

      },
        error => {
          console.log(error);
        }
      )

  }
  backToImageslist() {
    this.router.navigate(['/files']);
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

}
