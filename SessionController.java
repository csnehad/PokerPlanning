package com.pokerrestapi.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pokerrestapi.entity.Member;
import com.pokerrestapi.entity.Session;
import com.pokerrestapi.entity.UserStory;
import com.pokerrestapi.req.AddMemberRequest;
import com.pokerrestapi.req.CreatePokerSessionRequest;
import com.pokerrestapi.service.SessionService;

@RestController
@RequestMapping("/planningPoker/poker")
public class SessionController {


	@Autowired
	SessionService sessionService;

	@PostMapping("/createSession")
	public ResponseEntity<Session> createSession(@Validated @RequestBody CreatePokerSessionRequest pokSesReq) {

		try {

			Session pokerSession = sessionService.createSession(new Session(pokSesReq.getTitle(),pokSesReq.getDeckType()),pokSesReq.getMemberName());
			return new ResponseEntity<>(pokerSession, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/getAllSessions")
	public List<Session> getAllSessions() {
		List<Session> sessions=sessionService.getAllSessions();
		if(sessions.isEmpty())
			throw new RuntimeException("There are no sessions available now");
		return sessions;
	}

	@GetMapping("/getSessionBySessionId")
	public Session getSessionBySessionId(@RequestParam String sessionId) {

		Optional<Session> pokerSession=sessionService.getSessionBySessionId(sessionId);
		pokerSession.orElseThrow(() ->
		new RuntimeException("Invalid session id : " + sessionId));
		return pokerSession.get();
	}



	// get title of the session

	@GetMapping("/getTitleOfTheSession")
	public String getTitleOfTheSession(@RequestParam String sessionId) {
		return sessionService.getTitleOfTheSession(sessionId);
	}

	//add current member name to member list
	//get all member list
	@PostMapping("/addUpdateMemberList")
	public List<Member> addUpdateMemberList(@Validated @RequestBody AddMemberRequest addMemReq) {
		try {
			List<Member> memberList = sessionService.addUpdateMemberList(addMemReq.getSessionId(),addMemReq.getMemberName());

			return memberList;
		}catch(EntityNotFoundException ex) {
			throw new RuntimeException("Invalid session id : " + addMemReq.getSessionId());
		}catch(Exception ex) {
			throw new RuntimeException("Unable to Add or Update Member to this session "+ex.getMessage());
		}
	}

	//get user story list
	@GetMapping("/getUserStoryListInSession")
	public List<UserStory> getUserStoryListInSession(@RequestParam String sessionId) {

		try {
			List<UserStory> getUserStoryList= sessionService.getUserStoryListInSession(sessionId);

			if(getUserStoryList.isEmpty())
				throw new RuntimeException("UserStory not found for session "+sessionId);
			return getUserStoryList;
		}catch(EntityNotFoundException ex) {
			throw new RuntimeException("Invalid session id : " + sessionId);
		}catch(Exception ex) {
			throw new RuntimeException("Unable to fetch UserStory List "+ex.getMessage());
		}


	}

	//get member list against session
	@GetMapping("/getMembersInSession")
	public List<Member> getMembersInSession(@RequestParam String sessionId) {
		try {
			List<Member> memberList= sessionService.getMembersInSession(sessionId);

			if(memberList.isEmpty())
				throw new RuntimeException("Member not found for session "+sessionId);
			return memberList;
		}catch(EntityNotFoundException ex) {
			throw new RuntimeException("Invalid session id : " + sessionId);
		}catch(Exception ex) {
			throw new RuntimeException("Unable to fetch Member List "+ex.getMessage());
		}
	}

	@DeleteMapping("/destroyPokerSession")
	public void destroySession(@RequestParam String sessionId) {
		try {
			// get all data related to the session--pokersession,userstory,member
			//destroy all
			sessionService.destroySession(sessionId);
		}catch(Exception ex) {
			throw new RuntimeException("Invalid session id : " + sessionId);
		}

	}

}
