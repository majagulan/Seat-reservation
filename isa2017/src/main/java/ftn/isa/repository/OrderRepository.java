package ftn.isa.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ftn.isa.entity.Institution;
import ftn.isa.entity.InstitutionTable;
import ftn.isa.entity.Order;

public interface OrderRepository extends CrudRepository<Order, Long>{
	
	public Iterable<Order> findByTable(InstitutionTable t);	
	
	@Query("select o from Order o inner join o.table as table inner join table.segment as segment inner join segment.institution as res where res=?1")
	public Iterable<Order> getOrders(Institution institution);

	@Query("select o from Reservation r inner join r.orders as o where r.institution = ?1 and o.date = ?2")
	List<Order> getReservationsOfInstitutionForDay(Institution rest, Date date);
	
	@Query("select o from Reservation r inner join r.orders as o where r.institution = ?1 and o.date between ?2 and ?3")
	List<Order> getReservationsOfInstitutionForWeek(Institution rest, Date date, Date end);
	
}
