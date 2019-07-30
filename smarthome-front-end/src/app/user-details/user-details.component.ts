import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from "@angular/material";
import {FormControl, Validators} from '@angular/forms';
import {HttpClient} from '@angular/common/http';


@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {
  constructor(
    private dialogRef: MatDialogRef<UserDetailsComponent>,
    @Inject(MAT_DIALOG_DATA) data,
    private httpClient:HttpClient) {

}
userLoged = JSON.parse(sessionStorage.getItem("isAuthenticated"));
phoneAlreadyExist:boolean = false;
phoneNumbers:string[];
userDetails:JSON;

//Function for get all phone numbers from DB
getAllPhoneNumbers(){
  this.httpClient.get("http://localhost:8080/smarthome/phone")
  .subscribe((data:any) =>{
    console.log(data);
    this.phoneNumbers = data;
  })
}

//Function for get user details
getUserDetails(){
  this.httpClient.get("http://localhost:8080/smarthome/user?email=" + this.userLoged.email)
  .subscribe((data:any) =>{
    console.log(data);
    this.userDetails = data;
  })
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

//Function for update user details
// updateUserDetails(password,phoneNumber){
//   this.httpClient.put("http://localhost:8080/smarthome/user",
//   {
//     lastName:this.userDetails.lastName,
//     firstName:this.userDetails.firstName,
//     email:this.userDetails.email,
//     password:password,
//     adress:this.userDetails.adress,
//     phoneNumber:phoneNumber
//   }).subscribe(
//     (data:any) => {
//       console.log(data);
//     }
//   )
// }

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

close() {
    this.dialogRef.close();
}


passwordFormControl = new FormControl('', [
  Validators.required,
  Validators.minLength(8),
  Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]+$')
]);
phoneFormControl = new FormControl('', [
  Validators.required,
]);


  ngOnInit() {
    this.getAllPhoneNumbers();
    this.getUserDetails();
  }

}
