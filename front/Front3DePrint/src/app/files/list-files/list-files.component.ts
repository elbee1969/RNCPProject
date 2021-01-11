import { Component, OnInit} from '@angular/core';
import { UploadFileService } from 'src/app/services/upload-file.service';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/app/services/token-storage.service';

@Component({
  selector: 'app-list-files',
  templateUrl: './list-files.component.html',
  styleUrls: ['./list-files.component.css']
})
export class ListFilesComponent implements OnInit {

  selectedFiles: FileList;

  fileInfos: Observable<any>;
  images: any;

  user: any;
  role: boolean;
  constructor(private token: TokenStorageService, private uploadService: UploadFileService, private router: Router) { 
  }

selectedFile(event) {
  //this.selectedFiles = event.target.files;
  
}
ngOnInit() {
  this.user = this.token.getUser().authorities[0];

  if (this.user === "ROLE_ADMIN"){
    console.log("admin");
    this.role = true;
    this.uploadService.getFiles().subscribe(result => {
      this.images = result;
      console.log("result : "+JSON.stringify(result));
      this.images
    },
      error => console.log(error)
    );
  } else {
    console.log("pas admin!");
    this.uploadService.getOwnedFiles().subscribe(result => {
      console.log("result "+JSON.stringify(result));
      this.images = result;
    },
      error => console.log(error)
    );

  }
} 

  imageDetail(id: number) {
    this.router.navigate(['/image/',id]);
  }
}  
