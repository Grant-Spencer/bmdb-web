package com.bmdb.web;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bmdb.business.Actor;
import com.bmdb.db.ActorRepo;

@CrossOrigin
@RestController
@RequestMapping("/actors")
public class ActorController {
	/*
	 * A controller will implement 5 CRUD methods: 1) GET ALL 2) GET BY ID 3) POST -
	 * Insert 4) PUT - Update 5) DELETE - delete
	 */

	@Autowired
	private ActorRepo actorRepo;

	@GetMapping("/")
	public List<Actor> getAll() {
		return actorRepo.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Actor> getById(@PathVariable int id) {
		return actorRepo.findById(id);
	}

	// Add a movie
	@PostMapping("/")
	public Actor addActor(@RequestBody Actor a) {
		a = actorRepo.save(a);
		return a;
	}

	// Update a movie
	@PutMapping("/")
	public Actor updateActor(@RequestBody Actor a) {
		a = actorRepo.save(a);
		return a;
	}

	// Delete a movie
	@DeleteMapping("/{id}")
	public Actor deleteActor(@PathVariable int id) {
		// Optional type will wrap a movie
		Optional<Actor> a = actorRepo.findById(id);
		// isPresent() will return true if a movie was found
		if (a.isPresent()) {
			actorRepo.deleteById(id);
		} else {
			System.out.println("Error - actor not found for id " + id);
		}
		return a.get();

	}

	@GetMapping("/find-by-gender")
	public List<Actor> getAllActors(@RequestParam String gender) {
		return actorRepo.findByGender(gender);
	}

	@GetMapping("/find-by-lastName")
	public List<Actor> findBylastName(@RequestParam String lastName) {
		return actorRepo.findByLastName(lastName);
	}

	@GetMapping("/find-lastname-starts-with")
	public List<Actor> getActorsLastNameStarts(@RequestParam String letter) {
		return actorRepo.findByLastNameLike(letter + "%");
	}

	// list all actors born between d1 and d2
	@GetMapping("/find-by-birthdate-between")
	public List<Actor> getActorsByBirthDateBetween(
			@RequestParam("ld1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate ld1,
			@RequestParam("ld2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate ld2) {
		return actorRepo.findByBirthDateBetween(ld1, ld2);
	}
}
