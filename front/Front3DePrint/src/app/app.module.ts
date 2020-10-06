import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { BoardAdminComponent } from './board-admin/board-admin.component';
import { BoardUserComponent } from './board-user/board-user.component';
import { JwPaginationComponent } from 'jw-angular-pagination';
import { authInterceptorProviders } from './helpers/auth.interceptor';
import { UploadFilesComponent } from './files/upload-files/upload-files.component';
import { ListFilesComponent } from './files/list-files/list-files.component';
import { ShowFileComponent } from './files/show-file/show-file.component';
import { UsersComponent } from './users/users.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { DetailsComponent } from './details/details.component';


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
    JwPaginationComponent,
    DetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgxPaginationModule


  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
