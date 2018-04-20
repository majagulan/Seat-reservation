var app = angular.module('webApp' );


var compareTo = function() {
    return {
        require: "ngModel",
        scope: {
            otherModelValue: "=compareTo"
        },
        link: function(scope, element, attributes, ngModel) {
             
            ngModel.$validators.compareTo = function(modelValue) {
                return modelValue == scope.otherModelValue;
            };
 
            scope.$watch("otherModelValue", function() {
                ngModel.$validate();
            });
        }
    };
};

app.directive("compareTo", compareTo);


app.controller('appController',['$rootScope','$scope','$location','SessionService',function($rootScope,$scope,$location,sessionService){
	
	
	sessionService.getLoggedUser().then(function(response){
		$rootScope.loggedUser = response.data;
		if (!$rootScope.loggedUser) {
			$location.path('/login');
		} else {
			if(($rootScope.loggedUser.userRole == 'FUNMANAGER') && $rootScope.loggedUser.firstLogIn)
				$location.path('/changePassword');
			else
				$location.path('/home');
		}
	});
	
	
	$scope.logout = function(){
		$rootScope.loggedUser = '';
		
		
		sessionService.logOut().then(function(response){
		});
		
	}
}]);

app.controller('loginController',['$rootScope','$scope','$location','$sce','institutionManagerService','GuestService','SystemManagerService','SessionService',function($rootScope,$scope,$location,$sce,institutionManagerService,guestService,systemManagerService,sessionService){
	

	
	if (!$rootScope.loggedUser) {
		$location.path('/login');
	} else {
		if(($rootScope.loggedUser.userRole == 'FUNMANAGER') && $rootScope.loggedUser.firstLogIn)
			$location.path('/changePassword');
		else
			$location.path('/home');
	}
	
	 $scope.map = {center: {latitude: 51.219053, longitude: 4.404418 }, zoom: 14 };
	
	$scope.login = function(){
		var user = $scope.user;
	    sessionService.login(user)
	    .then(function(response){
	    	if(response.data.status=='NOT_ACTIVE'){
	    		swal("ERROR", "NOT ACTIVATED", "error");
	    		return;
	    	}
    		$rootScope.loggedUser = response.data;
    		swal({
    			  title: "Success!",
    			  text: "Welcome " + response.data.userName,
    			  type: "success",
    			  timer: 2000
 
    			});
    		if(($rootScope.loggedUser.userRole == 'FUNMANAGER') && $rootScope.loggedUser.firstLogIn)
				$location.path('/changePassword');
			else
				$location.path('/home');
	    })
	    .catch(function(response) {
	    	swal("ERROR", "BAD CREDENTIALS", "error");
	    });
	
	}
	
    $scope.register = function(){
    	$location.path('/register')
    }
    
	$scope.showView = function(type){
		$scope.type = type;
		$scope.selected = null;
	}
	
	$scope.setSelected = function(selected){
		if($scope.selected == selected){
			$scope.selected = null;
		} else {
			$scope.selected = selected;
			institutionManagerService.getProjectionsForInstitution($scope.selected.id).then(
					function(response) {
						$scope.institutionProjections = response.data;
						angular.forEach($scope.institutionProjections, function(value, key){
							guestService.getAverageGradeForProjection1(value.id).then(function(response){
								value.averageGradeForProjection=response.data;
							});
						});
						
					});
		}
		

	}
	
	$scope.trustSrc = function(url){
		return $sce.trustAsResourceUrl(url);
	}
	
	systemManagerService.getinstitutions().then(
			function(response) {
				$scope.institutions = response.data;
				angular.forEach($scope.institutions, function(value, key){
						guestService.getFreeTablesCountForInstitution(value.id).then(function(response){
							value.freeSpace=response.data;
						});
				});
			});
	
	
}]);

