var myapp=angular.module("myApp",['ngRoute','ngCookies']);
myapp.controller('indexcontroller', function($scope,$rootScope,$cookieStore,$http,$window)
		{
	
console.log("hello from Controller");
var ddd= JSON.parse($window.localStorage.getItem('userdetail'));
//console.log(ddd.role);
//console.log(ddd.status);

$scope.currentuser=ddd;
$scope.logout=function(){

	console.log("inside logout"+$scope.currentuser.userName);
			$http.get('http://localhost:9089/middlewarerest/logoutuser/'+$scope.currentuser.userName).then(function(response)
					{
				    var key = "userdetail";
					console.log('Successfully loggedout');
					console.log("ddddddddd",JSON.parse($window.localStorage.getItem(key)));
					$window.localStorage.removeItem(key)
					console.log("ddddddddd",JSON.parse($window.localStorage.getItem(key)));
					 //$cookieStore.remove('userdetail');
					 //var cook=$cookieStore.get('userdetail');
					 //$scope.currentuser=cook;
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
			.when("/AdminJob",{templateUrl:"../Job/AdminJob.html"})
			.when("/Job",{templateUrl:"../Job/Job.html"})
			
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
myapp.controller('logincontroller',function($scope,$http,$window,$rootScope)
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
			    $window.localStorage.setItem("userdetail",JSON.stringify($scope.userdetail));
			    var currentuser = JSON.parse($window.localStorage.getItem("userdetail"));
			    console.log("ffffffffff",currentuser.role);
			    console.log("ffffffffffff",currentuser.status);

			    
			    
			    
			    //$cookieStore.put("userdetail",$scope.userdetail);
			    //$cookies.remove('userdetail');
			    //onsole.log("value",$cookieStore.get('userdetail'));
			    //$rootScope.currentuser=$cookieStore.get('userdetail');
			    //console.log("inside login"+$rootScope.currentuser.userName);
				console.log('Successfully loggedin');
				$window.location.href='/angularfrontend/pages/home.html';
				});
	}
		});



myapp.controller("blogcontroller",function($scope,$http,$window)
		{
	
	
			$scope.blog={blogId:"",blogName:"",blogContent:"",createDate:"",likes:0,user:"",status:"NA"};
			var cuser= JSON.parse($window.localStorage.getItem('userdetail'));
			$scope.currentuser=cuser;
			$scope.addblog=function()
			{
				$scope.blog.user=cuser;
				console.log('Entered into InsertBlog');
				$http.post('http://localhost:9089/middlewarerest/addblog',$scope.blog)
				.then(function(response)
						{
						console.log('Successful Blog Entered');
						});
			}
			
			$http.get('http://localhost:9089/middlewarerest/getAllBlogs/'+cuser.userId)
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
				
				$http.get('http://localhost:9089/middlewarerest/getAllBlogs/'+cuser.userId)
				.then(function(response)
				{
				$scope.blogdata=response.data;
			
				});
			}
			$scope.unlike=function(blog)
			{
			blog.likes--;	
			}
			$scope.acceptblog=function(blogId)
			{
				$http.get('http://localhost:9089/middlewarerest/acceptblog/'+blogId).then(getallblogs(),function(response){
					console.log('blog accepted');
					
					});
			}
			$scope.rejectblog=function(blogId)
			{
				$http.get('http://localhost:9089/middlewarerest/rejectblog/'+blogId).then(getallblogs(),function(response){
					console.log('blog rejected');
					
					});
			}

		});


myapp.controller("jobcontroller",function($scope,$http,$window)
		{
	
	
			$scope.job={jobId:"",jobName:"",jobDesc:"",jobQualification:"",postDate:"",salary:""};
			var cuser= JSON.parse($window.localStorage.getItem('userdetail'));
			$scope.currentuser=cuser;
			$scope.addjob=function()
			{
				console.log('Entered into job');
				$http.post('http://localhost:9089/middlewarerest/addjob',$scope.job)
				.then(function(response)
						{
						console.log('Successful Blog Entered');
						});
			}
			$http.get('http://localhost:9089/middlewarerest/getAllJobs')
			.then(function(response)
			{
			$scope.jobdata=response.data;
		
			});
			
			$scope.selectjob=function(job)
			{
				console.log("retrived selected job",job);
				$scope.clickjob=job;
				}
	
			$scope.updatejob=function()
			{
				console.log('Entered into update job');
				$http.post('http://localhost:9089/middlewarerest/editjob',$scope.clickjob)
				.then(getalljobs(),function(response){

						
						console.log('Successful job updated');
						});
			}
			$scope.deletejob=function()
			{
				console.log('Entered into delete job');
				$http.post('http://localhost:9089/middlewarerest/deletejob',$scope.clickjob)
				.then(getalljobs(),function(response){	
						console.log('Successful job deleted');
						});
			}
			function getalljobs()
			{
				
				$http.get('http://localhost:9089/middlewarerest/getAllJobs')
				.then(function(response)
				{
				$scope.jobdata=response.data;
			
				});
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