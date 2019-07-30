import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from "@angular/material";
import { HttpClient } from '@angular/common/http';
import { disableDebugTools } from '@angular/platform-browser';

@Component({
  selector: 'app-schedule-dialog',
  templateUrl: './schedule-dialog.component.html',
  styleUrls: ['./schedule-dialog.component.css']
})
export class ScheduleDialogComponent implements OnInit {

  constructor(
    private dialogRef: MatDialogRef<ScheduleDialogComponent>,
    @Inject(MAT_DIALOG_DATA) data, private httpClient:HttpClient) {

}
scheduleArrayFirst:JSON[] = [];
scheduleArraySecond:JSON[] = [];
obj:any;
daysSelected: string[];
daysOfTheWeek: string[] = [
  'Mon',
  'Tue',
  'Wed',
  'Thu',
  'Fri',
  'Sat',
  'Sun'
];
bulbs;
action : String;
time : String;

getBulbs(){
  this.httpClient.get("http://localhost:8080/smarthome/bulbs/1")
  .subscribe((data:any) =>{
    console.log(data);
    this.bulbs = data;
    console.log(this.bulbs);
  })
}

getActiveSchedulesFirst(){
  this.httpClient.get("http://localhost:8080/smarthome/bulb/schedule/1")
  .subscribe((data:any) =>{
    console.log(data);
    this.scheduleArrayFirst = data;
  })
}

getActiveSchedulesSecond(){
  this.httpClient.get("http://localhost:8080/smarthome/bulb/schedule/2")
  .subscribe((data:any) =>{
    console.log(data);
    this.scheduleArraySecond = data;
  })
}

onChipSelected(chips: string[]) {
  this.daysSelected = chips;
  console.log(chips);
}

getActionFromInteger(x : number){
  if(x==0){
    return "OFF";
  }else{
    return "ON";
  }
}

setScheduleFirst(action,time,bulbId,bulbIp){
  if(action!=undefined && time!="" && this.daysSelected.length>0){
    
    this.obj = {
      "bulbId":bulbId,
      "time":time,
      "action":this.getActionFromInteger(action),
      "days":this.daysSelected.toString(),
      "cron":"0 " + time.split(":")[1] + " " + time.split(":")[0] + " * * " + this.daysSelected.toString(),
      "ip":bulbIp
    }
    console.log(this.obj);
    this.scheduleArrayFirst.push(this.obj);
    console.log(this.scheduleArrayFirst);
  }
}

setScheduleSecond(action,time,bulbId,bulbIp){
  if(action!=undefined && time!="" && this.daysSelected.length>0){
    this.obj = {
      "bulbId":bulbId,
      "time":time,
      "action":this.getActionFromInteger(action),
      "days":this.daysSelected.toString(),
      "cron":"0 " + time.split(":")[1] + " " + time.split(":")[0] + " * * " + this.daysSelected.toString(),
      "ip":bulbIp
    }
    var mySubString = time.split(":");
    console.log(mySubString);
    console.log(this.obj);
    this.scheduleArraySecond.push(this.obj);
    console.log(this.scheduleArraySecond);
  }
}

insertFirstBulbSchedule(){
  //delete all 
  for(let i = 0;i<this.scheduleArrayFirst.length;i++){
    this.httpClient.post("http://localhost:8080/smarthome/bulb/schedule",{
      "bulbId":this.scheduleArrayFirst[i].bulbId,
      "cron":this.scheduleArrayFirst[i].cron,
	    "time":this.scheduleArrayFirst[i].time,
	    "days":this.scheduleArrayFirst[i].days,
      "action":this.scheduleArrayFirst[i].action,
      "ip":this.scheduleArrayFirst[i].ip
    })
  .subscribe((data:any) =>{
    console.log(data);})
  }
}

insertSecondBulbSchedule(){
  //delete all 
  for(let i = 0;i<this.scheduleArrayFirst.length;i++){
    this.httpClient.post("http://localhost:8080/smarthome/bulb/schedule",{
      "bulbId":this.scheduleArraySecond[i].bulbId,
      "cron":this.scheduleArraySecond[i].cron,
	    "time":this.scheduleArraySecond[i].time,
	    "days":this.scheduleArraySecond[i].days,
      "action":this.scheduleArraySecond[i].action,
      "ip":this.scheduleArraySecond[i].ip
    })
  .subscribe((data:any) =>{
    console.log(data);})
  }
}

deleteAllSchedules(){
  this.httpClient.delete("http://localhost:8080/smarthome/bulb/schedule")
  .subscribe((data:any) =>{
    console.log(data);
  })
}

deleteFromScheduleArrayFirst(i){
  console.log(i);
  this.scheduleArrayFirst.splice(i, 1);
}

deleteFromScheduleArraySecond(i){
  console.log(i);
  this.scheduleArraySecond.splice(i, 1);
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

saveSchedule(){
  this.deleteAllSchedules();
  this.insertFirstBulbSchedule();
  this.insertSecondBulbSchedule();
}

close() {
    this.dialogRef.close();
}

  ngOnInit() {
    this.getBulbs();
    this.getActiveSchedulesFirst();
    this.getActiveSchedulesSecond();
  }

}
