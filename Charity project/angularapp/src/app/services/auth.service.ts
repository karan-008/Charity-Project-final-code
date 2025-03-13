import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/user.model';
import { Login } from '../models/login.model';
import { UserStoreService } from '../helpers/user-store.service';
import { BACKEND_URL } from '../link/links';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  backendUrl: string = `${BACKEND_URL}`;

  constructor(private readonly http: HttpClient, private readonly userStore: UserStoreService) { }

  register(user: User): Observable<User> {
    return this.http.post<User>(`${this.backendUrl}/register`, user);
  }

  login(login: Login): Observable<Login> {
    return new Observable<Login>(observer => {
      this.http.post<Login>(`${this.backendUrl}/login`, login).subscribe(
        response => {
          this.userStore.setUser(response);
          observer.next(response);
          observer.complete();
        },
        error => {
          observer.error(error);
        }
      );
    });
  }

  logout(): void {
    this.userStore.setUser(null);
    localStorage.removeItem('login');
  }

  isAuthenticated(): boolean {
    return this.userStore.isLoggedIn();
  }

  getCurrentUserId(): number | null {
    const loginUser = this.userStore.loginUser;
    return loginUser ? loginUser.userId : null;
 
  }
 
  getCustomerEmail(): string | null {
    const authUser = this.userStore.loginUser;
    return authUser?.email;
  }
}
