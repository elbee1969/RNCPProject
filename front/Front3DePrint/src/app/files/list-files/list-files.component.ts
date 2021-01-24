import { Component, OnInit} from '@angular/core';
import { UploadFileService } from 'src/app/services/upload-file.service';
import { Observable } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';


@Component({
  selector: 'app-list-files',
  templateUrl: './list-files.component.html',
  styleUrls: ['./list-files.component.css']
})
export class ListFilesComponent implements OnInit {

  selectedFiles: FileList;

  fileInfos: Observable<any>;
  status: number;
  imageIs: any;
  imageCs: any;
  quantity: number;
  resetValue: any;
  id: any;
  image: any;
  
  constructor(private uploadService: UploadFileService,
              private route: ActivatedRoute,
              private router: Router) { 
  }

selectedFile(event) {
  
}
ngOnInit() {


  this.id = this.route.snapshot.params['id'];



    console.log("pas admin!");
    this.uploadService.getOwnedImages().subscribe(result => {
      console.log("result "+JSON.stringify(result));
      this.imageIs = result;
    },
      error => console.log(error)
    );


    this.uploadService.getChoosedImages().subscribe(result => {
      console.log("result " + JSON.stringify(result));
      this.imageCs = result;
    },
      error => console.log(error)
    );
} 

  annulChoise(id: number) {
    this.image  = JSON.stringify({ status: "I", quantity: 1 });
    console.log("reset : " +this.image);
    return this.uploadService.updateImage(this.image, id)
      .subscribe(
        () => {
          console.log('Image updated successfully');
          this.router.navigate(['/files']);
        },
        error => {
          console.log(error);
        });
  }

  imageDetail(id: number) {
    this.router.navigate(['/image/',id]);
  }

  validation(id: number) {

    this.image = JSON.stringify({ status: "V"});

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
