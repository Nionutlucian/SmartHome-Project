import { Component, OnInit } from '@angular/core';
import { Injectable } from '@angular/core';
import { MatDialog, MatDialogConfig, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { UserDetailsComponent } from './user-details/user-details.component'


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  
  constructor(private dialog: MatDialog){
  }
  
  title = 'SmartHome';
  authenticated:boolean = false;

  userLoged = JSON.parse(sessionStorage.getItem("isAuthenticated"));

  isAuthenticated (){
    if(this.userLoged!=null){
      this.authenticated = true;
      console.log("Este autentificat? : " + this.authenticated);
      console.log("JSON-ul " + sessionStorage.getItem("isAuthenticated"));
    }else{
      console.log("Este autentificat? : " + this.authenticated);
    }
  }

  openUserDetailsDialog() {

    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

    this.dialog.open(UserDetailsComponent, dialogConfig);

    dialogConfig.position = {
      'top': '0',
      left: '0'
  };
  
}

  ngOnInit() {
    this.isAuthenticated();
  }


}