app.controller('registerController',['$rootScope','$scope','$location','$http','SessionService',function($rootScope,$scope,$location,$http,sessionService) {
	
	
	if (!$rootScope.loggedUser) {
		$location.path('/register');
	} else {
		$location.path('/shoppingCart');
	}
	
	$scope.error = false;
	
    $scope.register = function(){
    	var user = $scope.user;
    	sessionService.register(user).then(function(response){
    		if(response.data){
    			$scope.error = false;
    			$location.path('/login')
    		} else {
    			$scope.error = true;
    		}
    	});
	}
}]);

app.controller('homeController',['$rootScope','$scope','$location','$http', 'institutionManagerService',function($rootScope,$scope,$location,$http, institutionManagerService) {
	
	if (!$rootScope.loggedUser) {
		$location.path('/login');
	}  else if(($rootScope.loggedUser.userRole == 'FUNMANAGER') && $rootScope.loggedUser.firstLogIn)
		$location.path('/changePassword');
	else
		$location.path('/home');
	
	institutionManagerService
	.checkIfRequestOfferExpired();
	
	
	$scope.minDate = moment(new Date()).format('YYYY-MM-DD')

	
	switch($rootScope.loggedUser.userRole) {
	    case 'GUEST':
	    	$scope.show = 1;
	        break;
	}
	
	$scope.showView = function(number){
		$scope.show = number;
	}
	
}]);


app.controller('profileController',['$rootScope','$scope','$location','$http','SessionService','GuestService',function($rootScope,$scope,$location,$http,sessionService,guestService) {
	
	if (!$rootScope.loggedUser) {
		$location.path('/login');
	} else if(($rootScope.loggedUser.userRole == 'FUNMANAGER' ||  $rootScope.loggedUser.userRole == 'INSTITUTIONMANAGER') && $rootScope.loggedUser.firstLogIn)
		$location.path('/changePassword');
	
	
	sessionService.getLoggedUser().then(function(response){
		$scope.user = response.data;
		$scope.user.dateOfBirth = new Date(response.data.dateOfBirth);
	});
	
	$scope.confirmEditProfile=function(){
		sessionService.update($scope.user).then(function(response){
			$rootScope.loggedUser = response.data;
			$location.path('/home');
			swal({
	  			  title: "Success!",
	  			  text: 'You have successfully edited your profile.',
	  			  type: "success",
	  			  timer: 3000
	  			});
		});
	}
	
	guestService.getRankForUserPoints($rootScope.loggedUser.guestPoints).then(function(response){
		$scope.user.userRank = response.data.userRankType;
	});
	
	guestService.getPointsForUser($rootScope.loggedUser.id).then(function(response){
		$scope.user.guestPoints = response.data;
	});


}]);


app.controller('publishController',['$rootScope','$scope','$location','$http','GuestService','SessionService','ngNotify',function($rootScope,$scope,$location,$http,guestService,sessionService,ngNotify) {
	
	guestService
	.getAllNonActiveRequestOffers()
	.then(
			function(response) {
				if (response.data) {
					$scope.requestOffers = response.data;
				} 
			});
	
	$scope.setSelectedRequestOffer=function(selected){
		if($scope.selectedRequestOffer == selected){
			$scope.selectedRequestOffer = null;
		} else {
			$scope.selectedRequestOffer = selected;
		}
	}
	
	$scope.activate=function(){
		guestService.activateRequestOffer($scope.selectedRequestOffer.id).then(function(response){
			var index = $scope.requestOffers.indexOf($scope.selectedRequestOffer);
			$scope.requestOffers.splice(index, 1);
			$scope.selectedRequestOffer = null;
			ngNotify.set('Successfuly activated request offer' , {
				type : 'success'
			});
		});
	}
	
	$scope.erase = function(){
		guestService.destroyRequestOffer($scope.selectedRequestOffer.id).then(function(response){
			var index = $scope.requestOffers.indexOf($scope.selectedRequestOffer);
			$scope.requestOffers.splice(index, 1);
			$scope.selectedRequestOffer = null;
			ngNotify.set('Successfuly declined request offer' , {
				type : 'success'
			});
		});

	}

}]);

