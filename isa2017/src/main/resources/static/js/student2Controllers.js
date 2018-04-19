var app = angular.module('webApp');

app.run([
         'ngNotify',
         function(ngNotify) {

             ngNotify.config({
            	    position: 'bottom',
            	    duration: 1000,
					theme : 'pitchy',
            	    sticky: false,
             });
         }
     ]);

app.config(['$qProvider', function ($qProvider) {
    $qProvider.errorOnUnhandledRejections(false);
}]);

app.directive('ngConfirmClick', [
        function(){
            return {
                link: function (scope, element, attr) {
                    var msg = attr.ngConfirmClick || "Are you sure?";
                    var clickAction = attr.confirmedClick;
                    element.bind('click',function (event) {
                        if ( window.confirm(msg) ) {
                            scope.$eval(clickAction)
                        }
                    });
                }
            };
    }]);


app
.controller(
		'userRankController',
		[
				'$rootScope',
				'$scope',
				'$location','ngNotify',
				'SystemManagerService',
				function($rootScope, $scope, $location,
						ngNotify, systemManagerService) {
					
					$scope.showEditUserRank = false;
					
					systemManagerService.getUserRanks().then(
							function(response) {
								$scope.userRanks = response.data;
								angular.forEach($scope.userRanks, function(value, key){
									switch(value.userRankType) {
								    case 'GOLD':
								    	$scope.goldScale = value.userPointsRankScale;
								        break;
								    case 'SILVER':
								    	$scope.silverScale = value.userPointsRankScale;
								        break;
								    case 'BRONZE':
								    	$scope.bronzeScale = value.userPointsRankScale;
								        break;
									}
								});
					});

					$scope.setSelectedUserRank = function(userRank) {
						if($scope.selectedUserRank == userRank){
							$scope.selectedUserRank = null;
							$scope.showEditUserRank = false;
						}else {
							$scope.selectedUserRank = userRank;
							
							switch(userRank.userRankType) {
						    case 'GOLD':
						    	$scope.max = 1000;
						    	$scope.newScale = userRank.userPointsRankScale;
						    	$scope.min = $scope.silverScale+1;
						        break;
						    case 'SILVER':
						    	$scope.max = $scope.goldScale-1;
						    	$scope.newScale = userRank.userPointsRankScale;
						    	$scope.min = $scope.bronzeScale+1;
						        break;
						    case 'BRONZE':
						    	$scope.max = $scope.silverScale-1;
						    	$scope.newScale = userRank.userPointsRankScale;
						    	$scope.min = 0
						        break;
							}
							
						}
					}
					
					
					$scope.confirmEdit = function(newScale){
						systemManagerService.changeScale($scope.selectedUserRank.id,newScale).then(
								function(response) {
									var index = $scope.userRanks.indexOf($scope.selectedUserRank);
									$scope.userRanks[index] = response.data;
									switch(response.data.userRankType) {
								    case 'GOLD':
								    	$scope.goldScale = response.data.userPointsRankScale;
								        break;
								    case 'SILVER':
								    	$scope.silverScale = response.data.userPointsRankScale;
								        break;
								    case 'BRONZE':
								    	$scope.bronzeScale = response.data.userPointsRankScale;
								        break;
									}
									$scope.selectedUserRank = null;
								});
					}
					
					
		} ]);
    

