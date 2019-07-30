import { Component, OnInit, Inject } from '@angular/core';
import { MatDialog, MatDialogConfig, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DialogComponent } from '../dialog/dialog.component';
import { ProgressSpinnerComponent } from '../progress-spinner/progress-spinner.component';
import { Router } from '@angular/router';
import { AppComponent } from '../app.component';
import { NotificationsDialogComponent } from '../notifications-dialog/notifications-dialog.component';
import { ScheduleDialogComponent } from '../schedule-dialog/schedule-dialog.component';
import {HttpClient} from '@angular/common/http';
import { interval, Subscription } from 'rxjs';

@Component({
  selector: 'app-house',
  templateUrl: './house.component.html',
  styleUrls: ['./house.component.css']
})
export class HouseComponent implements OnInit {

  
  constructor(private dialog: MatDialog, private route:Router, private httpClient:HttpClient) {}
    
  entryDoor : String;
  gasSensor : String;
  temperature : String;
  humidity : String;
  lastUpdate : String;
  doorOpen : String;
  doorClose : String;
  gasDanger : String;
  gasSafe : String;
  subscription: Subscription;
  userLoged = JSON.parse(sessionStorage.getItem("isAuthenticated"));
  homeId = sessionStorage.getItem("houseId");
  bulbs : JSON;
  value = 1000;

  getBulbs(){
    console.log("dasd" + this.homeId);
    this.httpClient.get("http://localhost:8080/smarthome/bulbs/" + this.homeId)
    .subscribe((data:any) =>{
      console.log(data);
      this.bulbs = data;
    })
  }
  public bulbOnOff(state,ip) {
    console.log("am intrat in functie");
    //this.updateBulbState(ip,this.getIntegerFromBoolean(state));
    this.httpClient.get("http://localhost:8080/smarthome/bulb/" + this.getIntegerFromBoolean(state) + "?bulbIp=" + ip)
    .subscribe((data:any) =>{
      console.log(data);
    })
    
  }

  public bulbSetBrightness(brightness,ip) {
    console.log(brightness.value,ip);
    let x = Math.round(brightness.value/1000);
    console.log(x);
    this.updateBulbBrightness(ip,x);
    this.httpClient.get("http://localhost:8080/smarthome/bulb/brightness/" + x + "?bulbIp=" + ip)
    .subscribe((data:any) =>{
      console.log(data);
    })
    }
  
  public updateBulbState(ip,state){
    console.log("am intrat in PUT");
    this.httpClient.get("http://localhost:8080/smarthome/bulb/update/" + state + "?bulbIp=" + ip)
    .subscribe((data:any) =>{
      console.log(data);
    })
  }  

  public updateBulbName(name,ip){
    console.log("am intrat in PUT");
    this.httpClient.get("http://localhost:8080/smarthome/bulb/update/name/" + name.target.textContent + "?bulbIp=" + ip)
    .subscribe((data:any) =>{
      console.log(data);
    })
  }  
  public updateBulbBrightness(ip,brightness){
    console.log("am intrat in PUT");
    this.httpClient.get("http://localhost:8080/smarthome/bulb/update/brightness/" + brightness + "?bulbIp=" + ip)
    .subscribe((data:any) =>{
      console.log(data);
    })
  }  

  getIntegerFromBoolean(x : boolean){
    if(x){
      return 1;
    }else{
      return 0;
    }
  }

  getBooleanFromInteger(x : number){
    if(x==0){
      return false;
    }else{
      return true;
    }
  }

  getTimeFromMiliseconds(timestamp : number){
    let date = new Date(timestamp);
    var seconds = date.getSeconds();
    var minutes = date.getMinutes();
    var hour = date.getHours();
    var result = '';
    if(hour < 10){
      result = "0"+hour;
    }else{
      result =result + hour;
    }
    if(minutes < 10){
      result = result + ":0" + minutes; 
    }else{
      result = result + ":" + minutes;
    }
    if(seconds < 10){
      result = result + ":0" + seconds;
    }else{
      result = result + ":" + seconds;
    }
    console.log(result);
    return result;
  }

  getDoorAndGasStatus(){
    if(this.entryDoor=="1"){
        this.doorOpen = "false";
        this.doorClose = "true";
    }else{
      this.doorClose = "false";
      this.doorOpen = "true";
    }

    if(this.gasSensor=="1"){
      this.gasDanger = "false";
      this.gasSafe = "true";
    }else{
      this.gasDanger = "true";
      this.gasSafe = "false";
    }

  }

  getSensorsValues(){
    setInterval(()=>{this.httpClient.get("http://localhost:8080/smarthome/sensor/values?id=" + this.homeId)
    .subscribe((data:any) =>{
      console.log(data);
      this.lastUpdate = this.getTimeFromMiliseconds(data.timestamp);
      this.temperature = data.temperature;
      this.humidity = data.humidity;
      this.entryDoor = data.doorStatus;
      this.gasSensor = data.gasStatus;
      this.getDoorAndGasStatus();
    })
  },120000);
}

getSensorsValuesInitial(){
  this.httpClient.get("http://localhost:8080/smarthome/sensor/values?id=" + this.homeId)
  .subscribe((data:any) =>{
    console.log(data);
    if(data==null){
      window.alert("You house are not configured yet.Please contact the website administrator for configuring the house!");
      this.logOut();
    }else{
    this.lastUpdate = this.getTimeFromMiliseconds(data.timestamp);
    this.temperature = data.temperature;
    this.humidity = data.humidity;
    this.entryDoor = data.doorStatus;
    this.gasSensor = data.gasStatus;
    this.getDoorAndGasStatus();
    }
  })
}

  //Function for log-out
  logOut(){
    sessionStorage.clear();
    location.replace("http://localhost:4200/");
  }

  openDialog() {

      const dialogConfig = new MatDialogConfig();

      dialogConfig.disableClose = true;
      dialogConfig.autoFocus = true;

      this.dialog.open(DialogComponent, dialogConfig);

      dialogConfig.position = {
        'top': '0',
        left: '0'
    };
    
  }

  openNotificationDialog() {

    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

    this.dialog.open(NotificationsDialogComponent, dialogConfig);

    dialogConfig.position = {
      'top': '0',
      left: '0'
  };
  
}

openScheduleDialog() {

  const dialogConfig = new MatDialogConfig();

  dialogConfig.disableClose = true;
  dialogConfig.autoFocus = true;

  this.dialog.open(ScheduleDialogComponent, dialogConfig);

  dialogConfig.position = {
    'top': '0',
    left: '0'
};

}

  openSpinnerAndCheckDevices() {

    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

    this.dialog.open(ProgressSpinnerComponent, dialogConfig);

    dialogConfig.position = {
      'top': '0',
      left: '0'
  };
  this.getSensorsValuesInitial();
  this.getBulbs();
  
}

  items = [1,2,3,4,5];

  formatLabel(value: number | null) {
    if (!value) {
      return 0;
    }
    if (value >= 1000) {
      return Math.round(value / 1000) + '%';
    }
    return value;
  }
 
  ngOnInit() {
    this.getBulbs();
    this.getSensorsValuesInitial();
    this.getSensorsValues();
  }
}
