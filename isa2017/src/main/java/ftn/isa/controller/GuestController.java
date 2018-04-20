package ftn.isa.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ftn.isa.entity.Grade;
import ftn.isa.entity.InstitutionTable;
import ftn.isa.entity.Order;
import ftn.isa.entity.OrderStatus;
import ftn.isa.entity.Projection;
import ftn.isa.entity.ProjectionTime;
import ftn.isa.entity.RequestOffer;
import ftn.isa.entity.Reservation;
import ftn.isa.entity.Segment;
import ftn.isa.entity.users.Guest;
import ftn.isa.entity.users.User;
import ftn.isa.entity.users.UserRole;
import ftn.isa.mail.SendEmail;
import ftn.isa.service.GuestService;
import ftn.isa.service.InstitutionManagerService;
import ftn.isa.service.WaiterService;

@RestController
@RequestMapping(value="/guests")
public class GuestController {

	@Autowired
	private GuestService guestService;
	
	@Autowired
	private WaiterService waiterService;
	
	@Autowired
	private InstitutionManagerService institutionManagerService;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping(
			value = "/friends/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<Guest>> getFriends(@PathVariable("id") Long user_id){
		return this.guestService.getFriendsForGuest(user_id);
	}
	
	@RequestMapping(
			value = "/nonFriends/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<Guest>> getNonFriends(@PathVariable("id") Long user_id){
		return this.guestService.getNonFriendsForGuest(user_id);
	}
	
