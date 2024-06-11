import { NgModule } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { CarouselModule } from '@coreui/angular';
import { GoogleMapsModule } from '@angular/google-maps';
import { EventDetailsComponent } from './event-details/event-details.component';
import { AnimatedCardComponent } from './animated-card/animated-card.component';
import { GoogleMapComponent } from './google-map/google-map.component';
import { EventListComponent } from './event-list/event-list.component';
import {MatChipsModule} from '@angular/material/chips';
import {MatSliderModule} from '@angular/material/slider';
import { NgxSliderModule } from '@angular-slider/ngx-slider';
import { EventCardComponent } from './event-card/event-card.component';
import { AddEventComponent } from './add-event/add-event.component';
import { FormsModule } from '@angular/forms';
import { NgxDropzoneModule } from 'ngx-dropzone';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatIconModule} from '@angular/material/icon';
import {MatMenuModule} from '@angular/material/menu';
import {MatButtonModule} from '@angular/material/button';
import { PagingHeaderComponent } from 'app/Shared/paging-header/paging-header.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { RouterModule } from '@angular/router';

import { SafePipe } from 'app/pipes/SafePipe';


import { EventTableComponent } from './event-table/event-table.component';
import {MatTableModule} from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';

@NgModule({
    declarations: [
        EventDetailsComponent,
        AnimatedCardComponent,
        GoogleMapComponent,
        EventListComponent,
        EventCardComponent,
        AddEventComponent,
        PagingHeaderComponent,
        SafePipe,
        EventTableComponent


    ],
    exports: [
        EventDetailsComponent,
        GoogleMapComponent,
        EventListComponent,
        EventCardComponent,
        AddEventComponent,
        PagingHeaderComponent,
        SafePipe,
        EventTableComponent


    ],
    providers: [DatePipe],
    imports: [
        FormsModule,
        CarouselModule,
        GoogleMapsModule,
        MatChipsModule,
        MatSliderModule,
        NgxDropzoneModule,
        MatExpansionModule,
        MatButtonModule,
        MatMenuModule,
        MatIconModule,
        NgxPaginationModule,
        CommonModule,
        RouterModule,
        MatTableModule,
        MatPaginatorModule,
        NgxSliderModule

    ]
})
export class EventModule { }
