package ftn.isa.servis;

import java.text.ParseException;
import java.util.List;

import org.springframework.http.ResponseEntity;

import ftn.isa.model.Mesto;
import ftn.isa.model.Pogledano;
import ftn.isa.model.Projekcija;
import ftn.isa.model.Segment;
import ftn.isa.model.Ustanova;
import ftn.isa.model.korisnici.AdminUs;


public interface AdminUsServis {
	
	ResponseEntity<Ustanova> updateUstanovaProfile(Ustanova r);

	ResponseEntity<Projekcija> removeProjekcijaFromRepertoar(Long product, Long id);

	ResponseEntity<Projekcija> addNewProjekcijaToRepertoar(Projekcija p, Long r_id);
	
	ResponseEntity<Projekcija> addExistingProjekcijaToRepertoar(Long p_id, Long r_id);

	ResponseEntity<Segment> addSegmentToUstanova(Segment s, Long r_id);

	ResponseEntity<Mesto> addMestoToSegment(Mesto t, Long segment_id);

	ResponseEntity<Segment> removeSegment(Long id);

	ResponseEntity<Mesto> removeMesto(Long t);

	ResponseEntity<Mesto> updateMesto(Mesto t, Long id);

	ResponseEntity<Segment> updateSegment(Segment s);

	ResponseEntity<List<Segment>> getAllSegmentsForUstanova(Long id);

	ResponseEntity<List<Mesto>> getAllMestaForSegment(Long id);

	ResponseEntity<List<Mesto>> getAllMestoForUstanova(Long id);
	
	ResponseEntity<List<Projekcija>> getAllProjekcijaForUstanova(Long id);

	ResponseEntity<List<Projekcija>> getAllProjekcijeByNameAndUstanova(Long id, String name);

	double getGradeForPogledano(Long id, Long res_id);

	double getGradeForUstanova(Long id);

	ResponseEntity<Ustanova> getUstanovaForManager(Long id);

	ResponseEntity<List<Projekcija>> getAllProjekcija();

	double checkIfSegmentCanBeDeleted(Long id);

	ResponseEntity<AdminUs> update(AdminUs r);

	ResponseEntity<Mesto> getMesto(Long id);

	ResponseEntity<List<Pogledano>> getReservationsForWeek(Long id, String d) throws ParseException;

	ResponseEntity<List<Pogledano>> getReservationsForDay(Long id, String startDate) throws ParseException;

}
