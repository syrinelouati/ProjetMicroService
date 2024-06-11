import { Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { Router } from '@angular/router';
import { FeedbackService } from 'app/Services/feedback.service';
import { ReservationService } from 'app/Services/reservation.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-reservation-list',
  templateUrl: './reservation-list.component.html',
  styleUrls: ['./reservation-list.component.css']
})
export class ReservationListComponent {
  displayedColumns:string[]=['Id','Event','user','createAt','comment','edit'];

  @ViewChild(MatPaginator) paginator!:MatPaginator;


  reservations : any;
  selectedOptions: { [id: number]: boolean } = {};


  constructor(private router: Router, private reservationService :ReservationService) {}
  ngOnInit(): void {
    this.getReservations();
  }


  getReservations(){

    this.reservationService.getAll().subscribe(
      response =>{
        console.log(response);

        this.reservations = response;
        console.log(response);

      },
      error=>{
        console.log("error"+error.message);
      }
    )
  }






onReset(){
  this.getReservations();
}



  deleteFeedback(reservation: any){
    console.log("delete method");
    Swal.fire({
      title: 'Are you sure?',
      text: "You won't be able to revert this!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
      if (result.isConfirmed) {

        this.reservationService.deleteReservation(reservation.idReservation).subscribe(
          reponse =>{
            Swal.fire(
              'Deleted!',
              reservation.idReservation+' has been deleted.',
              'success'
            );
            this.getReservations();
          },
          error=>{
            console.log(error);
            Swal.fire({
              icon: 'error',
              title: 'Oops...',
              text: 'Something went wrong! Unable to delete the reservation',
            });
          }


          );

      }
    })




  }
}
