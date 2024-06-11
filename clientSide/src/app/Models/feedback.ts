export class Feedback{

  idFeedback: number;
  rating : number;
  comment : string;
  createAt :Date;
  idCampPlace: any;
  username?:any;


  constructor(idFeedback: number,
    rating : number,
    comment : string,
    createAt :Date,
    idCampPlace: any,
     userId?:any){
      this.idFeedback= idFeedback;
      this.comment = comment;
      this.rating = rating;
      this.createAt = createAt;
      this.idCampPlace= idCampPlace;
    }
}

