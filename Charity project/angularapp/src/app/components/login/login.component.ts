import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { UserStoreService } from 'src/app/helpers/user-store.service';
import { Login } from 'src/app/models/login.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  errorMessage: string = '';

  constructor(
    private readonly fb: FormBuilder,
    private readonly authService: AuthService,
    private readonly router: Router,
    private readonly userStore: UserStoreService
  ) {}

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]]
    });
  }

  login(): void {
    if (this.loginForm.valid) {
      this.userStore.logoutUser();
      const loginData: Login = {
        email: this.loginForm.value.email,
        password: this.loginForm.value.password,
        jwtToken: '',
        role: '',
        username:''
      };
      this.authService.login(loginData).subscribe({
        next: (user: any) => {
          this.userStore.setUser(user);
          //console.log(user);
          this.redirectBasedOnRole();
        },
        error: (err) => {
          this.errorMessage = 'Invalid Credentials! Please Try Again.';
          console.log('Login Error', err);
        }
      });
    }
  }

  private redirectBasedOnRole(): void {
    console.log(this.userStore.loginUser);
    if (this.userStore.loginUser?.role === 'ADMIN') {
      this.router.navigate(['/adminviewcharity']);
    } else if (this.userStore.loginUser?.role === 'USER') {
      this.router.navigate(['/userviewcharity']);
    } else {
      this.router.navigate(['/']);
    }
  }
}
