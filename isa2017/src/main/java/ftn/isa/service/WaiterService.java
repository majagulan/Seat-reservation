package ftn.isa.service;

import ftn.isa.entity.Institution;
import ftn.isa.entity.InstitutionTable;
import ftn.isa.entity.Order;
import ftn.isa.entity.Projection;
import ftn.isa.entity.Segment;

public interface WaiterService {

	public Order addOrder(Order order);
	public Iterable<Order> findAllOrders(Institution institution);
	public Order updateOrder(Order order);
	public void deleteOrder(Order order);
	public Iterable<Segment> getAllSegments(Institution institution);
	public Iterable<InstitutionTable> getAllTablesForSegment(Long segmentId);
	public InstitutionTable getTable(Long id);
	public Projection getProjection(Long id);
	public Segment getSegment(Long id);
	
}
