package naver.java.ch04.resort.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import fj.F;
import fj.Ord;
import fj.Ordering;
import fj.data.Stream;
import naver.java.ch04.resort.domain.Guest;
import naver.java.ch04.resort.repository.GuestRepository;

public class FunctionalJavaResort implements ResortService {
	private GuestRepository repository;
	public FunctionalJavaResort(GuestRepository repository) {
		this.repository = repository;
	}
	@Override
	public List<String> findGuestNamesByCompany(String company) {
		List<Guest> all = repository.findAll();
		
		Collection<String> mapped = Stream.iterableStream(all)
				.filter(new F<Guest, Boolean>() {
					@Override
					public Boolean f(Guest g) {
						return company.equals(g.getCompany());
					}
				})
				.sort(Ord.ord(new F<Guest, F<Guest,Ordering>>() {
					@Override
					public F<Guest, Ordering> f(final Guest a1) {
						return new F<Guest, Ordering>() {
							@Override
							public Ordering f(Guest a2) {
								int x = Integer.compare(a1.getGrade(), a2.getGrade());
								return x < 0 ? Ordering.LT : x == 0 ? Ordering.EQ : Ordering.GT;
							}
						};
					}
				}))
				.map(new F<Guest, String>() {
					@Override
					public String f(Guest g) {
						return g.getName();
					}
				})
				.toCollection();
		
		return new ArrayList<String>(mapped);
	}
}
