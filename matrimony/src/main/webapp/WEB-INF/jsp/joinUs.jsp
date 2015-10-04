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
                                
                                .st-valid-error{
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
                                
                                
                               
                            </style>
                            <script>
                                $(document).ready(function() {
                                	$('[data-toggle="tooltip"]').tooltip({trigger:'click'});
                                	$('[data-toggle="popover"]').popover({placement: 'left'});
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
                                	<img class="pull-right" src='http://scideck.com/mod/community3_theme/graphics/artificial-intelligence-connected-people.png'/>
                                	
                                	</div>
                                    <div id='login-with' class="col-lg-4">
                                        <a href="${fbConn.FBAuthUrl}" class="btn btn-block btn-social btn-facebook"><i class="fa fa-facebook"></i>&nbsp;Log-in with Facebook</a>
                                    	<a href="${fbConn.FBAuthUrl}" class="btn btn-block btn-social btn-google"><i class="fa fa-google"></i>&nbsp;Log-in with Google</a>
                                    	<a href="${fbConn.FBAuthUrl}" class="btn btn-block btn-social btn-twitter"><i class="fa fa-twitter"></i>&nbsp;Log-in with Twitter</a>
                                    	<a href="${fbConn.FBAuthUrl}" class="btn btn-block btn-social btn-yahoo"><i class="fa fa-yahoo"></i>&nbsp;Log-in with yahoo</a>
                                    	<a href="${fbConn.FBAuthUrl}" class="btn btn-block btn-social btn-instagram"><i class="fa fa-instagram"></i>&nbsp;Log-in with Instagram</a>
                                   </div>
                                </div>
                                <div id="right" class="col-lg-4">
                                    <form:form modelAttribute="userReg" id="registerForm" action="/matrimony/register" method="POST" class="col-lg-12">
                                        <c:if test="${registerFormError }">
                                        </c:if>
                                        <div style="display: none;" id="myAlert" class="alert alert-danger" role="alert"></div>

                                             <div class="form-group has-feedback col-lg-6" data-toggle="popover" data-content='${regErrorFirstName }'>
								                <input id="firstName" class="form-control" type="text" name="firstName" placeholder="Tên" value="${requestScope.userReg.firstName}"></input>
								                <i class="glyphicon glyphicon-exclamation-sign form-control-feedback st-valid-error"></i>
								            </div>
								            
								            <div class="form-group has-feedback col-lg-6" data-toggle="popover" data-content="${regErrorLastName }">
								                <input id="lastName" class="form-control" type="text" name="lastName" placeholder="Họ" value="${requestScope.userReg.lastName}"></input>
								                <i class="glyphicon glyphicon-exclamation-sign form-control-feedback st-valid-error"></i>
								                
								            </div>
                                           
                                            
                                            
                                     


										<div class="form-group has-feedback col-lg-12" data-toggle="popover" data-content="đây là một đoạn văn bản rất là dài và không có ý nghĩa gì cả">
								                <input id="email" onkeyup="" class="form-control" type="email" name="email" value="${userReg.email}" placeholder="Địa chỉ email"/>
								                <i class="glyphicon glyphicon-exclamation-sign form-control-feedback st-valid-error"></i>
								                <form:errors path="email" id="validError" />
								            </div>
                                       <div class="form-group has-feedback col-lg-12" data-toggle="popover" data-content="đây là một đoạn văn bản rất là dài và không có ý nghĩa gì cả">
								                <input id="reEmail" name="reEmail" class="form-control" type="email" placeholder="Nhập lại địa chỉ email" />
								                <i class="glyphicon glyphicon-exclamation-sign form-control-feedback st-valid-error"></i>
								        </div>

								        <div class="form-group has-feedback col-lg-12" data-toggle="popover" data-content="đây là một đoạn văn bản rất là dài và không có ý nghĩa gì cả">
								                <input id="password" class="form-control" type="password" name="password" placeholder="Mật khẩu"></input>
                                                <form:errors path="password" id="validError" />
								                <i class="glyphicon glyphicon-exclamation-sign form-control-feedback st-valid-error"></i>
								        </div>
                                       <div class="form-group has-feedback col-lg-12" data-toggle="popover" data-content="đây là một đoạn văn bản rất là dài và không có ý nghĩa gì cả">
								                <input id="phone" class="form-control" type="text" name="contactNumber" placeholder="Số điện thoại nếu có"></input>
                                                <form:errors path="contactNumber" id="validError" />
								                <i class="glyphicon glyphicon-exclamation-sign form-control-feedback st-valid-error"></i>
								        </div>
								        
								        <div class='form-group col-lg-12'>
                                                <label class="control-label">Ngày sinh</label>
                                         </div>
                                      
                                        <div class="form-group col-lg-3">
                                                <select class="" name="day">
                                                    <option>Ngày</option>
                                                    <c:forEach var="i" begin="1" end="31" step="1">
                                                        <option>${i}</option>
                                                    </c:forEach>
                                                </select>
                                         </div>
                                         
                                         
                                           <div class="form-group col-lg-3">
                                               <select class="" name="month">
                                                   <option>Tháng</option>
                                                   <c:forEach var="i" begin="1" end="12" step="1">
                                                       <option>${i}</option>
                                                   </c:forEach>

                                               </select>
                                           </div>
                                           
                                           <div class="form-group col-lg-3">
                                               <select class="" name="year">
                                                   <option>Năm</option>
                                                   <c:forEach var="i" begin="1905" end="2015" step="1">
                                                       <option>${i}</option>
                                                   </c:forEach>
                                               </select>
                                           </div>
                                             <div class="form-group has-feedback col-lg-3" data-toggle="popover" data-content="đây là một đoạn văn bản rất là dài và không có ý nghĩa gì cả">
                                               <i class="glyphicon glyphicon-exclamation-sign form-control-feedback st-valid-error"></i>
                                           </div>
                                           
                                           
                                          <div class="col-lg-5 col-lg-offset-1" id="validError">${requestScope.birthdayInvalid}</div>

                                        <div class="form-group has-feedback col-lg-12">
                                        <label class="control-label">Giới tính</label>
                                            <div style="font-size: 16px;" class="checkbox" id="sexGroup">
                                                <label class="control-label">
                                                    <input type="radio" id="sex" name="gender" value="female" /> Nữ
                                                </label>
                                                <label class="control-label">
                                                    <input id="sex" type="radio" name="gender" value="male" /> Nam
                                                </label>
                                                <form:errors path="gender" id="validError" />
                                            </div>
                                            <i class="glyphicon glyphicon-exclamation-sign form-control-feedback st-valid-error" data-toggle="popover" data-content="đây là một đoạn văn bản rất là dài và không có ý nghĩa gì cả"></i>
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