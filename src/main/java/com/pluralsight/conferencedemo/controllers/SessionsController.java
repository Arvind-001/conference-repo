package com.pluralsight.conferencedemo.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.pluralsight.conferencedemo.models.Session;
import com.pluralsight.conferencedemo.repositories.SessionRepository;

@RestController
@RequestMapping("api/v1/sessions")
public class SessionsController {
	
	@Autowired
	private SessionRepository sessionRepository;
	
	@GetMapping
	public List<Session> getAllSessions() {		
		return sessionRepository.findAll();
	}

	@GetMapping
	@RequestMapping("{id}")
	public Session findById(@PathVariable Long id) {
		return sessionRepository.getOne(id);
	}
	
	@PostMapping
	public Session saveSession(@RequestBody Session session) {
		return sessionRepository.saveAndFlush(session);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public void deleteById(@PathVariable Long id) {
		// Also should check for children before deleting
		sessionRepository.deleteById(id);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public Session updateById(@PathVariable Long id, @RequestBody Session session) {
		// because this is a PUT operation, we expect all the attributes to be present, and will update the 
		// entire entity attributes except session_id attribute. 
		// In line 58, we are ignoring update session_id attribute, otherwise if we update along the entity with session_id attr, 
		// then it will throw exception during update operation.  
		// IN case of PATCH operation, we will update only those attributes that are required. 
		// TODO : Should do validation and if there are any request data missing, throw 400 bad request payload
		Session existingSession = sessionRepository.getOne(id);
		BeanUtils.copyProperties(session, existingSession, "session_id");
		return sessionRepository.saveAndFlush(existingSession);		
	}
}
