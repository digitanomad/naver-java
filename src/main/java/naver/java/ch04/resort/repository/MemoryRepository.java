package naver.java.ch04.resort.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import naver.java.ch04.resort.domain.Guest;

public class MemoryRepository implements GuestRepository {

	private List<Guest> savedGuest = new ArrayList<>();
	
	@Override
	public void save(Guest... guests) {
		savedGuest.addAll(Arrays.asList(guests));
	}

	@Override
	public List<Guest> findAll() {
		return new ArrayList<Guest>(savedGuest);
	}

	@Override
	public void deleteAll() {
		savedGuest.clear();
	}

}
