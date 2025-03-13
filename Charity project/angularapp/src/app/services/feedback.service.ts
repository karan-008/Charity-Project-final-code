import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Feedback } from '../models/feedback.model';
import { BACKEND_URL } from '../link/links';
 
@Injectable({
  providedIn: 'root'
})
export class FeedbackService {
  backendUrl:string=`${BACKEND_URL}/feedback`;
  constructor(private readonly http: HttpClient) {}
 
  sendFeedback(feedback: Feedback): Observable<Feedback> {
    return this.http.post<Feedback>(this.backendUrl, feedback);
  }
 
 
  getAllFeedbacksByUserid(userId: string): Observable<Feedback[]> {
    return this.http.get<Feedback[]>(`${this.backendUrl}/user/${userId}`);
  }
 
  deleteFeedback(feedbackId: number): Observable<string> {
    return this.http.delete(`${this.backendUrl}/${feedbackId}`, { responseType: 'text' });
  }
 
  getFeedbacks(): Observable<Feedback[]> {
    return this.http.get<Feedback[]>(this.backendUrl);
  }
}
 