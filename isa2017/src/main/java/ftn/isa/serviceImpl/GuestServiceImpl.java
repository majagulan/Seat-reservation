package ftn.isa.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ftn.isa.entity.Grade;
import ftn.isa.entity.Institution;
import ftn.isa.entity.InstitutionTable;
import ftn.isa.entity.Order;
import ftn.isa.entity.OrderStatus;
import ftn.isa.entity.Projection;
import ftn.isa.entity.Reservation;
import ftn.isa.entity.Segment;
import ftn.isa.entity.users.Friend;
import ftn.isa.entity.users.FriendId;
import ftn.isa.entity.users.Guest;
import ftn.isa.entity.users.GuestStatus;
import ftn.isa.entity.users.User;
import ftn.isa.entity.users.UserRole;
import ftn.isa.mail.SendEmail;
import ftn.isa.repository.FriendRepository;
import ftn.isa.repository.GradeRepository;
import ftn.isa.repository.GuestRepository;
import ftn.isa.repository.InstitutionRepository;
import ftn.isa.repository.ProjectionRepository;
import ftn.isa.repository.ReservationRepository;
import ftn.isa.repository.SegmentRepository;
import ftn.isa.service.GuestService;
import ftn.isa.service.UserRepository;


@Service
@Transactional
public class GuestServiceImpl implements GuestService {
	
	@Autowired
	private GuestRepository guestRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FriendRepository friendRepository;
	
	@Autowired
	private SegmentRepository segmentRepository;
	
	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private GradeRepository gradeRepository;
	
	@Autowired
	private ProjectionRepository projectionRepository;
	
	@Autowired
	private InstitutionRepository institutionRepository;
	
	@Autowired
	private HttpSession session;

