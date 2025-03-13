import { Component, OnInit, AfterViewInit, ViewChild, ElementRef } from '@angular/core';
import { Charity } from 'src/app/models/charity.model';
import { CharityService } from 'src/app/services/charity.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-adminviewcharity',
  templateUrl: './adminviewcharity.component.html',
  styleUrls: ['./adminviewcharity.component.css']
})
export class AdminviewcharityComponent implements OnInit, AfterViewInit {
  charities: Charity[] = [];
  totalDonationAmount: number = 0;
  showModal: boolean = false;
  showDeleteConfirmation: boolean = false;
  charityToDeleteId: number | null = null;
  showSuccessMessage: boolean = false;

  @ViewChild('charitySelect') charitySelect!: ElementRef;

  constructor(
    private readonly charityService: CharityService,
    private readonly router: Router
  ) { }

  ngOnInit(): void {
    this.getCharities();
  }

  ngAfterViewInit(): void {
    if (this.charitySelect) {
      this.charitySelect.nativeElement.value = 'view';
    }
  }

  getCharities(): void {
    this.charityService.getAllCharities().subscribe(
      (charities) => {
        this.charities = charities;
        console.log(this.charities);
      },
      (error) => {
        console.error('Error fetching charities:', error);
      }
    );
  }

  editCharity(id: number): void {
    this.router.navigate(['/createcharity', id]);
  }

  confirmDelete(id: number): void {
    this.charityToDeleteId = id;
    this.showDeleteConfirmation = true;
  }

  deleteCharity(): void {
    if (this.charityToDeleteId !== null) {
      this.charityService.deleteCharity(this.charityToDeleteId).subscribe(
        (response) => {
          console.log('Deleted successfully:', response);
          this.getCharities();
          this.showDeleteConfirmation = false;
          this.showSuccessMessage = true;
          setTimeout(() => {
            this.showSuccessMessage = false;
          }, 2000);
        },
        (error) => {
          console.error('Error deleting charity:', error);
          this.showDeleteConfirmation = false;
        }
      );
    }
  }

  cancelDelete(): void {
    this.showDeleteConfirmation = false;
    this.charityToDeleteId = null;
  }

  checkDonation(id: number): void {
    console.log("button clicked");
    this.charityService.checkDonation(id).subscribe(
      (totalDonation) => {
        this.totalDonationAmount = totalDonation || 0;
        this.showModal = true;
        console.log("total donation: " + this.totalDonationAmount);
      },
      (error) => {
        console.error('Error checking donation:', error);
        this.totalDonationAmount = 0;
        this.showModal = true;
      }
    );
  }

  closeModal(): void {
    this.showModal = false;
  }
}
