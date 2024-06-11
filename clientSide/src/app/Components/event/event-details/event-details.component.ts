import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Event } from 'app/Models/event';
import { EventService } from 'app/Services/event.service';
import { formatDate } from '@angular/common';

@Component({
  selector: 'app-event-details',
  templateUrl: './event-details.component.html',
  styleUrls: ['./event-details.component.css']
})
export class EventDetailsComponent implements OnInit {
  event:any;
  relevantEvents :any;
  images : string[]=[
    "assets/zaghouan.jpg",
    "assets/bg3.jpg",
    "assets/tbarka.jpg",
    "assets/camp2.jpg"
  ];
  features : Feature[]=[]
  eventtest = new Event("Camping Night in Zaghouan","The Phi Phi Islands, located in Krabi province in southern Thailand, are a group of six islands known for their stunning natural beauty, crystal-clear waters, and white sandy beaches. The two main islands, Koh Phi Phi Don and Koh Phi Phi Leh, are the most popular among tourists. Koh Phi Phi Don is the larger of the two and is home to the main town and beaches,while Koh Phi Phi Leh is a smaller, uninhabited island known for its dramatic limestone cliffs and iconic Maya Bay, which was made famous by the movie “The Beach. Visitors can explore the islands by boat and enjoy activities such as snorkeling, diving, and island-hopping.",
  new Date(),new Date(),25,199,"WELL_BEING_CAMPING_BALNEAOTHERAPY_HAMMAM_SAUNA","");

  constructor(private eventService: EventService,private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = params.get('idEvent');
      this.eventService.getEventById(id).subscribe(
        reponse =>{
          console.log(reponse);

         this.event = reponse;
        },
        error=>{
          console.log("error "+error.message);
        }
      );
      this.eventService.getRelevantEvent(this.event.category).subscribe(
        reponse =>{
          console.log(reponse);

         this.relevantEvents = reponse;
        },
        error=>{
          console.log("error "+error.message);
        }
      );
    });


  }
  formatCategoryName(category: string): string {
    const formatedCategory = category.toLowerCase().replaceAll('_'," ");
    return formatedCategory;
  }

}

class Feature{
  constructor(public type :string, public description: string , public icon :string){

  }

}
