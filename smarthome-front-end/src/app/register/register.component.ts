import { Component, OnInit } from '@angular/core';
import {FormControl, Validators} from '@angular/forms';
import {ErrorStateMatcher} from '@angular/material/core';
import {HttpClient} from '@angular/common/http';
import {Router} from "@angular/router";


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private httpClient:HttpClient, private router:Router){}
  
  bool = true;
  emailAlreadyExist:boolean = false;
  phoneAlreadyExist:boolean = false;
  emails:string[];
  phoneNumbers:string[];

  //Function for check if email already exists in DB
  checkIfEmailAlreadyExist(event:any){
    
    for(let i of this.emails){
      if(i==event.target.value){
        this.emailAlreadyExist = true;
        console.log(this.emailAlreadyExist);
        break;
      }else{
        this.emailAlreadyExist = false;
        console.log(this.emailAlreadyExist);
      }
    }
    console.log(event.target.value);
  }

  //Function for check if phone number already exists in DB
  checkIfPhoneNumberAlreadyExist(event:any){
    
    for(let i of this.phoneNumbers){
      if(i==event.target.value){
        this.phoneAlreadyExist = true;
        console.log(this.phoneAlreadyExist);
        break;
      }else{
        this.phoneAlreadyExist = false;
        console.log(this.phoneAlreadyExist);
      }
    }
    console.log(event.target.value);
  }

  //Function for get all emails from DB
  getAllEmails(){
    this.httpClient.get("http://localhost:8080/smarthome/email")
    .subscribe((data:any) =>{
      console.log(data);
      this.emails = data;
    })
  }

  //Function for get all phone numbers from DB
  getAllPhoneNumbers(){
    this.httpClient.get("http://localhost:8080/smarthome/phone")
    .subscribe((data:any) =>{
      console.log(data);
      this.phoneNumbers = data;
    })
  }


  //Function for register an new user
  register(firstName,lastName,email,password,adress,phoneNumber){
    this.httpClient.post("http://localhost:8080/smarthome/register",
    {
      lastName:lastName,
      firstName:firstName,
      email:email,
      password:password,
      adress:adress,
      phoneNumber:phoneNumber
    }).subscribe(
      (data:any) => {
        console.log(data);
        alert("Your account has been successfully created. Please log in!");
        this.router.navigate(['/login']);
      }
    )
  }

  //Form controls
  emailFormControl = new FormControl('', [
    Validators.required,
    Validators.email,
  ]);
  passwordFormControl = new FormControl('', [
    Validators.required,
    Validators.minLength(8),
    Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]+$')
  ]);
  firstNameFormControl = new FormControl('', [
    Validators.required,
  ]);
  lastNameFormControl = new FormControl('', [
    Validators.required,
  ]);
  adressFormControl = new FormControl('', [
    Validators.required,
  ]);
  phoneFormControl = new FormControl('', [
    Validators.required,
  ]);
  
  matcher = new ErrorStateMatcher();
  ngOnInit() {
    this.getAllEmails();
    this.getAllPhoneNumbers();
  }

}
