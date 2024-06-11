import { Component, HostListener, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'app/Models/user';
import { AuthenticationService } from 'app/Services/authentication.service';
import { NotificationService } from 'app/Services/notification.service';
import { NotificationType } from 'app/enum/notification-type.enum';
import { Role } from 'app/enum/role.enum';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  public user: User = new User;
  isLoggedIn: boolean = false;

  constructor(private router: Router, private authenticationService: AuthenticationService,
   private notificationService: NotificationService) {}

   ngOnInit(): void {
    this.authenticationService.isUserLoggedIn();
    const user = this.authenticationService.getUserFromLocalCache();
    if (user !== null) {
      this.user = user;
    }
  }

  onLogIn(): void {
    this.authenticationService.isUserLoggedIn();
    this.isLoggedIn = true;
    //this.router.navigate(['/login']);
    this.sendNotification(NotificationType.SUCCESS, `You've been successfully logged in`);
  }

  onLogOut(): void {
    this.authenticationService.logOut();
    this.isLoggedIn = false;
    this.router.navigate(['/login']);
    this.sendNotification(NotificationType.SUCCESS, `You've been successfully logged out`);
  }

  @HostListener('window:scroll', ['$event'])

onWindowScroll() {
    let element = document.querySelector('.navbar') as HTMLElement;
    if (window.pageYOffset > element.clientHeight) {
      element.classList.add('navbar-inverse');
    } else {
      element.classList.remove('navbar-inverse');
    }
  }

  public get isAdmin(): boolean {
    return this.getUserRole() === Role.SUPER_ADMIN;
  }

  private getUserRole(): string {
    const user = this.authenticationService.getUserFromLocalCache();
    if (user !== null) {
      return user.role;
    }
    return '';
      }


  private sendNotification(notificationType: NotificationType, message: string): void {
    if (message) {
      this.notificationService.notify(notificationType, message);
    } else {
      this.notificationService.notify(notificationType, 'An error occurred. Please try again.');
    }
  }
}
