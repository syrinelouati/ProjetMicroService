import { Component } from '@angular/core';

@Component({
  selector: 'app-google-map',
  templateUrl: './google-map.component.html',
  styleUrls: ['./google-map.component.css']
})
export class GoogleMapComponent {

  display : any;
  center: google.maps.LatLngLiteral = {lat: 36.866257, lng: 10.164836};
  zoom = 16;
  markerOptions :google.maps.MarkerOptions = {draggable: false};
  moveMap(event: google.maps.MapMouseEvent) {
    if(event.latLng!= null)
    this.center = (event.latLng.toJSON());
  }

  move(event: google.maps.MapMouseEvent) {
    if(event.latLng != null)
    this.display = event.latLng.toJSON();
  }
}
