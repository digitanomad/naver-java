package naver.java.ch04.resort.service;

import static ch.lambdaj.Lambda.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import naver.java.ch04.resort.domain.Guest;
import naver.java.ch04.resort.repository.GuestRepository;
import ch.lambdaj.collection.LambdaCollections;

public class LambdaJResort implements ResortService {
	private GuestRepository repository;
	public LambdaJResort(GuestRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public List<String> findGuestNamesByCompany(String company) {
		List<Guest> all = repository.findAll();
		
		return LambdaCollections.with(all)
				.retain(having(on(Guest.class).getCompany(), equalTo(company)))
				.sort(on(Guest.class).getGrade())
				.extract(on(Guest.class).getName());
		
		/*
		 .convert(new Converter<Guest, String>() {
			@Override
			public String convert(Guest g) {
				return g.getName();
			}
		});
		
		convert(new PropertyExtractor<Guest, String>("name"));
		
		 */
	}

}
