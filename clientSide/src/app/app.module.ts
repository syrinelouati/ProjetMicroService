import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { HeaderComponent } from './Shared/header/header.component';
import { FooterComponent } from './Shared/footer/footer.component';
import { NotFoundComponent } from './Components/not-found/not-found.component';
import { HomeComponent } from './Components/home/home.component';
import { NgxDropzoneModule } from 'ngx-dropzone';
import {MatTableModule} from '@angular/material/table';
import { AddProductComponent } from './Components/product/add-product/add-product.component';
import { FormsModule } from '@angular/forms';
import {MatButtonModule} from '@angular/material/button';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CarouselModule } from 'ngx-owl-carousel-o';
import {EventModule} from './Components/event/event.module';
import { CommonModule } from '@angular/common';
import { ProductDetailsComponent } from './Components/product/product-details/product-details.component';
import { ProductCardComponent } from './Components/product/product-card/product-card.component';
import { ListProductComponent } from './Components/product/list-product/list-product.component';

import { AddCampPlaceComponent } from './Components/campPlace/add-camp-place/add-camp-place.component';
import { CampPlaceDetailsComponent } from './Components/campPlace/camp-place-details/camp-place-details.component';
import { CampPlaceCardComponent } from './Components/campPlace/camp-place-card/camp-place-card.component';
import { CampPlaceListComponent } from './Components/campPlace/camp-place-list/camp-place-list.component';
import {MatChipsModule} from '@angular/material/chips';
import {MatIconModule} from '@angular/material/icon';
import {MatMenuModule} from '@angular/material/menu';
import { NgxPaginationModule } from 'ngx-pagination';
import { CampPlaceTableComponent } from './Components/campPlace/camp-place-table/camp-place-table.component';
import { ToastrModule } from 'ngx-toastr';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';


import { AuthenticationService } from './Services/authentication.service';
import { UserService } from './Services/user.service';
import { AuthInterceptor } from './interceptor/auth.interceptor';
import { AuthenticationGuard } from './guard/authentication.guard';
import { NotificationModule } from './notification.module';
import { NotificationService } from './Services/notification.service';
import { LoginComponent } from './Components/login/login.component';
import { RegisterComponent } from './Components/register/register.component';
import { UserComponent } from './Components/user/user.component';
import { ResetPasswordComponent } from './Components/reset-password/reset-password.component';

import { PagingHeaderComponent } from './Shared/paging-header/paging-header.component';
import { NgxSliderModule } from '@angular-slider/ngx-slider';

import { ProductTableComponent } from './Components/product/product-table/product-table.component';
import { EditUserComponent } from './Components/edit-user/edit-user.component';
import { FeedbackComponent } from './Components/feedback/feedback.component';
import { ReservationComponent } from './Components/Reservation/reservation.component';
import { FeedbackListComponent } from './feedback-list/feedback-list.component';
import { ReservationListComponent } from './reservation-list/reservation-list.component';

@NgModule({

  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    NotFoundComponent,
    HomeComponent,
    AddProductComponent,
    ProductDetailsComponent,
    ProductCardComponent,
    ListProductComponent,
    AddCampPlaceComponent,
    CampPlaceDetailsComponent,
    CampPlaceCardComponent,
    CampPlaceListComponent,
    CampPlaceTableComponent,
    LoginComponent,
    RegisterComponent,
    UserComponent,
    ResetPasswordComponent,
    EditUserComponent,
    ProductTableComponent,
    FeedbackComponent,
    ReservationComponent,
    FeedbackListComponent,
    ReservationListComponent
    ],

  imports: [
    CommonModule,
    EventModule,
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    NgxDropzoneModule,
    HttpClientModule,
    BrowserAnimationsModule,
    CarouselModule,
    MatChipsModule,
    MatButtonModule,
    MatMenuModule,
    MatIconModule,
    NgxSliderModule,
    NgxPaginationModule,
    NgbModule,
    MatTableModule,
    NgxPaginationModule,
    NotificationModule,
    ToastrModule.forRoot(), // ToastrModule added
  ],
  providers: [NotificationService, AuthenticationGuard, AuthenticationService, UserService,
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true } ],
  bootstrap: [AppComponent]

})
export class AppModule { }