app.controller('guestRequestOfferController',['$rootScope','$scope','ngNotify','$location','$http','institutionManagerService','SessionService',function($rootScope,$scope,ngNotify,$location,$http,institutionManagerService,sessionService) {
	
	if (!$rootScope.loggedUser) {
		$location.path('/login');
	} else if(($rootScope.loggedUser.userRole == 'FUNMANAGER') && $rootScope.loggedUser.firstLogIn)
		$location.path('/changePassword');
	
	
	institutionManagerService
	.getRequestOffersForGuest($rootScope.loggedUser.id)
	.then(
			function(response) {
				if (response.data) {
					$scope.guestOffers = response.data;
				} 
			});
	
	$scope.setSelectedRequestOffer = function(requestOffer){
		$scope.showR = null;
		if($scope.selectedRequestOffer == requestOffer){
			$scope.selectedRequestOffer = null;
		} else {
			$scope.selectedRequestOffer = requestOffer;
		}
	}
	
	$scope.registerRequestOffer  = function() {
		institutionManagerService
				.registerRequestOffer($scope.requestOffer, $rootScope.loggedUser.id)
				.then(
						function(response) {
							if (response.data) {
								response.data.startDate =  moment(response.data.startDate).format('YYYY-MM-DD');
								response.data.expirationDate =  moment(response.data.expirationDate).format('YYYY-MM-DD');
								$scope.guestOffers.push(response.data);
								$scope.showR = null;
								ngNotify.set('Successfuly registrated request offer' , {
									type : 'success'
								});
							} 
						}).catch(function(response) {
							ngNotify.set('Date must be today, or future, expiration date must be after start date' , {
								type : 'error',
								sticky : 'true'
							});
							   console.error('Gists error', response.status, response.data)
						  });
	}
	
	$scope.deleteRequestOffer  = function() {
		institutionManagerService
				.deleteRequestOffer($scope.selectedRequestOffer.id)
				.then(
						function(response) {
							if (response.status == 200) {
								$scope.error = false;
								var index = $scope.guestOffers
								.indexOf($scope.selectedRequestOffer);
						$scope.guestOffers.splice(index, 1);
								$scope.showR = null;
								$scope.selectedBid = null;
								$scope.selectedinstitutionProjection = null;
								$scope.selectedRequestOfferProjection = null;
								ngNotify.set('Successfuly deleted request offer' , {
									type : 'success'
								});
							}
						}).catch(function(response) {
							ngNotify.set('Error delete' , {
								type : 'error',
								sticky : 'true'
							});
							   console.error('Gists error', response.status, response.data)
						  });
	}
	
	$scope.setSelectedBid = function(bid){
		$scope.selectedBid = bid;
	}
	
	$scope.acceptBid = function() {
		institutionManagerService
				.acceptBid($scope.selectedRequestOffer.id, $scope.selectedBid.id)
				.then(
						function(response) {
							if (response.data) {
								$scope.selectedBid = null;
								$scope.selectedinstitutionProjection = null;
								$scope.selectedRequestOfferProjection = null;
								$scope.selectedRequestOffer.status = false;
								$scope.showR = null;
								ngNotify.set('Successfuly accepted bidder offer' , {
									type : 'success'
								});
							}
						});
	}
	
	$scope.editRequestOfferData  = function() {
		institutionManagerService
				.editRequestOfferData($scope.editRequestOffer)
				.then(
						function(response) {
							if (response.data) {
								response.data.startDate =  moment(response.data.startDate).format('YYYY-MM-DD');
								response.data.expirationDate =  moment(response.data.expirationDate).format('YYYY-MM-DD');
								var index = $scope.guestOffers
								.indexOf($scope.selectedRequestOffer);
						$scope.guestOffers[index] = response.data;
								$scope.showR = null;

							} 
						}).catch(function(response) {
							ngNotify.set('Date must be today, or future, expiration date must be after start date' , {
								type : 'error',
								sticky : 'true'
							});
							   console.error('Gists error', response.status, response.data)
						  });
	}
	
	$scope.displayRequest = function(tab) {
		$scope.showR = tab;
		if(tab == 1)
			$scope.selectedRequestOffer = null; 
		if(tab == 2) {
			$scope.editRequestOffer = $scope.selectedRequestOffer;
			institutionManagerService.getRequestOffer(
					$scope.selectedRequestOffer.id).then(
					function(response) {
						if (response.data) {
							if($scope.selectedRequestOffer.status != response.data.status){
								var index = $scope.managerOffers
								.indexOf($scope.selectedRequestOffer);
								$scope.managerOffers[index] = response.data;
								$scope.showR = null;
								$scope.selectedShift = response.data;
								ngNotify.set('Sorry this request can not be edited.' );
							}
							else 		
								$scope.showR = 2;
						}
					});
			}
		
	}
	
	$scope.getBidderOffersForRequest = function() {
		institutionManagerService
				.getBidderOffersForRequest($scope.selectedRequestOffer.id)
				.then(
						function(response) {
							if (response.data) {
								$scope.biddings = response.data;
								$scope.showR = 3;
								$scope.selectedBid = null;
								$scope.selectedinstitutionProjection = null;
								$scope.selectedRequestOfferProjection = null;
							} 
						});
	}
	

}]);

