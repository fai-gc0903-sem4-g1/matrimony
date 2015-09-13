<%@page pageEncoding="UTF8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<t:layout>
	<jsp:body>
	<c:set var="alias" value="matrimony" />
	<style>
body{
	background-color:#f9f9f9;
    padding:50px;
}

#login-dp{
    min-width: 250px;
    padding: 14px 14px 0;
    overflow:hidden;
    background-color:rgba(255,255,255,.8);
}
#login-dp .help-block{
    font-size:12px    
}
#login-dp .bottom{
    background-color:rgba(255,255,255,.8);
    border-top:1px solid #ddd;
    clear:both;
    padding:14px;
}
#login-dp .social-buttons{
    margin:12px 0    
}
#login-dp .social-buttons a{
    width: 49%;
}
#login-dp .form-group {
    margin-bottom: 10px;
}
.btn-fb{
    color: #fff;
    background-color:#3b5998;
}
.btn-fb:hover{
    color: #fff;
    background-color:#496ebc 
}
.btn-tw{
    color: #fff;
    background-color:#55acee;
}
.btn-tw:hover{
    color: #fff;
    background-color:#59b5fa;
}
@media(max-width:768px){
    #login-dp{
        background-color: inherit;
        color: #fff;
    }
    #login-dp .bottom{
        background-color: inherit;
        border-top:0 none;
    }
}

</style>
<script>
	$(document).ready(function(){
		$('#btnLogout').click(function(){
			$('<form action="logout" method="POST"></form>').appendTo('body').submit();
		});
	});
</script>
	<!--navbar login-->
	<nav class="navbar navbar-default">
  <div class="container-fluid">
   
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Brand</a>
    </div>

   
    <div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Link <span
								class="sr-only">(current)</span></a></li>
        <li><a href="#">Link</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown"
							role="button" aria-expanded="false">Dropdown <span
								class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="#">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li class="divider"></li>
            <li><a href="#">Separated link</a></li>
            <li class="divider"></li>
            <li><a href="#">One more separated link</a></li>
          </ul>
        </li>
      </ul>
      <form id="signin" class="navbar-form navbar-right" role="form">
                        <div class="input-group">
                            <span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span>
                            <input id="email" type="email"
								class="form-control" name="email" value=""
								placeholder="Email Address">                                        
                        </div>

                        <div class="input-group">
                            <span class="input-group-addon"><i
								class="glyphicon glyphicon-lock"></i></span>
                            <input id="password" type="password"
								class="form-control" name="password" value=""
								placeholder="Password">                                        
                        </div>

                        <button type="submit" class="btn btn-primary">Login</button>
                   </form>
     
    </div>
  </div>
</nav>

<!--navbar logged in-->

	<nav class="navbar navbar-default">
  <div class="container-fluid">
   
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Brand</a>
    </div>

   
    <div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Link <span
								class="sr-only">(current)</span></a></li>
        <li><a href="#">Link</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown"
							role="button" aria-expanded="false">Dropdown <span
								class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="#">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li class="divider"></li>
            <li><a href="#">Separated link</a></li>
            <li class="divider"></li>
            <li><a href="#">One more separated link</a></li>
          </ul>
        </li>
      </ul>
      <form id="signin" class="navbar-form navbar-right" role="form">
                        <div class="input-group">
                            <span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span>
                            <input id="email" type="email"
								class="form-control" name="email" value=""
								placeholder="Email Address">                                        
                        </div>

                        <div class="input-group">
                            <span class="input-group-addon"><i
								class="glyphicon glyphicon-lock"></i></span>
                            <input id="password" type="password"
								class="form-control" name="password" value=""
								placeholder="Password">                                        
                        </div>

                        <button type="submit" class="btn btn-primary">Login</button>
                   </form>
     
    </div>
  </div>
</nav>



