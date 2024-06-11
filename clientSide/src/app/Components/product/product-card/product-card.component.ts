import { Component, Input, OnInit } from '@angular/core';
import { Equipement } from 'app/Models/equipement';

@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.css']
})
export class ProductCardComponent implements OnInit{
  ngOnInit(): void {
    console.log(this.equipement);
  }
  @Input() equipement: any;

  imageChange(equipment: any, index: number) {
    equipment.url = equipment.product.files[index].file;
  }



}