app.controller('institutionController',['$rootScope','$scope','$location','$http','$sce','SessionService','institutionManagerService','SystemManagerService','WaiterService','GuestService',function($rootScope,$scope,$location,$http,$sce,sessionService,institutionManagerService,systemManagerService,waiterService,guestService) {
	
	if (!$rootScope.loggedUser) {
		$location.path('/login');
	} else if(($rootScope.loggedUser.userRole == 'FUNMANAGER' ||  $rootScope.loggedUser.userRole == 'INSTITUTIONMANAGER') && $rootScope.loggedUser.firstLogIn)
		$location.path('/changePassword');
	else
		$location.path('/home');
	
	
	$scope.selected = null;
	
	$scope.currentDate = new Date();
	
	$scope.selectedTables = [];
	
	$scope.selectedFriends = [];
	
	$scope.makeReservation = false;
	$scope.fastReservation = false
	
	$scope.show = null;
	
	
	  $scope.sortInstitutionType     = 'name'; // set the default sort type
	  $scope.sortInstitutionReverse  = false;  // set the default sort order
	  
	  $scope.sortProjectionType     = 'name'; // set the default sort type
	  $scope.sortPojectionReverse  = false;  // set the default sort order
	
	$scope.institutionProjections = [];
	
	$scope.invite = function(friend){
		var friendIndex = $scope.selectedFriends.indexOf(friend.id);
		if(friendIndex == -1){
			$scope.selectedFriends.push(friend.id);
			friend.invited = true;
		} else {
			$scope.selectedFriends.splice(friendIndex,1);
			friend.invited = false;
		}
	}
	
	$scope.fastReserve = function(fastReserve){
		guestService.createFastReservation(fastReserve,$scope.selected.id).then(function(response){
				var index = $scope.fastInstitutionCards.indexOf($scope.selectedFastCard);
				$scope.fastInstitutionCards.splice(index,1);
				
	    		swal({
	    			  title: "Success!",
	    			  text: "Fast Card Reservation made",
	    			  type: "success",
	    			  timer: 2000
	 
	    			});			   
		});
	}
	
	$scope.confirm = function(){
		guestService.createReservation($scope.reservation,$scope.selected.id).then(function(response){
			   var reservation = response.data
			   angular.forEach($scope.selectedTables, function(value, key){
				      guestService.createOrder($scope.reservation.selectedProjectionTime,value,reservation.id,$scope.reservation.date,$scope.selectedinstitutionProjection.id).then(function(response){
				    	 $scope.lastAddedOrder = response.data; 
				      });
			   });
			   
			   angular.forEach($scope.selectedFriends, function(value, key){
				      guestService.inviteFriend(value,reservation.id).then(function(response){
				    	 $scope.lastAddedFriend = response.data; 
				      });
			   });
			   
			   
			      guestService.inviteFriend($rootScope.loggedUser.id,reservation.id).then(function(response){
				    	 $scope.lastAddedFriend = response.data; 
				      });
			   
	    		swal({
	    			  title: "Success!",
	    			  text: "Reservation made",
	    			  type: "success",
	    			  timer: 2000
	 
	    			});
	    		$scope.institution = null;
	    		$scope.makeReservation = false;
	    		$scope.selected = null;
	    		$scope.selectedTables = [];
	    		$scope.selectedFriends = [];
	    		$scope.show = null;
			   
		});

	}
	
	$scope.cancel = function(){
		$scope.show = null;
	}
	
	$scope.setSelectedinstitutionProjection = function(projection) {
		if($scope.selectedinstitutionProjection == projection){
			$scope.selectedinstitutionProjection = null;
			$scope.makeReservation = false;
			$scope.selctedSegment = null;
			$scope.reservation = null;
			$scope.show = null;
		} else {
			$scope.selectedinstitutionProjection = projection;
		     guestService.getProjectionTimesForProjection($scope.selectedinstitutionProjection.id).then(function(response){
		    	 $scope.selectedinstitutionProjection.projectionTimes = response.data; 
			 });
		}
		$scope.fastReservation = false
		
		
	}
	
	
	$scope.initFriend = function(friend){
		friend.invited = false;
	}
	
	$scope.showMake = function(){
		$scope.makeReservation = true;
		$scope.fastReservation = false;
	}
	
	$scope.showFast = function(){
		$scope.fastReservation = true;
		$scope.makeReservation = false;
	}
	
	$scope.getTablesForSegment=function(id,index){
		waiterService.getTablesForSegment(id).then(function(response){
			$scope.segments[index].tables=response.data;
		});
	}
	
	
	$scope.addTable=function(table,index){
		if(table.free){
			var tableIndex = $scope.selectedTables.indexOf(table.id);
			if(tableIndex == -1){
				var id =table.segment.id+"#"+index;
				document.getElementById(id).style.backgroundColor ="blue";
				$scope.selectedTables.push(table.id);
			} else {
				var id =table.segment.id+"#"+index;
				document.getElementById(id).style.backgroundColor = null;
				$scope.selectedTables.splice(tableIndex,1);
			}
		}
	}
	
	
	$scope.showView = function(show){
		if(show==1){
			$scope.getSegments();
		}
		$scope.show = show;
	}
	
	$scope.clearTables=function(){
		$scope.selectedTables = [];
	}
	$scope.setSelected = function(selected){
		if($scope.selected == selected){
			$scope.selected = null;
			$scope.selectedinstitutionProjection = null;
			$scope.fastReservation = false;
		} else {
			$scope.selected = selected;
			institutionManagerService.getProjectionsForInstitution($scope.selected.id).then(
					function(response) {
						$scope.institutionProjections = response.data;
						angular.forEach($scope.institutionProjections, function(value, key){
							guestService.getAverageGradeForProjection1(value.id).then(function(response){
								value.averageGradeForProjection=response.data;
							});
						});
					});
			
			guestService.getFastCardsForInstitution($scope.selected.id).then(
					function(response) {
						$scope.fastInstitutionCards = response.data;
					});
		}
		
		$scope.makeReservation = false;
		$scope.selctedSegment = null;
		$scope.reservation = null;
		$scope.show = null;
	}
	
	$scope.setSelectedFastCard = function(fastCard){
		if($scope.selectedFastCard == fastCard){
			$scope.selectedFastCard = null;
		} else {
			$scope.selectedFastCard = fastCard;
		}
	}
	
	systemManagerService.getinstitutions($rootScope.loggedUser.id).then(function(response){
		$scope.institutions = response.data;
	});
	
	$scope.getSegments = function(){
		guestService.getSegments($scope.reservation.date,$scope.reservation,$scope.selected.id).then(function(response){
			$scope.segments = response.data;
		});
	}
	
	guestService.getFriends($rootScope.loggedUser.id).then(function(response){
		$scope.friends = response.data;
	});
	
	$scope.range = function(min, max, step) {
	    step = step || 1;
	    var input = [];
	    for (var i = min; i < max; i += step) {
	        input.push(i);
	    }
	    return input;
	}
	
	$scope.trustSrc = function(url){
		return $sce.trustAsResourceUrl(url);
	};

	
}]);

