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
			$http.get('http://localhost:9099/middlewarerest/logoutuser/'+$scope.currentuser.userName).then(function(response)
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
			 .when("/AdminForum",{templateUrl:"../Forum/AdminForum.html"})	
			.when("/Job",{templateUrl:"../Job/Job.html"})
			.when("/showfriend",{templateUrl:"../Friend/showfriend.html"})
			.when("/userlist",{templateUrl:"../Friend/userlist.html"})
			
		});
myapp.controller('usercontroller', function($scope,$http) {
	$scope.userdetail={userName:"",password:"",emailId:""};
	$scope.insertuser= function(){
			console.log('entered into registration');
			$http.post('http://localhost:9099/middlewarerest/insertuser',$scope.userdetail)
			.then(function(response)
					{
				$scope.register=response.data;
				console.log("status",$scope.register.status);
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
		$http.post('http://localhost:9099/middlewarerest/loginuser',$scope.userdetail)
		.then(function(response)
				{
			    
			console.log("this is my response",response.data);
			     $scope.userdetail=response.data;
			    $window.localStorage.setItem("userdetail",JSON.stringify($scope.userdetail));
			    var currentuser = JSON.parse($window.localStorage.getItem("userdetail"));
			    console.log("ffffffffff",currentuser.role);
			    console.log("ffffffffffff",currentuser.status);
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
				$http.post('http://localhost:9099/middlewarerest/addblog',$scope.blog)
				.then(function(response)
						{
						console.log('Successful Blog Entered');
						});
			}
			
			$http.get('http://localhost:9099/middlewarerest/getAllBlogs/'+cuser.userId)
			.then(function(response)
			{
			$scope.blogdata=response.data;
		
			});
			$scope.like=function(blogId)
			{
				$http.get('http://localhost:9099/middlewarerest/increaselike/'+blogId).then(getallblogs(),function(response){
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
				$http.post('http://localhost:9099/middlewarerest/edit',$scope.clickblog)
				.then(getallblogs(),function(response){

						
						console.log('Successful Blog updated');
						});
			}
			$scope.deleteblog=function()
			{
				console.log('Entered into delete blog');
				$http.post('http://localhost:9099/middlewarerest/delete',$scope.clickblog)
				.then(getallblogs(),function(response){

						
						console.log('Successful Blog deleted');
						});
			}
			function getallblogs()
			{
				
				$http.get('http://localhost:9099/middlewarerest/getAllBlogs/'+cuser.userId)
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
				$http.get('http://localhost:9099/middlewarerest/acceptblog/'+blogId).then(getallblogs(),function(response){
					console.log('blog accepted');
					
					});
			}
			$scope.rejectblog=function(blogId)
			{
				$http.get('http://localhost:9099/middlewarerest/rejectblog/'+blogId).then(getallblogs(),function(response){
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
				$http.post('http://localhost:9099/middlewarerest/addjob',$scope.job)
				.then(function(response)
						{
						console.log('Successful Blog Entered');
						});
			}
			$http.get('http://localhost:9099/middlewarerest/getAllJobs')
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
				$http.post('http://localhost:9099/middlewarerest/editjob',$scope.clickjob)
				.then(getalljobs(),function(response){

						
						console.log('Successful job updated');
						});
			}
			$scope.deletejob=function()
			{
				console.log('Entered into delete job');
				$http.post('http://localhost:9099/middlewarerest/deletejob',$scope.clickjob)
				.then(getalljobs(),function(response){	
						console.log('Successful job deleted');
						});
			}
			function getalljobs()
			{
				
				$http.get('http://localhost:9099/middlewarerest/getAllJobs')
				.then(function(response)
				{
				$scope.jobdata=response.data;
			
				});
			}
			//function getalljobsapply()
			//{
				//var cuser= JSON.parse($window.localStorage.getItem('userdetail'));
				//$scope.currentuser=cuser;
				//$http.get('http://localhost:9099/middlewarerest/getAllJobsapply/'+$scope.currentuser.userId)
				//.then(function(response)
				//{
				//$scope.jobdata1=response.data;
			
				//});
			//}
			$scope.appliedjob=function()
			{
				var cuser= JSON.parse($window.localStorage.getItem('userdetail'));
				$scope.currentuser=cuser;
				$http.get('http://localhost:9099/middlewarerest/getAllJobsapply/'+$scope.currentuser.userId)
				.then(function(response)
				{
				$scope.jobdata1=response.data;
			
				});
			}
			
			
			$scope.applyjob=function(jobId)
			{
				var cuser= JSON.parse($window.localStorage.getItem('userdetail'));
				$scope.currentuser=cuser;
			
				$http.get('http://localhost:9099/middlewarerest/applyjob/'+jobId+"/"+$scope.currentuser.userId).then(getalljobs(),function(response){
					console.log('job applied');
			
					
					});
			}
			//$scope.deletejob=function()
			//{
				//$http.get('http://localhost:9099/middlewarerest/rejectblog/'+blogId).then(getallblogs(),function(response){
					//console.log('blog rejected');
					
				//	});
			//}
	
		});
			
myapp.controller('friendcontroller',function($scope,$http,$window)
		{
$scope.friend={friendId:"",friendName:"",userName:"",status:""};
	var cuser= JSON.parse($window.localStorage.getItem('userdetail'));
	$scope.currentuser=cuser;
	
		$http.get('http://localhost:9099/middlewarerest/getAllusers/'+$scope.currentuser.userId)
		.then(function(response)
		{
		$scope.userdata=response.data;
	
		});
		
		$http.get('http://localhost:9099/middlewarerest/getallfriends/'+$scope.currentuser.userName)
		.then(function(response)
		{
		$scope.frienddata=response.data;
	
		});
		
		function getallfriends()
		{
			$http.get('http://localhost:9099/middlewarerest/getallfriends/'+$scope.currentuser.userName)
			.then(function(response)
			{
			$scope.frienddata=response.data;
		
			});	
		}
		
		
		$scope.request=function(user)
		{
			$scope.clickuser=user;
		}
		
		$scope.addfriend=function()
		
		{
			var cuser= JSON.parse($window.localStorage.getItem('userdetail'));
			$scope.currentuser=cuser;
			$scope.friend.friendName=$scope.clickuser.userName;
			$scope.friend.userName=$scope.currentuser.userName;
			console.log("entered into request friend")
			$http.post('http://localhost:9099/middlewarerest/addfriend',$scope.friend)
		}
		
		
		$scope.acceptrequest=function(friendId)
		{
			$http.get('http://localhost:9099/middlewarerest/getapprove/'+friendId).then(getallfriends(),function(response){
				console.log('friend accepted');
				
				});
		}
		
		$scope.deleterequest=function(friendId)
		{
			$http.get('http://localhost:9099/middlewarerest/deletefriend/'+friendId).then(getallfriends(),function(response){
				console.log('friend deleted');
				
				});
		}
		
		
		$scope.friends=function()
		{
			$http.get('http://localhost:9099/middlewarerest/getapprovefriends/'+$scope.currentuser.userName)
			.then(function(response)
			{
			$scope.frienddata1=response.data;
		
			});	
		}
		
	});


myapp.controller("forumcontroller",function($scope,$http,$window)
		{
	
	        
			$scope.forum={forumId:"",forumName:"",ForumContent:"",createDate:"",ForumComment:"",user:"",status:"NA"};
			var cuser= JSON.parse($window.localStorage.getItem('userdetail'));
			$scope.currentuser=cuser;
			$scope.addforum=function()
			{
				$scope.forum.user=cuser;
				console.log('Entered into forum');
				$http.post('http://localhost:9099/middlewarerest/addforum',$scope.forum)
				.then(function(response)
						{
						console.log('Successful forum Entered');
						});
			}
			
			$http.get('http://localhost:9099/middlewarerest/getAllforums/'+cuser.userId)
			.then(function(response)
			{
			$scope.forumdata=response.data;
		
			});
			//$scope.like=function(blogId)
			//{
				//$http.get('http://localhost:9099/middlewarerest/increaselike/'+blogId).then(getallblogs(),function(response){
				//console.log('blog like incremented');
				
			//	});
	
			//}
			$scope.selectforum=function(forum)
			{
				console.log("retrived selected forum",forum);
				$scope.clickforum=forum;
				}
			$scope.updateforum=function()
			{
				console.log('Entered into update forum');
				$http.post('http://localhost:9099/middlewarerest/editforum',$scope.clickforum)
				.then(getallforums(),function(response){

						
						console.log('Successful forum updated');
						});
			}
			$scope.deleteforum=function()
			{
				console.log('Entered into delete forum');
				$http.post('http://localhost:9099/middlewarerest/deleteforum',$scope.clickforum)
				.then(getallforums(),function(response){

						
						console.log('Successful forum deleted');
						});
			}
			function getallforums()
			{
				
				$http.get('http://localhost:9099/middlewarerest/getAllforums/'+cuser.userId)
				.then(function(response)
				{
				$scope.forumdata=response.data;
			
				});
			}
			
			$scope.acceptforum=function(forumId)
			{
				$http.get('http://localhost:9099/middlewarerest/acceptforum/'+forumId).then(getallforums(),function(response){
					console.log('forum accepted');
					
					});
			}
			$scope.rejectforum=function(forumId)
			{
				$http.get('http://localhost:9099/middlewarerest/rejectforum/'+forumId).then(getallforums(),function(response){
					console.log('forum rejected');
					
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