app
.controller(
		'funManagersController',
		[
				'$rootScope',
				'$scope',
				'$location','ngNotify','SystemManagerService',
				'institutionManagerService','BidderService',
				function($rootScope, $scope, $location,
						ngNotify, systemManagerService,institutionManagerService,bidderService) {
					

					
					bidderService.getAllFunManagers().then(
							function(response) {
								$scope.funManagers = response.data;
							});

					$scope.setSelectedFunManager = function(funManager) {
						if($scope.selectedFunManager == funManager){
							$scope.selectedFunManager = null;
						}else {
							$scope.selectedFunManager = funManager;
						}
					}
					$scope.display = function(number){
						$scope.show = number;
					}
					
					$scope.registerFunManager = function(){
						institutionManagerService.registerBidder($scope.funManager).then(
								function(response) {
									$scope.funManagers.push(response.data);
									$scope.show = null;
								});
					}
					$scope.deleteFunManager = function() {
						systemManagerService
								.deleteFunManager(
										$scope.selectedFunManager.id)
								.then(
										function(response) {
											if (response.status == 200) {
												var index = $scope.funManagers
														.indexOf($scope.selectedFunManager);
												$scope. funManagers
														.splice(index,
																1);
												$scope.show = null;
												ngNotify.set('Successfuly deleted' , {
													type : 'success',
													theme : 'pitchy'
												});
												$selectedFunManager = null;
											} 
										}).catch(function(response) {
											ngNotify.set('Delete error' , {
												type : 'error',
											    sticky: true
											});
											   console.error('Gists error', response.status, response.data)
										  });

					}
					
					
} ]);
app
		.controller(
				'systemManagerController',
				[
						'$rootScope',
						'$scope',
						'$location','ngNotify',
						'SystemManagerService',
						function($rootScope, $scope, $location,
								ngNotify, systemManagerService) {
							


							$scope.display = function(tab) {
								if(tab == 1)
									$scope.selected = null;
								if(tab == 4)
									$scope.editinstitution = $scope.selected;
								$scope.show = tab;
								$scope.selectedinstitutionManager = null;
								$scope.institution = null;
								$scope.newinstitutionManager = null;
							}
							
							$scope.setSelected = function(selected) {
								$scope.selected = selected;
								$rootScope.institution = $scope.selected;
								$scope.selectedinstitutionManager = null;
								$scope.show = null;
								$scope.institution = null;
								$scope.newinstitutionManager = null;
								$scope.selectedTypeIns = null;

							}

							$scope.setSelectedinstitutionManager = function(
									selected) {
								$scope.selectedinstitutionManager = selected;
							}

							$scope.setSelectedSystemManager = function(selected) {
								$scope.show = null;
								$scope.selectedSystemManager = selected;
							}

							systemManagerService.getSystemManagers().then(
									function(response) {
										$scope.systemManagers = response.data;
									});

							systemManagerService.getinstitutions().then(
									function(response) {
										$scope.institutions = response.data;
									});


							$scope.registerInstitution = function() {
								var institution = $scope.institution;
								systemManagerService.registerInstitution(
										institution).then(
										function(response) {
											if (response.data) {
												ngNotify.set('Successfuly registrated institution' , {
													type : 'success'
												});
												$scope.institutions
														.push(response.data);
												$scope.show = null;
												$scope.institution = null;
											}
										}).catch(function(response) {
											ngNotify.set('There is error in your input. Name and description must start with capital letter and must not contain special signs' , {
												type : 'error',
											    sticky: true
											});
											console.error('Gists error', response.status, response.data)
										  });
							}

							$scope.editinstitutionData = function() {
								$scope.institution = null;
								systemManagerService
										.editinstitutionData($scope.editinstitution)
										.then(
												function(response) {
													if (response.data) {
														var index = $scope.institutions
																.indexOf($scope.selected);
														$scope.institutions[index] = response.data;
														$scope.institution = null;
														$scope.show = null;
													} 
												}).catch(function(response) {
													ngNotify.set('There is error in your input. Name and description must start with capital letter and must not contain special signs' , {
														type : 'error',
													    sticky: true
													});
													console.error('Gists error', response.status, response.data)
												  });
							}

							$scope.deleteinstitution = function() {
								$scope.institution = null;
								$scope.newinstitutionManager = null;
								systemManagerService
										.deleteinstitution($scope.selected.id)
										.then(
												function(response) {
													if (response.status == 200) {
														var index = $scope.institutions
																.indexOf($scope.selected);
														$scope.institutions
																.splice(index,
																		1);
														$scope.selected = null;
														$scope.show = null;
														ngNotify.set('Successfuly deleted' , {
															type : 'success',
															theme : 'pitchy'
														});
														$scope.selected = null;
													}
												}).catch(function(response) {
													ngNotify.set('Delete error' , {
														type : 'error',
													    sticky: true
													});
													console.error('Gists error', response.status, response.data)
												  });

							}

							$scope.getinstitutionManagers = function() {
								var institution = $scope.selected;
								systemManagerService.getinstitutionManagers(
										institution.id).then(function(response) {
									$scope.show = 2;
									$scope.institutionManagers = response.data;
								});
							}

							$scope.registerInstitutionManager = function() {
								var newinstitutionManager = $scope.newinstitutionManager;
								systemManagerService.registerInstitutionManager(
										$scope.selected.id,
										newinstitutionManager).then(
										function(response) {
											if (response.data) {
												$scope.show = null;
												ngNotify.set('Successfuly registrated institution manager' , {
													type : 'success'
												});
											} 
										}).catch(function(response) {
											ngNotify.set('Name, Surname must start with capital letter, also must not contain special signs. If you did everything as written, your email address is not unique' , {
												type : 'error',
											    sticky: true
											});
											console.error('Gists error', response.status, response.data)
										  });
							}

							$scope.deleteinstitutionManager = function() {
								systemManagerService
										.deleteinstitutionManager(
												$scope.selectedinstitutionManager.id)
										.then(
												function(response) {
													if (response.status == 200) {
														$scope.show = null;
														ngNotify.set('Successfuly deleted' , {
															type : 'success'
														});
														$scope.selectedinstitutionManager = null;
													} 
												}).catch(function(response) {
													ngNotify.set('Delete error' , {
														type : 'error',
													    sticky: true
													});
													console.error('Gists error', response.status, response.data)
												  });

							}

							$scope.registerSystemManager = function() {
								$scope.selectedSystemManager = null;
								systemManagerService.registerSystemManager(
										$scope.newSystemManager).then(
										function(response) {
											if (response.data) {
												ngNotify.set('Successfuly registrated system manager' , {
													type : 'success'
												});
												response.data.dateOfBirth =  moment(response.data.dateOfBirth).format('YYYY-MM-DD');
												$scope.systemManagers
														.push(response.data);
												$scope.show = null;
											} 
										}).catch(function(response) {
											ngNotify.set('Name, Surname must start with capital letter, also must not contain special signs. If you did everything as written, your email address is not unique' , {
												type : 'error',
											    sticky: true
											});
											console.error('Gists error', response.status, response.data)
										  });
							}

							$scope.deleteSystemManager = function() {
								systemManagerService
										.deleteSystemManager(
												$scope.selectedSystemManager.id)
										.then(
												function(response) {
													if (response.status == 200) {
														var index = $scope.systemManagers
																.indexOf($scope.selectedSystemManager);
														$scope.systemManagers
																.splice(index,
																		1);
														$scope.show = null;
														ngNotify.set('Successfuly deleted' , {
															type : 'success',
															theme : 'pitchy'
														});
														$selectedSystemManager = null;
													} 
												}).catch(function(response) {
													ngNotify.set('Delete error' , {
														type : 'error',
													    sticky: true
													});
													   console.error('Gists error', response.status, response.data)
												  });

							}
		
						} ]);