app.controller('historyController',['$rootScope','$scope','$location','$http','GuestService',function($rootScope,$scope,$location,$http,guestService) {
	
	if (!$rootScope.loggedUser) {
		$location.path('/login');
	} 
	
	$scope.selected = null;
	$scope.addGrade=null;
	$scope.editGrade=null;
	$scope.grade=new Object();
	
	guestService.getHistories($rootScope.loggedUser.id).then(function(response){
		$scope.histories = response.data;
		 angular.forEach($scope.histories, function(value, key){
				guestService.getFriendsForHistory(value.id).then(function(response){
					value.friends=response.data;
				});
				guestService.getProjectionForReservation(value.id).then(function(response){
					value.projection=response.data;
				});
				guestService.getGradeForUser($rootScope.loggedUser.id,value.id).then(function(response){
					value.gradeOfProjection=response.data.gradeOfOrderItem;
					value.gradeOfInstitution=response.data.gradeOfInstitution;
				});
				guestService.getAverageGradeForInstitution(value.institution.id).then(function(response){
					value.averageGradeForInstitution=response.data;
				});
				guestService.getAverageGradeForProjection(value.id).then(function(response){
					value.averageGradeForProjection=response.data;
				});
		 });
	});
	
	
	$scope.selectHistory=function(history){
		$scope.selected = history;
	}
	
	$scope.add=function(){
		$scope.addGrade=$scope.selected;
		$scope.editGrade=null;
	}
	
	$scope.edit=function(){
		$scope.addGrade=null;
		$scope.editGrade=$scope.selected;
	}
	
	$scope.deleteGrade=function(){
		guestService.deleteGrade($scope.selected.id).then(function(response){
			$scope.selected.gradeOfProjection = "";
			$scope.selected.gradeOfInstitution = "";
			$scope.addGrade=null;
			$scope.editGrade=null;
			
			 angular.forEach($scope.histories, function(value, key){
				 if(value.institution.id === $scope.selected.institution.id)
					guestService.getAverageGradeForInstitution($scope.selected.institution.id).then(function(response){
						value.averageGradeForInstitution=response.data;
					});
				 
				if(value.projection.id == $scope.selected.projection.id)
					guestService.getAverageGradeForProjection($scope.selected.id).then(function(response){
						value.averageGradeForProjection=response.data;
					});
			 });
			
			swal({
				  title: "Delete grade",
				  text: "You have successfully deleted grade for this reservation.",
				  type: "success",
				  timer: 3000
			});
		});
	}
	
	$scope.confirmAddGrade=function(){
		guestService.addGrade($scope.grade,$scope.addGrade.id).then(function(response){
			$scope.addGrade=null;
			$scope.selected.gradeOfProjection = $scope.grade.gradeOfProjection;
			$scope.selected.gradeOfInstitution = $scope.grade.gradeOfInstitution;
			
			 angular.forEach($scope.histories, function(value, key){
				 if(value.institution.id === $scope.selected.institution.id)
					guestService.getAverageGradeForInstitution($scope.selected.institution.id).then(function(response){
						value.averageGradeForInstitution=response.data;
					});
				 
				if(value.projection.id == $scope.selected.projection.id)
					guestService.getAverageGradeForProjection($scope.selected.id).then(function(response){
						value.averageGradeForProjection=response.data;
					});
			 });
			
			guestService.getAverageGradeForInstitution($scope.selected.institution.id).then(function(response){
				$scope.selected.averageGradeForInstitution=response.data;
			});
			
			guestService.getAverageGradeForProjection($scope.selected.id).then(function(response){
				$scope.selected.averageGradeForProjection=response.data;
			});
			
		}).catch(function(response) {
			swal({
				  title: "Add grade",
				  text: "You have already created grade for this reservation.",
				  type: "error",
				  timer: 3000
			});
	    });
	}
	
	$scope.confirmEditGrade=function(){
		guestService.editGrade($scope.grade,$scope.editGrade.id).then(function(response){
			$scope.editGrade=null;
			$scope.selected.gradeOfProjection = $scope.grade.gradeOfProjection;
			$scope.selected.gradeOfInstitution = $scope.grade.gradeOfInstitution;
			
			 angular.forEach($scope.histories, function(value, key){
				 if(value.institution.id === $scope.selected.institution.id)
					guestService.getAverageGradeForInstitution($scope.selected.institution.id).then(function(response){
						value.averageGradeForInstitution=response.data;
					});
				 
				if(value.projection.id == $scope.selected.projection.id)
					guestService.getAverageGradeForProjection($scope.selected.id).then(function(response){
						value.averageGradeForProjection=response.data;
					});
			 });
			 
		}).catch(function(response) {
			swal({
				  title: "Edit grade",
				  text: "You have not created grade for this reservation.",
				  type: "error",
				  timer: 3000
			});
	    });
	}
	
}]);

