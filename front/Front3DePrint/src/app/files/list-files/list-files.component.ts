import { Component, OnInit} from '@angular/core';
import { UploadFileService } from 'src/app/services/upload-file.service';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list-files',
  templateUrl: './list-files.component.html',
  styleUrls: ['./list-files.component.css']
})
export class ListFilesComponent implements OnInit {

  selectedFiles: FileList;

  fileInfos: Observable<any>;
  images: any;
  constructor(private uploadService: UploadFileService, private router: Router) { 

  }
  selectedFile(event) {
    //this.selectedFiles = event.target.files;

  }
  ngOnInit() {
    console.log('in NGonINIT');
    this.uploadService.getOwnedFiles().subscribe(result => {
      console.log(result);
      this.images =result;
    },
      error => console.log(error)
      );
  //  / console.log("file info : " + this.fileInfos);
  }
  imageDetail(id: number) {
    this.router.navigate(['/image/',id]);
  }
}
