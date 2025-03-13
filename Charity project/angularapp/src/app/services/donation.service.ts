import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Donation } from '../models/donation.model';
import { BACKEND_URL } from '../link/links';
 
@Injectable({
  providedIn: 'root'
})
export class DonationService {
  backendUrl:string=`${BACKEND_URL}/donations`;
  constructor(private readonly http: HttpClient) { }
  getAllDonations():Observable<Donation[]>{
    return this.http.get<Donation[]>(this.backendUrl);
  }
  getDonationById(donationId:number):Observable<Donation>{
    return this.http.get<Donation>(this.backendUrl+'/'+donationId);
  }
  addDonation(donation:Donation):Observable<Donation>{
    return this.http.post<Donation>(this.backendUrl,donation);
  }
  getDonationByUserId(userId:number):Observable<Donation[]>{
    return this.http.get<Donation[]>(this.backendUrl+'/user/'+userId);
  }
  getDonationByCharityId(charityId:number):Observable<Donation[]>{
    return this.http.get<Donation[]>(this.backendUrl+'/charity/'+charityId);
  }
}
 