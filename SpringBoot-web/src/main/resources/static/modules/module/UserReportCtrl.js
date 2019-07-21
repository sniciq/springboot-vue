angular.module('myApp').controller('UserReportCtrl', function($scope, $http, $anchorScroll, confirmDialogs){
	$scope.pagination = {start: 0, limit: 20, maxSize: 8, currentPage: 1, limitOptions:[10,20,50,100]};
	$scope.searchForm = {};
	
	$scope.search = function() {
		$anchorScroll();
		$scope.promise = $http.post(
			ctx + '/userreport/UserReportCtrl/search', 
			{data:$scope.searchForm, extLimit:$scope.pagination}
		).success(function(data){
			$scope.dataList = data;
		});
	};
	$scope.pageChanged = function() {
		$scope.pagination.start = ($scope.pagination.currentPage - 1) * $scope.pagination.limit;
		$scope.search();
	};
	
	$scope.clearSearch = function() {
		$scope.pagination.start = 0;
		$scope.pagination.currentPage = 1;
		$scope.searchForm = {};
		$scope.search();
	}
	
	$scope.search();
});
