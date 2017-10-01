package naver.java.ch04.resort.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import naver.java.ch04.resort.domain.Guest;
import naver.java.ch04.resort.repository.GuestRepository;

public class ClassicJavaResort implements ResortService {
	private GuestRepository repository;
	public ClassicJavaResort(GuestRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public List<String> findGuestNamesByCompany(String company) {
		List<Guest> all = repository.findAll();
		
		List<Guest> filtered = filter(all, company);
		sort(filtered);
		return mapNames(filtered);
	}
	
	/**
	 * 필터링<br>
	 * company 속성값이 특정한 값과 일치하는 Guest 객체만 필터링
	 * @param guests
	 * @param company
	 * @return
	 */
	private List<Guest> filter(List<Guest> guests, String company) {
		List<Guest> filtered = new ArrayList<>();
		for (Guest guest : guests) {
			if (company.equals(guest.getCompany())) {
				filtered.add(guest);
			}
		}
		
		return filtered;
	}
	
	/**
	 * 정렬<br>
	 * grade 속성 값을 기준으로 Guest 객체를 오름차순으로 정렬
	 * @param guests
	 */
	private void sort(List<Guest> guests) {
		Collections.sort(guests, new Comparator<Guest>() {
			@Override
			public int compare(Guest o1, Guest o2) {
				return Integer.compare(o1.getGrade(), o2.getGrade());
			}
		});
	}
	
	/**
	 * 변환<br>
	 * name 속성만 추출해 List<String>으로 변환
	 * @param guests
	 * @return
	 */
	private List<String> mapNames(List<Guest> guests) {
		List<String> names = new ArrayList<>();
		for (Guest guest : guests) {
			names.add(guest.getName());
		}
		
		return names;
	}

}
