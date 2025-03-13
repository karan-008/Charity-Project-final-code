import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Charity } from 'src/app/models/charity.model';
import { CharityService } from 'src/app/services/charity.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-createcharity',
  templateUrl: './createcharity.component.html',
  styleUrls: ['./createcharity.component.css']
})
export class CreatecharityComponent implements OnInit {

  charity: Charity = {
    charityId: 0,
    charityName: '',
    description: '',
    founder: '',
    status: '',
    creationDate: ''
  };
  isEditing: boolean = false;
  modalMessage: string = '';
  registrationSuccess: boolean = false;
  errorMessage: string = '';

  constructor(
    private readonly route: ActivatedRoute,
    private readonly router: Router,
    private readonly charityService: CharityService
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const id = +params['id'];
      if (id) {
        this.isEditing = true;
        this.loadCharity(id);
      }
    });
  }

  loadCharity(id: number): void {
    this.charityService.getCharityById(id).subscribe(
      (data) => (this.charity = data),
      (error) => {
        console.error('Error loading charity:', error);
      }
    );
  }

  addOrUpdateCharity(form: NgForm): void {
    if (form.invalid) {
      this.errorMessage = 'Please fill in all required fields.';
      return;
    }

    this.modalMessage = this.isEditing ? 'Updated Successfully' : 'Successfully added';
    if (this.isEditing) {
      this.charityService.updateCharity(this.charity.charityId, this.charity).subscribe(() => {
        this.registrationSuccess = true;
        this.router.navigate(['/adminviewcharity']);
      });
    } else {
      this.charityService.addCharity(form.value).subscribe(
        () => {
          this.registrationSuccess = true;
          this.modalMessage = 'Charity added successfully!';
        },
        (error) => {
          this.errorMessage = 'Charity Name Already Exists!';
        }
      );
    }
  }

  cancelOperation(): void {
    this.router.navigate(['/adminviewcharity']);
  }

  closeModal(): void {
    this.registrationSuccess = false;
  }
}
