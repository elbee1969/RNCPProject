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
  data: Order;

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

    this.editForm = this.formBuilder.group({
      status: ['C']
    });

  }


  onSubmit(){
  
    return this.orderService.getOrders(this.clientId, this.status)
    .subscribe(
      (result) => {
        //this.data = JSON.stringify(result);
        this.data = result;
        let nb = 0;
        for (let i in result) {
          nb++;
          console.log("key nbr :" + i);
          console.log("id value : " + result[i].id);
          console.log("status value : " + result[i].status);
        }
        console.log(" keys by record : " + nb);
        for (let i = 0 ; i < nb; i++){       
          this.orderService.updateOrder(this.editForm.value, result[i].id)
          .subscribe(
            () => {
              console.log('order ' + result[i].id + ' updated successfully');
              return;
            },
            error => {
              console.log(error);
            });
        }
        console.log("cpt : " + nb);
        console.log('get order successfully');
        console.log('result : ' + JSON.stringify(result));
        this.createBill();


      },
      error => {
        console.log(error);
      });
    console.log ("id clientinit : " + this.clientId);
    console.log("status : " + this.status);
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

validateOrder(id: number, status: string) {
  console.log ("id client : " + id);
  console.log("status order : " + status);
  this.clientId = id;
  this.status = status;
}

createBill(){
console.log("bill to create");
  console.log('client id : ' + this.clientId);
  console.log(' order status : ' + this.status);
  this.reloadPage();
  
}

reloadPage() {
  window.location.reload();
  };

  isEmptyObject(obj) {
    return (obj && (Object.keys(obj).length === 0));
  }
}
