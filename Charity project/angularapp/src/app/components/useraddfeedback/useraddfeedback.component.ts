import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserStoreService } from 'src/app/helpers/user-store.service';
import { FeedbackService } from 'src/app/services/feedback.service';

@Component({
  selector: 'app-useraddfeedback',
  templateUrl: './useraddfeedback.component.html',
  styleUrls: ['./useraddfeedback.component.css']
})
export class UseraddfeedbackComponent implements OnInit {
  feedbackform: FormGroup;
  isPopupVisible: boolean = false;
  message: string = '';

  constructor(
    private readonly fb: FormBuilder,
    private readonly router: Router,
    private readonly feedbackservice: FeedbackService,
    private readonly userStore: UserStoreService
  ) {}

  ngOnInit(): void {
    this.feedbackform = this.fb.group({
      feedbackText: ["", Validators.required]
    });
  }

  submit(): void {
    if (this.feedbackform.valid) {
      const feedbackData = {
        feedbackText: this.feedbackform.value.feedbackText,
        date: new Date().toISOString(), // Corrected here
        user: this.userStore.loginUser
      };

      this.feedbackservice.sendFeedback(feedbackData).subscribe(
        () => {
          console.log(feedbackData);
          this.isPopupVisible = true;
          this.message = 'Successfully added';
        },
      );
    }
  }

  get feedbackText() {
    return this.feedbackform.get('feedbackText');
  }

  closePopup(): void {
    this.isPopupVisible = false;
    this.router.navigate(['/userviewfeedback']);
  }
}
