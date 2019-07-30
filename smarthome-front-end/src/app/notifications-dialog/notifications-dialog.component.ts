import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from "@angular/material";
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-notifications-dialog',
  templateUrl: './notifications-dialog.component.html',
  styleUrls: ['./notifications-dialog.component.css']
})
export class NotificationsDialogComponent implements OnInit {

  constructor(
    private dialogRef: MatDialogRef<NotificationsDialogComponent>,
    @Inject(MAT_DIALOG_DATA) data, private httpClient:HttpClient) {

}
userDetails:JSON;
userLoged = JSON.parse(sessionStorage.getItem("isAuthenticated"));
gasEmail : boolean = false;
gasPhone : boolean = false;
doorEmail : boolean = false;
doorPhone : boolean = false;
doorEmailNotification;
doorPhoneNotification;
gasEmailNotification;
gasPhoneNotification;

initializeNotificationStatus(){
  this.httpClient.get("http://localhost:8080/smarthome/notification/1")
  .subscribe((data:any) =>{
    console.log(data);
    this.doorEmailNotification = this.getBooleanFromInteger(data.doorNotificationEmail);
    this.doorPhoneNotification = this.getBooleanFromInteger(data.doorNotificationPhone);
    this.gasEmailNotification = this.getBooleanFromInteger(data.gasNotificationEmail);
    this.gasPhoneNotification = this.getBooleanFromInteger(data.gasNotificationPhone);
  })
}

setNotificationStatus(doorEmailNotification,doorPhoneNotification,gasEmailNotification,gasPhoneNotification){
  this.httpClient.put("http://localhost:8080/smarthome/notification/1",
    {
      doorNotificationPhone:doorPhoneNotification,
      doorNotificationEmail:doorEmailNotification,
      gasNotificationPhone:gasPhoneNotification,
      gasNotificationEmail:gasEmailNotification
    }).subscribe(
      (data:any) => {
        console.log(data);
      }
    )
}

getBooleanFromInteger(x : number){
  if(x==0){
    return false;
  }else{
    return true;
  }
}
getIntegerFromBoolean(x : boolean){
  if(x){
    return 1;
  }else{
    return 0;
  }
}

//Function for get user details
getUserDetails(){
  this.httpClient.get("http://localhost:8080/smarthome/user?email=" + this.userLoged.email)
  .subscribe((data:any) =>{
    console.log(data);
    this.userDetails = data;
  })
}


formatLabel(value: number | null) {
  if (!value) {
    return 0;
  }
  if (value >= 1000) {
    return Math.round(value / 1000) + '%';
  }
  return value;
}

save() {
    this.dialogRef.close();
}

close(doorPhoneNotification,doorEmailNotification,gasPhoneNotification,gasEmailNotification) {
  this.setNotificationStatus(this.getIntegerFromBoolean(doorPhoneNotification),
                            this.getIntegerFromBoolean(doorEmailNotification),
                            this.getIntegerFromBoolean(gasPhoneNotification),
                            this.getIntegerFromBoolean(gasEmailNotification));
    this.dialogRef.close();
}

  ngOnInit() {
    this.getUserDetails();
    this.initializeNotificationStatus();
  }

}
