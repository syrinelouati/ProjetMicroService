export class Product
{
  idProduct: any;
  name: string;
  description: string;
  state: string;
  price: number;
  available: boolean;
  size: number;
  weight: number;
  color: string;
  files:any
category: any;
  constructor(  idProduct: number, name: string, description: string, state: string, price: number, available: boolean, size: number,files:any,weight: number, color: string ){

  this.idProduct= idProduct;
  this.name= name;
  this.description= description;
  this.state= state;
  this.price= price;
  this.available= available;
  this.size= size;
  this.weight= weight;
  this.color= color;
  this.files=files;


  }

}
