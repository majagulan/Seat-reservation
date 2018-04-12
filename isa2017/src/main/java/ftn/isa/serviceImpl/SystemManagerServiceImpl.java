package ftn.isa.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ftn.isa.entity.Institution;
import ftn.isa.entity.users.InstitutionManager;
import ftn.isa.entity.users.SystemManager;
import ftn.isa.repository.InstitutionManagerRepository;
import ftn.isa.repository.InstitutionRepository;
import ftn.isa.repository.SystemManagerRepository;
import ftn.isa.service.SystemManagerService;
import ftn.isa.service.UserRepository;

@Service
@Transactional
public class SystemManagerServiceImpl implements SystemManagerService {

	@Autowired
	private SystemManagerRepository systemManagerRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private InstitutionManagerRepository institutionManagerRepository;

	@Autowired
	private InstitutionRepository institutionRepository;

	@Override
	public ResponseEntity<SystemManager> registerSystemManager(SystemManager sm) {
		if (this.userRepository.findByEmail(sm.getEmail()) != null)
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		return new ResponseEntity<SystemManager>(this.systemManagerRepository.save(sm), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<SystemManager> updateSystemManager(SystemManager sm) {
		SystemManager temp = this.systemManagerRepository.findOne(sm.getId());
		if (!temp.getEmail().equals(sm.getEmail()))
			if (this.userRepository.findByEmail(sm.getEmail()) != null)
				return new ResponseEntity<>(HttpStatus.CONFLICT);
		temp.setDateOfBirth(sm.getDateOfBirth());
		temp.setEmail(sm.getEmail());
		temp.setPassword(sm.getPassword());
		temp.setSurname(sm.getSurname());
		temp.setUserName(sm.getUserName());
		return new ResponseEntity<SystemManager>(this.systemManagerRepository.save(temp), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<InstitutionManager> registerInstitutionManager(InstitutionManager sm, Long institution_id) {
		if (this.userRepository.findByEmail(sm.getEmail()) != null)
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		Institution r = this.institutionRepository.findOne(institution_id);
		sm.setinstitution(r);
		InstitutionManager rs = this.institutionManagerRepository.save(sm);
		return new ResponseEntity<InstitutionManager>(rs, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Institution> registerInstitution(Institution r) {
		return new ResponseEntity<Institution>(this.institutionRepository.save(r), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Institution> updateinstitution(Institution r) {
		Institution temp = this.institutionRepository.findOne(r.getId());
		temp.setDescription(r.getDescription());
		temp.setinstitutionName(r.getinstitutionName());
		return new ResponseEntity<Institution>(this.institutionRepository.save(temp), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Institution> removeinstitution(Long r_id) {
		this.institutionRepository.delete(r_id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<SystemManager> removeSystemManager(Long r_id) {
		this.systemManagerRepository.delete(r_id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<InstitutionManager> removeinstitutionManager(Long r_id) {
		this.institutionManagerRepository.delete(r_id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Institution>> getAllinstitutions() {
		return new ResponseEntity<List<Institution>>((List<Institution>) this.institutionRepository.findAll(),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<InstitutionManager>> getinstitutionManagersForinstitution(Long id) {
		return new ResponseEntity<List<InstitutionManager>>((List<InstitutionManager>) this.institutionManagerRepository
				.findByInstitution(this.institutionRepository.findOne(id)), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<SystemManager>> getAllSystemManager() {
		return new ResponseEntity<List<SystemManager>>((List<SystemManager>) this.systemManagerRepository.findAll(),
				HttpStatus.OK);
	}

}
