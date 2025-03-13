import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { AdminnavComponent } from './components/adminnav/adminnav.component';
import { AdminviewcharityComponent } from './components/adminviewcharity/adminviewcharity.component';
import { AdminviewfeedbackComponent } from './components/adminviewfeedback/adminviewfeedback.component';
import { CreatecharityComponent } from './components/createcharity/createcharity.component';
import { ErrorComponent } from './components/error/error.component';
import { UseraddfeedbackComponent } from './components/useraddfeedback/useraddfeedback.component';
import { UsernavComponent } from './components/usernav/usernav.component';
import { UserviewcharityComponent } from './components/userviewcharity/userviewcharity.component';
import { UserviewfeedbackComponent } from './components/userviewfeedback/userviewfeedback.component';
import { ViewalldonationComponent } from './components/viewalldonation/viewalldonation.component';
import { MydonationComponent } from './components/mydonation/mydonation.component';
import {AuthguardService } from './authguard/authguard.service';
 
const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'error', component: ErrorComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegistrationComponent },

  {
   path:'adminnav',component:AdminnavComponent,
    canActivate: [AuthguardService],
    data: { role: 'ADMIN' }
  },
  {
   path:'adminviewcharity',component:AdminviewcharityComponent,
    canActivate: [AuthguardService],
    data: { role: 'ADMIN' }
  },
  {
    path:'adminviewfeedback',component:AdminviewfeedbackComponent,
    canActivate: [AuthguardService],
    data: { role: 'ADMIN' }
  },
  {
    path:'createcharity',component:CreatecharityComponent,
    canActivate: [AuthguardService],
    data: { role: 'ADMIN' }
  },
  {
    path:'createcharity/:id',component:CreatecharityComponent,
    canActivate: [AuthguardService],
    data: { role: 'ADMIN' }
  },
  {
    path:'viewalldonation',component:ViewalldonationComponent,
    canActivate: [AuthguardService],
    data: { role: 'ADMIN' }
  },
  {
    path:'mydonation',component:MydonationComponent,
    canActivate: [AuthguardService],
    data: { role: 'USER' }
  },
  {
    path:'useraddfeedback',component:UseraddfeedbackComponent,
    canActivate: [AuthguardService],
    data: { role: 'USER' }
  },
  {
    path:'usernav',component:UsernavComponent,
    canActivate: [AuthguardService],
    data: { role: 'USER' }
  },
  {
    path:'userviewcharity',component:UserviewcharityComponent,
    canActivate: [AuthguardService],
    data: { role: 'USER' }
  },
  {
    path:'userviewfeedback',component:UserviewfeedbackComponent,
    canActivate: [AuthguardService],
    data: { role: 'USER' }
  },
  
  {path:'**',redirectTo:'/error'}
];
 
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
 