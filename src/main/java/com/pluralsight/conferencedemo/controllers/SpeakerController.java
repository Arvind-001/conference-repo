package com.pluralsight.conferencedemo.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pluralsight.conferencedemo.models.Speaker;
import com.pluralsight.conferencedemo.repositories.SpeakerRepository;

@RestController
@RequestMapping("api/v1/speakers")
public class SpeakerController {

	@Autowired
	private SpeakerRepository speakerRepository;
	
	@GetMapping
	public List<Speaker> getAllSpeakers() {
		return speakerRepository.findAll();
	}
	
	@GetMapping
	@RequestMapping("{id}")
	public Speaker findById(@PathVariable Long id) {
		return speakerRepository.getOne(id);
	}
	
	@PostMapping
	public Speaker saveSpeaker(@RequestBody final Speaker speaker) {
		return speakerRepository.saveAndFlush(speaker);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public void deleteBySpeakerId(@PathVariable Long id) {
		speakerRepository.deleteById(id);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT) 
	public Speaker update(@PathVariable Long id, @RequestBody Speaker speaker) {
		// because this is a PUT operation, we expect all the attributes to be present, and will update the 
		//entire entity attributes except speaker_id attribute. 
		// In line 54, we are ignoring update speaker_id attribute, otherwise when we update the entity then it will throw exception.  
		// IN case of PATCH operation, we will update only those attributes that are required. 
		// TODO : Should do validation and if there are any request data missing, throw 400 bad request payload
		Speaker existingSpeaker = speakerRepository.getOne(id);
		BeanUtils.copyProperties(speaker, existingSpeaker, "speaker_id");
		return speakerRepository.saveAndFlush(existingSpeaker);
	}
	
	
}
