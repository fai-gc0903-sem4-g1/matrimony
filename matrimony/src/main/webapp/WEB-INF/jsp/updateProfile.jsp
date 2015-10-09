<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
                    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
                        <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
                        <jsp:useBean id="userDAO" class="com.matrimony.database.UserDAO" />
        <t:homeLayout>
            <jsp:attribute name="head">
                <title>Payment</title>
                <style>

/*  bhoechie tab */
div.bhoechie-tab-container{
  z-index: 10;
  background-color: #ffffff;
  padding: 0 !important;
  border-radius: 4px;
  -moz-border-radius: 4px;
  border:1px solid #ddd;
  margin-top: 20px;
  margin-left: 50px;
  -webkit-box-shadow: 0 6px 12px rgba(0,0,0,.175);
  box-shadow: 0 6px 12px rgba(0,0,0,.175);
  -moz-box-shadow: 0 6px 12px rgba(0,0,0,.175);
  background-clip: padding-box;
  opacity: 0.97;
  filter: alpha(opacity=97);
}
div.bhoechie-tab-menu{
  padding-right: 0;
  padding-left: 0;
  padding-bottom: 0;
}
div.bhoechie-tab-menu div.list-group{
  margin-bottom: 0;
}
div.bhoechie-tab-menu div.list-group>a{
  margin-bottom: 0;
}
div.bhoechie-tab-menu div.list-group>a .glyphicon,
div.bhoechie-tab-menu div.list-group>a .fa {
  color: #5A55A3;
}
div.bhoechie-tab-menu div.list-group>a:first-child{
  border-top-right-radius: 0;
  -moz-border-top-right-radius: 0;
}
div.bhoechie-tab-menu div.list-group>a:last-child{
  border-bottom-right-radius: 0;
  -moz-border-bottom-right-radius: 0;
}
div.bhoechie-tab-menu div.list-group>a.active,
div.bhoechie-tab-menu div.list-group>a.active .glyphicon,
div.bhoechie-tab-menu div.list-group>a.active .fa{
  background-color: #5A55A3;
  background-image: #5A55A3;
  color: #ffffff;
}
div.bhoechie-tab-menu div.list-group>a.active:after{
  content: '';
  position: absolute;
  left: 100%;
  top: 50%;
  margin-top: -13px;
  border-left: 0;
  border-bottom: 13px solid transparent;
  border-top: 13px solid transparent;
  border-left: 10px solid #5A55A3;
}

div.bhoechie-tab-content{
  background-color: #ffffff;
  /* border: 1px solid #eeeeee; */
  padding-left: 20px;
  padding-top: 10px;
}

div.bhoechie-tab div.bhoechie-tab-content:not(.active){
  display: none;
}
</style>
                <script>
                $(document).ready(function() {
            		$("div.bhoechie-tab-menu>div.list-group>a").click(function(e) {
            			e.preventDefault();
            			$(this).siblings('a.active').removeClass("active");
            			$(this).addClass("active");
            			var index = $(this).index();
            			$("div.bhoechie-tab>div.bhoechie-tab-content").removeClass("active");
            			$("div.bhoechie-tab>div.bhoechie-tab-content").eq(index).addClass("active");
            		});
            	});
                </script>
            </jsp:attribute>
            <jsp:attribute name="middle">
            <div class='col-lg-12 row' style='padding:0px;'>
            <div class="col-lg-2 col-md-3 col-sm-3 col-xs-3 bhoechie-tab-menu">
              <div class="list-group">
                <a href="#" class="list-group-item active text-center">Bản thân</a>
                <a href="#" class="list-group-item text-center">Sở thích</a>
                <a href="#" class="list-group-item text-center">Học vấn</a>
                <a href="#" class="list-group-item text-center">Nghề nghiệp</a>
                <a href="#" class="list-group-item text-center">Khác</a>
              </div>
            </div>
            <div class="col-lg-10 col-md-9 col-sm-9 col-xs-9 bhoechie-tab">
                <!-- flight section -->
                <div class="bhoechie-tab-content active">  
                <h3 style="margin-top: 0;color:#55518a">Về bản thân</h3> 
                
                 
                </div>
                <!-- train section -->
                <div class="bhoechie-tab-content">      <h3 style="margin-top: 0;color:#55518a">Sở thích</h3>  
                </div>
    
                <!-- hotel search -->
                <div class="bhoechie-tab-content">    <h3 style="margin-top: 0;color:#55518a">Học vấn</h3>  
                </div>
                <div class="bhoechie-tab-content">       <h3 style="margin-top: 0;color:#55518a">Nghề nghiệp</h3>  
                </div>
                <div class="bhoechie-tab-content">     <h3 style="margin-top: 0;color:#55518a">Khác</h3>  
                </div>
            </div>
            </div>
            </jsp:attribute>
        </t:homeLayout>