	@Override
	public ResponseEntity<List<Guest>> getFriendsForGuest(Long id) {
		if(id != null){
			
			Guest guest = guestRepository.findOne(id);
			List<Guest>friends=this.guestRepository.getFriendsForSender(guest);
			List<Guest>friends1=this.guestRepository.getFriendsForReciever(guest);
			
			for(Guest g : friends1){
				friends.add(g);
			}
			
			return new ResponseEntity<List<Guest>>(friends,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}


	@Override
	public ResponseEntity<List<Guest>> getSentRequestsForGuest(Long id) {

		if(id!= null){
			Guest guest = guestRepository.findOne(id);
			return new ResponseEntity<List<Guest>>(this.guestRepository.getSentRequestsForGuest(guest),HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<List<Guest>> getRecievedRequestsForGuest(Long id) {
		
		if(id!= null){
			Guest guest = guestRepository.findOne(id);
			return new ResponseEntity<List<Guest>>(this.guestRepository.getRecievedRequestsForGuest(guest),HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@Override
	public ResponseEntity<Guest> sendRequest(Long user_id, Long reciever_id) {
		
		if(user_id != null && reciever_id != null && user_id != reciever_id){
		
			Guest sender = guestRepository.findOne(user_id);
			Guest reciever = guestRepository.findOne(reciever_id);
			
			
			Friend f = new Friend();
			f.setReciever(reciever);
			f.setSender(sender);
			f.setStatus(false);
			
			sender.getSent().add(f);
			this.guestRepository.save(sender);
			return new ResponseEntity<Guest>(reciever,HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}

	@Override
	public ResponseEntity<Guest> acceptRequest(Long user_id, Long sender_id) {
		if(sender_id != null && user_id != null && sender_id != user_id){
			Guest user = guestRepository.findOne(user_id);
			Guest sender = guestRepository.findOne(sender_id);
	
			Friend f = friendRepository.findOne(new FriendId(sender,user));
			f.setStatus(true);
			
			friendRepository.save(f);
			
			return new ResponseEntity<Guest>(sender,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<Guest> declineRequest(Long user_id, Long sender_id) {
		
		if(user_id != null && sender_id != null && user_id != sender_id){
			
			Guest user = guestRepository.findOne(user_id);
			Guest sender = guestRepository.findOne(sender_id);
			Friend f = friendRepository.findOne(new FriendId(sender,user));
			friendRepository.delete(f);
			return new ResponseEntity<Guest>(sender,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}


	@Override
	public ResponseEntity<Guest> removeFriend(Long user_id, Long friend_id) {
		
		if(user_id != null && friend_id != null && user_id != friend_id){
			
			Guest user = guestRepository.findOne(user_id);
			Guest friend = guestRepository.findOne(friend_id);
			
			Friend f = friendRepository.findOne(new FriendId(user,friend));
			
			if(f != null){
				if(f.isStatus()){
					friendRepository.delete(f);
				} else {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
			}
			else {
				Friend f1 = friendRepository.findOne(new FriendId(friend,user));
				if(f1 != null){
					if(f1.isStatus()){
						friendRepository.delete(f1);
					} else {
						return new ResponseEntity<>(HttpStatus.NO_CONTENT);
					}
				} else {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
			}
			return new ResponseEntity<Guest>(friend,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}


	@Override
	public Guest register(Guest guest) {
		
		return guestRepository.save(guest);
		
	}


	@Override
	public ResponseEntity<List<Guest>> getNonFriendsForGuest(Long id) {
		Guest guest = guestRepository.findOne(id);
		
		List<Guest>friends=this.guestRepository.getLinksForSender(guest);
		List<Guest>friends1=this.guestRepository.getLinksForReciever(guest);
		
		for(Guest g : friends1){
			friends.add(g);
		}
		List<Guest> allGuests = guestRepository.getAllGuests(guest);
		
		for(Guest g : friends){
			for(Guest f : allGuests){
				if(g.getId() == f.getId()){
					allGuests.remove(f);
					break;
				}
			}
		}
		return new ResponseEntity<List<Guest>>(allGuests,HttpStatus.OK);
	}


	@Override
	public Guest activate(String email) {
		User user = userRepository.findByEmail(email);
		Guest old = guestRepository.findOne(user.getId());
		old.setStatus(GuestStatus.ACTIVE);
		Guest g = guestRepository.save(old);
		return g;
	}


	@Override
	public List<Segment> getSegments(Date date,Reservation re,Long resId) {
		List<Segment> segments = (List<Segment>) segmentRepository.findAll();
		List<Reservation> reservations = (List<Reservation>) reservationRepository.findAll();
		List<Segment> institutionSegments = new ArrayList<Segment>();
		
		for(Reservation r : reservations){
			if(r.getinstitution().getId() == resId){
				boolean flag = false;
				if((re.getStartTime() >= r.getStartTime() && re.getStartTime() <= r.getEndTime()) || (re.getEndTime() >= r.getStartTime() && re.getEndTime() <= r.getEndTime())){
					flag = true;
				}
				for(Order o : r.getOrders()){
					if(o.getOrderStatus().equals(OrderStatus.NOTPAID) && o.getDate().equals(date)){
						if(flag){
							for(Segment s : segments){
								for(InstitutionTable rt : s.getTables()){
									if(rt.getId() == o.getTable().getId()){
										rt.setFree(false);
										break;
									}
								}
							}
						}
					}
				}
			}
		}
		
		for(Segment s : segments){
			if(s.getinstitution().getId() == resId){
				institutionSegments.add(s);
			}
		}
		
		return institutionSegments;
	}

	
	@Override
	public ResponseEntity<Grade> addGrade(Grade grade, Long reservationId) {
		User user=(User) session.getAttribute("user");
		if(user==null || user.getUserRole()!=UserRole.GUEST)
			return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
		Reservation reservation=reservationRepository.findOne(reservationId);
		for(Order order:reservation.getOrders()){
			Grade g=gradeRepository.findGradeByProjection(order.getProjection(), (Guest)user);
			if(g!=null)
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		for(Order order:reservation.getOrders()){
			Grade g=new Grade(grade.getGradeOfOrderItem(), grade.getgradeOfInstitution());
			g.setProjection(order.getProjection());
			g.setinstitution(order.getTable().getSegment().getinstitution());
			g.setGuest((Guest)user);
			g.setReservation(reservation);
			gradeRepository.save(g);
			break;
		}
		return new ResponseEntity<Grade>(grade, HttpStatus.OK);
	}


	@Override
	public ResponseEntity<Grade> editGrade(Grade grade, Long reservationId) {
		User user=(User) session.getAttribute("user");
		if(user==null || user.getUserRole()!=UserRole.GUEST)
			return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
		Reservation reservation=reservationRepository.findOne(reservationId);
		for(Order order:reservation.getOrders()){
			Grade g=gradeRepository.findGradeByProjection(order.getProjection(), (Guest)user);
			if(g==null)
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			g.setGradeOfOrderItem(grade.getGradeOfOrderItem());
			g.setgradeOfInstitution(grade.getgradeOfInstitution());
			g.setReservation(reservation);
			gradeRepository.save(g);
			break;
		}
		return new ResponseEntity<Grade>(grade, HttpStatus.OK);
	}


	@Override
	public ResponseEntity<Grade> deleteGrade(Long reservationId) {
		User user=(User) session.getAttribute("user");
		if(user==null || user.getUserRole()!=UserRole.GUEST)
			return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
		Reservation reservation=reservationRepository.findOne(reservationId);
		for(Order order:reservation.getOrders()){
			Grade g=gradeRepository.findGradeByProjection(order.getProjection(), (Guest)user);
			if(g==null)
				continue;
			gradeRepository.delete(g);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}


	@Override
	public Reservation createReservation(Reservation reservation,Long institutionId) {
		Institution r = institutionRepository.findOne(institutionId);
		reservation.setinstitution(r);
		return reservationRepository.save(reservation);
	}


	@Override
	public Reservation getReservation(Long id) {
		return reservationRepository.findOne(id);
	}


	@Override
	public Guest inviteFriend(Long friendId, Long resId) {
		Guest g = guestRepository.findOne(friendId);
		Reservation r = reservationRepository.findOne(resId);
		new SendEmail(g.getEmail(),"<a href=http://localhost:8000/#!/home>OVDE</a>", "Reservation invitation", "To accept click here:").start();
		r.getPeople().add(g);
		reservationRepository.save(r);
		return g;
		
	}


	@Override
	public List<Reservation> getHistory(Long id) {
		List<Reservation> reservations = (List<Reservation>) reservationRepository.findAll();
		List<Reservation> history = new ArrayList<Reservation>();
		
		for(Reservation r : reservations){
			boolean guestPresent = false;
			boolean isHistory = true;
			for(Guest f : r.getPeople()){
				if(f.getId()==id){
					guestPresent = true;
					break;
				}
			}
			if(guestPresent){
				for(Order o : r.getOrders()){
					if(o.getOrderStatus().equals(OrderStatus.NOTPAID)){
						isHistory = false;
						break;
					}
				}
				if(isHistory){
					history.add(r);
				}
			}
		}
		return history;
	}


	@Override
	public List<Guest> getHistoryFriends(Long resId) {
		Reservation r = reservationRepository.findOne(resId);
		return reservationRepository.getHistoryFriends(r);
	}


	@Override
	public Guest getGuest(long id) {
		return guestRepository.findOne(id);
	}


	@Override
	public Guest updateGuestInformation(Guest temp) {
		return guestRepository.save(temp);
	}


	@Override
	public List<Reservation> getReservationsForGuest(Long userId) {
		Guest g = guestRepository.findOne(userId);
		return reservationRepository.getReservationsForGuest(g);
	}


	@Override
	public Double getAverageGradeForInstitution(Long institutionId) {
		Institution i = institutionRepository.findOne(institutionId);
		return gradeRepository.getAverageGradeForInstitution(i);
	}


	@Override
	public Grade getGradeForUser(Long userId,Long reservationId) {
		Guest g = guestRepository.findOne(userId);
		Reservation r = reservationRepository.findOne(reservationId);
		Grade gg = gradeRepository.getGradeForUser(g,r);
		return gg;
	}


	@Override
	public Double getAverageGradeForProjection(Long reservationId) {
		Reservation r = reservationRepository.findOne(reservationId);
		Projection p = projectionRepository.findProjectionByReservation(r);
		return gradeRepository.getAverageGradeForProjection(p);
	}

}
