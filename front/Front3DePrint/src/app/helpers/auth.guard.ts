import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth.service';
import { TokenStorageService } from '../services/token-storage.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(
    private router: Router,
    private tokenStorage: TokenStorageService,
    private authService: AuthService
  ) { }


  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    // check if logged
    if (!this.tokenStorage.getUser()) {
      this.router.navigate(['/login']);
    } else {
      const currentUser = this.tokenStorage.getUser().authorities;
      console.log("cur usr role : " + currentUser.toString());
      const r1 = currentUser.toString();
      console.log("cur usr route role : " + route.data.roles.toString());
      const r2 = route.data.roles.toString()
    if (currentUser) {
            // check if route is restricted by role
      if (route.data.roles && route.data.roles.indexOf(currentUser.role) === -1) {
        if (r1 !== r2) {
          this.router.navigate(['/']);
        } 
              
                          // authorised so return true
                          return true;
            }
            // role not authorised so redirect to home page
            this.router.navigate(['/']);
            return false;
        }

        // not logged in so redirect to login page with the return url
        this.router.navigate(['/login'], { queryParams: { returnUrl: state.url } });
        return false;
    }
  }
}