import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() { }

  public isAuthenticated(): boolean {
    if(sessionStorage.getItem('isAuthenticated')!=null){
      console.log("sadas" + sessionStorage.getItem('isAuthenticated'));
      return true;
    }else{
      console.log("sadas" + sessionStorage.getItem('isAuthenticated'));
      return false;
    }
  }
}
