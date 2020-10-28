import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ProfileComponent } from './profile/profile.component';
import { BoardUserComponent } from './board-user/board-user.component';
import { BoardAdminComponent } from './board-admin/board-admin.component';
import { UploadFilesComponent } from './files/upload-files/upload-files.component';
import { ListFilesComponent } from './files/list-files/list-files.component';
import { ShowFileComponent } from './files/show-file/show-file.component';
import { AuthGuard } from './helpers/auth.guard';
import { UsersComponent } from './users/users.component';
import { DetailsComponent } from './details/details.component';
import { UserUpdateComponent } from './user-update/user-update.component';


const routes: Routes = [
  { path: 'home', component: HomeComponent},
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent  },
  { path: 'user', component: BoardUserComponent },
  { path: 'users', component: UsersComponent },
  { path: 'update/:id', component: UserUpdateComponent },
  { path: 'details/:id', component: DetailsComponent },
  { path: 'users/callback', component: UsersComponent },
  { path: 'upload', component: UploadFilesComponent},
  { path: 'upload/callback', component: UploadFilesComponent },
  { path: 'files', component: ListFilesComponent },
  { path: 'files/callback', component: ListFilesComponent },
  { path: 'files/:id', component: ShowFileComponent },
  { path: 'files/:id/callback', component: ShowFileComponent },
  { path: 'admin', component: BoardAdminComponent  },
  { path: '', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
