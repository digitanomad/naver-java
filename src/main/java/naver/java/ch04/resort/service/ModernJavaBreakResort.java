package naver.java.ch04.resort.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import naver.java.ch04.resort.domain.Guest;
import naver.java.ch04.resort.repository.GuestRepository;

public class ModernJavaBreakResort implements ResortService {
	private GuestRepository repository;
	public ModernJavaBreakResort(GuestRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public List<String> findGuestNamesByCompany(String company) {
		List<Guest> all = repository.findAll();
		Stream<Guest> stream = all.stream();
		// filter
		Stream<Guest> filtered = stream.filter(g -> company.equals(g.getCompany()));
		// sort
		Stream<Guest> sorted = filtered.sorted(Comparator.comparing(Guest::getGrade));
		// map
		Stream<String> mapped = sorted.map(Guest::getName);
		
		return mapped.collect(Collectors.toList());
	}

}
