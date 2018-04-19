package ftn.isa;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ftn.isa.entity.Institution;
import ftn.isa.entity.Segment;
import ftn.isa.repository.SegmentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SegmentRepositoryTest {
	
	@Autowired
	private SegmentRepository segmentRepository;
	
	private Institution institution;
	
	@Before
	public void setUp(){
		institution=new Institution(1,"Dva stapica","Kineski restoran","M.Glisica1");
	}
	
	@Test
	public void getSegmentsForRestautant(){
		Iterable<Segment> segments=segmentRepository.getSegmentsForInstitution(institution);
		boolean equal=true;
		for(Segment segment:segments){
			if(segment.getinstitution().getId().longValue()!=institution.getId().longValue()){
				equal=false;
				break;
			}
		}
		assertEquals(equal, true);
	}
	
}
