package com.market.marketplace;

import com.market.marketplace.model.Comment;
import com.market.marketplace.model.Product;
import com.market.marketplace.service.CommentService;
import com.market.marketplace.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductResource {
    private final ProductService productService;
    private final CommentService commentService;


    public ProductResource(ProductService productService, CommentService commentService) {
        this.productService = productService;

        this.commentService = commentService;
    }

    @GetMapping("/all")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = productService.findAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id){
        Product product = productService.findProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    @PostMapping("/add")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        Product newProduct = productService.addProduct(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }
    @PutMapping("/update")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product){
        Product updateProduct = productService.updateProduct(product);
        return new ResponseEntity<>(updateProduct, HttpStatus.OK);
    }
    @Transactional
    @DeleteMapping("/delete/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id){
        System.out.println(id);
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping("/comments/{id}")
//    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/comment/all")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Comment>> getAllComments(){
        List<Comment> comments = commentService.findAllComments();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/comment/find/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Comment>> getProductComments(@PathVariable("id") Long productId){
        List<Comment> comments = commentService.getProductComments(productId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PostMapping("/comment/add")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment){
        Comment newComment = commentService.addComment(comment);
        return new ResponseEntity<>(newComment, HttpStatus.CREATED);
    }

    @Transactional
    @DeleteMapping("/comment/delete/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> deleteComment(@PathVariable("id") Long id){
        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @RestController
//    @RequestMapping("/comments")
//    public static class CommentResource {
//        private final CommentService commentService;
//
//        public CommentResource(CommentService commentService) {
//            this.commentService = commentService;
//        }
//
//        @GetMapping("/all")
//        @CrossOrigin(origins = "http://localhost:4200")
//        public ResponseEntity<List<Comment>> getAllComments(){
//            List<Comment> comments = commentService.findAllComments();
//            return new ResponseEntity<>(comments, HttpStatus.OK);
//        }
//
//        @GetMapping("/find/{id}")
//        @CrossOrigin(origins = "http://localhost:4200")
//        public ResponseEntity<List<Comment>> getProductComments(@PathVariable("id") Long productId){
//            List<Comment> comments = commentService.getProductComments(productId);
//            return new ResponseEntity<>(comments, HttpStatus.OK);
//        }
//
//
//    }
}
    