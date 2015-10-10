<%@page pageEncoding="UTF8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<t:layout>
<jsp:attribute name="head">
<script>
	console.log('Javascript testing');
	console.log('starting ajax');
	$.ajax({
		url:'sortUserProfile',
		method: 'POST',
		data:{id:'990258dd4ff88341014ff883469e0000'},
		async:false,
		success:function(data){
			return data;
		},
		error:function(){
			alert('can not connect to server');
		}
	});
</script>

</jsp:attribute>
	<jsp:body>
            <form action="/matrimony/profile" method="POST"></form>
<div class="container">
    <h1>Edit Profile</h1>
  	<hr>
	<div class="row">
      <!-- left column -->
      <div class="col-md-3">
        <div class="text-center">
          <img src="//placehold.it/100" class="avatar img-circle" alt="avatar">
          <h6>Upload a different photo...</h6>
          
          <input type="file" class="form-control">
        </div>
      </div>
      
      <!-- edit form column -->
      <div class="col-md-9 personal-info">
        <div class="alert alert-info alert-dismissable">
          <a class="panel-close close" data-dismiss="alert">×</a> 
          <i class="fa fa-coffee"></i>
          This is an <strong>.alert</strong>. Use this to show important messages to the user.
        </div>
        <h3>Personal info</h3>
        
        <form class="form-horizontal" role="form">
          <div class="form-group">
            <label class="col-lg-3 control-label">Mobile Phones</label>
            <div class="col-lg-5">
              <input class="form-control" type="text" value="0985059751">
            </div>
          </div>
          <div class="form-group">
            <label class="col-lg-3 control-label">Email</label>
            <div class="col-lg-5">
              <input class="form-control" type="text" value="nghongdang@gmail.com">
            </div>
          </div>
          <div class="form-group">
            <label class="col-lg-3 control-label">Birth Date</label>
            <div class="col-lg-5">
              <input class="form-control" type="text" value="">
            </div>
          </div>
          <div class="form-group">
            <label class="col-lg-3 control-label">Birth Year</label>
            <div class="col-lg-5">
              <input class="form-control" type="text" value="">
            </div>
          </div>
          <div class="form-group">
            <label class="col-lg-3 control-label">Language</label>
            <div class="col-lg-5">
              <div class="ui-select">
                <select id="Language" class="form-control">
                  <option value="Vn">Viet Nam</option>
                  <option value="US">English(US)</option>
                  <option value="US(pri)">English(Pirate)</option>
                  <option value="US1">English(UK)</option>
                  <option value="US2">English(Upsite Down)</option>
                  <option value="US3">Galego</option>
                  <option value="US4">Italiano</option>
                  <option value="US5">Dansk</option>
                </select>
              </div>
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-3 control-label">Relationship</label>
            <div class="col-lg-5">
              <input class="form-control" type="text" value="Singer">
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-3 control-label">Other Names</label>
            <div class="col-lg-5">
              <input class="form-control" type="text" value="">
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-3 control-label">General Account </label>
            <div class="col-lg-5">
              <input class="form-control" type="text" value="">
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-3 control-label"></label>
            <div class="col-lg-5">
              <input type="button" class="btn btn-primary" value="Save Changes">
              <span></span>
              <input type="reset" class="btn btn-default" value="Cancel">
            </div>
          </div>
        </form>
      </div>
  </div>
</div>
<hr>
	</jsp:body>
</t:layout>