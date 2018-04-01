package ftn.isa.servisImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ftn.isa.mail.SendEmail;
import ftn.isa.model.Ocena;
import ftn.isa.model.Pogledano;
import ftn.isa.model.Rezervacija;
import ftn.isa.model.Segment;
import ftn.isa.model.Ustanova;
import ftn.isa.model.korisnici.Korisnik;
import ftn.isa.model.korisnici.KorisnikTip;
import ftn.isa.model.korisnici.Posetilac;
import ftn.isa.model.korisnici.PosetilacStatus;
import ftn.isa.model.korisnici.Prijatelj;
import ftn.isa.model.korisnici.PrijateljId;
import ftn.isa.repozitorijum.KorisnikRepozitorijum;
import ftn.isa.repozitorijum.OcenaRepozitorijum;
import ftn.isa.repozitorijum.PosetilacRepozitorijum;
import ftn.isa.repozitorijum.PrijateljRepozitorijum;
import ftn.isa.repozitorijum.RezervacijaRepozitorijum;
import ftn.isa.repozitorijum.UstanovaRepozitorijum;
import ftn.isa.servis.PosetilacServis;


@Service
@Transactional
public class PosetilacServisImpl implements PosetilacServis {

	@Autowired
	private PosetilacRepozitorijum guestRepository;
	
	@Autowired
	private KorisnikRepozitorijum userRepository;
	
	@Autowired
	private PrijateljRepozitorijum friendRepository;
	
	@Autowired
	private RezervacijaRepozitorijum reservationRepository;

	@Autowired
	private OcenaRepozitorijum gradeRepository;
	
	@Autowired
	private UstanovaRepozitorijum ustanovaRepository;
	
	@Autowired
	private HttpSession session;

