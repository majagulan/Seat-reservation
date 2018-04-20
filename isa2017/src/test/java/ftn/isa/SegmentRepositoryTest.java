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
		institution=new Institution(1,"Arena cineplex","Opis bioskopa","Bulevar Mihajla Pupina 3","https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2808.6982904757137!2d19.840478015955338!3d45.253893479099!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x475b1069ec9deca9%3A0xd7dc4b85b5fc753!2z0JHRg9C70LXQstCw0YAg0JzQuNGF0LDRmNC70LAg0J_Rg9C_0LjQvdCwIDMsINCd0L7QstC4INCh0LDQtA!5e0!3m2!1ssr!2srs!4v1524158968044");
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
