import { Component, Input, OnInit } from '@angular/core';
import { EventService } from 'app/Services/event.service';

@Component({
  selector: 'app-animated-card',
  templateUrl: './animated-card.component.html',
  styleUrls: ['./animated-card.component.css']
})
export class AnimatedCardComponent implements OnInit {

  @Input() category: any;
  relevantEvents: any;
  constructor(private eventService: EventService) { }
  ngOnInit(): void {

      this.eventService.getRelevantEvent(this.category).subscribe(
        reponse =>{
          console.log(reponse);

         this.relevantEvents = reponse;
        },
        error=>{
          console.log("error "+error.message);
        }
      );
    }


  }


