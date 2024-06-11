import { Component } from '@angular/core';
import { CampPlace } from 'app/Models/campPlace.model';
import{CampPlaceService} from 'app/Services/campPlace.service'
import { NgForm } from '@angular/forms';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-camp-place',
  templateUrl: './add-camp-place.component.html',
  styleUrls: ['./add-camp-place.component.css']
})
export class AddCampPlaceComponent {
  files: File[] = [];
  selectedCategory : any;
  campPlace: CampPlace = new CampPlace ("", "", 0,"","","",[],"",0,0);
  

  categories :string[]=[];
  states :string[]=[];
  constructor(private campPlaceService: CampPlaceService){

  }
  ngOnInit(): void {
    this.campPlaceService.getCampPlaceCategories().subscribe(
      response =>{

        this.categories = response
      },
      error =>{
        console.log(error);
      }
    );
    this.campPlaceService.getState().subscribe(
      response =>{

        this.states = response
      },
      error =>{
        console.log(error);
      }
    )
  }



  upsertCampPlace(eventNgForm: NgForm){
    if (eventNgForm.valid == false || this.files.length < 1) {
      console.log(eventNgForm.errors);
      return;
    }

 

    if(this.campPlace.idCampPlace >0){
      this.campPlaceService.updateCampPlace(this.campPlace, this.files).subscribe(
        reponse =>{
          console.log('Event updated successfully');
          Swal.fire({
            position: 'top-end',
            icon: 'success',
            title: 'Your event has been saved',
            showConfirmButton: false,
            timer: 2500
          });
          eventNgForm.resetForm();
          this.files = [];
        },
        error=>{
          console.log(error);
          Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Something went wrong! Unable to upload image',
          });
        }
      );
    }else{
      this.campPlaceService.addCampPlace(this.campPlace, this.files).subscribe(
        reponse =>{
          console.log('Event added successfully');
          Swal.fire({
            position: 'top-end',
            icon: 'success',
            title: 'Your event has been saved',
            showConfirmButton: false,
            timer: 2500
          });
          eventNgForm.resetForm();
          this.files = [];
        },
        error=>{
          console.log(error);
          Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Something went wrong! Unable to upload image',
          });
        }
      );
    }

 

  }





  addCampPlace(eventNgForm: NgForm){
    if (eventNgForm.valid == false || this.files.length < 1) {
      console.log(eventNgForm.errors);
      return;
    }
    console.log(this.selectedCategory);
    console.log(this.campPlace);
    this.campPlaceService.addCampPlace(this.campPlace, this.files).subscribe(
      reponse =>{
        console.log('Event added successfully');
        Swal.fire({
          position: 'top-end',
          icon: 'success',
          title: 'Your campPlace has been saved',
          showConfirmButton: false,
          timer: 2500
        });
        eventNgForm.resetForm();
        this.files = [];
      },
      error=>{
        console.log(error);
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'Something went wrong! Unable to upload image',
        });
      }
    );
  }


	onSelect(event: any) {
		console.log(event);
		this.files.push(...event.addedFiles);
	}

	onRemove(event: any) {
		console.log(event);
		this.files.splice(this.files.indexOf(event), 1);
	}

  formatEnumName(name: string): string {
    const formatedName = name.toLowerCase().replaceAll('_'," ");
    return formatedName;
  }

}
