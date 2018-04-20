var app = angular.module('webApp');

app.factory('SessionService', function sessionService($http) {
	
	sessionService.register = function(user){
		return $http({
			method : 'POST',
			url: '../guests/register',
			data: {
				"userName": user.userName,
				"password": user.password,
				"surname": user.surname,
				"email": user.email,
				"userRole": 'GUEST',
				"status": 'NOT_ACTIVE'
			}
		});
	}
	
	
	
	sessionService.getLoggedUser = function(){
		return $http({
			method : 'GET',
			url: '../users/loggedUser'

		});
	}
	
	sessionService.logOut = function(){
		return $http({
			method : 'GET',
			url: '../users/logout'

		});
	}
	
	sessionService.login = function(user){
		return $http({
			method : 'POST',
			url: '../users/login',
			data: {
				"email": user.email,
				"password": user.password,
			}
		});
	}
	
	sessionService.update = function(user){
		return $http({
			method : 'PUT',
			url: '../'+ user.userRole.toLowerCase()+'s/update',
			data: {
				"id": user.id,
				"userName": user.userName,
				"password": user.password,
				"surname": user.surname,
				"userRole": user.userRole,
				"email": user.email,
				"dateOfBirth": user.dateOfBirth,
				"shirtSize": user.shirtSize,
				"shoeNumber": user.shoeNumber
			}
		});
	}
	
	
	return sessionService;
	
});

app.factory('GuestService', function guestService($http) {
	
	guestService.getFriends = function(id){
		return $http({
			method : 'GET',
			url: '../guests/friends/'+id
		});
	}
	
	guestService.createOrder = function(time,tableNum,resId,date,proId){
		return $http({
			method : 'POST',
			url: '../guests/createOrder?tableId='+tableNum+"&resId="+resId+'&proId='+proId,
			data: {
				"date": date,
				"time": time
			}
		
		});
	}
	
	guestService.createCard = function(discount,time,tableNum,resId,date,proId){
		return $http({
			method : 'POST',
			url: '../guests/createCard?tableId='+tableNum+"&resId="+resId+'&proId='+proId,
			data: {
				"date": date,
				"time": time,
				"discount": discount
			}
		
		});
	}
	
	
	
	guestService.getFastCardsForInstitution = function(institutionId){
		return $http({
			method : 'GET',
			url: '../guests/getFastCardsForInstitution/'+institutionId
		
		});
	}
	
	guestService.getReservations = function(userId){
		return $http({
			method : 'GET',
			url: '../guests/getReservations/'+userId
		
		});
	}
	
	guestService.getProjectionForReservation = function(reservationId){
		return $http({
			method : 'GET',
			url: '../guests/getProjectionForReservation/'+reservationId
		
		});
	}
	
	guestService.inviteFriend = function(friendId,resId){
		return $http({
			method : 'POST',
			url: '../guests/inviteFriend?friendId='+friendId+"&resId="+resId
		});
	}
	
	guestService.getFreeTablesCountForInstitution = function(institutionId){
		return $http({
			method : 'GET',
			url: '../guests/getFreeTablesCountForInstitution/'+institutionId
		});
	}
	
	guestService.createReservation = function(reservation,resId){
		return $http({
			method : 'POST',
			url: '../guests/createReservation?id='+resId,
			data: {
					"startTime": reservation.startTime,
					"endTime": reservation.endTime
			}	
		});
	}
	
	guestService.createFastReservation = function(fastCard,insId){
		return $http({
			method : 'POST',
			url: '../guests/createFastReservation/'+ fastCard.id +'/'+insId,
			data: {
					"startTime": fastCard.time,
					"endTime": fastCard.time
			}	
		});
	}
	
	guestService.getProjectionTimesForProjection = function(projectionid){
		return $http({
			method : 'GET',
			url: '../guests/getAllProjectionTimesForProjection/'+projectionid
		});
	}
	
	guestService.getAllTimes = function(){
		return $http({
			method : 'GET',
			url: '../guests/getAllTimes/'
		});
	}
	
	
	
	guestService.getSegments = function(date,reservation,resId){
		return $http({
			method : 'POST',
			url: '../guests/segments/'+date+'/'+resId,
			data: {
				"startTime": reservation.startTime,
				"endTime": reservation.endTime
			}
		});
	}
	
	guestService.getRequests = function(id){
		return $http({
			method : 'GET',
			url: '../guests/recievedRequests/'+id
		});
	}
	
	guestService.getSentRequests = function(id){
		return $http({
			method : 'GET',
			url: '../guests/sentRequests/'+id
		});
	}
	
	guestService.acceptRequest = function(user_id,sender_id){
		return $http({
			method : 'POST',
			url: '../guests/acceptRequest?user_id='+user_id+'&sender_id='+sender_id
		});
	}
	
	guestService.declineRequest = function(user_id,sender_id){
		return $http({
			method : 'POST',
			url: '../guests/declineRequest?user_id='+user_id+'&sender_id='+sender_id
		});
	}
	
	guestService.removeFriend = function(user_id,friend_id){
		return $http({
			method : 'POST',
			url: '../guests/removeFriend?user_id='+user_id+'&friend_id='+friend_id
		});
	}
	
	guestService.getNonFriends = function(user_id){
		return $http({
			method : 'GET',
			url: '../guests/nonFriends/' + user_id
		});
	}
	
	guestService.getFriendsForHistory = function(resId){
		return $http({
			method : 'GET',
			url: '../guests/historyFriends/' + resId
		});
	}
	
	guestService.getHistories = function(userId){
		return $http({
			method : 'GET',
			url: '../guests/history/' + userId
		});
	}
	
	guestService.sendRequest = function(user_id,reciever_id){
		return $http({
			method : 'POST',
			url: '../guests/sendRequest?user_id='+user_id+'&reciever_id='+reciever_id
		});
	}
	
	guestService.addGrade = function(grade,reservationId){
		return $http({
			method : 'POST',
			url: '../guests/addGrade/'+reservationId,
			data: {
				"gradeOfOrderItem": grade.gradeOfProjection,
				"gradeOfInstitution": grade.gradeOfInstitution
			}
		});
	}
	
	guestService.editGrade = function(grade,reservationId){
		return $http({
			method : 'PUT',
			url: 'guests/editGrade/'+reservationId,
			data: {
				"gradeOfOrderItem": grade.gradeOfProjection,
				"gradeOfInstitution": grade.gradeOfInstitution
			}
		});
	}
	
	guestService.getAverageGradeForInstitution = function(institutionId){
		return $http({
			method : 'GET',
			url: 'guests/getAverageGradeForInstitution/'+institutionId
		});
	}
	
	guestService.getAverageGradeForProjection = function(reservationId){
		return $http({
			method : 'GET',
			url: 'guests/getAverageGradeForProjection/'+reservationId
		});
	}
	
	guestService.getAverageGradeForProjection1 = function(projectionId){
		return $http({
			method : 'GET',
			url: 'guests/getAverageGradeForProjection1/'+projectionId
		});
	}
	
	guestService.getGradeForUser = function(userId,reservationId){
		return $http({
			method : 'GET',
			headers: { 'Content-Type': 'application/json'},
			url: 'guests/getGradeForUser/'+ userId + '/' +reservationId,
			data: ''
		});
	}
	
	guestService.deleteGrade = function(reservationId){
		return $http({
			method : 'DELETE',
			url: 'guests/deleteGrade/'+reservationId,
		});
	}

	return guestService;
});