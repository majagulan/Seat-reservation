package ftn.isa;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ftn.isa.entity.Projection;
import ftn.isa.repository.ProjectionRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectionRepostoryTests {

	@Autowired
	private ProjectionRepository pr;

	@Test
	public void getProjectionsForInstitution() {
		List<Projection> projections = pr.getProjectionsForInstitution((long) 1);
		assertEquals(projections.size(), 3);
	}

	@Test
	public void seeIfBelongsToinstitution() {
		Projection projection = pr.seeIfBelongsToinstitution((long) 1, (long) 1);
		assertEquals(projection.getId(), (long) 1);
	}

	
	@Test
	public void findProjectionByInstitutionAndName() {
		List<Projection> projections = pr.findProjectionByInstitutionAndName("Star Wars 1",(long) 1);
		assertEquals(projections.size(), 1);
	}
}
