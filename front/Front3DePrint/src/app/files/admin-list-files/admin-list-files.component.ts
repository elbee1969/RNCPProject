import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { UploadFileService } from 'src/app/services/upload-file.service';
import { Image } from 'src/app/model/Image';
import { OrderService } from 'src/app/services/order-service';
import { Order } from 'src/app/model/Order';
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
  orders: Order;

  constructor(private uploadService: UploadFileService,
              private orderService: OrderService,
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
     
    this.orderService.listOrder().subscribe(data => {
      this.orders = data;
      console.log("orders : " + JSON.stringify(data));

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
