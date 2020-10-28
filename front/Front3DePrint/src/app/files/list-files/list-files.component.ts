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
  constructor(private uploadService: UploadFileService, private router: Router) { 

  }
  selectedFile(event) {
    this.selectedFiles = event.target.files;

  }
  ngOnInit() {
    this.fileInfos = this.uploadService.getOwnedFiles();
  }
  imageDetail(id: number) {
    this.router.navigate(['/files/',id]);
  }
}