app
		.controller(
				'bidderController',
				[
						'$rootScope',
						'$scope',
						'$location', 'ngNotify',
						'BidderService',
						function($rootScope, $scope, $location, ngNotify, bidderService) {
							
							$scope.display = function(tab) {
								$scope.show = tab;
								if(tab == 1)
									$scope.selected = null;
								
							}
							$scope.setSelectedRequestOffer = function(selected) {
								$scope.selectedRequestOffer = selected;
								$scope.show = null;
							}
							$scope.setSelectedBidderOffer = function(selected) {
								$scope.selectedBidderOffer = selected;
								$scope.show = null;
								$scope.newBidderOffer = null;
							}

							bidderService.getActiveOffers().then(
									function(response) {
										$scope.requestOffers = response.data;
									});

							bidderService.getBidderOffersForBidder(
									$rootScope.loggedUser.id).then(
									function(response) {
										$scope.bidderOffers = response.data;
									});

							$scope.showProjectionsForRequestOffer = function(id) {
								bidderService
										.getProjections(id)
										.then(
												function(response) {
													if (response.data) {
														$scope.requestOfferProjections = response.data;
														$scope.show = 2;
													}
												});
							}

							$scope.getBidderOfferForBidderAndRequest = function() {
								bidderService.getRequestOffer(
										$scope.selectedRequestOffer.id).then(
										function(response) {
											if (response.data) {
												if($scope.selectedRequestOffer.status != response.data.status) {
													var index = $scope.requestOffers
													.indexOf($scope.selectedRequestOffer);
													$scope.requestOffers[index] = response.data;
													$scope.show = null;
													ngNotify.set('Sorry, this request is no longer open for biddings.');
												}
												else {
													bidderService
													.getBidderOfferForBidderAndRequest(
															$scope.selectedRequestOffer.id,
															$rootScope.loggedUser.id)
													.then(
															function(response) {
																if (response.data) {
																	$scope.show = 3;
																	$scope.editBidderOffer = response.data;
																} else 
																	{
																	$scope.show = 1;
																	}
															});
												}							
											}
										});	
							}

							$scope.getDetailsOfRequestOffer = function() {
								bidderService
										.getRequestOffer(
												$scope.selectedBidderOffer.requestOffer.id)
										.then(
												function(response) {
													if (response.data) {
														$scope.show = 1;
														$scope.requestOffer = response.data;
													}
												});
							}

							$scope.registerBidderOffer = function() {
								bidderService.registerBidderOffer(
										$scope.newBidderOffer,
										$rootScope.loggedUser.id,
										$scope.selectedRequestOffer.id).then(
										function(response) {
											if (response.data) {
												$scope.show = null;
											}
										}).catch(function(response) {
											ngNotify.set('All text inputs must start with capital letter, date input must be after expiration date.' , {
												type : 'error',
											    sticky: true
											});
											   console.error('Gists error', response.status, response.data)
										  });
							}
							
							$scope.editSelectedBidderOffer = function(id) {
								bidderService.getBidderOffer(
										$scope.selectedBidderOffer.id).then(
										function(response) {
											if (response.data) {
												if($scope.selectedBidderOffer.offerStatus != response.data.offerStatus) {
													var index = $scope.bidderOffers
													.indexOf($scope.selectedBidderOffer);
													$scope.bidderOffers[index] = response.data;
													$scope.show = null;
												}
												else 		
													$scope.show = 3;
											}
										});
								$scope.editBidderOffer = $scope.selectedBidderOffer;
							}

							$scope.editBidderOfferData = function() {
								bidderService.editBidderOfferData(
										$scope.editBidderOffer).then(
										function(response) {
											if (response.data) {
												$scope.show = null;
											}
										}).catch(function(response) {
											ngNotify.set('Name, Surname must start with capital letter, also must not contain special signs. If you did everything as written, your email address is not unique' , {
												type : 'error',
											    sticky: true
											});
											   console.error('Gists error', response.status, response.data)
										  });
							}

							$scope.deleteBidderOffer = function() {
								bidderService
										.deleteBidderOffer(
												$scope.selectedBidderOffer.id)
										.then(
												function(response) {
													if (response.status == 200) {
														var index = $scope.bidderOffers
														.indexOf($scope.selectedBidderOffer);
														$scope.bidderOffers
																.splice(index,
																		1);
														ngNotify.set('Successfuly deleted' , {
															type : 'success'
														});
														$scope.show = null;
														$scope.selectedBidderOffer = null;
													}
												}).catch(function(response) {
													ngNotify.set('Error delete' , {
														type : 'error',
													    sticky: true
													});
													   console.error('Gists error', response.status, response.data)
												  });

							}

							$scope.editBidder = function(firstLog) {
								if ($scope.editBidder.userName == null)
									$scope.editBidder.userName = $rootScope.loggedUser.userName;
								if ($scope.editBidder.surname == null)
									$scope.editBidder.surname = $rootScope.loggedUser.surname;
								if ($scope.editBidder.password == null)
									$scope.editBidder.password = $rootScope.loggedUser.password;
								if ($scope.editBidder.dateOfBirth == null)
									$scope.editBidder.dateOfBirth = new Date(
											$rootScope.loggedUser.dateOfBirth);
								if ($scope.editBidder.email == null)
									$scope.editBidder.email = $rootScope.loggedUser.email;
								$scope.editBidder.id = $rootScope.loggedUser.id;
								$scope.editBidder.firstLogIn = firstLog;
								var bidder = $scope.editBidder;
								bidderService
										.editBidder(bidder)
										.then(
												function(response) {
													if (response.data) {
														$rootScope.loggedUser = response.data;
														if(!firstLog)
															$location.path('/home');
													}
												}).catch(function(response) {
													ngNotify.set('Name, Surname must start with capital letter, also must not contain special signs. If you did everything as written, your email address is not unique' , {
														type : 'error',
													    sticky: true
													});
													   console.error('Gists error', response.status, response.data)
												  });
							}
						} ]);






