import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Feedback } from 'src/app/models/feedback.model';
import { FeedbackService } from 'src/app/services/feedback.service';
import { UserStoreService } from 'src/app/helpers/user-store.service';

@Component({
  selector: 'app-userviewfeedback',
  templateUrl: './userviewfeedback.component.html',
  styleUrls: ['./userviewfeedback.component.css']
})
export class UserviewfeedbackComponent implements OnInit {
  feedbacks: Feedback[] = [];
  errorMessage: string = '';
  selectedFeedbackId: number | null = null;

  constructor(
    private readonly feedbackService: FeedbackService,
    private readonly userStore: UserStoreService,
    private readonly route: Router
  ) {}

  ngOnInit(): void {
    this.loadFeedbacks();
  }

  loadFeedbacks() {
    const currentUser = this.userStore.loginUser;
    if (currentUser) {
      this.feedbackService.getAllFeedbacksByUserid(currentUser.userId.toString()).subscribe(
        (data) => {
          this.feedbacks = data;
          console.log(this.feedbacks);
        },
        (error) => {
          this.errorMessage = 'No feedbacks';
        }
      );
    } else {
      this.errorMessage = 'User not logged in';
    }
  }

  confirmDelete(feedbackId: number) {
    this.selectedFeedbackId = feedbackId;
  }

  deleteFeedback() {
    if (this.selectedFeedbackId !== null) {
      this.feedbackService.deleteFeedback(this.selectedFeedbackId).subscribe(() => {
        this.feedbacks = this.feedbacks.filter((feedback) => feedback.feedbackId !== this.selectedFeedbackId);
        this.selectedFeedbackId = null;
      });
    }
  }

  closeDialog() {
    this.selectedFeedbackId = null;
  }
}
