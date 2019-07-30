import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { RegisterComponent } from './register/register.component';
import { HouseComponent } from './house/house.component';
import { MatSidenavModule } from '@angular/material/sidenav';
import { ScrollDispatchModule } from '@angular/cdk/scrolling';
import { MatSliderModule } from '@angular/material/slider';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatDialogModule } from "@angular/material";
import { DialogComponent } from './dialog/dialog.component';
import { MatStepperModule } from '@angular/material/stepper';
import { ProgressSpinnerComponent } from './progress-spinner/progress-spinner.component';
import { HttpClientModule } from '@angular/common/http';
import { AuthGuardService } from './service/auth-guard.service';
import { UserDetailsComponent } from './user-details/user-details.component';
import { NotificationsDialogComponent } from './notifications-dialog/notifications-dialog.component';
import { ScheduleDialogComponent } from './schedule-dialog/schedule-dialog.component';
import { SelectableChipsComponent } from './selectable-chips/selectable-chips.component';
import { MatChipsModule } from '@angular/material';
import { FirstpageComponent } from './firstpage/firstpage.component';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HouseComponent,
    DialogComponent,
    ProgressSpinnerComponent,
    UserDetailsComponent,
    NotificationsDialogComponent,
    ScheduleDialogComponent,
    SelectableChipsComponent,
    FirstpageComponent],
    providers: [AuthGuardService],
  entryComponents: [DialogComponent, ProgressSpinnerComponent, 
                    UserDetailsComponent, NotificationsDialogComponent, ScheduleDialogComponent],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    FlexLayoutModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatProgressSpinnerModule,
    FormsModule,
    ReactiveFormsModule,
    MatSidenavModule,
    ScrollDispatchModule,
    MatSliderModule,
    MatSlideToggleModule,
    MatDialogModule,
    MatStepperModule,
    MatChipsModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
 }

