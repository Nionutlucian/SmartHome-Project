import { Component, OnInit } from '@angular/core';
import {FormControl, Validators} from '@angular/forms';
import {ErrorStateMatcher} from '@angular/material/core';
import {HttpClient} from '@angular/common/http';
import {Router} from "@angular/router";
import {AuthService} from '../service/auth.service'


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {

  

  constructor(private httpClient:HttpClient, private router:Router, private authService:AuthService){}

  incorrectUserNameOrPassword:boolean = false;
  check:boolean = false;

  private callBackSetUserLoged() {
    this.router.navigate(['/house']);
  }

  setHouseId(email){
    this.httpClient.get("http://localhost:8080/smarthome/user?email=" + email)
    .subscribe((data:any) =>{
      console.log(String(data.houseId));
      sessionStorage.setItem("houseId",data.houseId);
    })
  }

  //Function for login
  login(email, password){
    this.httpClient.post("http://localhost:8080/smarthome/login",
    {
      lastName:null,
      firstName:null,
      email:email,
      password:password,
      adress:null,
      phoneNumber:null
    }).toPromise().then(
      (data:any) => {
        console.log("s-a trimis request de login " + data);
        if(data.email!=null){
        sessionStorage.setItem("isAuthenticated",JSON.stringify(data));
        this.setHouseId(data.email);
        if(sessionStorage.getItem("isAuthenticated")!=null){
          // this.router.navigate["house"];
          location.replace("http://localhost:4200/house");
        }
        }else{
          this.incorrectUserNameOrPassword = true;
          console.log(this.incorrectUserNameOrPassword);
        }
      }
    )
  }

  emailFormControl = new FormControl('', [
    Validators.required,
    Validators.email,
  ]);
  passwordFormControl = new FormControl('', [
    Validators.required,
  ]);
  
  matcher = new ErrorStateMatcher();
  ngOnInit() {
  }

}
