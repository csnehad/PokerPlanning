package com.pokerrestapi.serviceimpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pokerrestapi.constant.PlanningPokerConstant;
import com.pokerrestapi.entity.Member;
import com.pokerrestapi.entity.MemberUserStory;
import com.pokerrestapi.entity.UserStory;
import com.pokerrestapi.entity.VotingStatus;
import com.pokerrestapi.repository.MemberRepository;
import com.pokerrestapi.repository.MemberUserStoryRepository;
import com.pokerrestapi.repository.UserStoryRepository;
import com.pokerrestapi.service.VoteService;

@Service
public class VoteServiceImpl implements VoteService {

	
	@Autowired
	MemberRepository memberRepository;

	@Autowired
	UserStoryRepository userStoryRepository;

	@Autowired
	MemberUserStoryRepository memberUserStoryRepository;

	@Override
	public List<Map<String, List<UserStory>>> getUserStoriesWithStatus(String session) {

		//fetch all vote status
		//List<VoteStatus> voteStatuses = voteStatusRepository.findAll();
		//fetch userstory against each status
		//voteStatuses.stream().collect(Collectors.groupingBy(VoteStatus::getStatusId));
		Map<String, List<UserStory>> map = null;
		List<UserStory> userStories = null;
		/*
		 * for(VoteStatus voteStatus:voteStatuses) { if(map.get(voteStatus.getStatus())
		 * == null) { userStories = new ArrayList<UserStory>(); map = new
		 * HashMap<String, List<UserStory>>(); } map.put(voteStatus.getStatus(),
		 * userStories); }
		 */


		return null;
	}

	@Override
	public int submitUserStoryVote(String uStoryId,String memberId,String session,Integer votePoint) {

		//get userstory by uStoryId
		UserStory uStory = userStoryRepository.getById(uStoryId);
		//get count of user story
		int count = uStory.getCount();
		Member member = memberRepository.getById(memberId);		
		String memberStatus = member.getMemberStatus();
		//if userStory Status is VOTED then throw exception with message No More Vote Accepted
		if(uStory.getVotingStatus()!=null && uStory.getVotingStatus().compareTo(VotingStatus.VOTED)==0) {
			throw new RuntimeException("Voting is not Available");
		}else if(uStory.getVotingStatus()!=null && uStory.getVotingStatus().compareTo(VotingStatus.PENDING)==0){
			//change the status to VOTING
			uStory.setVotingStatus(VotingStatus.VOTING);
		}
		//if member is voted and trying to vote second time then throws exception member already voted
		if(memberStatus != null && memberStatus.equalsIgnoreCase(PlanningPokerConstant.VOTED)){
			throw new RuntimeException("Member already voted");
		}
		//increment count by 1
		count = count+1;
		uStory.setCount(count);
		//update member status to VOTED from NOT_VOTED
		if(memberStatus != null && memberStatus.equalsIgnoreCase(PlanningPokerConstant.NOT_VOTED)) {
			member.setMemberStatus(PlanningPokerConstant.VOTED);
		}
		/*
		 * member.setVotePoint(votePoint); uStory.getMembers().add(member);
		 */
		MemberUserStory memberUstory = new MemberUserStory();
		memberUstory.setMember(member);
		memberUstory.setUserStory(uStory);
		memberUstory.setVotePoint(votePoint);
	


		//update userstory with status (if applicable) and count
		//userStoryRepository.save(uStory);
		//userStoryRepository.flush();
		memberUserStoryRepository.save(memberUstory);
		return count;
	}

	@Override
	public List<Member> showMemberVotingStatus() {
		//get all members with status
		List<Member> members = memberRepository.findAll();
		return members;
	}

	@Override
	public List<UserStory> showUserStoryVotingStatus() {
		// get all userStories
		return userStoryRepository.findAll();
	}

	@Override
	public UserStory moveUserStoryStatusToVoted(String uStoryId) {
		// update userstory status to VOTED
		//VoteStatus voteStatus = voteStatusRepository.findByStatusDesc(PlanningPokerConstant.VOTED);
		UserStory userStory = userStoryRepository.getById(uStoryId); 
		userStory.setVotingStatus(VotingStatus.VOTED);
		userStoryRepository.save(userStory);
		//fetch member votedetails , for this need Many to one mapping for member and userstory

		//Set<Member> members = userStory.getMembers();

		//show vote count against storyid
		int voteCount = userStory.getCount();
		return userStory;
	}

	@Override
	public List<MemberUserStory> stopuserStoryVoting(String uStoryId) {
		// update userstory status to VOTED
		UserStory userStory = userStoryRepository.getById(uStoryId); 
		userStory.setVotingStatus(VotingStatus.VOTED);
		userStoryRepository.save(userStory);
		//fetch MemberUserStory against a uStoryId
		List<MemberUserStory> memberUserStoryList = memberUserStoryRepository.findByUserStoryId(uStoryId);
		return memberUserStoryList;
	}

	@Override
	public int fetchVoteCountForUserStory(String uStoryId) {
		UserStory userStory = userStoryRepository.getById(uStoryId); 

		int voteCount = userStory.getCount();
		return voteCount;
	}

	@Override
	public int fetchVoteFinalResultForUserStory(String uStoryId,String memberId) {

		UserStory userStory = userStoryRepository.getById(uStoryId);

		Set<MemberUserStory> memberUserStories = userStory.getMemberUstory();
		List<MemberUserStory> memberUserStoryList = new ArrayList<MemberUserStory>();
		memberUserStoryList.addAll(memberUserStories);

		Optional<MemberUserStory> memberUStory = memberUserStoryList.stream().sorted(Comparator.comparingInt(MemberUserStory::getVotePoint).reversed()).findFirst();
		int voteFinalResult = memberUStory.get().getVotePoint();
		return voteFinalResult;
	}

}
