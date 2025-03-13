import { Component, OnInit } from '@angular/core';
import { Charity } from 'src/app/models/charity.model';
import { Donation } from 'src/app/models/donation.model';
import { DonationService } from 'src/app/services/donation.service';
import { AuthService } from 'src/app/services/auth.service'; // Add this line
 
@Component({
  selector: 'app-mydonation',
  templateUrl: './mydonation.component.html',
  styleUrls: ['./mydonation.component.css']
})
export class MydonationComponent implements OnInit {
  donations: Donation[] = [];
  selectedCharity: any = null;
  selected: boolean = false;
  userId: number;
 
  constructor(private service: DonationService, private authService: AuthService) {} // Modify this line
 
  ngOnInit(): void {
    this.userId = this.authService.getCurrentUserId(); // Get the user ID from auth service
    this.getDonationsById(this.userId);
  }
 
  getDonationsById(userId: number): void {
    this.service.getDonationByUserId(userId).subscribe(
      (data) => {
        this.donations = data;
      },
      (error) => {
        if (error.status === 404) {
          console.error('No donations found for user with ID:', userId);
        } else {
          console.error('Error fetching donations:', error);
        }
      }
    );
  }
 
  showCharityDetails(charity: Charity): void {
    this.selected = true;
    this.selectedCharity = {
      charityName: charity.charityName,
      description: charity.description,
      founder: charity.founder,
      creationDate: charity.creationDate,
      status: charity.status
    };
  }
 
  close(): void {
    this.selected = false;
  }
}