import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Login } from '../models/login.model';

@Injectable({
  providedIn: 'root' // This service is available throughout the application
})
export class UserStoreService {
  // BehaviorSubject is a special type of Subject in RxJS that holds the current value and emits it to new subscribers.
  private readonly userSubject = new BehaviorSubject<Login | null>(null);

  // Observable is a stream of data that subscribers can listen to. The user$ observable emits the current user state.
  user$: Observable<Login | null> = this.userSubject.asObservable();

  constructor() {
    // Retrieve the user data from localStorage, if available, when the service is instantiated.
    const storedUser = localStorage.getItem('login');
    if (storedUser) {
      this.setUser(JSON.parse(storedUser)); // Parse the stored JSON string and set the user.
    }
  }

  // Method to set the current user. It updates both the BehaviorSubject and localStorage.
  setUser(user: Login | null): void {
    if (user) {
      localStorage.setItem('login', JSON.stringify(user)); // Store the user data as a JSON string in localStorage.
    } else {
      console.log("STORE USER IS NULL"); // Log a message if user is null.
      localStorage.removeItem('login'); // Remove the user data from localStorage.
    }
    this.userSubject.next(user); // Emit the new user value to all subscribers.
  }

  // Getter for the current user. It retrieves the current value held by the BehaviorSubject.
  get loginUser(): Login | null {
    return this.userSubject.getValue(); // Returns the current user or null if not set.
  }

  // Method to check if the user is logged in. Returns true if a user is set, otherwise false.
  isLoggedIn(): boolean {
    return !!this.loginUser; // Double negation to convert the value to a boolean.
  }

  // Method to get the user ID. Returns the user ID if a user is set, otherwise null.
  getUserId(): number | null {
    return this.loginUser ? this.loginUser.userId : null; // Conditional (ternary) operator to check if user is set.
  }

  // Method to log out the user. It clears the current user data.
  logoutUser() {
    this.setUser(null); // Clear the user data by setting it to null.
  }
}
