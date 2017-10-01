package naver.java.ch04.resort.repository;

import java.util.List;

import naver.java.ch04.resort.domain.Guest;

public interface GuestRepository {
	public void save(Guest ...guest);
	public List<Guest> findAll();
	public void deleteAll();
}