<nav class="navbar navbar-default navbar-inverse" role="navigation">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Login dropdown</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Link</a></li>
        <li><a href="#">Link</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <span
								class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="#">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li class="divider"></li>
            <li><a href="#">Separated link</a></li>
            <li class="divider"></li>
            <li><a href="#">One more separated link</a></li>
          </ul>
        </li>
      </ul>
      <form class="navbar-form navbar-left" role="search">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
      </form>
      <ul class="nav navbar-nav navbar-right">
        <li><p class="navbar-text">Already have an account?</p></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown"><b>Login</b> <span
								class="caret"></span></a>
			<ul id="login-dp" class="dropdown-menu">
				<li>
					 <div class="row">
							<div class="col-md-12">
								Login via
								<div class="social-buttons">
									<a href="#" class="btn btn-fb"><i class="fa fa-facebook"></i> Facebook</a>
									<a href="#" class="btn btn-tw"><i class="fa fa-twitter"></i> Twitter</a>
								</div>
                                or
								 <form class="form" role="form" method="post" action="login"
												accept-charset="UTF-8" id="login-nav">
										<div class="form-group">
											 <label class="sr-only" for="exampleInputEmail2">Email address</label>
											 <input type="email" class="form-control"
														id="exampleInputEmail2" placeholder="Email address"
														required>
										</div>
										<div class="form-group">
											 <label class="sr-only" for="exampleInputPassword2">Password</label>
											 <input type="password" class="form-control"
														id="exampleInputPassword2" placeholder="Password" required>
                                             <div
														class="help-block text-right">
														<a href="">Forget the password ?</a>
													</div>
										</div>
										<div class="form-group">
											 <button type="submit" class="btn btn-primary btn-block">Sign in</button>
										</div>
										<div class="checkbox">
											 <label>
											 <input type="checkbox"> keep me logged-in
											 </label>
										</div>
								 </form>
							</div>
							<div class="bottom text-center">
								New here ? <a href="#"><b>Join Us</b></a>
							</div>
					 </div>
				</li>
			</ul>
        </li>
      </ul>
    </div>
				<!-- /.navbar-collapse -->
  </div>
			<!-- /.container-fluid -->
</nav>

<style>
#person-panel {
	border: solid 2px #f7f7f7;
	margin-left: 0px;
	margin-top: 10px;
	padding: 12px 0px;
}

#person-name {
	font-weight: bold;
	color: #116CBB;
}

#person-btna {
	color: #8DBAD9;
	cursor: pointer;
}
</style>
<div id="container" class='row'>
	<div id='left' class='col-lg-2' style='width: 233px;'>
		<img id='avartar' alt='avatar' style='height: 200px; width: 200px;'
					src='avatar.jpg' />
		<br />
		<br />
		<span id='name' style='font-weight: bold;'>Đàm Sơn</span>
		<br /><br />
		<ul class="list-group">
		  <li class="list-group-item"><a href="#">My friend</a> <span
						class="badge">14</span></li>
		  <li class="list-group-item"><a href="#">My favorite people</a> <span
						class="badge">33</span></li>
		</ul>
	</div>
	
	<div id='center' class='col-lg-6'
				style='background-color: #ffffff; border: solid 2px #f4f4f4; border-radius: 6px; width: 726px;'>
	Friend tables
		<div id='tblPeople'>
			<div id='person-panel' class='row col-sm-12'>
				<div class='col-sm-2'>
				<img id='person-avatar' alt='person-avatar' src='person-avatar.png'
								style='height: 70px; width: 70px;' />
				</div>
			
				<div id='person-left' class='col-sm-5'>
					<span id='person-name'>Azad Siddha</span><br />
					<span id='person-age'>24</span><br />
					<span id='person-gender'>Female</span><br />
					<span id='person-btna'>Send message</span>
					<span id='person-btna'>1 friend</span>
				</div>
				<div id='person-right' class='col-sm-4'>
					<span id='person-name'>Viet nam</span><br />
					<span id='person-city'>Ha noi</span><br />
					<span id='person-status'>Alone</span><br />
					<span id='person-btna'>Add friend</span>
				</div>
			</div>
			
			<div class='row col-sm-12' style='text-align: center;'>
				<h4>Trống</h4>
			</div>
			</div>
		</div>
		<div id='right' class='col-lg-3'>
		<div class="panel panel-default">
	  <div class="panel-heading">
		<h3 class="panel-title">Panel title</h3>
	  </div>
	  <div class="panel-body">
		Panel content
	  </div>
		</div>
	</div>
	</div>
	
	
</div>
	</jsp:body>
</t:layout>