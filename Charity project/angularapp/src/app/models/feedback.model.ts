import { Login } from "./login.model";

export interface Feedback {
    userId?: number;
    feedbackId?: number;
    feedbackText: string;
    date?: string;
    user?: Login; // Adding user object to Feedback interface
}