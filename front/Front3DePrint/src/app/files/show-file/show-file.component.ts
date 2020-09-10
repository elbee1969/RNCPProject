import { Component, OnInit } from '@angular/core';
import { UploadFileService } from 'src/app/services/upload-file.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-show-file',
  templateUrl: './show-file.component.html',
  styleUrls: ['./show-file.component.css']
})
export class ShowFileComponent implements OnInit {
  currentFile = null;

  constructor(private router: Router,private uploadService: UploadFileService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.getFile(this.route.snapshot.paramMap.get('id'));
  }
  getFile(id) {
    this.uploadService.selectedFile(id)
      .subscribe(
        data => {
          this.currentFile = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }

  deleteFile() {
    this.uploadService.delete(this.currentFile.id)
      .subscribe(
        response => {
          console.log(response);
          this.router.navigate(['/files']);
        },
        error => {
          console.log(error);
        });
  }

}
