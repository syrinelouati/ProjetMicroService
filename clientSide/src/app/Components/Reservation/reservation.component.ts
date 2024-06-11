import { Component, OnInit } from '@angular/core';
import { FormControl, Validators  } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Reservation } from 'app/Models/reservation.model';
import { EventService } from 'app/Services/event.service';
import { ReservationService } from 'app/Services/reservation.service';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent implements OnInit{
  reservation= new Reservation("",0,new Date(),"",0);
  idEvent : any;
  event: any;
  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.idEvent = params.get('idEvent');
      this.eventService.getEventById(this.idEvent).subscribe(
        reponse =>{

         this.event = reponse;
         this.reservation.idEvent= this.idEvent;
        },
        error=>{
          console.log("error "+error.message);
        }
      );
    });

  }

  submitReservation(){

    this.reservationService.addReservation(this.reservation).subscribe(
      reponse =>{
        console.log('Feedback added successfully');
        Swal.fire({
          position: 'top-end',
          icon: 'success',
          title: 'Your Reservation has been saved',
          showConfirmButton: false,
          timer: 2500
        });

          this.router.navigate(['/EventDetails/'+this.idEvent]);

      },
      error=>{
        console.log(error);
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'Something went wrong! Unable to add reservation',
        });
      }
    );
  }


  backendLocation: string;
  user_input!: string;
  phone_number!: string; // Add the phone_number variable
  additional_notes!: string;
  validateInput(event: any): void {
    const input = event.target.value;
    event.target.value = input.replace(/[^0-9]/g, '');
  }
    validatePhoneNumber(event: any): void {
    const input = event.target.value;
    event.target.value = input.replace(/[^0-9]/g, '');
    this.phone_number = event.target.value;
  }
  backendDate: string; // Assuming you retrieve the date from the backend as a Date object
  backendTime: string; // Assuming you retrieve the time from the backend as a string

  constructor(private route: ActivatedRoute, private reservationService: ReservationService,
    private router: Router, private eventService: EventService) {
    // Set the backendDate to a specific date
    this.backendDate='30 July 2023';

    // Set the backendTime to a specific time
    this.backendTime = '10:30 PM';
    this.backendLocation = 'Tabarka ';

  }




}


