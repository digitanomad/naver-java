package naver.java.ch04.resort.service;

import java.util.List;

import com.googlecode.totallylazy.Callable1;
import com.googlecode.totallylazy.Predicate;
import com.googlecode.totallylazy.Sequences;

import naver.java.ch04.resort.domain.Guest;
import naver.java.ch04.resort.repository.GuestRepository;

public class TotallyLazyResort implements ResortService {
	private GuestRepository repository;
	public TotallyLazyResort(GuestRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public List<String> findGuestNamesByCompany(String company) {
		List<Guest> all = repository.findAll();
		return Sequences.sequence(all)
				.filter(new Predicate<Guest>() {
					@Override
					public boolean matches(Guest g) {
						return company.equals(g.getCompany());
					}
				})
				.sortBy(new Callable1<Guest, Integer>() {
					@Override
					public Integer call(Guest g) throws Exception {
						return g.getGrade();
					}
				})
				.map(new Callable1<Guest, String>() {
					@Override
					public String call(Guest g) throws Exception {
						return g.getName();
					}
				})
				.toList();
	}

}
