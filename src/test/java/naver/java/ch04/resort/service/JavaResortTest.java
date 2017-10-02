package naver.java.ch04.resort.service;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import naver.java.ch04.resort.domain.Guest;
import naver.java.ch04.resort.repository.GuestRepository;
import naver.java.ch04.resort.repository.MemoryRepository;

public class JavaResortTest {
	GuestRepository repository = new MemoryRepository();
	
	@Test
	public void classjava(){
		assertImpl(new ClassicJavaResort(repository));
	}
	
	@Test
	public void modernJava(){
		assertImpl(new ModernJavaResort(repository));
	}
	
	@Test
	public void modernJavaWithMethodReference(){
		assertImpl(new ModernJavaAdvancedResort(repository));
	}

	@Test
	public void guava(){
		assertImpl(new GuavaResort(repository));
	}

	@Test
	public void lambdaj(){
		assertImpl(new LambdaJResort(repository));
	}
	
	@Test
	public void functionalJava(){
		assertImpl(new FunctionalJavaResort(repository));
	}
	
	@Test
	public void totallyLazy(){
		assertImpl(new TotallyLazyResort(repository));
	}
	
	private void assertImpl(ResortService service) {
		repository.save(
			new Guest(1, 15, "jsh", "Naver"),
			new Guest(2, 10, "hny", "Line"),
			new Guest(3, 5, "chy", "Naver")
		);
		
		List<String> names = service.findGuestNamesByCompany("Naver");
		assertThat(names).isEqualTo(Arrays.asList("chy","jsh"));
	}
}
