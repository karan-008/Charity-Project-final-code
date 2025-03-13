import { Component, ViewChild, ElementRef, AfterViewInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-adminnav',
  templateUrl: './adminnav.component.html',
  styleUrls: ['./adminnav.component.css']
})
export class AdminnavComponent implements AfterViewInit {
  @ViewChild('charitySelect') charitySelect!: ElementRef;

  constructor(private readonly router: Router) {}

  ngAfterViewInit(): void {
    this.router.events.pipe(
      filter(event => event instanceof NavigationEnd)
    ).subscribe(() => {
      if (this.router.url === '/adminviewcharity') {
        this.charitySelect.nativeElement.value = 'view';
      } else if (this.router.url === '/createcharity') {
        this.charitySelect.nativeElement.value = 'add';
      } else {
        this.charitySelect.nativeElement.value = 'select';
      }
    });
  }

  handleCharityChange(event: string) {
    const selectedValue = event;
    if (selectedValue === 'add') {
      this.addCharity();
    } else if (selectedValue === 'view') {
      this.viewCharity();
    }
  }

  addCharity() {
    this.router.navigate(['/createcharity']);
  }

  viewCharity() {
    this.router.navigate(['/adminviewcharity']);
  }

  resetSelect(selectElement: HTMLSelectElement) {
    selectElement.value = 'select';
  }
}
