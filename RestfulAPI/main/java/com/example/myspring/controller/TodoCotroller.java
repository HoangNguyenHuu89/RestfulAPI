package com.example.myspring.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.myspring.model.ResponseObject;
import com.example.myspring.model.SubErrors;
import com.example.myspring.model.Todo;
import com.example.myspring.repository.TodoRepository;

@RestController
public class TodoCotroller {
	@Autowired
	TodoRepository todoRepository;

	@GetMapping(value = "/")
	public List<Todo> getAll() {
		return (List<Todo>) todoRepository.findAll();
	}

	@GetMapping(value = "/todos")
	public List<Todo> getByName(@RequestParam(name = "title") String title) {
		return (List<Todo>) todoRepository.findByTitleContaining(title);
	}

	@GetMapping("/todos/{id}")
	public ResponseEntity<ResponseObject> getByID(@PathVariable Long id) {
		Optional<Todo> todo = todoRepository.findById(id);
		LocalDateTime date = LocalDateTime.now();
		if (!todo.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObject("NOT_FOUND", "Todo was not found for parameters " + id, date, todo));
		}
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Find todo with  parameters " + id + " success", date, todo));
	}

	@PutMapping("/todos/{id}")
	public ResponseEntity<ResponseObject> update(@PathVariable Long id, @RequestBody Todo t) {
		LocalDateTime date = LocalDateTime.now();
		SubErrors subErros = new SubErrors("todo", "title", null, "must not null");
		if (t.getTitle().equals("")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ResponseObject("BAD_REQUEST", "Validation Errors", date,subErros));
		}
		Optional<Todo> todoUpdate = todoRepository.findById(id).map(todo -> {
			todo.setTitle(t.getTitle());
		    return todoRepository.save(todo);
		});
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseObject("OK", "Update success", date));
	}

	@PostMapping("/todos")
	public void add(@RequestBody Todo t) {
		todoRepository.save(t);
	}

	@DeleteMapping("/todos/{id}")
	public void delete(@PathVariable Long id) {
		todoRepository.deleteById(id);
	}
}
