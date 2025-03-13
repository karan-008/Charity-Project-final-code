import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Feedback } from 'src/app/models/feedback.model';
import { User } from 'src/app/models/user.model';
import { FeedbackService } from 'src/app/services/feedback.service';

@Component({
  selector: 'app-adminviewfeedback',
  templateUrl: './adminviewfeedback.component.html',
  styleUrls: ['./adminviewfeedback.component.css']
})
export class AdminviewfeedbackComponent implements OnInit {
  feedbacks: Feedback[] = [];
  selectedProfile: any = null;

  constructor(private readonly feedbackService: FeedbackService, private readonly route: Router) { }

  ngOnInit(): void {
    this.loadFeedbacks();
  }

  loadFeedbacks() {
    this.feedbackService.getFeedbacks().subscribe(
      (data) => {
        this.feedbacks = data;
        console.log(data);
      },
      (error) => {
      
      }
    );
  }

  showProfile(user: User): void {
    this.selectedProfile = {
      username: user.username,
      email: user.email,
      mobile: user.mobileNumber
    };
  }

  closeDialog() {
    this.selectedProfile='';
    this.route.navigate(["/adminviewfeedback"]);
  }
}
