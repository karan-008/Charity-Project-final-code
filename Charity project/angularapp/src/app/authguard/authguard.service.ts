import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { UserStoreService } from '../helpers/user-store.service';

@Injectable({
  providedIn: 'root'
})
export class AuthguardService implements CanActivate {
  constructor(
    private readonly router: Router,private readonly userStore:UserStoreService) { }
  canActivate(route: ActivatedRouteSnapshot): Observable <boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      if(!this.userStore.isLoggedIn()){
        this.router.navigate(['/login']);
        return false;
      }
      const requiredRole=route.data['role'];
      if(requiredRole){
        const userRole=this.userStore.loginUser?.role;
        if(requiredRole === 'ADMIN' && userRole !== 'ADMIN'){
          this.router.navigate(['/error']);
          return false;

        }
        if(requiredRole === 'USER' && userRole !== 'USER'){
          this.router.navigate(['/error']);
          return false;
        }

      }
      return true;
  }
}
