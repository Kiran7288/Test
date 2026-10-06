package com.tca.api;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tca.entity.Book;
import com.tca.exception.BookAlreadyExistException;
import com.tca.exception.BookNotFoundException;
import com.tca.service.BookService;
import com.tca.util.JwtUtil;

@RestController
public class BookRESTController {
	
	@Autowired
	BookService bookService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        String token = jwtUtil.generateToken(authRequest.getUsername());
        JwtResponse jwtResp = new JwtResponse(authRequest.getUsername(), token);
        return new ResponseEntity<>(jwtResp, HttpStatus.OK);
    }
	
	
	
	
	@GetMapping( value = "/{id}",
			     produces = "application/json"
			   )
//	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Book> getBookById(@PathVariable Integer id) {
		return new ResponseEntity<Book>(bookService.fetchBookById(id), HttpStatus.OK);	
	}
	
	@GetMapping( value = "/books",
			    // produces = "application/json"
			     produces = MediaType.APPLICATION_JSON_VALUE
			   )
	public ResponseEntity<List<Book>>  getBooks() 
	{
		return new ResponseEntity<List<Book>>(bookService.fetchBooks(), HttpStatus.OK);
	}
	
	@PostMapping(value = "/create", 
			     consumes = MediaType.APPLICATION_JSON_VALUE,
			     produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Book> saveBook(@RequestBody Book book) {
		return new ResponseEntity<Book>(bookService.saveBook(book), HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void>  deleteBook(@PathVariable Integer id) {
		bookService.deleteBookById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	@ExceptionHandler(BookAlreadyExistException.class)
	public ResponseEntity<String> handleBookAlreadyExist(BookAlreadyExistException ex) {
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BookNotFoundException.class)
	public ResponseEntity<String> handleBookNotFound(BookNotFoundException ex) {
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception ex) {
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

}
