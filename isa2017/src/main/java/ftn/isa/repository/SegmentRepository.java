package ftn.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ftn.isa.entity.Institution;
import ftn.isa.entity.Segment;
import ftn.isa.entity.SegmentType;

public interface SegmentRepository extends CrudRepository<Segment, Long>{

	Segment findByInstitutionAndId(Institution t, Long id);
	
	List<Segment> findByInstitution(Institution r);
	
	@Query("select s from Segment s where s.institution=?1")
	public Iterable<Segment> getSegmentsForInstitution(Institution institution);
	
	Segment findByPositionAndInstitution(SegmentType s, Institution t);
}
