package ftn.isa.repozitorijum;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ftn.isa.model.Ustanova;
import ftn.isa.model.Mesto;
import ftn.isa.model.Segment;


public interface MestoRepozitorijum extends CrudRepository<Mesto, Long> {

	public Iterable<Mesto> findBySegment(Segment segment);

	@Query("select t from Segment s inner join s.mesta as t where s.bioskop = ?1")
	List<Mesto> getAllMestoForUstanova(Ustanova r);

	@Query("select t from Segment s inner join s.mesta as t where s.id = ?1 and t.free = 'true'")
	List<Mesto> seeIfCanDeleteSegment(Long id);
	
	@Query()
	Mesto findBySegmentAndTableRowAndTableColumn(Segment s,int row, int column);
}