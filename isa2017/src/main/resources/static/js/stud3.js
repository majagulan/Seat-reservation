var app=angular.module('webApp')

app.factory('WaiterService', function waiterService($http) {
	
	
	waiterService.createOrder = function(tableNum){
		return $http({
			method : 'POST',
			url: 'waiters/createOrder/'+tableNum
		});
	}
	
	waiterService.updateOrder = function(order,tableNum){
		return $http({
			method : 'PUT',
			url: '../waiters/updateOrder/'+tableNum,
			data: {
				"id": order.id,
				"date": order.date,
				"time": order.time
			}
		});
	}
	
	waiterService.getOrders = function(){
		return $http({
			method : 'GET',
			url: '../waiters/getOrders'
		});
	}
	
	waiterService.deleteOrder = function(order){
		return $http({
			method : 'DELETE',
			url: '../waiters/deleteOrder',
			data: {
				"id": order.id,
				"date": order.date,
				"time": order.time
			},
			headers: {
				   'Content-Type': 'application/json',
				   'Accept': 'application/json'
			}
		});
	}
	
	waiterService.addOrderItem = function(orderId,projectionId,quantity){
		return $http({
			method : 'POST',
			url: '../waiters/addOrderItem/'+orderId+'/'+projectionId,
			data: {
				"quantity": quantity
			}
		});
	}
	
	waiterService.updateOrderItem = function(orderItem){
		return $http({
			method : 'PUT',
			url: '../waiters/updateOrderItem',
			data: {
				"id": orderItem.id,
				"quantity": orderItem.quantity
			}
		});
	}
	
	waiterService.deleteOrderItem = function(order){
		return $http({
			method : 'DELETE',
			url: '../waiters/deleteOrderItem',
			data: {
				"id": order.id
			},
			headers: {
				   'Content-Type': 'application/json',
				   'Accept': 'application/json'
			}
		});
	}
	
	
	waiterService.getSegments = function(){
		return $http({
			method : 'GET',
			url: '../waiters/getSegments'
		});
	}
	
	waiterService.getTablesForSegment = function(segmentId){
		return $http({
			method : 'GET',
			url: '../guests/getTablesForSegment/'+segmentId
		});
	}
	
	
	waiterService.getProjections = function(){
		return $http({
			method : 'GET',
			url: '../waiters/getProjections'
		});
	}
	
	waiterService.getOrderItemsForOrder = function(orderId){
		return $http({
			method : 'GET',
			url: '../waiters/getOrderItems/'+orderId
		});
	}
	
	waiterService.getAllTables = function(){
		return $http({
			method : 'GET',
			url: '../waiters/getAllTables'
		});
	}
	
	return waiterService;
	
});

