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
import { ThreeComponent } from './three/three.component';
import { AdminListFilesComponent } from './files/admin-list-files/admin-list-files.component';
import { PrintImageComponent } from './files/print-image/print-image.component';


const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent},
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent, canActivate: [AuthGuard]  },
  { path: 'user', component: BoardUserComponent, canActivate: [AuthGuard] },
  { path: 'users', component: UsersComponent, canActivate: [AuthGuard] },
  { path: 'update/:id', component: UserUpdateComponent, canActivate: [AuthGuard] },
  { path: 'details/:id', component: DetailsComponent, canActivate: [AuthGuard] },
  { path: 'upload', component: UploadFilesComponent, canActivate: [AuthGuard]},
  { path: 'files', component: ListFilesComponent, canActivate: [AuthGuard] },
  { path: 'adminfiles', component: AdminListFilesComponent, canActivate: [AuthGuard] },
  { path: 'image/:id', component: ShowFileComponent, canActivate: [AuthGuard] },
  { path: 'print/:id', component: PrintImageComponent, canActivate: [AuthGuard] },
  { path: 'admin', component: BoardAdminComponent, canActivate: [AuthGuard]  },
  { path: 'three/:id', component: ThreeComponent, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { relativeLinkResolution: 'legacy' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
