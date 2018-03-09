package ftn.isa.repozitorijum;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ftn.isa.model.Ustanova;
import ftn.isa.model.Segment;

public interface SegmentRepozitorijum  extends CrudRepository<Segment, Long>{
	
	Segment findByBioskopAndId(Ustanova t, Long id);
	List<Segment> findByBioskop(Ustanova r);
	
	@Query("select s from Segment s where s.bioskop=?1")
	public Iterable<Segment> getSegmentsForRestautant(Ustanova restaurant);
	

}
