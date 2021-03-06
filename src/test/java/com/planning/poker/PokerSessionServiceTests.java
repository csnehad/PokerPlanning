package com.planning.poker;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.pokerrestapi.constant.PlanningPokerConstant;
import com.pokerrestapi.entity.Member;
import com.pokerrestapi.entity.Session;
import com.pokerrestapi.entity.UserStory;
import com.pokerrestapi.entity.VotingStatus;
import com.pokerrestapi.repository.MemberRepository;
import com.pokerrestapi.repository.SessionRepository;
import com.pokerrestapi.serviceimpl.SessionServiceImpl;

@RunWith(MockitoJUnitRunner.class)
class PokerSessionServiceTests {

	@InjectMocks
	public SessionServiceImpl pokerSessionServiceMocked;

	@Mock
	SessionRepository pokerSessionRepository;

	@Mock
	MemberRepository memberRepository;
	
	 static List<Session> pokerSessions = new ArrayList<Session>();
	 static Session pokerSession = null;
	 static Member member2 = null;
	
	

	//	@Ignore
	@Test
	//@Order(1)
	void createPockerPlanningSessionTest() {

		Mockito.when(pokerSessionRepository.save(pokerSession)).thenReturn(pokerSession);
		Session ps = pokerSessionServiceMocked.createSession(pokerSession, "XYZ");
		assertEquals(pokerSession, ps);
	}

	@Test
	//@Order(2)
	void getAllPokerSessionTest() {

		Mockito.when(pokerSessionRepository.findAll()).thenReturn(pokerSessions);
		List<Session> pokerSessionList = pokerSessionServiceMocked.getAllSessions();
		assertEquals(pokerSessions, pokerSessionList);
	}
	
	@Test
	//@Order(3)
	void getPokerSessionBySessionTest() {

		String session = "12345";
		Mockito.when(pokerSessionRepository.getPokerSessionBySessionId(session)).thenReturn(pokerSession);
		Optional<Session> ps = pokerSessionServiceMocked.getSessionBySessionId(session);
		assertEquals(pokerSession, ps.get());
	}
	
	@Test
	//@Order(4)
	void getTitleOfTheSessionTest() {

		String session = "12345";
		//String title = ""
		Mockito.when(pokerSessionRepository.getById(session)).thenReturn(pokerSession);
		String title = pokerSessionServiceMocked.getTitleOfTheSession(session);
		assertEquals(pokerSession.getTitle(), title);
	}
	
	@Test
	void addUpdateMemberListTest() {

		String session = "12345";
		String memberName = "ABC";
		Mockito.when(pokerSessionRepository.getById(session)).thenReturn(pokerSession);
		Mockito.when(memberRepository.save(member2)).thenReturn(member2);
		List<Member> memberList = pokerSessionServiceMocked.addUpdateMemberList(session, memberName);
		System.out.println(memberList.size());
		//assertEquals(pokerSession.getTitle(), memberList);
	}
	
	@Test
	void getUserStoryListOfTheSessionTest() {

		String session = "12345";
		List<UserStory> userStoryListExpected = new ArrayList<UserStory>();
		//String title = ""
		Mockito.when(pokerSessionRepository.getById(session)).thenReturn(pokerSession);
		userStoryListExpected.addAll(pokerSession.getUserStories());
		List<UserStory> userStoryList = pokerSessionServiceMocked.getUserStoryListInSession(session);
		assertEquals(userStoryListExpected, userStoryList);
	}
	
	@Test
	void getMemberListOfTheSessionTest() {

		String session = "12345";
		List<Member> memberListExpected = new ArrayList<Member>();
		//String title = ""
		Mockito.when(pokerSessionRepository.getById(session)).thenReturn(pokerSession);
		memberListExpected.addAll(pokerSession.getMembers());
		List<Member> memberList = pokerSessionServiceMocked.getMembersInSession(session);
		assertEquals(memberListExpected, memberList);
	}
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		pokerSession = createPokerSession();
		pokerSessions = createPokerSessionList(pokerSession);
		member2 = createMember();
	}

	private Member createMember() {
		member2 = new Member("ABC", PlanningPokerConstant.NOT_VOTED);
		member2.setMemberId("2");
		pokerSession.getMembers().add(member2);
		member2.setSession(pokerSession);
		return member2;
	}
	
	public static List<Session> createPokerSessionList(Session pokerSession) {
		pokerSessions = new ArrayList<Session>();
		
		
		List<Session> pokerSessions = new ArrayList<Session>();
		pokerSessions.add(pokerSession);
		return pokerSessions;
	}

	private Session createPokerSession() {
		UserStory userStory = new UserStory("us1", "desc1", 0);
		userStory.setVotingStatus(VotingStatus.PENDING);
		Set<UserStory> userStories = new HashSet<UserStory>();
		userStories.add(userStory);
		Member member = new Member("XYZ", PlanningPokerConstant.NOT_VOTED);
		member.setMemberId("1");
		Set<Member> members = new HashSet<Member>();
		members.add(member);
		pokerSession = new Session("12345", "title1", "deck1", userStories, members);
		return pokerSession;
	}
}
