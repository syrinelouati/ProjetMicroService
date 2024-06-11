import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Feedback } from "app/Models/feedback";
import { AuthenticationService } from "./authentication.service";
import { UserService } from "./user.service";



@Injectable({
  providedIn: 'root'
})
export class FeedbackService {

  apiurl ="http://localhost:8088/";

  constructor(private httpClient: HttpClient, private authenticationService: AuthenticationService){
  }

  addFeedback(feedback: Feedback) {
    let headers = new HttpHeaders();
    headers = headers.set('Content-Type', 'multipart/form-data');
    const user = this.authenticationService.getUserFromLocalCache();
    if (user !== null) {
      feedback.username=user.username;
    }
   return  this.httpClient.post(this.apiurl + 'feedback', feedback) ;

  }
  getAll(){
    return this.httpClient.get<Feedback[]>(this.apiurl+'feedback');
  }

  deleteFeedback(id:any){
    return this.httpClient.delete<string[]>(this.apiurl+'feedback/'+id);

  }


}
