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
import { FourofourComponent } from './fourofour/fourofour.component';


const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent},
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent, canActivate: [AuthGuard], data: { roles: ["ROLE_USER"] }  },
  { path: 'user', component: BoardUserComponent, canActivate: [AuthGuard], data: { roles: ["ROLE_USER"] } },
  { path: 'users', component: UsersComponent, canActivate: [AuthGuard], data: { roles: ["ROLE_ADMIN"] } },
  { path: 'update/:id', component: UserUpdateComponent, canActivate: [AuthGuard], data: { roles: ["ROLE_USER"] } },
  { path: 'details/:id', component: DetailsComponent, canActivate: [AuthGuard], data: { roles: ["ROLE_ADMIN"] } },
  { path: 'upload', component: UploadFilesComponent, canActivate: [AuthGuard], data: { roles: ["ROLE_USER"] }},
  { path: 'files', component: ListFilesComponent, canActivate: [AuthGuard], data: { roles: ["ROLE_USER"] } },
  { path: 'adminfiles', component: AdminListFilesComponent, canActivate: [AuthGuard], data: { roles: ["ROLE_ADMIN"] } },
  { path: 'image/:id', component: ShowFileComponent, canActivate: [AuthGuard], data: { roles: ["ROLE_USER"] } },
  { path: 'print/:id', component: PrintImageComponent, canActivate: [AuthGuard], data: { roles: ["ROLE_ADMIN"] } },
  { path: 'admin', component: BoardAdminComponent, canActivate: [AuthGuard], data: { roles: ["ROLE_ADMIN"] }  },
  { path: 'three/:id', component: ThreeComponent, canActivate: [AuthGuard], data: { roles: ["ROLE_USER"] } },
  { path: 'FourofourComponent', component: FourofourComponent },  // Wildcard route for a 404 page
  { path: '**', redirectTo: '/FourofourComponent', pathMatch: "full" }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { relativeLinkResolution: 'legacy' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
