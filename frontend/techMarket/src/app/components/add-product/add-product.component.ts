import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../services/product.service';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ProductCategory } from '../../common/product-category';
import { NewProduct } from '../../common/new-product';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrl: './add-product.component.css'
})
export class AddProductComponent implements OnInit {

  addProductFormGroup!: FormGroup;
  productCategories: ProductCategory[] = [];

  constructor(private productService: ProductService,
    private formBuilder: FormBuilder,
    private router: Router) { }

  ngOnInit() {
    this.listProductCategories();

    this.addProductFormGroup = this.formBuilder.group({
      product: this.formBuilder.group({
        name: new FormControl('', [Validators.required, Validators.minLength(2)]),
        description: new FormControl('', [Validators.required]),
        unitPrice: new FormControl('', [Validators.required, Validators.pattern(/^\d+(\.\d{1,2})?$/)]),
        stock: new FormControl('', [Validators.required, Validators.pattern(/^[1-9]\d*$/)]),
        category: new FormControl('', [Validators.required])
      })
    });
  }

  listProductCategories() {
    this.productService.getProductCategories().subscribe(
      data => {
        this.productCategories = data;
      }
    );
  }

  onSubmit() {
    if (this.addProductFormGroup.invalid) {
      this.addProductFormGroup.markAllAsTouched();
      return;
    }

    const productForm = this.addProductFormGroup.get('product');

    if (productForm) {
      const newProduct = new NewProduct(
        productForm.get('name')?.value,
        productForm.get('description')?.value,
        productForm.get('unitPrice')?.value,
        '', // Adjust as needed, imageUrl not present in the form
        productForm.get('stock')?.value,
        productForm.get('category')?.value
      );

      this.productService.addProduct(newProduct).subscribe({
        next: addedProduct => {
          // Handle success, e.g., display a success message or navigate to a different page
          alert('Product added successfully');
          this.addProductFormGroup.reset();

          this.router.navigateByUrl("/products");
        },
        error: err => {
          // Handle error, e.g., display an error message
          alert(`Error adding product`);
        }
      });
    }
  }

  public get name() { return this.addProductFormGroup.get('product.name'); }
  public get description() { return this.addProductFormGroup.get('product.description'); }
  public get unitPrice() { return this.addProductFormGroup.get('product.unitPrice'); }
  public get stock() { return this.addProductFormGroup.get('product.stock'); }
  public get category() { return this.addProductFormGroup.get('product.category'); }
}
