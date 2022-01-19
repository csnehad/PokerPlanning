package com.pokerrestapi.service;

import java.util.List;
import java.util.Map;

import com.pokerrestapi.entity.Member;
import com.pokerrestapi.entity.MemberUserStory;
import com.pokerrestapi.entity.UserStory;

public interface VoteService {

	List<Map<String, List<UserStory>>> getUserStoriesWithStatus(String session);

	int submitUserStoryVote(String uStoryId,String memberId,String sessionId, Integer votePoint);

	List<Member> showMemberVotingStatus();

	List<UserStory> showUserStoryVotingStatus();

	UserStory moveUserStoryStatusToVoted(String uStoryId);

	List<MemberUserStory> stopuserStoryVoting(String uStoryId);

	int fetchVoteCountForUserStory(String uStoryId);

	int fetchVoteFinalResultForUserStory(String uStoryId,String memberId);

}
