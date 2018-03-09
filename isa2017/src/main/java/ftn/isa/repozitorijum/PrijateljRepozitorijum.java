package ftn.isa.repozitorijum;

import org.springframework.data.repository.CrudRepository;

import ftn.isa.model.korisnici.Prijatelj;
import ftn.isa.model.korisnici.PrijateljId;

public interface PrijateljRepozitorijum  extends CrudRepository<Prijatelj, PrijateljId>{

}
