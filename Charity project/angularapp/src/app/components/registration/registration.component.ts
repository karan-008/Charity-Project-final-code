import { Component} from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

 
@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent {
  successMessage:string='';
  errorMessage:string='';
  constructor(private readonly authService: AuthService,private readonly router:Router) { } 
  register(form:NgForm){
    console.log(form.value)
    if(form.valid){
      this.authService.register(form.value).subscribe({
        next: (registeredUser) => {
          this.successMessage = 'Registration Successful!';
          setTimeout(() => {
            this.router.navigate(['/login']);
          }, 2000);
        },
        error: (err) => {
          this.errorMessage = 'You have already registered. Please login!';
        }
      });
    }
  }
}
