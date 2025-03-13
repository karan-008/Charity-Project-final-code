import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http'
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CommonModule,DatePipe } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AdminnavComponent } from './components/adminnav/adminnav.component';
import { AdminviewcharityComponent } from './components/adminviewcharity/adminviewcharity.component';
import { AdminviewfeedbackComponent } from './components/adminviewfeedback/adminviewfeedback.component';
import { CreatecharityComponent } from './components/createcharity/createcharity.component';
import { ErrorComponent } from './components/error/error.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { MydonationComponent } from './components/mydonation/mydonation.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { UseraddfeedbackComponent } from './components/useraddfeedback/useraddfeedback.component';
import { UsernavComponent } from './components/usernav/usernav.component';
import { UserviewcharityComponent } from './components/userviewcharity/userviewcharity.component';
import { UserviewfeedbackComponent } from './components/userviewfeedback/userviewfeedback.component';
import { ViewalldonationComponent } from './components/viewalldonation/viewalldonation.component';
import { AuthInterceptor } from './interceptor/auth.interceptor';

@NgModule({
  declarations: [
    AppComponent,
    AdminnavComponent,
    AdminviewcharityComponent,
    AdminviewfeedbackComponent,
    CreatecharityComponent,
    ErrorComponent,
    HomeComponent,
    LoginComponent,
    MydonationComponent,
    NavbarComponent,
    RegistrationComponent,
    UseraddfeedbackComponent,
    UsernavComponent,
    UserviewcharityComponent,
    UserviewfeedbackComponent,
    ViewalldonationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    CommonModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [DatePipe,
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
