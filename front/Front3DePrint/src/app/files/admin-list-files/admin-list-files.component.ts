import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { UploadFileService } from 'src/app/services/upload-file.service';
import { Image } from 'src/app/model/Image';
import { OrderService } from 'src/app/services/order-service';
import { Order } from 'src/app/model/Order';
import { FormBuilder } from '@angular/forms';
import { ThrowStmt } from '@angular/compiler';
import { JsonpClientBackend } from '@angular/common/http';
import { BillServiceService } from 'src/app/services/bill-service.service';
@Component({
  selector: 'app-admin-list-files',
  templateUrl: './admin-list-files.component.html',
  styleUrls: ['./admin-list-files.component.css']
})
export class AdminListFilesComponent implements OnInit {

  selectedFiles: FileList;
  clientId: number;
  fileInfos: Observable<any>;
  image: any;
  status: string;
  images: Image;
  editForm: any;
  role: boolean;
  quantity: number;
  resetValue: any;
  id: any;
  orders: Order;
  taille: any;
  data: any;
  dataB: any;
  order: any;

  constructor(private uploadService: UploadFileService,
              private orderService: OrderService,
              private billService: BillServiceService,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private router: Router) {
  }

  selectedFile(event) {

  }
  ngOnInit() {
    
    this.id = this.route.snapshot.params['id'];
    this.uploadService.getImages("V").subscribe(result => {
      this.images = result;
      console.log("result : " + JSON.stringify(result));
    },
      error => console.log(error)
    );
     
    this.orderService.listOrders("I").subscribe(data => {
      this.orders = data;
      console.log("orders : " + JSON.stringify(data));
    },
      error => console.log(error)
    );

  }


  validateOrder(id, status){
  
    return this.orderService.getOrders(id, status)
    .subscribe(
      (result) => {
        this.data = JSON.stringify(result);
        this.data = result;
        // get the number of orders
        let nb = 0;
        for (let i in result) {
          nb++;
          console.log("key nbr :" + i);
          console.log("id value : " + result[i].id);
          console.log("status value : " + result[i].status);
          console.log("userid value : " + result[i].customUserId);        
        }
        console.log(" keys by record : " + nb);
        
        // modify orders status from I to C
        
            this.order = JSON.stringify({ status: "C" });
        for (let i = 0 ; i < nb; i++){       
          this.orderService.updateOrder(this.order, result[i].id)
          .subscribe(
            () => {
              console.log('order ' + result[i].id + ' updated successfully');
            },
            error => {
              console.log(error);
            });
        }
        console.log("cpt : " + nb);
        console.log('get order successfully');
        console.log('result : ' + JSON.stringify(result));
        this.ngOnInit();
        this.reloadPage();
        
      },
      error => {
        console.log(error);
      });
  }

  printImage(id: number) {
    this.router.navigate(['/print/', id]);
  }

 
reloadPage() {
  window.location.reload();
  };

  isEmptyObject(obj) {
    return (obj && (Object.keys(obj).length === 0));
  }
}
