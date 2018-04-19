package ftn.isa;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ftn.isa.entity.Institution;
import ftn.isa.entity.Order;
import ftn.isa.repository.InstitutionRepository;
import ftn.isa.repository.OrderRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderRepositoryTest {

	private SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private InstitutionRepository institutionRepository;
	
	private Institution institution;
	
	@Before
	public void setUp(){
		institution=new Institution(1,"Dva stapica","Kineski restoran","M.Glisica1");
	}
	
	@Test
	public void getOrders(){
		Iterable<Order> orders=orderRepository.getOrders(institution);
		boolean equal=true;
		for(Order order:orders){
			if(order.getTable().getSegment().getinstitution().getId().longValue()!=institution.getId().longValue()){
				equal=false;
				break;
			}
		}
		assertEquals(equal, true);
	}

	@Test
	public void getReservationsOfInstitutionForDay() throws ParseException{
		Date d = formatter.parse("02-28-2017");
		List<Order> orders = orderRepository.getReservationsOfInstitutionForDay(institutionRepository.findOne((long) 1), d);
		assertEquals(orders.size(), 2);
	}
	@Test
	public void getReservationsOfInstitutionForWeek() throws ParseException{
		Date d = formatter.parse("02-26-2017");
		Date de = formatter.parse("03-04-2017");
		List<Order> orders = orderRepository.getReservationsOfInstitutionForWeek(institutionRepository.findOne((long) 1), d, de);
		for(Order o : orders)
			System.out.println(o.getDate() + " date");
		assertEquals(orders.size(), 3);
	}
}