app
		.controller(
				'institutionManagerController',
				[
						'$rootScope',
						'$scope',
						'$location','ngNotify',
						'institutionManagerService',
						function($rootScope, $scope, $location,ngNotify,
								institutionManagerService) {
							$scope.currentMonth=0;
							$scope.display = function(tab) {
								$scope.show = tab;
								$scope.projection = null;
								$scope.selectedProjection = null;
								$scope.selectedinstitutionProjection = null;
								if(tab == 2)
									$scope.editinstitution = $scope.institution;
							}
							$scope.displayWorker = function(tab) {
								if(tab == 1 || tab == 2 || tab == 3)
									$scope.selectedWorker = null;
								$scope.showW = tab;
							}
							$scope.displaySegment = function(tab) {
								if(tab == 1)
									$scope.selectedSegment = null;
								if(tab == 2)
									$scope.editSegment = $scope.selectedSegment;
								$scope.showS = tab;
								
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
								$scope.selectedBid = null;
								$scope.selectedinstitutionProjection = null;
								$scope.selectedRequestOfferProjection = null;
								
							}

							$scope.setSelectedWorker = function(selected) {
								$scope.selectedWorker = selected;
								$scope.showW = null;
								$scope.waiterGrade = null;
								$scope.waiterEarnings = null;
								$scope.waiter = null;
							}
							
							$scope.setSelectedRequestOffer = function(selected) {
								$scope.selectedRequestOffer = selected;
								$scope.showR = null;
							}
							
							$scope.setSelectedBid = function(selected) {
								$scope.selectedBid = selected;
							}
							
							$scope.setSelectedinstitutionProjection = function(selected) {
								$scope.selectedinstitutionProjection = selected;
							}
							$scope.setSelectedRequestOfferProjection = function(selected) {
								$scope.selectedRequestOfferProjection = selected;
							}
							$scope.setSelectedProjection = function(selected) {
								$scope.selectedProjection = selected;
							}
							
							$scope.setSelectedWaiter = function(selected) {
								$scope.selectedWaiter = selected;
							}
							$scope.setSelectedNameProjection = function(selected) {
								$scope.selectedNameProjection = selected;
							}
							$scope.canEditSegment = false;
							$scope.setSelectedSegment = function(selected) {
								institutionManagerService
								.seeIfCanDeleteSegment(selected.id).then(function(response) {
									if(response.data == 0) {
										$scope.canEditSegment = true;
									}
									else{
										$scope.canEditSegment = false;
									}
								});
								$scope.selectedSegment = selected;
								$scope.selectedTable = null;
								$scope.showS = null;
							}
							$scope.setSelectedTable = function(selected) {
								$scope.selectedTable = selected;
								$scope.editTable = selected;
							}
							
							institutionManagerService
									.getinstitution($rootScope.loggedUser.id)
									.then(
											function(response) {
												$scope.institution = response.data;
												$rootScope.institution = $scope.institution;
												institutionManagerService
												.institutionGrade($scope.institution.id)
												.then(
														function(response) {
															if(response.data) {
																$scope.error = false
																if(response.data != -1)
																	$scope.grade = response.data;
																else 
																	$scope.grade = 'No grades yet'
															}
															else
																$scope.error = true;
														});
												institutionManagerService
														.getinstitutionWorkers(
																$scope.institution.id)
														.then(
																function(
																		response) {
																	$scope.institutionWorkers = response.data;
																});
												institutionManagerService
												.getinstitutionSegments(
														$scope.institution.id)
												.then(
														function(
																response) {
															$scope.institutionSegments = response.data;
														});
											});
							
							$scope.editinstitutionData = function() {
								institutionManagerService
										.editinstitutionData($scope.editinstitution)
										.then(
												function(response) {
													if (response.data) {
														$scope.show = null;
														$scope.institution =  response.data;
													}
												}).catch(function(response) {
													ngNotify.set('This will never happen' , {
														type : 'error',
													    sticky: true
													});
													   console.error('Gists error', response.status, response.data)
												  });
							}
							
							institutionManagerService
							.getRequestOffers($rootScope.loggedUser.id)
							.then(
									function(response) {
										if (response.data) {
											$scope.managerOffers = response.data;
										} 
									});
							$scope.getProjectionsForInstitution = function() {
								$scope.projection = null;
								$scope.selectedProjection = null;
								institutionManagerService
										.getProjectionsForInstitution($scope.institution.id)
										.then(
												function(response) {
													if (response.data) {
														$scope.institutionProjections = response.data;
														$scope.selectedBid = null;
														$scope.selectedinstitutionProjection = null;
														$scope.selectedRequestOfferProjection = null;
														$scope.show = 3;
														$scope.showR = 5;
													} 
												});
							}
							$scope.getAllProjections = function() {
								$scope.projection = null;
								institutionManagerService
										.getAllProjections()
										.then(
												function(response) {
													if (response.data) {
														$scope.allProjections = response.data;
														$scope.show = 4;
													}
												});
							}
							
							$scope.registerProjection = function() {
								institutionManagerService
										.registerProjection($scope.projection,
												$scope.institution.id)
										.then(
												function(response) {
													if (response.data) {
														$scope.show = null;
														ngNotify.set('Successfuly added projection' , {
															type : 'success',
														});
													} 
												}).catch(function(response) {
													ngNotify.set('This will never happen' , {
														type : 'error',
														sticky : true
													});
													   console.error('Gists error', response.status, response.data)
												  });
							}
							$scope.deleteProjection = function() {
								institutionManagerService
										.deleteProjection($scope.institution.id,$scope.selectedinstitutionProjection.id)
										.then(
												function(response) {
													if (response.status == 200) {
														ngNotify.set('Successfuly removed projection' , {
															type : 'success',
														});
														$scope.selectedinstitutionProjection = null;
														$scope.show = null;
													}
												}).catch(function(response) {
													ngNotify.set('Error delete' , {
														type : 'error',
														sticky : true
													});
													   console.error('Gists error', response.status, response.data)
												  });
							}
							
							$scope.addProjection = function() {
								institutionManagerService
										.addProjection($scope.selectedProjection.id,
												$scope.institution.id)
										.then(
												function(response) {
													if (response.data) {
														ngNotify.set('Successfuly added projection' , {
															type : 'success'
														});
														$scope.show = null;
													}
												}).catch(function(response) {
													ngNotify.set('This will never happen' , {
														type : 'error',
														sticky : true
													});
													   console.error('Gists error', response.status, response.data)
												  });
							}		
							
							$scope.registerWaiter = function() {
								institutionManagerService
										.registerWaiter($scope.waiter,
												$scope.institution.id)
										.then(
												function(response) {
													if (response.data) {
														response.data.dateOfBirth =  moment(response.data.dateOfBirth).format('YYYY-MM-DD');
														$scope.institutionWorkers
																.push(response.data);
														ngNotify.set('Successfuly registrated waiter' , {
															type : 'success'
														});
														$scope.waiter = null;
														$scope.show = null;
													} 
												}).catch(function(response) {
													ngNotify.set('Email you have entered is already used' , {
														type : 'error',
														sticky : true
													});
													   console.error('Gists error', response.status, response.data)
												  });;
							}
							$scope.registerBidder = function() {
								institutionManagerService
										.registerBidder($scope.newBidder)
										.then(
												function(response) {
													if (response.data) {
														$scope.showR = null;
														$scope.newBidder = null;
														$scope.show = null;
														ngNotify.set('Successfuly registrated bidder' , {
															type : 'success'
														});
													} else {
														$scope.error = true;
													}
												}).catch(function(response) {
													ngNotify.set('Email you have entered is already used' , {
														type : 'error',
														sticky : true
													});
													   console.error('Gists error', response.status, response.data)
												  });;
							}
							$scope.deleteWorker = function() {
								institutionManagerService
										.deleteWorker($scope.selectedWorker.id)
										.then(
												function(response) {
													if (response.status == 200) {
														ngNotify.set('Successfuly deleted worker' , {
															type : 'success'
														});
														var index = $scope.institutionWorkers
																.indexOf($scope.selectedWorker);
														$scope.institutionWorkers
																.splice(index,
																		1);
														$scope.show = null;
														$scope.selectedWorker = null;
													}
												}).catch(function(response) {
													ngNotify.set('Error delete' , {
														type : 'error',
														sticky : true
													});
													   console.error('Gists error', response.status, response.data)
												  });;
							}
							
							
							$scope.showSegmentTables = function() {
								$scope.replaceSegment = $scope.selectedSegment;
								institutionManagerService
										.getTables($scope.selectedSegment.id)
										.then(
												function(response) {
													if (response.data) {
														$scope.segmentTables = response.data;
														$scope.showS = 3;
													}
												});
							}
							
							$scope.showSegmentViewTables = function(id, index) {
								institutionManagerService
										.getTables(id)
										.then(
												function(response) {
													if (response.data) {
														$scope.institutionSegments[index].tables = response.data;
													}
												});
							}
							

							$scope.changeReplaceSegment = function(segment) {
								$scope.replaceSegment = segment;
							}
							
							$scope.registerTable = function() {
								institutionManagerService
										.registerTable($scope.table,
												$scope.selectedSegment.id)
										.then(
												function(response) {
													if (response.data) {
														ngNotify.set('Successfuly registrated table' , {
															type : 'success'
														});
														$scope.showS = null;
													} 
												}).catch(function(response) {
													ngNotify.set('Unexpected error' , {
														type : 'error',
														sticky : 'true'
													});
													   console.error('Gists error', response.status, response.data)
												  });
							}

							$scope.editTableData = function() {
								$scope.checkTableStatus();
								institutionManagerService
										.editTableData($scope.editTable, $scope.editTable.segment.id)
										.then(
												function(response) {
													if (response.data) {
														var index = $scope.segmentTables
														.indexOf($scope.selectedTable);
														if($scope.editTable.segment.id == $scope.selectedSegment.id)
															$scope.segmentTables[index] = response.data;
														else 
															$scope.segmentTables.splice(index, 1);
														$scope.selectedTable = null;
													} 
												}).catch(function(response) {
													ngNotify.set('Position in segment taken' , {
														type : 'error',
														sticky : 'true'
													});
													   console.error('Gists error', response.status, response.data)
												  });
							}
							
							$scope.checkTableStatus = function() {
								institutionManagerService.getInstitutionTable(
										$scope.selectedTable.id).then(
										function(response) {
											if(response.data) {
												if($scope.selectedTable.free != response.data.free){
													var index = $scope.segmentTables
													.indexOf($scope.selectedTable);
													$scope.segmentTables[index] = response.data;
													$scope.selectedTable = null;
													ngNotify.set('Sorry , looks like this table is no longer free, so it can not be deleted or edited.');
												}
											}
										});
							}
							
							$scope.deleteTable = function() {
								$scope.checkTableStatus();
								institutionManagerService
										.deleteTable($scope.selectedTable.id)
										.then(
												function(response) {
													if (response.status == 200) {
														var index = $scope.segmentTables
														.indexOf($scope.selectedTable);
												$scope.segmentTables
														.splice(index,
																1);
												ngNotify.set('Successfuly delete table' , {
													type : 'success'
												});
													}
													$scope.selectedTable = null;
												}).catch(function(response) {
													ngNotify.set('Unexpected error' , {
														type : 'error',
														sticky : 'true'
													});
													   console.error('Gists error', response.status, response.data)
												  });
							}
							
							$scope.registerSegment = function() {
								institutionManagerService
										.registerSegment($scope.segment,
												$scope.institution.id)
										.then(
												function(response) {
													if (response.data) {
														$scope.institutionSegments.push(response.data);
														$scope.showS = null;
														ngNotify.set('Successfuly registrated segment to this institution' , {
															type : 'success'
														});
													} 
												}).catch(function(response) {
													ngNotify.set('Unexpected error' , {
														type : 'error',
														sticky : 'true'
													});s
													   console.error('Gists error', response.status, response.data)
												  });
							}
							
							$scope.editSegmentData = function() {
								institutionManagerService
										.editSegmentData($scope.editSegment)
										.then(
												function(response) {
													if (response.data) {
														var index = $scope.institutionSegments
														.indexOf($scope.selectedSegment);
												$scope.institutionSegments[index] = response.data;
														$scope.showS = null;
													} 
												}).catch(function(response) {
													ngNotify.set('Unexpected error' , {
														type : 'error',
														sticky : 'true'
													});
													   console.error('Gists error', response.status, response.data)
												  });
							}
							
							$scope.deleteSegment = function() {
								institutionManagerService
										.deleteSegment($scope.selectedSegment.id)
										.then(
												function(response) {
													if (response.status == 200) {
														var index = $scope.institutionSegments
														.indexOf($scope.selectedSegment);
												$scope.institutionSegments
														.splice(index,
																1);
														$scope.showS = null;
														$scope.selectedSegment = null;
														ngNotify.set('Successfuly deleted segment' , {
															type : 'success'
														});
													} 
												}).catch(function(response) {
													ngNotify.set('Unexpected error' , {
														type : 'error',
														sticky : 'true'
													});
													   console.error('Gists error', response.status, response.data)
												  });
							}
							$scope.workerIsWaiter = false;
							$scope.enableReplacments = false;
							$scope.enableSubmit = true;
							$scope.changeStatusOfWorkerIsWaiter = function(worker) {
								if(worker.userRole == 'WAITER') {
									$scope.workerIsWaiter = true;		
								}
								else 
									$scope.workerIsWaiter = false;
							}
							
							
							$scope.registerRequestOffer  = function() {
								institutionManagerService
										.registerRequestOffer($scope.requestOffer, $rootScope.loggedUser.id)
										.then(
												function(response) {
													if (response.data) {
														response.data.startDate =  moment(response.data.startDate).format('YYYY-MM-DD');
														response.data.expirationDate =  moment(response.data.expirationDate).format('YYYY-MM-DD');
														$scope.managerOffers.push(response.data);
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
							
							$scope.editRequestOfferData  = function() {
								institutionManagerService
										.editRequestOfferData($scope.editRequestOffer)
										.then(
												function(response) {
													if (response.data) {
														response.data.startDate =  moment(response.data.startDate).format('YYYY-MM-DD');
														response.data.expirationDate =  moment(response.data.expirationDate).format('YYYY-MM-DD');
														var index = $scope.managerOffers
														.indexOf($scope.selectedRequestOffer);
												$scope.managerOffers[index] = response.data;
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
							
							$scope.deleteRequestOffer  = function() {
								institutionManagerService
										.deleteRequestOffer($scope.selectedRequestOffer.id)
										.then(
												function(response) {
													if (response.status == 200) {
														$scope.error = false;
														var index = $scope.managerOffers
														.indexOf($scope.selectedRequestOffer);
												$scope.managerOffers.splice(index, 1);
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
							$scope.addProdustToRequestOffer  = function() {
								institutionManagerService
										.addProjectionToRequestOffer($scope.selectedRequestOffer.id, $scope.selectedinstitutionProjection.id)
										.then(
												function(response) {
													if (response.data) {
														ngNotify.set('Successfuly added projection to request offer' , {
															type : 'success'
														});
														$scope.showR = null;

													}
												}).catch(function(response) {
													ngNotify.set('Error adding projection' , {
														type : 'error',
														sticky: true
													});
													   console.error('Gists error', response.status, response.data)
												  });
							}
							$scope.removeProdustFromRequestOffer  = function() {
								institutionManagerService
										.removeProdustFromRequestOffer($scope.selectedRequestOffer.id, $scope.selectedRequestProjection.id)
										.then(
												function(response) {
													if (response.status == 200) {
														var index = $scope.requestOfferProjections
														.indexOf($scope.selectedRequestProjection);
												$scope.requestOfferProjections.splice(index, 1);
														$scope.showR = null;
														ngNotify.set('Successfuly deleted projection from request offer' , {
															type : 'success'
														});

													}
												}).catch(function(response) {
													ngNotify.set('Error delete' , {
														type : 'error',
														sticky: true
													});
													   console.error('Gists error', response.status, response.data)
												  });
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
							
							$scope.showProjectionsForRequestOffer = function() {
								institutionManagerService
										.getProjections($scope.selectedRequestOffer.id)
										.then(
												function(response) {
													if (response.data) {
														$scope.requestOfferProjections = response.data;
														$scope.showR = 4;
														$scope.selectedBid = null;
														$scope.selectedinstitutionProjection = null;
														$scope.selectedRequestOfferProjection = null;
													}
												});
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
							$scope.getGradeOfWaiter = function() {
							institutionManagerService
							.getGradeOfWaiter($scope.selectedWaiter.id)
							.then(
									function(response) {
										if(response.data) { 
											if(response.data != -1)  
												$scope.waiterGrade = response.data;
											else 
												$scope.waiterGrade = 'No grades yet'
												
													
										}});
							}
							
							$scope.getEarningForWaiter = function() {
								institutionManagerService
								.getEarningForWaiter($scope.selectedWorker.id)
								.then(
										function(response) {
											if(response.data) {
												if(response.data != -1)
													$scope.waiterEarnings = response.data;
												else 
													$scope.waiterEarnings = 'No earnings yet'
											}
										});
								}
							
							$scope.getinstitutionEarnings = function() {
								var start =  moment($scope.institutionStart).format('MM-DD-YYYY');
								var end = moment($scope.institutionEnd).format('MM-DD-YYYY');
								institutionManagerService
								.getinstitutionEarnings($scope.institution.id, start, end)
								.then(
										function(response) {
											if(response.data) {
												if(response.data != -1)
													$scope.institutionEarnings = response.data;
												else 
													$scope.institutionEarnings = 'No earnings yet'
											}
									
										});
								}
							
							$scope.getProjectionGrade = function() {
								institutionManagerService
								.getProjectionGrade($scope.institution.id, $scope.selectedNamedProjection.id)
								.then(
										function(response) {
											if(response.data) {
												if(response.data != -1)
													$scope.gradeProjection = response.data;
												else 
													$scope.gradeProjection = 'No grades yet'
											}
										});
								}
							$scope.onlyWithThatName = true;
							$scope.getAllWaitersByNameAndInstitution = function() {
								institutionManagerService
								.getAllWaitersByNameAndInstitution($scope.institution.id, $scope.waiterName)
								.then(
										function(response) {
											if(response.data) {
												if(response.data.length > 1) {
													$scope.namedWaiters = response.data;
													$scope.onlyWithThatName = false;
												}else if(response.data.length == 0){
													$scope.waiterGrade = 'No waiter with that name'
												} else{
													$scope.onlyWithThatName = true;
													$scope.selectedWaiter = response.data[0];
													$scope.getGradeOfWaiter();
													
												}
											}
										});
								}
							$scope.showProjections = false;
							$scope.getAllProjectionsByNameAndInstitution = function() {
								institutionManagerService
								.getAllProjectionsByNameAndInstitution($scope.institution.id, $scope.projectionName)
								.then(
										function(response) {
											if(response.data) {
												if(response.data.length > 1) {
													$scope.namedProjections = response.data;
													$scope.showProjections = true;
												}else if(response.data.length == 0){
													$scope.gradeProjection = 'No projection with that name'
												} else{
													$scope.showProjections = false;
													$scope.selectedNamedProjection = response.data[0];
													$scope.getProjectionGrade();
													
												}
											}
										});
								}
							
							$scope.getReservations = function() {
								var start =  moment($scope.dailyChart.date).format('MM-DD-YYYY');
								if($scope.dailyChart.base == 'true'){
									institutionManagerService
									.getReservationsForWeek($scope.institution.id, start)
									.then(
											function(response) {
												if(response.data) {
													if(response.data.length >= 1) {
														$scope.reservations = response.data;
														$scope.createChart();
														$scope.showChart = true;
													}else {
														$scope.showChart = false;
														$scope.noResevations = 'No visits for selected week'
													}
												}
											});
									} else {
										institutionManagerService
										.getReservationsForDay($scope.institution.id, start)
										.then(
												function(response) {
													if(response.data) {
														if(response.data.length >= 1) {
															$scope.reservations = response.data;
															$scope.createChart();
															$scope.showChart = true;
														}else {
															$scope.showChart = false;
															$scope.noResevations = 'No visits for selected day'
														}
													}
												});
									}
								}
							$scope.createChart = function() {
								if($scope.dailyChart.base == 'true') {
									var datesName = [];
									var days = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
									for(var i = 0; i < $scope.reservations.length; i++) {
										var temp = $scope.reservations[i];
										var date = new Date(temp.date);
										var dayName = days[date.getDay()];
										datesName[i] = dayName;			
									}
									var counts = {};

									datesName.forEach(function(element) {
									  counts[element] = (counts[element] || 0) + 1;
									});
									var countDay = [];
									var n = 0;
									for (var element in counts) {
									 countDay[n] = counts[element];
									 n++;
									} 
									$scope.labels =  ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
									  $scope.series = ['Series A'];
									  $scope.data = [
									    [countDay[0], countDay[1], countDay[2], countDay[3], countDay[4], countDay[5], countDay[6]]
									  ];
									  $scope.datasetOverride = [{ yAxisID: 'y-axis-1' }];
									  $scope.options = {
									    scales: {
									      yAxes: [
									        { 
									        	ticks: {
									            min: 0,
									            beginAtZero:true,
									            max: 10,
									            stepSize: 1
									        },
									          id: 'y-axis-1',
									          type: 'linear',
									          display: true,
									          position: 'left'
									        }
									      ]
									    }
									  };
								}else {
									var startTime = [];
									var endTime = [];
									for(var i = 0; i < $scope.reservations.length; i++) {
										var temp = $scope.reservations[i];
										startTime[i] = temp.reservation.startTime;
										endTime[i] = temp.reservation.endTime;
									}
									$scope.labels =  startTime;
									  $scope.series = ['Series A'];
									  $scope.data =endTime;
									  $scope.datasetOverride = [{ yAxisID: 'y-axis-1' }];
									  $scope.options = {
									    scales: {
									      yAxes: [
									        { 
									        	ticks: {
									            min: 0,
									            beginAtZero:true,
									            max : 24
									        },
									          id: 'y-axis-1',
									          type: 'linear',
									          display: true,
									          position: 'left'
									        }
									      ]
									    }
									  };
									
								}
							}
							
							$scope.range = function(min, max, step) {
							    step = step || 1;
							    var input = [];
							    for (var i = min; i < max; i += step) {
							        input.push(i);
							    }
							    return input;
							};


						} ]);

