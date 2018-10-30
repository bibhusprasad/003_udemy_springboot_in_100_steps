package com.springboot100steps.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springboot100steps.model.Question;
import com.springboot100steps.model.Survey;
import com.springboot100steps.service.SurveyService;

@RestController
public class SurveyController {

	@Autowired
	private SurveyService surveyService;

	@GetMapping(path = "/surveys")
	public List<Survey> getAllSurveys() {
		return surveyService.retrieveAllSurveys();
	}

	@GetMapping(path="/surveys/{surveyId}")
	public Survey getSurveyById(@PathVariable String surveyId) {
		return surveyService.retrieveSurvey(surveyId);
	}

	@GetMapping(path="/surveys/{surveyId}/questions")
	public List<Question> getAllQuestions(@PathVariable String surveyId){
		return surveyService.retrieveQuestions(surveyId);
	}
	
	@GetMapping(path="/surveys/{surveyId}/questions/{questionId}")
	public Question getQuestionById(@PathVariable String surveyId, @PathVariable String questionId){
		return surveyService.retrieveQuestion(surveyId, questionId);
	}
	
	@PostMapping(path="/surveys/{surveyId}/questions")
	public ResponseEntity<Void> addQuestion(@PathVariable String surveyId, @RequestBody Question newQuestion){
		Question question = surveyService.addQuestion(surveyId, newQuestion);
		if(null == question) {
			ResponseEntity.noContent().build();
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(question.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
}
