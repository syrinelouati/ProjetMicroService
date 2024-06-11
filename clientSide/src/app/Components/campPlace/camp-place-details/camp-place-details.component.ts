import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CampPlace } from 'app/Models/campPlace.model';
import { CampPlaceService } from 'app/Services/campPlace.service';

@Component({
  selector: 'app-camp-place-details',
  templateUrl: './camp-place-details.component.html',
  styleUrls: ['./camp-place-details.component.css']
})
export class CampPlaceDetailsComponent implements OnInit{
 campPlaces : any;
constructor(private route: ActivatedRoute, private campPlaceService : CampPlaceService) { } 
  campPlace : any;
  selectedCampImage : any;
  ngOnInit(): void {
this.route.paramMap.subscribe(params => {

      const id = params.get('idCampPlace');
      this.campPlaceService.getCampPlaceById(id).subscribe(

        reponse =>{

          this.campPlace= reponse
          this.selectedCampImage= this.campPlace.images[0].image;
         
          console.log(this.campPlace);
          this.campPlaceService.getCampPlaceByCategory(this.campPlace.category).subscribe(reponse =>{
 
            this.campPlaces= reponse;
            console.log(this.campPlaces.length);
 
     },
 
     error=>{
 
       console.log("error"+error);
 
     });
        },

        error=>{

          console.log("error"+error.message);

        }

      );

    });

       


  }


  imageList: string[] = [

    'https://images.pexels.com/photos/16952091/pexels-photo-16952091/free-photo-of-wood-landscape-field-summer.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1',

    'https://images.pexels.com/photos/13713058/pexels-photo-13713058.jpeg?auto=compress&cs=tinysrgb&w=600&lazy=load',

    'https://images.pexels.com/photos/6732034/pexels-photo-6732034.jpeg?auto=compress&cs=tinysrgb&w=600&lazy=load',

    'https://images.pexels.com/photos/9083457/pexels-photo-9083457.jpeg?auto=compress&cs=tinysrgb&w=600&lazy=load',

    // Add more images to the list

  ];
  //campPlace = new CampPlace('bni mtir','FORET',28315715,'aaaaa','aaaa','BEJA',this.imageList,"Your ultimate destination for camping enthusiasts in Tunisia! We are passionate about providing you with the best camping experiences and offering a platform for buying and selling camping equipment  we curate exciting camping events across Tunisia, bringing together nature lovers and adventure seekers. Whether you're looking for camping trips with direct access to the beach, camping sites with heated swimming pools, or unique accommodations, we have something for everyone ",1,1);
  
 selectImage(image: string) {

    this.selectedCampImage = image;
    

  }



}
