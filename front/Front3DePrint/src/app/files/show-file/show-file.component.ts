import { Component, OnInit, Input } from '@angular/core';
import { UploadFileService } from 'src/app/services/upload-file.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-show-file',
  templateUrl: './show-file.component.html',
  styleUrls: ['./show-file.component.css']
})
export class ShowFileComponent implements OnInit {
  id: any;
  currentFile: any;
  image: any;
  constructor(private uploadService: UploadFileService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
      this.id = this.route.snapshot.params['id']
      this.uploadService.getCurrentFile(this.id).subscribe(response => {
        this.image = response;
        console.log('file infos : ' + response);
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
