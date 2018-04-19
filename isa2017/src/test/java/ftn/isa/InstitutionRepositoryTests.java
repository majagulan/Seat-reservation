package ftn.isa;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ftn.isa.entity.Institution;
import ftn.isa.repository.InstitutionManagerRepository;
import ftn.isa.repository.InstitutionRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InstitutionRepositoryTests {

	@Autowired
	InstitutionRepository rr;
	
	@Autowired
	InstitutionManagerRepository rmr;
	
	@Test
	public void getgradeForInstitution() {
		Double grade = rr.getgradeForInstitution((long) 1);
		assertEquals(grade, 5, 0.001);
	}
	
	@Test
	public void getByManager() {
		Institution t = rr.getByManager(rmr.findOne((long) 9));
		assertEquals(t.getId(),rr.findOne((long) 1).getId()); 
		
	}
}