	@Override
	public ResponseEntity<List<Posetilac>> getFriendsForGuest(Long id) {
		if(id != null){
			
			Posetilac guest = guestRepository.findOne(id);
			List<Posetilac>friends=this.guestRepository.getFriendsForSender(guest);
			List<Posetilac>friends1=this.guestRepository.getFriendsForReciever(guest);
			
			for(Posetilac g : friends1){
				friends.add(g);
			}
			
			return new ResponseEntity<List<Posetilac>>(friends,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}


	@Override
	public ResponseEntity<List<Posetilac>> getSentRequestsForGuest(Long id) {

		if(id!= null){
			Posetilac guest = guestRepository.findOne(id);
			return new ResponseEntity<List<Posetilac>>(this.guestRepository.getSentRequestsForGuest(guest),HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<List<Posetilac>> getRecievedRequestsForGuest(Long id) {
		
		if(id!= null){
			Posetilac guest = guestRepository.findOne(id);
			return new ResponseEntity<List<Posetilac>>(this.guestRepository.getRecievedRequestsForGuest(guest),HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@Override
	public ResponseEntity<Posetilac> sendRequest(Long user_id, Long reciever_id) {
		
		if(user_id != null && reciever_id != null && user_id != reciever_id){
		
			Posetilac sender = guestRepository.findOne(user_id);
			Posetilac reciever = guestRepository.findOne(reciever_id);
			
			
			Prijatelj f = new Prijatelj();
			f.setReciever(reciever);
			f.setSender(sender);
			f.setStatus(false);
			
			sender.getSent().add(f);
			this.guestRepository.save(sender);
			return new ResponseEntity<Posetilac>(reciever,HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}

	@Override
	public ResponseEntity<Posetilac> acceptRequest(Long user_id, Long sender_id) {
		if(sender_id != null && user_id != null && sender_id != user_id){
			Posetilac user = guestRepository.findOne(user_id);
			Posetilac sender = guestRepository.findOne(sender_id);
	
			Prijatelj f = friendRepository.findOne(new PrijateljId(sender,user));
			f.setStatus(true);
			
			friendRepository.save(f);
			
			return new ResponseEntity<Posetilac>(sender,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<Posetilac> declineRequest(Long user_id, Long sender_id) {
		
		if(user_id != null && sender_id != null && user_id != sender_id){
			
			Posetilac user = guestRepository.findOne(user_id);
			Posetilac sender = guestRepository.findOne(sender_id);
			Prijatelj f = friendRepository.findOne(new PrijateljId(sender,user));
			friendRepository.delete(f);
			return new ResponseEntity<Posetilac>(sender,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}


	@Override
	public ResponseEntity<Posetilac> removeFriend(Long user_id, Long friend_id) {
		
		if(user_id != null && friend_id != null && user_id != friend_id){
			
			Posetilac user = guestRepository.findOne(user_id);
			Posetilac friend = guestRepository.findOne(friend_id);
			
			Prijatelj f = friendRepository.findOne(new PrijateljId(user,friend));
			
			if(f != null){
				if(f.isStatus()){
					friendRepository.delete(f);
				} else {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
			}
			else {
				Prijatelj f1 = friendRepository.findOne(new PrijateljId(friend,user));
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
			return new ResponseEntity<Posetilac>(friend,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}


	@Override
	public Posetilac register(Posetilac guest) {
		
		return guestRepository.save(guest);
		
	}


	@Override
	public ResponseEntity<List<Posetilac>> getNonFriendsForGuest(Long id) {
		Posetilac guest = guestRepository.findOne(id);
		
		List<Posetilac>friends=this.guestRepository.getLinksForSender(guest);
		List<Posetilac>friends1=this.guestRepository.getLinksForReciever(guest);
		
		for(Posetilac g : friends1){
			friends.add(g);
		}
		List<Posetilac> allGuests = guestRepository.getAllGuests(guest);
		
		for(Posetilac g : friends){
			for(Posetilac f : allGuests){
				if(g.getId() == f.getId()){
					allGuests.remove(f);
					break;
				}
			}
		}
		return new ResponseEntity<List<Posetilac>>(allGuests,HttpStatus.OK);
	}


	@Override
	public Posetilac activate(String email) {
		Korisnik user = userRepository.findByEmail(email);
		Posetilac old = guestRepository.findOne(user.getId());
		old.setStatus(PosetilacStatus.ACTIVE);
		Posetilac g = guestRepository.save(old);
		return g;
	}


	@Override
	public List<Segment> getSegments(Date date,Rezervacija re,Long resId) {
		return null;
	
	}

	
	@Override
	public ResponseEntity<Ocena> addGrade(Ocena grade, Long reservationId) {
		Korisnik user=(Korisnik) session.getAttribute("user");
		if(user==null || user.getUserRole()!=KorisnikTip.POSETILAC)
			return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
		Rezervacija reservation=reservationRepository.findOne(reservationId);
		for(Pogledano order:reservation.getPogledano()){
			Ocena g=gradeRepository.findOcenaByProjekcija(order, (Posetilac)user);
			if(g!=null)
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		for(Pogledano order:reservation.getPogledano()){
			Ocena g=new Ocena(grade.getOcenaProjekcije(), grade.getOcenaUstanove());
			g.setPogledano(order);
			g.setUstanova(order.getMesto().getSegment().getUstanova());
			g.setPosetilac((Posetilac)user);
			gradeRepository.save(g);
		}
		return new ResponseEntity<Ocena>(grade, HttpStatus.OK);
	}


	@Override
	public ResponseEntity<Ocena> editGrade(Ocena grade, Long reservationId) {
		Korisnik user=(Korisnik) session.getAttribute("user");
		if(user==null || user.getUserRole()!=KorisnikTip.POSETILAC)
			return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
		Rezervacija reservation=reservationRepository.findOne(reservationId);
		for(Pogledano order:reservation.getPogledano()){
			Ocena g=gradeRepository.findOcenaByProjekcija(order, (Posetilac)user);
			if(g==null)
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			g.setOcenaProjekcije(grade.getOcenaProjekcije());
			g.setOcenaUstanove(grade.getOcenaUstanove());
			gradeRepository.save(g);
		}
		return new ResponseEntity<Ocena>(grade, HttpStatus.OK);
	}


	@Override
	public ResponseEntity<Ocena> deleteGrade(Long reservationId) {
		Korisnik user=(Korisnik) session.getAttribute("user");
		if(user==null || user.getUserRole()!=KorisnikTip.POSETILAC)
			return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
		Rezervacija reservation=reservationRepository.findOne(reservationId);
		for(Pogledano order:reservation.getPogledano()){
			Ocena g=gradeRepository.findOcenaByProjekcija(order, (Posetilac)user);
			if(g==null)
				continue;
			gradeRepository.delete(g);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}


	@Override
	public Rezervacija createReservation(Rezervacija reservation,Long restaurantId) {
		Ustanova r = ustanovaRepository.findOne(restaurantId);
		reservation.setUstanova(r);
		return reservationRepository.save(reservation);
	}


	@Override
	public Rezervacija getReservation(Long id) {
		return reservationRepository.findOne(id);
	}


	@Override
	public Posetilac inviteFriend(Long friendId, Long resId) {
		Posetilac g = guestRepository.findOne(friendId);
		Rezervacija r = reservationRepository.findOne(resId);
		new SendEmail(g.getEmail(),"<a href=http://localhost:8080/#!/home>OVDE</a>", "Reservation invitation", "To accept click here:").start();
		r.getPeople().add(g);
		reservationRepository.save(r);
		return g;
		
	}


	@Override
	public List<Rezervacija> getHistory(Long id) {
		List<Rezervacija> reservations = (List<Rezervacija>) reservationRepository.findAll();
		List<Rezervacija> history = new ArrayList<Rezervacija>();
		
		for(Rezervacija r : reservations){
			boolean guestPresent = false;
			boolean isHistory = true;
			for(Posetilac f : r.getPeople()){
				if(f.getId()==id){
					guestPresent = true;
					break;
				}
			}
			if(guestPresent){
				/*for(Pogledano o : r.getProjekcija()){
					if(o.getOrderStatus().equals(OrderStatus.NOTPAID)){
						isHistory = false;
						break;
					}
				}*/
				if(isHistory){
					history.add(r);
				}
			}
		}
		return history;
	}


	@Override
	public List<Posetilac> getHistoryFriends(Long resId) {
		Rezervacija r = reservationRepository.findOne(resId);
		return reservationRepository.getHistoryFriends(r);
	}


	@Override
	public Posetilac getGuest(long id) {
		return guestRepository.findOne(id);
	}


	@Override
	public Posetilac updateGuestInformation(Posetilac temp) {
		return guestRepository.save(temp);
	}


	@Override
	public List<Rezervacija> getReservationsForGuest(Long userId) {
		Posetilac g = guestRepository.findOne(userId);
		return reservationRepository.getReservationsForGuest(g);
	}

}
