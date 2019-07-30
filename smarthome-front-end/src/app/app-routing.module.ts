import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { HouseComponent } from './house/house.component';
import { FirstpageComponent } from './firstpage/firstpage.component';
import { AuthGuardService } from './service/auth-guard.service';

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: '', component: FirstpageComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'house', component: HouseComponent, canActivate:[AuthGuardService]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
