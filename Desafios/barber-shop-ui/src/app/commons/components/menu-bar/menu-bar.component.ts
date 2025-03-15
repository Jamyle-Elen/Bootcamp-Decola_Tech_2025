import { Component } from '@angular/core';
import { CardHeaderComponent } from "../card-header/card-header.component";
import {MatMenuModule} from '@angular/material/menu';
import { Router } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-menu-bar',
  imports: [
		CardHeaderComponent,
		MatMenuModule,
		MatButtonModule

	],
  templateUrl: './menu-bar.component.html',
  styleUrl: './menu-bar.component.scss'
})
export class MenuBarComponent {

	constructor(private readonly router: Router) {}

	navigateTo(path: string) {
		this.router.navigate([path]);
	}

}
