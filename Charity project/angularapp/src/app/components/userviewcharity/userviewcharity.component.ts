import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserStoreService } from 'src/app/helpers/user-store.service';
import { Charity } from 'src/app/models/charity.model';
import { CharityService } from 'src/app/services/charity.service';
import { DonationService } from 'src/app/services/donation.service';
 
@Component({
  selector: 'app-userviewcharity',
  templateUrl: './userviewcharity.component.html',
  styleUrls: ['./userviewcharity.component.css']
})
export class UserviewcharityComponent implements OnInit {
  charities: Charity[] = [];
  dtoObject: any = null;
  currentUserId: number | null = null;
  currentCharityId: number | null = null;
 
  newDonate: any = {
    amount: 0,
    user: {
      userId: null
    },
    charity: {
      charityId: null
    }
  }
  dailogBox: boolean = false;
  successMessage: string = ""; // Add successMessage property
 
  constructor(
    private readonly service: CharityService,
    private readonly donateService: DonationService,
    private readonly router: Router,
    private readonly userStore: UserStoreService
  ) { }
 
  ngOnInit(): void {
    this.getAllCharities();
    this.currentUserId = this.userStore.getUserId();
  }
 
  getAllCharities() {
    this.service.getAllCharities().subscribe((data) => {
      this.charities = data;
    })
  }
 
  // to pop the donation dialog
  donate(id: number) {
    this.currentCharityId = id;
    console.log(this.currentCharityId);
    this.newDonate.user.userId = this.currentUserId;
    this.newDonate.charity.charityId = this.currentCharityId;
    this.dailogBox = true;
    this.successMessage = ""; // Reset the success message
  }
 
  // to actually do the donation
  donation() {
    this.dailogBox = false;
    console.log(this.newDonate);
    this.donateService.addDonation(this.newDonate).subscribe({
      next: () => {
        this.getAllCharities();
        this.router.navigate(['/userviewcharity']);
        this.showSuccessMessage(); // Show success message after donation
      },
      error: (err) => {
        console.error('Error during donation:', err);
      }
    });
  }
 
  showSuccessMessage() {
    this.dailogBox = true;
    this.successMessage = "Donation successful!";
  }
 
  close() {
    this.dailogBox = false; // Ensure the dialog box closes
    this.router.navigate(['/userviewcharity']);
    this.successMessage = ""; // Reset the success message when closing the dialog
    this.newDonate.amount = 0; // Reset the donation amount
  }
}
 