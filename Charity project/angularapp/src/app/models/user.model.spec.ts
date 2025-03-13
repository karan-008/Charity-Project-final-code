import { User } from './user.model';

describe('User Model', () => {
const password=crypto.randomUUID();
  fit('frontend_user model should create an instance', () => {
    // Create a sample user object
    const user: User = {
      username: 'Admin',
      email:'admin@gmail.com',
      password:password,
      role:'ADMIN'
    };

    expect(user).toBeTruthy();
    expect(user.email).toBe('admin@gmail.com');
    expect(user.password).toBe('Admin@123');

  });
});
