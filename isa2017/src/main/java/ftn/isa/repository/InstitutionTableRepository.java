package ftn.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ftn.isa.entity.Institution;
import ftn.isa.entity.InstitutionTable;
import ftn.isa.entity.Segment;

public interface InstitutionTableRepository extends CrudRepository<InstitutionTable, Long> {

	public Iterable<InstitutionTable> findBySegment(Segment segment);

	@Query("select t from Segment s inner join s.tables as t where s.institution = ?1")
	List<InstitutionTable> getTablesForInstitution(Institution r);

	@Query("select t from Segment s inner join s.tables as t where s.id = ?1 and t.free = 'true'")
	List<InstitutionTable> seeIfCanDeleteSegment(Long id);
	
	InstitutionTable findBySegmentAndTableRowAndTableColumn(Segment s,int row, int column);
}
