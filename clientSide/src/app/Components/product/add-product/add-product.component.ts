import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Product } from 'app/Models/Product.model';
import { productservice } from 'app/Services/product.service';
import Swal from 'sweetalert2';



@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit{
  files: File[] = [];
  selectedCategory : any;

  product: Product = new Product(1, "", "", "",0,false, 0,[],0,"");

  categories :string[]=[];

  constructor(private productService: productservice,private route: ActivatedRoute){

  }
  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.product.idProduct = params.get('idProduct');
    });
    this.productService.getProductCategories().subscribe(
      response =>{

        this.categories = response
      },
      error =>{
        console.log(error);
      }
    )
  }


  upsertProduct(eventNgForm: NgForm){
    if (eventNgForm.valid == false || this.files.length < 1) {
      console.log(eventNgForm.errors);
      return;
    }

    if(this.product.idProduct >0){
      this.productService.updateProduct(this.product, this.files).subscribe(
        reponse =>{
          console.log('Product updated successfully');
          Swal.fire({
            position: 'top-end',
            icon: 'success',
            title: 'Your product has been saved',
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
      this.productService.addProduct(this.product, this.files).subscribe(
        reponse =>{
          console.log('Product added successfully');
          Swal.fire({
            position: 'top-end',
            icon: 'success',
            title: 'Your product has been saved',
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





  onSelect(product: any) {
    console.log(product);
    this.files.push(...product.addedFiles);
  }

  onRemove(product: any) {
    console.log(product);
    this.files.splice(this.files.indexOf(product), 1);
  }

  formatCategoryName(category: string): string {
    const formatedCategory = category.toLowerCase().replaceAll('_'," ");
    return formatedCategory;
  }

  }


