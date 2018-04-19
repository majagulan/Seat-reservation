package ftn.isa.service;

import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;

import ftn.isa.entity.Grade;
import ftn.isa.entity.Order;
import ftn.isa.entity.Projection;
import ftn.isa.entity.ProjectionTime;
import ftn.isa.entity.Reservation;
import ftn.isa.entity.Segment;
import ftn.isa.entity.users.Guest;

public interface GuestService {
	
	ResponseEntity<List<Guest>> getFriendsForGuest(Long id);
	
	Guest register(Guest guest);
	
	Guest activate(String email);
	
	List<Reservation> getHistory(Long id);
	
	List<Guest> getHistoryFriends(Long resId);
	
	List<Segment> getSegments(Date date,Reservation r, Long resId);
	
	Reservation getReservation(Long id);
	
	Projection getProjectionForReservation(Long reservationId);
	
	List<ProjectionTime> getProjectionTimeForProjection(Long projectionId);
	
	List<ProjectionTime> getAllTimes();
	
	List<Order> getFastCardsForInstitution(Long institutionId);
	
	Reservation createReservation(Reservation reservation,Long institutionId);
	
	Order createFastReservation(Guest g,Reservation reservation,Long institutionId,Long fastCardId);
	
	Guest inviteFriend(Long friendId,Long resId);
	
	Double getAverageGradeForInstitution(Long institutionId);
	
	Double getAverageGradeForProjection(Long reservationId);
	
	Grade getGradeForUser(Long userId,Long reservationId);
	
	ResponseEntity<List<Guest>> getNonFriendsForGuest(Long id);
	
	ResponseEntity<List<Guest>> getSentRequestsForGuest(Long id);
	
	ResponseEntity<List<Guest>>  getRecievedRequestsForGuest(Long id);
	
	ResponseEntity<Guest> sendRequest(Long user_id,Long reciever_id);
	
	ResponseEntity<Guest> acceptRequest(Long user_id,Long sender_id);
	
	ResponseEntity<Guest> declineRequest(Long user_id,Long sender_id);
	
	ResponseEntity<Guest> removeFriend(Long user_id,Long friend_id);
	
	ResponseEntity<Grade> addGrade(Grade grade,Long reservationId);
	
	ResponseEntity<Grade> editGrade(Grade grade,Long reservationId);
	
	ResponseEntity<Grade> deleteGrade(Long reservationId);

	Guest getGuest(long id);

	Guest updateGuestInformation(Guest temp);

	List<Reservation> getReservationsForGuest(Long userId);
}
