var app = angular.module('webApp');

app.factory('SystemManagerService', function systemManagerService($http) {

	systemManagerService.getinstitutionManagers = function(id) {
		return $http({
			method : 'GET',
			url : '../systemmanagers/getinstitutionManagersForinstitution?id='
					+ id
		});
	}

	systemManagerService.getinstitutions = function() {
		return $http({
			method : 'GET',
			url : '../systemmanagers/getAllinstitutions'
		});
	}

	systemManagerService.getSystemManagers = function() {
		return $http({
			method : 'GET',
			url : '../systemmanagers/getAllSystemManager'
		});
	}
	
	systemManagerService.registerInstitution = function(institution) {
		return $http({
			method : 'POST',
			url : '../systemmanagers/registerInstitution',
			data : {
				"institutionName" : institution.institutionName,
				"description" : institution.description,
				"institutionType" : institution.institutionType,
				"adresaIns" : institution.adresaIns
			}
		});
	}

	systemManagerService.editinstitutionData = function(institution) {
		return $http({
			method : 'PUT',
			url : '../systemmanagers/updateinstitution',
			data : {
				"id" : institution.id,
				"institutionName" : institution.institutionName,
				"description" : institution.description,
				"institutionType" : institution.institutionType,
				"adresaIns" : institution.adresaIns
			}
		});
	}
	systemManagerService.deleteinstitution = function(institution_id) {
		return $http({
			method : 'DELETE',
			url : '../systemmanagers/deleteinstitution?id=' + institution_id
		});
	}

	systemManagerService.registerInstitutionManager = function(institution_id,
			institutionManager) {
		return $http({
			method : 'POST',
			url : '../systemmanagers/registerInstitutionManager?id='
					+ institution_id,
			data : {
				"userName" : institutionManager.userName,
				"surname" : institutionManager.surname,
				"email" : institutionManager.email,
				"password" : institutionManager.password,
				"dateOfBirth" : institutionManager.dateOfBirth,
				"userRole" : "INSTITUTIONMANAGER"
			}
		});
	}
	
	systemManagerService.deleteinstitutionManager = function(
			institution_manager_id) {
		return $http({
			method : 'DELETE',
			url : '../systemmanagers/deleteinstitutionManager?id='
					+ institution_manager_id
		});
	}


	systemManagerService.registerSystemManager = function(systemManager) {
		return $http({
			method : 'POST',
			url : '../systemmanagers/registerSystem',
			data : {
				"userName" : systemManager.userName,
				"surname" : systemManager.surname,
				"email" : systemManager.email,
				"password" : systemManager.password,
				"dateOfBirth" : systemManager.dateOfBirth,
				"userRole" : "SYSTEMMANAGER"
			}
		});
	}

	systemManagerService.deleteSystemManager = function(system_manager_id) {
		return $http({
			method : 'DELETE',
			url : '../systemmanagers/deleteSystemManager?id='
					+ system_manager_id
		});
	}

	return systemManagerService;

});

