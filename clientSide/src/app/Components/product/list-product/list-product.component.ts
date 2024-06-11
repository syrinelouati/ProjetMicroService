import { Component } from '@angular/core';
import { ProductFilterDto } from 'app/Models/ProductFilterDto';
import { productservice } from 'app/Services/product.service';
import { Options } from "@angular-slider/ngx-slider";
@Component({
  selector: 'app-list-product',
  templateUrl: './list-product.component.html',
  styleUrls: ['./list-product.component.css']
})
export class ListProductComponent {
  products : any;
  ProductFilter = new ProductFilterDto([],0.0,500,"name,asc","");
  selectedOptions: { [id: number]: boolean } = {};
  categories :any;
  selectedCategories: any;
  panelOpenState = false;
  /// slider variables
  options: Options = {
    floor: 0,
    ceil: 500,
    step:10
  };
  pageSize = 6;
  p: number = 1;
  totalItems = 1;

  sortOptions = [
    {name: 'Alphabetical', value: 'name,asc',icon: "sort_by_alpha"},
    {name: 'Price: Low to high', value: 'price,asc' ,icon: "arrow_upward"},
    {name: 'Price: High to low', value: 'price,desc', icon:"arrow_downward"},
  ];
  constructor(private eventServive : productservice){
  }

  ngOnInit(): void {
    this.eventServive.getProductCategories().subscribe(
      response =>{
        this.categories = response
      },
      error =>{
        console.log(error.message);
      }
    );

    this.getFilteredProducts();
  }


  getFilteredProducts(){

    this.eventServive.getFilteredProducts(this.ProductFilter,this.p-1, this.pageSize).subscribe(
      response =>{
        console.log(response);

        this.products = response.content;
        this.totalItems = response.totalElements;
        console.log(response.totalElements);

      },
      error=>{
        console.log("error"+error.message);
      }
    )
  }

  formatCategoryName(category: string): string {
    const formatedCategory = category.toLowerCase().replaceAll('_'," ");
    return formatedCategory;
  }

  toggleCategorySelection(category: any) {
    if(this.ProductFilter.categories){
    const index = this.ProductFilter.categories.indexOf(category);
    if (index >= 0) {
      this.ProductFilter.categories.splice(index, 1); // Category is already selected, so remove it
    } else {
      this.ProductFilter.categories.push(category); // Category is not selected, so add it
    }
  }
  console.log(this.ProductFilter)
  this.getFilteredProducts();

}

onPageChange(event: any) {
  console.log(event);
  this.p = event;
  this.getFilteredProducts();
}

clearFilter(){
  console.log("clean data");
  this.ProductFilter = new ProductFilterDto([],0.0,500,"name,asc");
  this.p = 1;
  this.selectedCategories= [];
  this.getFilteredProducts();
}


onReset(){
  this.ProductFilter.searchTerm ="";
  this.getFilteredProducts();
}

selectSortOption(value : string){
  this.ProductFilter.sort = value;
  this.getFilteredProducts();
}

}
