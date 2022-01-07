package com.example.springboot.controller;

import com.example.springboot.models.ProductModel;
import com.example.springboot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Here we are using @RestController which returns only data
@RestController
public class ProductController {

    //@Autowired to inject dependency
    //Here we are injecting field dependency
    //Here we write "required=false" in @Autowired annotation argument
    //Which means that we did not provide implementation of interface but still we need to inject dependency
    //This will allow to inject dependency without giving implementation of interface
    @Autowired
    ProductRepository productRepository;

    //@RequestMapping to make URL and set request type GET
    //GET is mainly use to get the value and if we want to pass any parameters then we need to pass in URL
    @RequestMapping(value="/product",method = RequestMethod.GET)
    public List<ProductModel> getProducts(){

        System.out.println("Check ProductRepository object:"+productRepository);
        //Here we are using by default method of JPA Repository findAll() to get all the data of the table
        return productRepository.findAll();
    }

    //Here we are providing {id} in URL which means that we are requesting with giving "id"
    @RequestMapping(value = "/product/{id}",method = RequestMethod.GET)
    //@PathVariable("id") used to get "id" from URL
    public ProductModel getProduct(@PathVariable("id") int id){

        //Here we are using by default method of JPA Repository findById() which take argument as an id and give record by given id
        return productRepository.findById(id).get();
    }

    //Here we are providing POST as request type
    //POST is mainly use to send the data and if we want to pass parameters then we need to pass in request body
    @RequestMapping(value = "/createproduct",method = RequestMethod.POST)
    public ProductModel createProduct(@RequestBody ProductModel productModel){

        //Here we are using save() method to save data in database
        return productRepository.save(productModel);
    }

    //Here we are providing PUT as request type
    //PUT is mainly use to update the data and if we want to pass parameters then we need to pass in request body
    @RequestMapping(value = "/updateproduct",method = RequestMethod.PUT)
    public ProductModel updateProduct(@RequestBody ProductModel productModel){

        //Here we are using save() method to save data in database
        //It finds the data in database if there is available this data then update otherwise insert new record
        return productRepository.save(productModel);
    }

    //Here we are providing {id} in URL which means that we are requesting with giving "id"
    //Here we are using DELETE as request type
    //DELETE mainly used to delete the data
    @RequestMapping(value = "/deleteproduct/{id}",method = RequestMethod.DELETE)
    //@PathVariable("id") used to get "id" from URL
    public void deleteProduct(@PathVariable("id") int id){

        //Here we are using by default method of JPA Repository deleteById() which take argument as an id and delete record by given id
        productRepository.deleteById(id);
    }
}
