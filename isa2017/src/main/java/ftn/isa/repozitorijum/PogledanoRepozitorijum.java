package ftn.isa.repozitorijum;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ftn.isa.model.Mesto;
import ftn.isa.model.Pogledano;
import ftn.isa.model.Ustanova;


public interface PogledanoRepozitorijum extends CrudRepository<Pogledano, Long>{
	
	public Iterable<Pogledano> findByMesto(Mesto t);	
	
	@Query("select sum(g.gradeOfOrderItem)/count(g.gradeOfOrderItem) from Pogledano o inner join o.orderedItems as oi inner join o.grades as g where oi.product.id = ?1 and o.table.segment.restaurant.id = ?2 and o.orderStatus='PAID' and oi.product.productType != 'DRINK'")
	Double getGradeForPogledano(Long t, Long id);
	
	@Query("select o from Order o inner join o.table as table inner join table.segment as segment inner join segment.restaurant as res where res=?1")
	public Iterable<Pogledano> getPogledano(Ustanova restaurant);

	@Query("select o from Reservation r inner join r.orders as o where r.restaurant = ?1 and o.date = ?2")
	List<Pogledano> getReservationsOfRestaurantForDay(Ustanova rest, Date date);
	
	@Query("select o from Reservation r inner join r.orders as o where r.restaurant = ?1 and o.date between ?2 and ?3")
	List<Pogledano> getReservationsOfRestaurantForWeek(Ustanova rest, Date date, Date end);

}
