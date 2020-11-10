package com.bmdb.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bmdb.business.Credit;
import com.bmdb.db.CreditRepo;

@CrossOrigin
@RestController
@RequestMapping("/credits")
public class CreditController {
	/*
	 * A controller will implement 5 CRUD methods: 1) GET ALL 2) GET BY ID 3) POST -
	 * Insert 4) PUT - Update 5) DELETE - delete
	 */

	@Autowired
	private CreditRepo creditRepo;

	@GetMapping("/")
	public List<Credit> getAll() {
		return creditRepo.findAll();
	}

	// GET Credit BY ID
	@GetMapping("/{id}")
	public Optional<Credit> getCredit(@PathVariable int id) {
		Optional<Credit> c = creditRepo.findById(id);
		if (c.isPresent()) {
		}
		return c;
	}

	// Add a movie
	@PostMapping("/")
	public Credit addCredit(@RequestBody Credit a) {
		a = creditRepo.save(a);
		return a;
	}

	// Update a movie
	@PutMapping("/")
	public Credit updateCredit(@RequestBody Credit a) {
		a = creditRepo.save(a);
		return a;
	}

	@DeleteMapping("/{id}")
	public Credit deleteCredit(@PathVariable int id) {
		Optional<Credit> a = creditRepo.findById(id);
		if (a.isPresent()) {
			creditRepo.deleteById(id);
		} else {
			System.out.println("Error - credit not found" + id);
		}
		return a.get();
	}
}