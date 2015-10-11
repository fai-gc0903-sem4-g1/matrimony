<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
    <%@page contentType="text/html" pageEncoding="UTF-8" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
                <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

                    <jsp:useBean id="fbConn" class="com.facebook.api.FBConnection" />
                    <c:set var="current" value="${param.ddlLanguage}" scope="session" />

                    <c:if test="${not empty current}">
                        <fmt:setLocale value="${current}" scope="session" />
                    </c:if>
                    <fmt:setBundle basename="com.matrimony.i18n.StringResource" scope="session" />
                    <t:layout>
                        <jsp:attribute name="head">
                            <title>Welcome to matrimony</title>
                            <link rel="stylesheet" href="/matrimony/resources/css/login-form-dialog.css" />
                            <style>
                             	body{
                                	background-color: #EFEFEF;
                                }
                                #hidden-div {
                                    display: none;
                                }
                                
                                .st-reg-invalid{
                                	    font-size: 22px;
									    color: #CB2027;
									    margin-right: 12px;
                                }
                                .popover{
                                	background-color:#CB2027;
                                	color:#ffffff;
                                }
                                .popover.left .arrow:after {
								  border-left-color: #CB2027;
								}
								.popover.bottom .arrow:after {
								  border-bottom-color: #CB2027;
								}
                                
                                
                               
                            </style>
                            <script>
                                $(document).ready(function() { 
                                	$('.st-reg-form-group').popover({placement: 'left'});
                                	$('.st-reg-form-group').click(function(){
                                		var popoverWin;
                                		var formGroup=$(this);
                                        $('.popover').each(function(i){
                                        	if(!$(this).children('.popover-content').html()==formGroup.data('content'))
                                        	{
                                        		$(this).remove();
                                        	}
                                        });
                                	});
                                });
                            </script>
                        </jsp:attribute>


                        <jsp:body>
                            <div id='hidden-div'>
                                <a href="#" id="show-loggin-model-trigger" data-toggle="modal" data-target="#login-modal">Login</a>
                            </div>
                            <div id='container row'>
                                <div id="left" class="col-lg-7">
                                	<div class='col-lg-8'>
                                	<img class="pull-right" src='/matrimony/resources/imgs/connected-people-symbolic.png'/>
                                	
                                	</div>
                                    <div id='login-with' class="col-lg-4">
                                        <a href="${fbConn.FBAuthUrl}" class="btn btn-block btn-social btn-facebook"><i class="fa fa-facebook"></i>&nbsp;Log-in with Facebook</a>
                                    	<a href="${fbConn.FBAuthUrl}" class="btn btn-block btn-social btn-google" disabled><i class="fa fa-google"></i>&nbsp;Log-in with Google</a>
                                    	<a href="${fbConn.FBAuthUrl}" class="btn btn-block btn-social btn-twitter" disabled><i class="fa fa-twitter"></i>&nbsp;Log-in with Twitter</a>
                                    	<a href="${fbConn.FBAuthUrl}" class="btn btn-block btn-social btn-yahoo" disabled><i class="fa fa-yahoo"></i>&nbsp;Log-in with yahoo</a>
                                    	<a href="${fbConn.FBAuthUrl}" class="btn btn-block btn-social btn-instagram" disabled><i class="fa fa-instagram"></i>&nbsp;Log-in with Instagram</a>
                                   </div>
                                </div>
                                <div id="right" class="col-lg-4">
                                    <form:form modelAttribute="userReg" id="registerForm" action="/matrimony/register" method="POST" class="col-lg-12">
                                        <c:if test="${registerFormError }">
                                        </c:if>
                                        <div style="display: none;" id="myAlert" class="alert alert-danger" role="alert"></div>

                                             <div class="st-reg-form-group form-group has-feedback col-lg-6" data-toggle="popover" data-content='${regFirstNameInvalid }'>
								                <input id="firstName" class="form-control" type="text" name="firstName" placeholder="Tên" value="${requestScope.userReg.firstName}"></input>
								                <c:if test="${ not empty regFirstNameInvalid}"><i class="glyphicon glyphicon-exclamation-sign form-control-feedback st-reg-invalid"></i></c:if>
								            </div>
								            
								            <div class="st-reg-form-group form-group has-feedback col-lg-6" data-toggle="popover" data-content="${regLastNameInvalid }">
								                <input id="lastName" class="form-control" type="text" name="lastName" placeholder="Họ" value="${requestScope.userReg.lastName}"></input>
								                <c:if test="${ not empty regLastNameInvalid}"><i class="glyphicon glyphicon-exclamation-sign form-control-feedback st-reg-invalid"></i></c:if>
								                
								            </div>
                                           
                                            
                                            
                                     


										<div class="st-reg-form-group form-group has-feedback col-lg-12" data-toggle="popover" data-content="${ regEmailInvalid}">
								                <input id="email" onkeyup="" class="form-control" type="email" name="email" value="${userReg.email}" placeholder="Địa chỉ email"/>
								                <c:if test="${ not empty regEmailInvalid}"><i class="glyphicon glyphicon-exclamation-sign form-control-feedback st-reg-invalid"></i></c:if>
								                <form:errors path="email" id="validError" />
								            </div>
                                       <div class="st-reg-form-group form-group has-feedback col-lg-12" data-toggle="popover" data-content="">
								                <input id="reEmail" name="reEmail" class="form-control" type="email" placeholder="Nhập lại địa chỉ email" />
								                
								        </div>

								        <div class="st-reg-form-group form-group has-feedback col-lg-12" data-toggle="popover" data-content="${regPasswordInvalid }">
								                <input id="password" class="form-control" type="password" name="password" placeholder="Mật khẩu"></input>
                                                <form:errors path="password" id="validError" />
								                <c:if test="${ not empty regPasswordInvalid}"><i class="glyphicon glyphicon-exclamation-sign form-control-feedback st-reg-invalid"></i></c:if>
								        </div>
                                       <div class="st-reg-form-group form-group has-feedback col-lg-12" data-toggle="popover" data-content="đây là một đoạn văn bản rất là dài và không có ý nghĩa gì cả">
								                <input id="phone" class="form-control" type="text" name="contactNumber" placeholder="Số điện thoại nếu có"></input>
                                                
								        </div>
								        
								        <div class='form-group col-lg-12'>
                                                <span style='font-weight:700;'>Ngày sinh</span>
                                         </div>
                                      
                                        <div class="form-group col-lg-3">
                                                <select class="" name="day">
                                                    <option value='' disabled selected>Ngày</option>
                                                    <c:forEach var="i" begin="1" end="31" step="1">
                                                        <option>${i}</option>
                                                    </c:forEach>
                                                </select>
                                         </div>
                                         
                                         
                                           <div class="form-group col-lg-3">
                                               <select class="" name="month">
                                                   <option value='' disabled selected>Tháng</option>
                                                   <c:forEach var="i" begin="1" end="12" step="1">
                                                       <option>${i}</option>
                                                   </c:forEach>

                                               </select>
                                           </div>
                                           
                                           <div class="form-group col-lg-3">
                                               <select name="year">
                                                   <option value='' disabled selected>Năm</option>
                                                   <c:forEach var="i" begin="1905" end="2015" step="1">
                                                       <option>${i}</option>
                                                   </c:forEach>
                                               </select>
                                           </div>
                                             <div class="st-reg-form-group form-group has-feedback col-lg-3" data-toggle="popover" data-content="${regBirthdayInvalid }">
                                               <c:if test="${ not empty regBirthdayInvalid}"><i class="glyphicon glyphicon-exclamation-sign form-control-feedback st-reg-invalid"></i></c:if>
                                           </div>
                                           

                                        <div class="st-reg-form-group form-group has-feedback col-lg-12">
                                        <span style='font-weight:700;'>Giới tính</span>
                                            <div class='radio' style="font-size: 16px;">
                                                    <label><input type="radio" name="gender" value="FEMALE" /> Nữ</label>
                                                    &nbsp;
                                                     <label><input type="radio" name="gender" value="MALE" /> Nam</label>
                                                     <label style='display:none;'><input type="radio" name="gender" value="none" checked /></label>
                                            </div>
                                            <c:if test="${not empty regGenderInvalid }"><i class="glyphicon glyphicon-exclamation-sign form-control-feedback st-reg-invalid" data-toggle="popover" data-content="${regGenderInvalid }"></i></c:if>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-sm-12">
                                                <input class="btn btn-success col-sm-12" type="submit" value="Đăng ký" />
                                            </div>
                                        </div>
                                    </form:form>
                                </div>
                            </div>

                            <div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-hidden="true" style="display: none;">
                                <div class="modal-dialog">
                                    <div class="loginmodal-container">
                                        <h1>New password</h1>
                                        <br>
                                        <form:form modelAttribute="logginFBUser" action="loginWithFacebook" method="POST">
                                            <input id="txtPassword" type="password" name="password" placeholder="Enter new password">
                                            <form:errors path="password" id="validError" />
                                            <input id="txtRePassword" type="password" name="rePassword" placeholder="Re-enter new password">
                                            <div id="validError">${rePasswordInvalid }</div>
                                            <input id="btnSubmitPassword" type="submit" name="login" class="login loginmodal-submit" value="Login">
                                        </form:form>

                                        <div class="login-help">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name='keepLoggin' /> keep me logged-in</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </jsp:body>
                    </t:layout>