app.controller('waiterController',['$rootScope','$scope','$location','WaiterService',function($rootScope,$scope,$location,waiterService){
	
	$scope.selected= "";
	$scope.allSegments=true;
	$scope.lastAddedOrder=null;
	$scope.orders=null;
	$scope.projections=null;
	$scope.editOrdersItems=null;
	$scope.orderItems=null;
	$scope.tables=null;
	$scope.user= $rootScope.loggedUser;
	
	$scope.setSelected = function(ord){
		if($scope.selected == ord){
			$scope.selected= "";
			$scope.edit=false;
			$scope.lastAddedOrder=null;
			$scope.editOrdersItems=null;
			return;
		}
		$scope.editOrdersItems=null;
		$scope.selected = ord;
		$scope.edit=false;
	}
	
	waiterService.getSegments().then(function(response){
		$scope.segments=response.data;
	})
	
	
	$scope.getTablesForSegment=function(id,index){
		waiterService.getTablesForSegment(id).then(function(response){
			$scope.segments[index].tables=response.data;
		});
	}
	
	$scope.checkSegment=function(seg){
		if($scope.allSegments)
			return true;
		return false;
	}
	
	waiterService.getOrders().then(function(response){
		$scope.orders=response.data;
	});
	
	$scope.showEditOrder=function(){
		$scope.edit=true;
		$scope.editOrdersItems=null;
		$scope.lastAddedOrder=null;
	}
	
	$scope.editOrder=function(){
		var tableId=document.getElementById('editTable').value;
		waiterService.updateOrder($scope.selected,tableId).then(function(response){
			var index=$scope.orders.indexOf($scope.selected);
			$scope.orders.splice(index,1);
			$scope.orders.push(response.data)
		}).catch(function(response) {
			swal({
				  title: "Edit order error",
				  text: "There are no waiter allocated for choosen table.",
				  type: "success",
				  timer: 3000
			});
	    });
		$scope.edit=false;
	}
	
	$scope.createOrder=function(tableNum){
		$scope.editOrdersItems=null;
		waiterService.createOrder(tableNum).then(function(response){
			$scope.lastAddedOrder=response.data;
			$scope.selected=null;
			$scope.orders.push(response.data);
		});
	}
	
	$scope.deleteOrder=function(){
		waiterService.deleteOrder($scope.selected).then(function(response){
			var index=$scope.orders.indexOf($scope.selected);
			$scope.orders.splice(index,1);
		});
	}
	
	waiterService.getProjections().then(function(response){
		$scope.projections=response.data;
	});
	
	waiterService.getAllTables().then(function(response){
		$scope.tables=response.data;
	});
	
	$scope.addOrderItems=function(){
		for(var i=0;i<$scope.projections.length;i++){
			var projectionId=$scope.projections[i].id;
			var quantity=document.getElementById('projection'+projectionId).value;
			if(quantity==0)
				continue;
			waiterService.addOrderItem($scope.lastAddedOrder.id,projectionId,quantity).then(function(response){
			});
		}
		$scope.lastAddedOrder=null;
	}
	
	$scope.addItemsTo=function(){
		$scope.lastAddedOrder=$scope.selected;
		$scope.editOrdersItems=null;
		$scope.edit=false;
	}
	
	$scope.editOrderItems=function(){
		$scope.editOrdersItems=$scope.selected;
		$scope.lastAddedOrder=null;
		$scope.edit=false;
		waiterService.getOrderItemsForOrder($scope.editOrdersItems.id).then(function(response){
			$scope.orderItems=response.data;
		});
	}
	
	$scope.editItems=function(){
		var orderItem=new Object();
		for(var i=0;i<$scope.orderItems.length;i++){
			orderItem.id=$scope.orderItems[i].id;
			orderItem.quantity=document.getElementById('item'+orderItem.id).value;
			waiterService.updateOrderItem(orderItem).then(function(response){
			});
		}
		$scope.editOrdersItems=null;
	}
	
	$scope.deleteOrderItem=function(orderItem){
		waiterService.deleteOrderItem(orderItem).then(function(response){
			var index=$scope.orderItems.indexOf(orderItem);
			$scope.orderItems.splice(index,1);
			var item=response.data;
			swal({
	  			  title: "Deleted order item",
	  			  text: 'You deleted '+item.quantity+' '+ item.projection.projectionName+'\n from order '+item.order.id,
	  			  type: "success",
	  			  timer: 2000
	  		});
		})
		
	}
	
	
	$scope.range = function(min, max, step) {
	    step = step || 1;
	    var input = [];
	    for (var i = min; i < max; i += step) {
	        input.push(i);
	    }
	    return input;
	};
	
}]);

app.controller('workerController',['$rootScope','$scope','$location','WaiterService',function($rootScope,$scope,$location,waiterService){
	
	$scope.user= $rootScope.loggedUser;
	$scope.groups=null;
	$scope.currentMonth=0;
	
	$scope.initDate=function(){
		var now=new Date();
		$scope.startDate=now;
		$scope.endDate=now;
	}

	
	$scope.range = function(min, max, step) {
	    step = step || 1;
	    var input = [];
	    for (var i = min; i < max; i += step) {
	        input.push(i);
	    }
	    return input;
	};
	
}]);

