import { Component, OnInit } from '@angular/core';
import { Donation } from 'src/app/models/donation.model';
import { DonationService } from 'src/app/services/donation.service';
 
@Component({
  selector: 'app-viewalldonation',
  templateUrl: './viewalldonation.component.html',
  styleUrls: ['./viewalldonation.component.css']
})
export class ViewalldonationComponent implements OnInit {
  donations: Donation[];
 
  constructor(private readonly service: DonationService) {}
 
  ngOnInit(): void {
    this.getAllDonations();
  }
  getAllDonations() {
    this.service.getAllDonations().subscribe((data) => {
      this.donations = data;
      console.log(this.donations);
    });
  }
}
