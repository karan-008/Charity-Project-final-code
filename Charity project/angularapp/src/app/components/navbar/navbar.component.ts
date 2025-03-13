import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { UserStoreService } from 'src/app/helpers/user-store.service';
import { AuthService } from 'src/app/services/auth.service';
 
@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit, OnDestroy {
  showLogoutPopup = false;
  isLoggedIn = false;
  userRole: string | null = null;
  userName: string = null;
 
  private userSubscription: Subscription | null = null;
 
  constructor(
    private readonly authService: AuthService,
    private readonly router: Router,
    private readonly userStore: UserStoreService
  ) {}
 
  ngOnInit(): void {
    this.updateUserState();
 
    this.userSubscription = this.userStore.user$.subscribe(() => {
      this.updateUserState();
    });
  }
 
  private updateUserState(): void {
    this.isLoggedIn = this.userStore.isLoggedIn();
    this.userName = this.userStore.loginUser?.username || null;
    this.userRole = this.userStore.loginUser?.role || null;
  }
 
  logout(): void {
    this.authService.logout();
    this.showLogoutPopup = false;
    this.router.navigate(['/login']);
  }
 
  ngOnDestroy(): void {
    if (this.userSubscription) {
      this.userSubscription.unsubscribe();
    }
  }
}
 