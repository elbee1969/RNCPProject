import { Component, OnInit} from '@angular/core';
import { UploadFileService } from 'src/app/services/upload-file.service';
import { Observable } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { TokenStorageService } from 'src/app/services/token-storage.service';


@Component({
  selector: 'app-list-files',
  templateUrl: './list-files.component.html',
  styleUrls: ['./list-files.component.css']
})
export class ListFilesComponent implements OnInit {

  selectedFiles: FileList;

  fileInfos: Observable<any>;
  image: any;
  status: number;
  images: any;
  imageIs: any;
  imageCs: any;
 
  user: any;
  role: boolean;
  quantity: number;
  resetValue: any;
  id: any;
  
  constructor(private token: TokenStorageService,
              private uploadService: UploadFileService,
              private route: ActivatedRoute,
              private router: Router) { 
  }

selectedFile(event) {
  //this.selectedFiles = event.target.files;
  
}
ngOnInit() {


  this.id = this.route.snapshot.params['id'];
  this.user = this.token.getUser().authorities[0];

  if (this.user === "ROLE_ADMIN"){
    console.log("admin");
    this.role = true;
    this.uploadService.getImages().subscribe(result => {
      this.images = result;
      console.log("result : "+JSON.stringify(result));
       },
      error => console.log(error)
    );
  } else {
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
}  
