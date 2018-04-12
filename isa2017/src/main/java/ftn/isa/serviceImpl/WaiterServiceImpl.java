package ftn.isa.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ftn.isa.entity.Institution;
import ftn.isa.entity.InstitutionTable;
import ftn.isa.entity.Order;
import ftn.isa.entity.Projection;
import ftn.isa.entity.Segment;
import ftn.isa.repository.InstitutionTableRepository;
import ftn.isa.repository.OrderRepository;
import ftn.isa.repository.ProjectionRepository;
import ftn.isa.repository.SegmentRepository;
import ftn.isa.service.WaiterService;

@Service
@Transactional
public class WaiterServiceImpl implements WaiterService {
	
	@Autowired
	private OrderRepository orderRepository;

	
	@Autowired
	private SegmentRepository segmentRepository;
	
	@Autowired
	private InstitutionTableRepository institutionTableRepository;
	
	@Autowired
	private ProjectionRepository projectionRepository;
	
	
	@Override
	public Order addOrder(Order order){
		return orderRepository.save(order);
	}
	
	@Override
	public Order updateOrder(Order order){
		return orderRepository.save(order);
	}
	
	@Override
	public Iterable<Order> findAllOrders(Institution institution){
		return orderRepository.getOrders(institution);
	}
	
	@Override
	public void deleteOrder(Order order){
		orderRepository.delete(order);
	}
	
	@Override
	public Iterable<Segment> getAllSegments(Institution institution) {
		return segmentRepository.getSegmentsForInstitution(institution);
	}
	
	@Override
	public Iterable<InstitutionTable> getAllTablesForSegment(Long segmentId) {
		Segment segment=segmentRepository.findOne(segmentId);
		return institutionTableRepository.findBySegment(segment);
	}

	@Override
	public InstitutionTable getTable(Long id) {
		return institutionTableRepository.findOne(id);
	}

	@Override
	public Projection getProjection(Long id) {
		return projectionRepository.findOne(id);
	}

	@Override
	public Segment getSegment(Long id) {
		return segmentRepository.findOne(id);
	}

}
