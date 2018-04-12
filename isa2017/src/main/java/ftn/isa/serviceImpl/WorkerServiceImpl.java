package ftn.isa.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ftn.isa.entity.Order;
import ftn.isa.repository.OrderRepository;
import ftn.isa.service.WorkerService;

@Service
@Transactional
public class WorkerServiceImpl implements WorkerService {

	@Autowired
	private OrderRepository orderRepository;
	

	
	@Override
	public Order getOrder(Long id) {
		return orderRepository.findOne(id);
	}

}
