package com.pokerrestapi.req;

/**
 * 		
 * @author Pocker Planning Session Request
 *
 */
//@Getter @Setter
public class CreatePokerSessionRequest {
	String title;
	String deckType;
	String memberName;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDeckType() {
		return deckType;
	}
	public void setDeckType(String deckType) {
		this.deckType = deckType;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	
}
