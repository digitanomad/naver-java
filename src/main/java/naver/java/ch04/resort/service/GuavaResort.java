package naver.java.ch04.resort.service;

import java.util.List;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Ordering;

import naver.java.ch04.resort.domain.Guest;
import naver.java.ch04.resort.repository.GuestRepository;

public class GuavaResort implements ResortService {
	private GuestRepository repository;
	public GuavaResort(GuestRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public List<String> findGuestNamesByCompany(final String company) {
		List<Guest> all = repository.findAll();
		
		List<Guest> sorted = FluentIterable.from(all)
				.filter(new Predicate<Guest>() {
					@Override
					public boolean apply(Guest g) {
						return company.equals(g.getCompany());
					}
				})
				.toSortedList(Ordering.natural().onResultOf(
						new Function<Guest, Integer>() {
							@Override
							public Integer apply(Guest g) {
								return g.getGrade();
							}
				}));
		
		return FluentIterable.from(sorted)
						.transform(new Function<Guest, String>() {
							@Override
							public String apply(Guest g) {
								return g.getName();
							}
						})
						.toList();
		
	}

}