app.factory('BidderService', function bidderService($http) {

	bidderService.getActiveOffers = function() {
		return $http({
			method : 'GET',
			url : '../bidders/getActiveRequestOffers'
		});
	}

	bidderService.getProjections = function(request_id) {
		return $http({
			method : 'GET',
			url : '../institutionmanagers/getAllProjectionsForRequestOffer?id='
					+ request_id
		});
	}

	bidderService.getBidderOffersForBidder = function(bidder_id) {
		return $http({
			method : 'GET',
			url : '../bidders/getBiddingsForBidder?id=' + bidder_id
		});
	}

	bidderService.getRequestOffer = function(id) {
		return $http({
			method : 'GET',
			url : '../bidders/getRequestOffer?id=' + id
		});
	}
	
	bidderService.getBidderOffer = function(id) {
		return $http({
			method : 'GET',
			url : '../bidders/getBidderOffer?id=' + id
		});
	}

	bidderService.getBidderOfferForBidderAndRequest = function(request_id,
			bidder_id) {
		return $http({
			method : 'GET',
			url : '../bidders/getBidderOfferForBidderAndRequestOffer?b_id='
					+ bidder_id + '&ro_id=' + request_id
		});
	}
	
	bidderService.registerBidderOffer = function(bidderOffer, bidder_id,
			request_offer_id) {
		return $http({
			method : 'POST',
			url : '../bidders/registerBid?request_offer_id=' + request_offer_id
					+ '&bidder_id=' + bidder_id,
			data : {
				"price" : bidderOffer.price,
				"garanty" : bidderOffer.garanty,
				"dateOfDelivery" : bidderOffer.dateOfDelivery
			}
		});
	}

	bidderService.editBidderOfferData = function(bidderOffer) {
		return $http({
			method : 'PUT',
			url : '../bidders/updateBid',
			data : {
				"id" : bidderOffer.id,
				"price" : bidderOffer.price,
				"garanty" : bidderOffer.garanty,
				"dateOfDelivery" : bidderOffer.dateOfDelivery
			}
		});
	}	

	bidderService.deleteBidderOffer = function(id) {
		return $http({
			method : 'DELETE',
			url : '../bidders/deleteBid?id=' + id
		});
	}

	bidderService.editBidder = function(bidder) {
		return $http({
			method : 'PUT',
			url : '../bidders/update',
			data : {
				"id" : bidder.id,
				"userName" : bidder.userName,
				"surname" : bidder.surname,
				"email" : bidder.email,
				"password" : bidder.password,
				"dateOfBirth" : bidder.dateOfBirth,
				"firstLogIn" : bidder.firstLogIn
			}
		});
	}

	return bidderService;
});

