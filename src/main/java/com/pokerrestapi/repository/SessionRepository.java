package com.pokerrestapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pokerrestapi.entity.Session;

public interface SessionRepository extends JpaRepository<Session, String> {

	@Query(value = "SELECT ps from PokerSession ps where ps.title=?1")
	Session existsByTitle(String title);

	@Query(value = "SELECT ps from PokerSession ps where ps.session=?1")
	Session getPokerSessionBySessionId(String sessionId);


}
