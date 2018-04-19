package ftn.isa.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import ftn.isa.entity.Institution;
import ftn.isa.entity.users.FunManager;
import ftn.isa.entity.users.InstitutionManager;
import ftn.isa.entity.users.SystemManager;
import ftn.isa.entity.users.UserRank;

public interface SystemManagerService {
	ResponseEntity<SystemManager> registerSystemManager(SystemManager sm);

	ResponseEntity<InstitutionManager> registerInstitutionManager(InstitutionManager sm, Long param);
	
	ResponseEntity<Institution> registerInstitution(Institution r);
	
	ResponseEntity<Institution> removeinstitution(Long r_id);

	ResponseEntity<SystemManager> removeSystemManager(Long r_id);

	ResponseEntity<InstitutionManager> removeinstitutionManager(Long r_id);
	
	ResponseEntity<FunManager> removeFunManager(Long fm_id);

	ResponseEntity<List<Institution>> getAllinstitutions();

	ResponseEntity<List<InstitutionManager>> getinstitutionManagersForinstitution(Long id);

	ResponseEntity<List<SystemManager>> getAllSystemManager();

	ResponseEntity<SystemManager> updateSystemManager(SystemManager sm);

	ResponseEntity<Institution> updateinstitution(Institution r);
	
	ResponseEntity<Iterable<UserRank>> getAllUserRanks();
	
	ResponseEntity<UserRank> changeScale(Long userRankId,Double scale);

	
	

}