app
		.factory(
				'institutionManagerService',
				function institutionManagerService($http) {

					institutionManagerService.checkIfRequestOfferExpired = function() {
						return $http({
							method : 'PUT',
							url : '../institutionmanagers/checkIfRequestOfferExpired'
						});
					}
					


					institutionManagerService.seeIfCanDeleteSegment = function(
							id) {
						return $http({
							method : 'GET',
							url : '../institutionmanagers/checkIfSegmentCanBeDeleted?id='
									+ id
						});
					}
					
					institutionManagerService.getInstitutionTable = function(
							id) {
						return $http({
							method : 'GET',
							url : '../institutionmanagers/getInstitutionTable?id='
									+ id
						});
					}
					

					institutionManagerService.getRequestOffer = function(
							id) {
						return $http({
							method : 'GET',
							url : '../institutionmanagers/getRequestOffer?id='
									+ id
						});
					}
					
					institutionManagerService.institutionGrade = function(
							id) {
						return $http({
							method : 'GET',
							url : '../institutionmanagers/getgradeForInstitution?id='
									+ id
						});
					}
					
					institutionManagerService.getGradeOfWaiter = function(
							id) {
						return $http({
							method : 'GET',
							url : '../institutionmanagers/getGradeForWaiter?id='
									+ id
						});
					}
					
					institutionManagerService.getProjectionGrade = function(
							id, pro_id) {
						return $http({
							method : 'GET',
							url : '../institutionmanagers/getGradeForOrder?id='
									+ pro_id + '&res_id=' + id
						});
					}
					
					institutionManagerService.getEarningForWaiter = function(
							id) {
						return $http({
							method : 'GET',
							url : '../institutionmanagers/getWaiterEarnings?id='
									+ id
						});
					}
					
					institutionManagerService.getinstitutionEarnings = function(
							id, start, end) {
						return $http({
							method : 'GET',
							url : '../institutionmanagers/getinstitutionEarnings?id='
									+ id + '&start=' + start + '&end=' + end
						});
					}

					institutionManagerService.getinstitution = function(id) {
						return $http({
							method : 'GET',
							url : '../institutionmanagers/getInstitutionForManager?id='
									+ id
						});
					}

					institutionManagerService.getinstitutionWorkers = function(id) {
						return $http({
							method : 'GET',
							url : '../institutionmanagers/getAllWorkersForInstitution?id='
									+ id
						});
					}

					institutionManagerService.getinstitutionSegments = function(
							id) {
						return $http({
							method : 'GET',
							url : '../institutionmanagers/getAllSegmentsForInstitution?id='
									+ id
						});
					}

					institutionManagerService.getTables = function(id) {
						return $http({
							method : 'GET',
							url : '../institutionmanagers/getAllTablesForSegment?id='
									+ id
						});
					}

					institutionManagerService.getProjectionsForInstitution = function(
							id) {
						return $http({
							method : 'GET',
							url : '../institutionmanagers/getAllProjectionsForInstitution?id='
									+ id
						});
					}

					institutionManagerService.getAllProjections = function() {
						return $http({
							method : 'GET',
							url : '../institutionmanagers/getAllProjections'
						});
					}

					institutionManagerService.registerWaiter = function(waiter,
							id) {
						return $http({
							method : 'POST',
							url : '../institutionmanagers/registerWaiter?id='
									+ id,
							data : {
								"userName" : waiter.userName,
								"surname" : waiter.surname,
								"email" : waiter.email,
								"password" : waiter.password,
								"dateOfBirth" : waiter.dateOfBirth,
								"shirtSize" : waiter.shirtSize,
								"shoeNumber" : waiter.shoeNumber,
								"userRole" : "WAITER"
							}
						});
					}
					institutionManagerService.registerBidder = function(b) {
						return $http({
							method : 'POST',
							url : '../institutionmanagers/registerBidder',
							data : {
								"userName" : b.userName,
								"surname" : b.surname,
								"email" : b.email,
								"password" : b.password,
								"dateOfBirth" : b.dateOfBirth,
								"userRole" : "BIDDER"
							}
						});
					}
					institutionManagerService.deleteWorker = function(id) {
						return $http({
							method : 'DELETE',
							url : '../institutionmanagers/removeWorker?id=' + id
						});
					}

					institutionManagerService.editinstitutionData = function(
							institution) {
						return $http({
							method : 'PUT',
							url : '../institutionmanagers/updateinstitution',
							data : {
								"id" : institution.id,
								"institutionName" : institution.institutionName,
								"description" : institution.description,
								"institutionType" : institution.institutionType,
								"adresaIns" : institution.adresaIns
							}
						});
					}

					institutionManagerService.registerProjection = function(
							projection, id) {
						return $http({
							method : 'POST',
							url : '../institutionmanagers/addProjection?rest_id=' + id,
							data : {
								"projectionName" : projection.projectionName,
								"description" : projection.description,
								"actor" : projection.actor,
								"duration" : projection.duration,
								"price" : projection.price, 
								"projectionType" : projection.projectionType
							}
						});
					}

					institutionManagerService.addProjection = function(projection_id,
							id) {
						return $http({
							method : 'PUT',
							url : '../institutionmanagers/addExistingProjection?projection_id='
									+ projection_id + '&rest_id=' + id
						});
					}

					institutionManagerService.registerTable = function(table, id) {
						return $http({
							method : 'POST',
							url : '../institutionmanagers/addinstitutionTable?segment='
									+ id,
							data : {
								"numberOfChairs" : table.numberOfChairs,
								"tableRow" : table.tableRow,
								"tableColumn" : table.tableColumn
							}
						});
					}
					institutionManagerService.editTableData = function(table, id) {
						return $http({
							method : 'PUT',
							url : '../institutionmanagers/updateInstitutionTable?id='
									+ id,
							data : {
								"id" : table.id,
								"numberOfChairs" : table.numberOfChairs,
								"tableRow" : table.tableRow,
								"tableColumn" : table.tableColumn
								
							}
						});
					}

					institutionManagerService.deleteProjection = function(id,
							pro_id) {
						return $http({
							method : 'DELETE',
							url : '../institutionmanagers/removeProjection?projection_id='
									+ pro_id + '&rest_id=' + id
						});
					}

					institutionManagerService.deleteSegment = function(id) {
						return $http({
							method : 'DELETE',
							url : '../institutionmanagers/removeSegment?id='
									+ id
						});
					}

					institutionManagerService.deleteTable = function(id) {
						return $http({
							method : 'DELETE',
							url : '../institutionmanagers/removeInstitutionTable?id='
									+ id
						});
					}

					institutionManagerService.registerSegment = function(
							segment, id) {
						return $http({
							method : 'POST',
							url : '../institutionmanagers/addSegment?rest=' + id,
							data : {
								"position" : segment.position,
								"width" : segment.width,
								"height" : segment.height
							}
						});
					}
					institutionManagerService.editSegmentData = function(segment) {
						return $http({
							method : 'PUT',
							url : '../institutionmanagers/updateSegment',
							data : {
								"id" : segment.id,
								"position" : segment.position,
								"width" : segment.width,
								"height" : segment.height
							}
						});
					}

					institutionManagerService.getRequestOffers = function(id) {
						return $http({
							method : 'GET',
							url : '../institutionmanagers//getAllRequestOffersForManager?id='
									+ id
						});
					}
					
					institutionManagerService.registerRequestOffer = function(
							rq, id) {
						return $http({
							method : 'POST',
							url : '../institutionmanagers/registerRequestOffer?rm_id=' + id,
							data : {
								"startDate" : rq.startDate,
								"expirationDate" : rq.expirationDate
							}
						});
					}
					
					institutionManagerService.editRequestOfferData = function(
							rq) {
						return $http({
							method : 'PUT',
							url : '../institutionmanagers/updateRequestOffer',
							data : {
								"id" : rq.id,
								"startDate" : rq.startDate,
								"expirationDate" : rq.expirationDate
							}
						});
					}
					institutionManagerService.addProjectionToRequestOffer = function(
							ro_id, p_id) {
						return $http({
							method : 'PUT',
							url : '../institutionmanagers/addProjectionToRequestOffer?projection_id=' + p_id + '&rest_id=' + ro_id
						});
					}
					
					institutionManagerService.acceptBid = function(
							ro_id, bid_id) {
						return $http({
							method : 'PUT',
							url : '../institutionmanagers/acceptBidderOffer?bid_id=' + bid_id + '&req_id=' + ro_id
						});
					}
					institutionManagerService.removeProdustFromRequestOffer = function(
							ro_id, p_id) {
						return $http({
							method : 'POST',
							url : '../institutionmanagers/removeProjectionFromRequestOffer?projection_id=' + p_id + '&ro_id=' + ro_id
						});
					}
					institutionManagerService.deleteRequestOffer = function(id) {
						return $http({
							method : 'DELETE',
							url : '../institutionmanagers/removeRequestOffer?id='
									+ id
						});
					}
					
					institutionManagerService.getProjections = function(request_id) {
						return $http({
							method : 'GET',
							url : '../institutionmanagers/getAllProjectionsForRequestOffer?id='
									+ request_id
						});
					}
					
					institutionManagerService.getBidderOffersForRequest = function(request_id) {
						return $http({
							method : 'GET',
							url : '../institutionmanagers/getAllBidderOffersForRequestOffer?id='
									+ request_id
						});
					}

					institutionManagerService.getAllWaitersByNameAndInstitution = function(id, name) {
						return $http({
							method : 'GET',
							url : '../institutionmanagers/getAllWaitersByNameAndInstitution?id='
									+ id + '&name=' + name
						});
					}
					
					institutionManagerService.getAllProjectionsByNameAndInstitution = function(id, name) {
						return $http({
							method : 'GET',
							url : '../institutionmanagers/getAllProjectionsByNameAndInstitution?id='
									+ id + '&name=' + name
						});
					}
					
					institutionManagerService.getReservationsForWeek = function(id, date) {
						return $http({
							method : 'GET',
							url : '../institutionmanagers/getReservationsForWeek?id='
									+ id + '&date=' + date
						});
					}
					institutionManagerService.getReservationsForDay = function(id, date) {
						return $http({
							method : 'GET',
							url : '../institutionmanagers/getReservationsForDay?id='
									+ id + '&date=' + date
						});
					}
					
					return institutionManagerService;

				});
