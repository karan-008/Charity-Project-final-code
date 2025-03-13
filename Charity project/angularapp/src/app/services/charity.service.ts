import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Charity } from '../models/charity.model';
import { BACKEND_URL } from '../link/links';
 
@Injectable({
  providedIn: 'root'
})
export class CharityService {
  backendUrl:string=`${BACKEND_URL}/charities`;
  constructor(private readonly http: HttpClient) {}
 
  getAllCharities(): Observable<Charity[]> {
    return this.http.get<Charity[]>(this.backendUrl);
  }
 
  getCharityById(charityId: number): Observable<Charity> {
    return this.http.get<Charity>(`${this.backendUrl}/${charityId}`);
  }
 
  addCharity(charity: Charity): Observable<any> {
    return this.http.post<Charity>(this.backendUrl, charity);
  }
 
  updateCharity(charityId: number, charity: Charity): Observable<Charity> {
    return this.http.put<Charity>(`${this.backendUrl}/${charityId}`, charity);
  }
 
  deleteCharity(charityId: number): Observable<string> {
    return this.http.delete(`${this.backendUrl}/${charityId}`, { responseType: 'text' });
  }
 
  checkDonation(charityId: number): Observable<number> {
    return this.http.get<number>(`${this.backendUrl}/${charityId}/total-donations`);
  }
}