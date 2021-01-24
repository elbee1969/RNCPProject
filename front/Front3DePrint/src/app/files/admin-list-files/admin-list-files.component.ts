import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { UploadFileService } from 'src/app/services/upload-file.service';
import { Image } from 'src/app/model/Image';
@Component({
  selector: 'app-admin-list-files',
  templateUrl: './admin-list-files.component.html',
  styleUrls: ['./admin-list-files.component.css']
})
export class AdminListFilesComponent implements OnInit {

  selectedFiles: FileList;

  fileInfos: Observable<any>;
  image: any;
  status: number;
  images: Image;
  role: boolean;
  quantity: number;
  resetValue: any;
  id: any;

  constructor(private uploadService: UploadFileService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  selectedFile(event) {

  }
  ngOnInit() {


    this.id = this.route.snapshot.params['id'];
    this.uploadService.getImages().subscribe(result => {
      this.images = result;
      console.log("result : " + JSON.stringify(result));
    },
      error => console.log(error)
    );
      
  }

  printImage(id: number) {
    this.router.navigate(['/print/', id]);
  }

  validation(id: number) {
    this.image = JSON.stringify({ status: "V" });
    return this.uploadService.updateImageV(this.image, id)
      .subscribe(
        () => {
          console.log('Image updated successfully');
          this.router.navigate(['/files']);
        },
        error => {
          console.log(error);
        });
  }



}
