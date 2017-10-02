package naver.java.ch04.resort.service;

import static com.insightfullogic.lambdabehave.Suite.*;

import java.util.Arrays;
import java.util.List;

import naver.java.ch04.resort.domain.Guest;
import naver.java.ch04.resort.repository.GuestRepository;
import naver.java.ch04.resort.repository.MemoryRepository;

import org.junit.runner.RunWith;

import com.insightfullogic.lambdabehave.JunitSuiteRunner;

@RunWith(JunitSuiteRunner.class)
public class ResortServiceSpec {{
	GuestRepository repository = new MemoryRepository();
	ResortService service = new ModernJavaResort(repository);
	
	describe("ResortService with modern Java", it -> {
		it.isSetupWith(() -> {
			repository.save(
					new Guest(1, 15, "jsh", "Naver"),
					new Guest(2, 10, "hny", "Line"),
					new Guest(3, 5, "chy", "Naver")
					);
		});
		it.isConcludedWith(repository::deleteAll);
		
		it.should("find names of guests by company ", expect -> {
			List<String> names = service.findGuestNamesByCompany("Naver");
			expect.that(names).isEqualTo(Arrays.asList("chy", "jsh"));
		});
	});
}}
