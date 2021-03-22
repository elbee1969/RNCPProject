import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { BoardAdminComponent } from './board-admin/board-admin.component';
import { BoardUserComponent } from './board-user/board-user.component';
import { AuthInterceptor, authInterceptorProviders } from './helpers/auth.interceptor';
import { UploadFilesComponent } from './files/upload-files/upload-files.component';
import { ListFilesComponent } from './files/list-files/list-files.component';
import { ShowFileComponent } from './files/show-file/show-file.component';
import { UsersComponent } from './users/users.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { DetailsComponent } from './details/details.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ModalModule } from './modals/modal/modal.module';
import { UserUpdateComponent } from './user-update/user-update.component';
import { StlModelViewerModule } from 'angular-stl-model-viewer';
import { ThreeComponent } from './three/three.component';
import { UserService } from './services/user.service';
import { AdminListFilesComponent } from './files/admin-list-files/admin-list-files.component';
import { PrintImageComponent } from './files/print-image/print-image.component';
import { OrderService } from './services/order-service';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { HeaderComponent } from './navigation/header/header.component';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { RouterModule } from '@angular/router';
import { MatListModule, MatTabsModule } from '@angular/material';
import { FlexLayoutModule } from '@angular/flex-layout';
import { AuthGuard } from './helpers/auth.guard';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    ProfileComponent,
    BoardAdminComponent,
    BoardUserComponent,
    UploadFilesComponent,
    ListFilesComponent,
    ShowFileComponent,
    UsersComponent,
    DetailsComponent,
    UserUpdateComponent,
    ThreeComponent,
    AdminListFilesComponent,
    PrintImageComponent,
    HeaderComponent
  

  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    NgxPaginationModule,
    BrowserAnimationsModule,
    ModalModule,
    StlModelViewerModule,
    MatSidenavModule,
    MatToolbarModule,
    MatButtonModule,
    MatTabsModule,
    MatIconModule,
    MatListModule,
    FlexLayoutModule


  ],
   exports: [
    RouterModule,
    MatTabsModule,
    MatSidenavModule,
     MatToolbarModule,
    MatButtonModule,
     MatIconModule,
     MatListModule,
    
  ],
  providers: [UserService, {
    provide: HTTP_INTERCEPTORS,
    useClass: AuthInterceptor,
    multi: true
  },
  AuthGuard
  ],
  bootstrap: [AppComponent]
 
})
export class AppModule {
 }