	@RequestMapping(
			value = "/getAllNonActiveRequestOffers/",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<RequestOffer>> getAllNonActiveRequestOffers(){
		return this.guestService.getAllNonActiveRequestOffers();
	}
	
	@RequestMapping(
			value = "/activateRequestOffer/{requestOfferId}",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<RequestOffer> activateRequestOffer(@PathVariable("requestOfferId") Long reqId){
		return this.guestService.activateRequestOffer(reqId);
	}
	
	@RequestMapping(
			value = "/destroyRequestOffer/{requestOfferId}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<RequestOffer> destroyRequestOffer(@PathVariable("requestOfferId") Long reqId){
		return this.guestService.destroyRequestOffer(reqId);
	}
	
	@RequestMapping(
			value = "/getTablesForSegment/{segmentId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public Iterable<InstitutionTable> getTablesForSegment(@PathVariable("segmentId") Long seg_id){
		Iterable<InstitutionTable> tables = this.waiterService.getAllTablesForSegment(seg_id);	
		return tables;
	}
	
	@RequestMapping(
			value = "/getFreeTablesCountForInstitution/{institutionId}",
			method = RequestMethod.GET)
	@ResponseBody
	@Transactional
	public Integer getFreeTablesCountForInstitution(@PathVariable("institutionId") Long institutionId){
		Integer count = institutionManagerService.getFreeTablesCountForInstitution(institutionId);
		return count;
	}
	
	@RequestMapping(
			value = "/sentRequests/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<Guest>> getSentRequests(@PathVariable("id") Long user_id){
		return this.guestService.getSentRequestsForGuest(user_id);
	}
	
	@RequestMapping(
			value = "/recievedRequests/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<Guest>> getRecievedRequests(@PathVariable("id") Long user_id){
		return this.guestService.getRecievedRequestsForGuest(user_id);
	}
	
	@RequestMapping(
			value = "/sendRequest",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Guest> sendRequest(@RequestParam(value = "user_id") Long user_id,@RequestParam(value = "reciever_id") Long reciever_id){
		return this.guestService.sendRequest(user_id,reciever_id);
	}
	
	@RequestMapping(
			value = "/acceptRequest",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Guest> acceptRequest(@RequestParam(value = "user_id") Long user_id,@RequestParam(value = "sender_id") Long sender_id){
		return this.guestService.acceptRequest(user_id,sender_id);
	}
	
	@RequestMapping(
			value = "/declineRequest",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Guest> declineRequest(@RequestParam(value = "user_id") Long user_id,@RequestParam(value = "sender_id") Long sender_id){
		return this.guestService.declineRequest(user_id,sender_id);
	}
	
	@RequestMapping(
			value = "/removeFriend",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Guest> removeFriend(@RequestParam(value = "user_id") Long user_id,@RequestParam(value = "friend_id") Long friend_id){
		return this.guestService.removeFriend(user_id,friend_id);
	}
	
	@RequestMapping(
			value="/addGrade/{reservationId}",
			method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Grade> addGrade(@RequestBody Grade grade,@PathVariable("reservationId")Long reservationId){
		return guestService.addGrade(grade, reservationId);
	}
	
	@RequestMapping(
			value="/getAverageGradeForInstitution/{institutionId}",
			method=RequestMethod.GET)
	@ResponseBody
	@Transactional
	public Double getAverageGradeForInstitution(@PathVariable("institutionId")Long institutionId){
		return guestService.getAverageGradeForInstitution(institutionId);
	}
	
	@RequestMapping(
			value="/getAverageGradeForProjection/{reservationId}",
			method=RequestMethod.GET)
	@ResponseBody
	@Transactional
	public Double getAverageGradeForProjection(@PathVariable("reservationId")Long reservationId){
		return guestService.getAverageGradeForProjection(reservationId);
	}
	
	@RequestMapping(
			value="/getAverageGradeForProjection1/{projectionId}",
			method=RequestMethod.GET)
	@ResponseBody
	@Transactional
	public Double getAverageGradeForProjection1(@PathVariable("projectionId")Long projectionId){
		return guestService.getAverageGradeForProjection1(projectionId);
	}
	
	@RequestMapping(
			value="/getGradeForUser/{userId}/{reservationId}",
			method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Grade> getGradeForUser(@PathVariable("userId")Long userId,@PathVariable("reservationId")Long reservationId){
		Grade g = guestService.getGradeForUser(userId,reservationId);
		return new ResponseEntity<Grade>(g,HttpStatus.OK);
	}
	
	@RequestMapping(
			value="/getProjectionForReservation/{reservationId}",
			method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Projection> getGradeForUser(@PathVariable("reservationId")Long reservationId){
		Projection p = guestService.getProjectionForReservation(reservationId);
		return new ResponseEntity<Projection>(p,HttpStatus.OK);
	}
	
	
	
	@RequestMapping(
			value="/deleteGrade/{reservationId}",
			method=RequestMethod.DELETE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Grade> deleteGrade(@PathVariable("reservationId")Long reservationId){
		return guestService.deleteGrade(reservationId);
	}
	
	@RequestMapping(
			value="/editGrade/{reservationId}",
			method=RequestMethod.PUT,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Grade> editGrade(@RequestBody Grade grade,@PathVariable("reservationId") Long reservationId){
		return guestService.editGrade(grade,reservationId);	
	}
	
	@RequestMapping(
			value="/register",
			method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Guest> register(@RequestBody Guest guest) throws Exception{
		Guest g = guestService.register(guest);
		new SendEmail(g.getEmail(),"<a href=http://localhost:8000/guests/activate?email="+g.getEmail()+">HERE</a>", "Activation", "For activation click: ").start();
		return new ResponseEntity<Guest>(g, HttpStatus.CREATED);
	}
	@RequestMapping(
			value="/activate",
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Guest> activate(@RequestParam("email") String email) throws Exception{
		Guest g = guestService.activate(email);
		return new ResponseEntity<Guest>(g, HttpStatus.OK);	
	}
	
	@RequestMapping(
			value="/segments/{date}/{resId}",
			method=RequestMethod.POST,
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<Segment>> segments(@PathVariable("date")Date date,@RequestBody Reservation r,@PathVariable("resId")Long resId ){
		List<Segment> segments = guestService.getSegments(date,r,resId);
		return new ResponseEntity<List<Segment>>(segments, HttpStatus.OK);	
	}
	
	@RequestMapping(
			value="/getReservations/{userId}",
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<Reservation>> getReservationsForGuest(@PathVariable("userId")Long userId ){
		List<Reservation> reservations = guestService.getReservationsForGuest(userId);
		return new ResponseEntity<List<Reservation>>(reservations, HttpStatus.OK);	
	}
	
	@RequestMapping(
			value="/createReservation", 
			method=RequestMethod.POST,
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Reservation> createReservation(@RequestBody Reservation r,@RequestParam("id") Long institutionId){
		Reservation reservation = guestService.createReservation(r,institutionId);
		return new ResponseEntity<Reservation>(reservation, HttpStatus.OK);	
	}
	
	@RequestMapping(
			value="/createFastReservation/{fastCardId}/{institutionId}", 
			method=RequestMethod.POST,
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Order> createFastReservation(@RequestBody Reservation r,@PathVariable("fastCardId") Long fastCardId,@PathVariable("institutionId") Long institutionId){
		User user = (User) session.getAttribute("user");
		
		if(user==null || user.getUserRole()!=UserRole.GUEST)
			return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
		
		Order order = guestService.createFastReservation((Guest)user,r,fastCardId,institutionId);
		return new ResponseEntity<Order>(order, HttpStatus.OK);	
	}
	
	@RequestMapping(
			value="/createOrder",
			method=RequestMethod.POST,
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Order> createOrder(@RequestBody Order order,@RequestParam("tableId")Long tableId,@RequestParam("resId")Long resId,@RequestParam("proId")Long proId){
		Reservation r = guestService.getReservation(resId);
		Projection p = institutionManagerService.getProjection(proId);
		System.out.println(order.getDate());
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(order.getDate());
		InstitutionTable rt=waiterService.getTable(tableId);
		order.setTable(rt);
		order.setReservation(r);
		order.setPrice(p.getPrice());
		order.setProjection(p);
		order.setOrderStatus(OrderStatus.PAID);
		Order o=waiterService.addOrder(order);
		return new ResponseEntity<Order>(o, HttpStatus.OK);	
	}
	
	@RequestMapping(
			value="/createCard",
			method=RequestMethod.POST,
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Order> createCard(@RequestBody Order order,@RequestParam("tableId")Long tableId,@RequestParam("resId")Long resId,@RequestParam("proId")Long proId){
		Reservation r = guestService.getReservation(resId);
		Projection p = institutionManagerService.getProjection(proId);
		System.out.println(order.getDate());
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(order.getDate());
		InstitutionTable rt=waiterService.getTable(tableId);
		order.setTable(rt);
		order.setReservation(r);
		order.setPrice(p.getPrice());
		order.setProjection(p);
		order.setOrderStatus(OrderStatus.PAID);
		order.setFastReservation(true);
		Order o=waiterService.addOrder(order);
		return new ResponseEntity<Order>(o, HttpStatus.OK);	
	}
	
	@RequestMapping(
			value="/inviteFriend",
			method=RequestMethod.POST,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Guest> inviteFriend(@RequestParam("friendId")Long friendId,@RequestParam("resId")Long resId){
		return new ResponseEntity<Guest>(this.guestService.inviteFriend(friendId,resId), HttpStatus.OK);	
	}
	
	@RequestMapping(
			value="/history/{id}",
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<Reservation>> getHistory(@PathVariable("id")Long id){
		return new ResponseEntity<List<Reservation>>(this.guestService.getHistory(id), HttpStatus.OK);	
	}
	
	@RequestMapping(
			value="/getFastCardsForInstitution/{institutionid}",
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<Order>> getFastCardsForInstitution(@PathVariable("institutionid")Long institutionid){
		return new ResponseEntity<List<Order>>(this.guestService.getFastCardsForInstitution(institutionid), HttpStatus.OK);	
	}
	
	@RequestMapping(
			value="/getAllProjectionTimesForProjection/{projectionId}",
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<ProjectionTime>> getAllProjectionTimesForProjection(@PathVariable("projectionId")Long id){
		return new ResponseEntity<List<ProjectionTime>>(this.guestService.getProjectionTimeForProjection(id), HttpStatus.OK);	
	}
	
	@RequestMapping(
			value="/getAllTimes",
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<ProjectionTime>> getAllTimes(){
		return new ResponseEntity<List<ProjectionTime>>(this.guestService.getAllTimes(), HttpStatus.OK);	
	}
	
	@RequestMapping(
			value="/historyFriends/{resId}",
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<Guest>> getHistoryFriends(@PathVariable("resId")Long id){
		return new ResponseEntity<List<Guest>>(this.guestService.getHistoryFriends(id), HttpStatus.OK);	
	}
	
	@RequestMapping(
			value = "/update",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Guest> updateInformation(@RequestBody Guest guest){
		Guest temp=guestService.getGuest(guest.getId());
		temp.setEmail(guest.getEmail());
		temp.setUserName(guest.getUserName());
		temp.setSurname(guest.getSurname());
		temp.setPassword(guest.getPassword());
		temp.setDateOfBirth(guest.getDateOfBirth());
		temp.setPhone(guest.getPhone());
		temp.setCity(guest.getCity());
		Guest g=guestService.updateGuestInformation(temp);
		session.setAttribute("user", temp);
		return new ResponseEntity<Guest>(g, HttpStatus.OK);
	}
	
}
