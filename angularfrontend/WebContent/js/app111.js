var myapp=angular.module("myApp",['ngRoute','ngCookies']);
myapp.controller('indexcontroller', function($scope,$rootScope,$cookieStore,$http,$window)
		{
	
console.log("hello from Controller");
console.log($rootScope);
console.log($scope);
var ddd=$cookieStore.get('userdetail');
$scope.currentuser=ddd;
$scope.logout=function(){
	
	console.log("inside logout"+$scope.currentuser.userName);
			$http.get('http://localhost:9089/middlewarerest/logoutuser/'+$scope.currentuser.userName).then(function(response)
					{
					console.log('Successfully loggedout');
					 $cookieStore.remove('userdetail');
					 var cook=$cookieStore.get('userdetail');
					 $scope.currentuser=cook;
					$window.location.href='/angularfrontend/';
					});
		}
});
myapp.config(function($routeProvider)
		{
			$routeProvider
			.when("/navbar",{templateUrl:"../pages/navbar.html",controller:"logincontroller"})
			.when("/Blog",{templateUrl:"../Blog/Blog.html"})
            .when("/Forum",{templateUrl:"../Forum/Forum.html"})						  
			.when("/AdminBlog",{templateUrl:"../Blog/AdminBlog.html"})
		});
myapp.controller('usercontroller', function($scope,$http) {
	$scope.userdetail={userName:"",password:"",emailId:""};
	$scope.insertuser= function(){
			console.log('entered into registration');
			$http.post('http://localhost:9089/middlewarerest/insertuser',$scope.userdetail)
			.then(function(response)
					{
					console.log('Successfully registered');
					});
		}
		
		
});
myapp.controller('logincontroller',function($scope,$http,$cookieStore,$window,$rootScope)
		{
	$scope.userdetail={userName:"",password:""};
	$scope.login=function()
	{
		console.log("enterd into login process");
		$http.post('http://localhost:9089/middlewarerest/loginuser',$scope.userdetail)
		.then(function(response)
				{
			    
			console.log("this is my response",response.data);
			     $scope.userdetail=response.data;
			    $cookieStore.put("userdetail",$scope.userdetail);
			    //$cookies.remove('userdetail');
			    console.log("value",$cookieStore.get('userdetail'));
			    $rootScope.currentuser=$cookieStore.get('userdetail');
			    console.log("inside login"+$rootScope.currentuser.userName);
				console.log('Successfully loggedin');
				$window.location.href='/angularfrontend/pages/home.html';
				});
	}
		});



myapp.controller("blogcontroller",function($scope,$http)
		{
			$scope.blog={blogId:"",blogName:"",blogContent:"",createDate:"",likes:0,userId:"",status:"NA"};
			
			$scope.addblog=function()
			{
				
				console.log('Entered into InsertBlog');
				$http.post('http://localhost:9089/middlewarerest/addblog',$scope.blog)
				.then(function(response)
						{
						console.log('Successful Blog Entered');
						});
			}
			$http.get("http://localhost:9089/middlewarerest/getAllBlogs")
			.then(function(response)
			{
			$scope.blogdata=response.data;
		
			});
			$scope.like=function(blogId)
			{
				$http.get('http://localhost:9089/middlewarerest/increaselike/'+blogId).then(getallblogs(),function(response){
				console.log('blog like incremented');
				
				});
	
			}
			$scope.selectblog=function(blog)
			{
				console.log("retrived selected blog",blog);
				$scope.clickblog=blog;
				}
	
			$scope.updateblog=function()
			{
				console.log('Entered into update blog');
				$http.post('http://localhost:9089/middlewarerest/edit',$scope.clickblog)
				.then(getallblogs(),function(response){

						
						console.log('Successful Blog updated');
						});
			}
			$scope.deleteblog=function()
			{
				console.log('Entered into delete blog');
				$http.post('http://localhost:9089/middlewarerest/delete',$scope.clickblog)
				.then(getallblogs(),function(response){

						
						console.log('Successful Blog deleted');
						});
			}
			function getallblogs()
			{
				$http.get("http://localhost:9089/middlewarerest/getAllBlogs")
				.then(function(response)
				{
				$scope.blogdata=response.data;
			
				});
			}
			$scope.unlike=function(blog)
			{
			blog.likes--;	
			}

		});
/*myapp.run(function($rootScope,$cookies)
		{
	console.log("i am in function");
	console.log($rootScope.currentuser);
	console.log("dddddddddd",$cookies.get('userdetail'));
	if($rootScope.currentuser==undefined)
		{
		
		$rootScope.currentuser=$cookies.get('userdetail');
		console.log("inside the if")
		}
	else
		{
		console.log($rootScope.currentuser.userName);
		console.log($rootScope.currentuser.password);
		}
		});*/