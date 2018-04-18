package ftn.isa.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ftn.isa.entity.InstitutionTable;
import ftn.isa.repository.InstitutionRepository;
import ftn.isa.repository.InstitutionTableRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InstitutionTableRepositoryTests {

	@Autowired
	InstitutionRepository rr;
	
	@Autowired
	InstitutionTableRepository rtr;
	
	@Test
	public void seeIfCanDeleteSegment() {
		List<InstitutionTable> notFree = rtr.seeIfCanDeleteSegment((long) 1);
		assertEquals(notFree.size(), 0);
	}
}
