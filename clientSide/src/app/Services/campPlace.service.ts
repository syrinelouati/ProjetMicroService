import { HttpClient, HttpHeaders, HttpParams  } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CampPlace } from 'app/Models/campPlace.model';
import { CampPlaceFilterDto } from 'app/Models/campPlaceFilterDto';
import { Page } from 'app/Models/page';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CampPlaceService {

  apiurl ="http://localhost:8088/";

  constructor(private httpClient: HttpClient){

  }


  addCampPlace(campPlace:CampPlace, file:File[]) {
    let headers = new HttpHeaders();
    headers = headers.set('Content-Type', 'multipart/form-data');

   return  this.httpClient.post(this.apiurl + 'campPlace', this.convertCampPlaceToFormData(campPlace,file)) ;

  }


  getAllCampPlace(){
    return this.httpClient.get<CampPlace[]>(this.apiurl+'campPlace');
  }
  // for home screen
  getTop5CampPlace(){
    return this.httpClient.get<CampPlace[]>(this.apiurl+'campPlace/getTop5CampPlace');
  }
  getCampPlaceCount(){
    return this.httpClient.get<number>(this.apiurl+'campPlace/campPlacesCount');
  }

  getCampPlaceCategories(){
    return this.httpClient.get<string[]>(this.apiurl+'campPlace/categories');

  }
  getState(){
    return this.httpClient.get<string[]>(this.apiurl+'campPlace/state');

  }

  convertCampPlaceToFormData(campPlace: CampPlace, files:File[]): FormData{
    const formData = new FormData();
    formData.append('idCampPlace', campPlace.idCampPlace);
    formData.append('name', campPlace.name);
    formData.append('tel', campPlace.tel.toString());
    formData.append('email', campPlace.email);
    formData.append('address', campPlace.address);
    formData.append('category', campPlace.category)
    formData.append('state', campPlace.state);
    formData.append('description', campPlace.description);
     formData.append('longitude', campPlace.longitude.toString());
      formData.append('latitude', campPlace.latitude.toString());
    //formData.append('image', files[0], files[0].name);
    for (let i = 0; i < files.length; i++) {
      //formData.append('images', files[i], files[i].name);
      formData.append('images[' + i + ']', files[i], files[i].name);

    }
    console.log(formData);
    return formData;
  }


  getFilteredCamPlace(campPlaceFilterDto: CampPlaceFilterDto, page: number, size: number): Observable<Page<CampPlace>> {

    let params = new HttpParams();

    if (campPlaceFilterDto.categories && campPlaceFilterDto.categories.length > 0) {

      params = params.append('categories', campPlaceFilterDto.categories.toString());

    }   if (campPlaceFilterDto.states && campPlaceFilterDto.states.length > 0) {

      params = params.append('states', campPlaceFilterDto.states.toString());

    }

    params = params.append('page', page.toString());

    params = params.append('size', size.toString());

    params = params.append('sort', campPlaceFilterDto.sort);

    params = params.append('search', campPlaceFilterDto.searchTerm);




    return this.httpClient.get<Page<CampPlace>>(this.apiurl + 'campPlace/filteredCampPlaces', { params });

  }
  getCampPlaceByCategory (category : any){
    return this.httpClient.get<CampPlace>(this.apiurl + 'campPlace/getSimilaireCampPlace/'+ category);
  }
  getCampPlaceById (idCampPlace : any){
    return this.httpClient.get<CampPlace>(this.apiurl + 'campPlace/'+ idCampPlace);
  }

  deleteCampPlace(id:any){

    return this.httpClient.delete<string[]>(this.apiurl+'campPlace/'+id);
  }
  updateCampPlace(campPlace: CampPlace, file:File []) {
    let headers = new HttpHeaders();
    headers = headers.set('Content-Type', 'multipart/form-data');

   return  this.httpClient.put(this.apiurl + 'campPlace', this.convertCampPlaceToFormData(campPlace,file)) ;}

  getCampPlacesSelect(){
    return this.httpClient.get<{idCampPlace:any,name:any}>(this.apiurl+'campPlace/getCampPlaceForSelect');
  }
}