app.controller('friendsController',['$rootScope','$scope','$location','$http','GuestService',function($rootScope,$scope,$location,$http,guestService) {
	
	if (!$rootScope.loggedUser) {
		$location.path('/login');
	} 
	
	$scope.forRemoval = null;
	
	$scope.showRequest = true;
	$scope.showFriend = true;
	$scope.search = true;
	$scope.showSent = true;
	
	
		
	$scope.showDialog = function(friend){
		document.getElementById('id01').style.display='block';
		$scope.display = friend;
	}
	
	$scope.showSearch = function(){
		if(!$scope.search){
			$scope.search = true;
		}else{
			$scope.search = false;
		}
	}
	
	$scope.showSents = function(){
		if(!$scope.showSent){
			$scope.showSent = true;
		}else{
			$scope.showSent = false;
		}
	}
	
	$scope.showRequests = function(){
		if(!$scope.showRequest){
			$scope.showRequest = true;
		}else{
			$scope.showRequest = false;
		}
	}
	
	$scope.showFriends = function(){
		if(!$scope.showFriend){
			$scope.showFriend = true;
		}else{
			$scope.showFriend = false;
		}
	}
	
	$scope.acceptRequest = function(request){
		$scope.forRemoval = request;
		guestService.acceptRequest($rootScope.loggedUser.id,request.id).then(function(response){
			var index = $scope.requests.indexOf($scope.forRemoval);
			$scope.requests.splice(index,1);
			$scope.friends.push(response.data);
			$scope.forRemoval = null;
		});
	}
	
	$scope.declineRequest = function(request){
		$scope.forRemoval = request;
		guestService.declineRequest($rootScope.loggedUser.id,request.id).then(function(response){
			var index = $scope.requests.indexOf($scope.forRemoval);
			$scope.requests.splice(index,1);
			$scope.forRemoval = null;
		});
	}
	
	$scope.removeFriend = function(friend){
		$scope.forRemoval = friend;
		guestService.removeFriend($rootScope.loggedUser.id,friend.id).then(function(response){
			var index = $scope.friends.indexOf($scope.forRemoval);
			$scope.friends.splice(index,1);
			$scope.nonFriends.push($scope.forRemoval)
			$scope.forRemoval = null;
		});
	}
	
	$scope.sendRequest = function(reciever){
		$scope.forRemoval = reciever
		guestService.sendRequest($rootScope.loggedUser.id,reciever.id).then(function(response){
			var index = $scope.nonFriends.indexOf($scope.forRemoval);
			$scope.nonFriends.splice(index,1);
			$scope.sentRequests.push($scope.forRemoval);
			$scope.forRemoval = null;
		});
	}
	
	guestService.getFriends($rootScope.loggedUser.id).then(function(response){
		$scope.friends = response.data;
	});
	
	guestService.getRequests($rootScope.loggedUser.id).then(function(response){
		$scope.requests = response.data;
	});
	
	guestService.getSentRequests($rootScope.loggedUser.id).then(function(response){
		$scope.sentRequests = response.data;
	});
	
	guestService.getNonFriends($rootScope.loggedUser.id).then(function(response){
		$scope.nonFriends = response.data;
	});



}]);