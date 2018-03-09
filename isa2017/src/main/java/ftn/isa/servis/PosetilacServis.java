package ftn.isa.servis;

import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;

import ftn.isa.model.Ocena;
import ftn.isa.model.Rezervacija;
import ftn.isa.model.Segment;
import ftn.isa.model.korisnici.Posetilac;

public interface PosetilacServis {
	
	ResponseEntity<List<Posetilac>> getFriendsForGuest(Long id);
	
	Posetilac register(Posetilac guest);
	
	Posetilac activate(String email);
	
	List<Rezervacija> getHistory(Long id);
	
	List<Posetilac> getHistoryFriends(Long resId);
	
	List<Segment> getSegments(Date date,Rezervacija r, Long resId);
	
	Rezervacija getReservation(Long id);
	
	Rezervacija createReservation(Rezervacija reservation,Long restaurantId);
	
	Posetilac inviteFriend(Long friendId,Long resId);
	
	
	
	ResponseEntity<List<Posetilac>> getNonFriendsForGuest(Long id);
	
	ResponseEntity<List<Posetilac>> getSentRequestsForGuest(Long id);
	
	ResponseEntity<List<Posetilac>>  getRecievedRequestsForGuest(Long id);
	
	ResponseEntity<Posetilac> sendRequest(Long user_id,Long reciever_id);
	
	ResponseEntity<Posetilac> acceptRequest(Long user_id,Long sender_id);
	
	ResponseEntity<Posetilac> declineRequest(Long user_id,Long sender_id);
	
	ResponseEntity<Posetilac> removeFriend(Long user_id,Long friend_id);
	
	ResponseEntity<Ocena> addGrade(Ocena grade,Long reservationId);
	
	ResponseEntity<Ocena> editGrade(Ocena grade,Long reservationId);
	
	ResponseEntity<Ocena> deleteGrade(Long reservationId);

	Posetilac getGuest(long id);

	Posetilac updateGuestInformation(Posetilac temp);

	List<Rezervacija> getReservationsForGuest(Long userId);

}
