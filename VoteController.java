package com.pokerrestapi.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pokerrestapi.entity.Member;
import com.pokerrestapi.entity.MemberUserStory;
import com.pokerrestapi.entity.UserStory;
import com.pokerrestapi.req.SubmitVoteRequest;
import com.pokerrestapi.service.VoteService;

@RestController
@RequestMapping("/planningPoker/voting")
public class VoteController {

	
	@Autowired
	VoteService voteService;
	
	@GetMapping("/getUserStoriesWithStatus")
	public List<Map<String, List<UserStory>>> getUserStoriesWithStatus(@RequestParam String sessionId) {
		List<Map<String, List<UserStory>>> listStoriesWithStatus = voteService.getUserStoriesWithStatus(sessionId);

		return listStoriesWithStatus;
	}
	
	@PostMapping("/submitUserStoryVote")
	public int submitUserStoryVote( @Validated @RequestBody SubmitVoteRequest req ) {

		return voteService.submitUserStoryVote(req.getuStoryId(),req.getMemberId(),req.getSessionId(),req.getVotePoint());
	}

	@GetMapping("/showMemberVotingStatus")
	public List<Member> showMemberVotingStatus() {

		return voteService.showMemberVotingStatus();
	}
	
	@GetMapping("/showUserStoryVotingStatus")
	public List<UserStory> showUserStoryVotingStatus() {

		return voteService.showUserStoryVotingStatus();
	}
	
	@PostMapping("/moveStoryStatusToVoted")
	public UserStory moveUserStoryStatusToVoted(@RequestParam String uStoryId) {

		return voteService.moveUserStoryStatusToVoted(uStoryId);
	}
	@PostMapping("/stopuserStoryVoting")
	public List<MemberUserStory> stopuserStoryVoting(@RequestParam String uStoryId) {
		return voteService.stopuserStoryVoting(uStoryId);
	}
	
	@GetMapping("/fetchVoteCountForUserStory")
	public int fetchVoteCountForUserStory(@RequestParam String uStoryId) {
		return voteService.fetchVoteCountForUserStory(uStoryId);
	}
	
	@GetMapping("/fetchVoteFinalResultForUserStory")
	public int fetchVoteFinalResultForUserStory(@RequestParam String uStoryId,@RequestParam String memberId) {
		return voteService.fetchVoteFinalResultForUserStory(uStoryId,memberId);
	}
}
