package ftn.isa.kontroler;

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

import ftn.isa.mail.SendEmail;
import ftn.isa.model.Ocena;
import ftn.isa.model.Rezervacija;
import ftn.isa.model.Segment;
import ftn.isa.model.korisnici.Posetilac;
import ftn.isa.servis.PosetilacServis;


@RestController
@RequestMapping(value="/guests")
public class PosetilacKontroler {
	
	@Autowired
	private PosetilacServis guestService;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping(
			value = "/friends/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<Posetilac>> getFriends(@PathVariable("id") Long user_id){
		return this.guestService.getFriendsForGuest(user_id);
	}
	
	@RequestMapping(
			value = "/nonFriends/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<Posetilac>> getNonFriends(@PathVariable("id") Long user_id){
		return this.guestService.getNonFriendsForGuest(user_id);
	}
	
	@RequestMapping(
			value = "/sentRequests/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<Posetilac>> getSentRequests(@PathVariable("id") Long user_id){
		return this.guestService.getSentRequestsForGuest(user_id);
	}
	
	@RequestMapping(
			value = "/recievedRequests/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<Posetilac>> getRecievedRequests(@PathVariable("id") Long user_id){
		return this.guestService.getRecievedRequestsForGuest(user_id);
	}
	
	@RequestMapping(
			value = "/sendRequest",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Posetilac> sendRequest(@RequestParam(value = "user_id") Long user_id,@RequestParam(value = "reciever_id") Long reciever_id){
		return this.guestService.sendRequest(user_id,reciever_id);
	}
	
	@RequestMapping(
			value = "/acceptRequest",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Posetilac> acceptRequest(@RequestParam(value = "user_id") Long user_id,@RequestParam(value = "sender_id") Long sender_id){
		return this.guestService.acceptRequest(user_id,sender_id);
	}
	
	@RequestMapping(
			value = "/declineRequest",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Posetilac> declineRequest(@RequestParam(value = "user_id") Long user_id,@RequestParam(value = "sender_id") Long sender_id){
		return this.guestService.declineRequest(user_id,sender_id);
	}
	
	@RequestMapping(
			value = "/removeFriend",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Posetilac> removeFriend(@RequestParam(value = "user_id") Long user_id,@RequestParam(value = "friend_id") Long friend_id){
		return this.guestService.removeFriend(user_id,friend_id);
	}
	
	@RequestMapping(
			value="/addGrade/{reservationId}",
			method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Ocena> addGrade(@RequestBody Ocena grade,@PathVariable("reservationId")Long reservationId){
		return guestService.addGrade(grade, reservationId);
	}
	
	@RequestMapping(
			value="/deleteGrade/{reservationId}",
			method=RequestMethod.DELETE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Ocena> deleteGrade(@PathVariable("reservationId")Long reservationId){
		return guestService.deleteGrade(reservationId);
	}
	
	@RequestMapping(
			value="/editGrade/{reservationId}",
			method=RequestMethod.PUT,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Ocena> editGrade(@RequestBody Ocena grade,@PathVariable("reservationId") Long reservationId){
		return guestService.editGrade(grade,reservationId);	
	}
	
	@RequestMapping(
			value="/register",
			method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Posetilac> register(@RequestBody Posetilac guest) throws Exception{
		Posetilac g = guestService.register(guest);
		new SendEmail(g.getEmail(),"<a href=http://localhost:8080/guests/activate?email="+g.getEmail()+">HERE</a>", "Activation", "For activation click: ").start();
		return new ResponseEntity<Posetilac>(g, HttpStatus.CREATED);
	}
	@RequestMapping(
			value="/activate",
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Posetilac> activate(@RequestParam("email") String email) throws Exception{
		Posetilac g = guestService.activate(email);
		return new ResponseEntity<Posetilac>(g, HttpStatus.OK);	
	}
	
	@RequestMapping(
			value="/segments/{date}/{resId}",
			method=RequestMethod.POST,
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<Segment>> segments(@PathVariable("date")Date date,@RequestBody Rezervacija r,@PathVariable("resId")Long resId ){
		List<Segment> segments = guestService.getSegments(date,r,resId);
		return new ResponseEntity<List<Segment>>(segments, HttpStatus.OK);	
	}
	
	@RequestMapping(
			value="/getReservations/{userId}",
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<Rezervacija>> getReservationsForGuest(@PathVariable("userId")Long userId ){
		List<Rezervacija> reservations = guestService.getReservationsForGuest(userId);
		return new ResponseEntity<List<Rezervacija>>(reservations, HttpStatus.OK);	
	}
	
	@RequestMapping(
			value="/createReservation", 
			method=RequestMethod.POST,
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Rezervacija> createReservation(@RequestBody Rezervacija r,@RequestParam("id") Long restaurantId){
		Rezervacija reservation = guestService.createReservation(r,restaurantId);
		return new ResponseEntity<Rezervacija>(reservation, HttpStatus.OK);	
	}
	
	@RequestMapping(
			value="/inviteFriend",
			method=RequestMethod.POST,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Posetilac> inviteFriend(@RequestParam("friendId")Long friendId,@RequestParam("resId")Long resId){
		return new ResponseEntity<Posetilac>(this.guestService.inviteFriend(friendId,resId), HttpStatus.OK);	
	}
	
	@RequestMapping(
			value="/history/{id}",
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<Rezervacija>> getHistory(@PathVariable("id")Long id){
		return new ResponseEntity<List<Rezervacija>>(this.guestService.getHistory(id), HttpStatus.OK);	
	}
	
	@RequestMapping(
			value="/historyFriends/{resId}",
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<Posetilac>> getHistoryFriends(@PathVariable("resId")Long id){
		return new ResponseEntity<List<Posetilac>>(this.guestService.getHistoryFriends(id), HttpStatus.OK);	
	}
	
	@RequestMapping(
			value = "/update",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Posetilac> updateInformation(@RequestBody Posetilac guest){
		Posetilac temp=guestService.getGuest(guest.getId());
		temp.setEmail(guest.getEmail());
		temp.setUserName(guest.getUserName());
		temp.setSurname(guest.getSurname());
		temp.setPassword(guest.getPassword());
		temp.setDateOfBirth(guest.getDateOfBirth());
		Posetilac g=guestService.updateGuestInformation(temp);
		session.setAttribute("user", temp);
		return new ResponseEntity<Posetilac>(g, HttpStatus.OK);
	}

}
