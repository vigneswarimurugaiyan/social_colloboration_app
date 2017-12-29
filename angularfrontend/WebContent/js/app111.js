var myapp=angular.module("myApp",['ngRoute','ngCookies']);
myapp.controller('indexcontroller', function($scope,$cookieStore,$http)
		{
$scope.logout=function(){
			$http.get('http://localhost:9090/middlewarerest/logoutuser/'+$scope.currentuser.userName).then(function(response)
					{
					console.log('Successfully loggedout');
					 $cookieStore.remove('userdetail');
					
					});
		}
});
myapp.config(function($routeProvider)
		{
			$routeProvider
			.when("/registration",{templateUrl:"user/registration.html",controller:"usercontroller"})
			.when("/home",{templateUrl:"pages/home.html",controller:"usercontroller"})
			.when("/navbar",{templateUrl:"pages/navbar.html",controller:"logincontroller"})
			.when("/Blog",{templateUrl:"Blog/Blog.html"})
						  .when("/Forum",{templateUrl:"Forum/Forum.html"})						  
						  .when("/AdminBlog",{templateUrl:"Blog/AdminBlog.html"})
			.when("/login",{templateUrl:"user/login.html",controller:"usercontroller"});
		});
myapp.controller('usercontroller', function($scope,$http) {
	$scope.userdetail={userName:"",password:"",emailId:""};
	$scope.insertuser= function(){
			console.log('entered into registration');
			$http.post('http://localhost:9090/middlewarerest/insertuser',$scope.userdetail)
			.then(function(response)
					{
					console.log('Successfully registered');
					});
		}
		
		
});
myapp.controller('logincontroller',function($scope,$http,$cookieStore,$location,$rootScope)
		{
	$scope.userdetail={userName:"",password:""};
	$scope.login=function()
	{
		console.log("enterd into login process");
		$http.post('http://localhost:9090/middlewarerest/loginuser',$scope.userdetail)
		.then(function(response)
				{
			    
			console.log("this is my response",response.data);
			     $scope.udetail=response.data;
			    $cookieStore.put("userdetail",$scope.udetail)  
			    console.log("value",$cookieStore.get('userdetail'));
			    $rootScope.currentuser=$cookieStore.get('userdetail');
				console.log('Successfully loggedin');
				$location.path('/navbar');
				});
	}
		});



myapp.controller("blogcontroller",function($scope,$http)
		{
			$scope.blog={blogId:23,blogName:"",blogContent:"",createDate:"",likes:3,userId:"",status:"A"};
			$http.get("http://localhost:9090/middlewarerest/getAllBlogs")
			.then(function(response)
			{
			$scope.blogdata=response.data;
			});
			
			$scope.addBlog=function()
			{
				console.log('Entered into InsertBlog');
				$http.post('http://localhost:9090/middlewarerest/addBlog',$scope.blog)
				.then(function(response)
						{
						console.log('Successful Blog Entered');
						});
			}
			
			$scope.deleteBlog=function(blogId)
			{
				//console.log('Entered into InsertBlog');
				$http.post('http://localhost:9090/middlewarerest/deleteBlog',$scope.blog)
				.then(function(response)
						{
						console.log('Successful Deleted');
						});
			}
		});






myapp.run(function($rootScope,$cookieStore)
		{
	console.log("i am in function");
	console.log($rootScope.currentuser);
	console.log("dddddddddd",$cookieStore.get('userdetail'));
	if($rootScope.currentuser==undefined)
		{
		
		$rootScope.currentuser=$cookieStore.get('userdetail');
		console.log("inside the if")
		}
	else
		{
		console.log($rootScope.currentuser.userName);
		console.log($rootScope.currentuser.password);
		}
		});