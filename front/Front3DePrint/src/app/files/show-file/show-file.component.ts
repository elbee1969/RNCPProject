import { Component, OnInit, Input, ViewContainerRef, ViewChild, ComponentFactoryResolver } from '@angular/core';
import { UploadFileService } from 'src/app/services/upload-file.service';
import { ActivatedRoute, Router } from '@angular/router';
import { DomSanitizer } from '@angular/platform-browser';
import { FormControl } from '@angular/forms';
import * as THREE from 'three';

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
  
  @ViewChild('vc', { read: ViewContainerRef, static: false }) vc: ViewContainerRef;
  file = new FormControl('');
  imagePath: string;
  imgURL: any;
  camera = new THREE.PerspectiveCamera(35, window.innerWidth / window.innerHeight, 1, 15);
  stlfiles = "";
  _reload = false;
  stlmodels: string;
  
  
  
  
  constructor(private uploadService: UploadFileService,
    private route: ActivatedRoute,
    private router: Router,
    private sanitizer: DomSanitizer,
    private componentFactoryResolver: ComponentFactoryResolver,
    private _vcr: ViewContainerRef,
    private viewContainerRef: ViewContainerRef
    
    
    
    ) { 
      
    }
    
    
    ngOnInit() {
      
      this.id = this.route.snapshot.params['id']
      this.uploadService.getCurrentFile(this.id).subscribe(response => {
        this.image = response;
        console.log('file infos : ' + JSON.stringify(response));
        console.log('file infos data : ' + this.image.name);
        this.thumbnail = this.image.name;
        // this.base64Data = this.image.data;
        // this.image = 'data:image/jpeg;base64,' + this.base64Data;
        //  this.thumbnail = this.sanitizer.bypassSecurityTrustUrl(this.image);
        // this.stlfiles = this.thumbnail;

        debugger;
        this.stlfiles.slice(0)
        const componentFactory = this.componentFactoryResolver.resolveComponentFactory(ShowFileComponent);

        const viewContainerRef = this.vc;
        viewContainerRef.clear();

        const componentRef = viewContainerRef.createComponent(componentFactory);
        (componentRef.instance).stlmodels = this.stlfiles;
        
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
