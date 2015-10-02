<%@page pageEncoding="UTF8" %>
    <%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
                <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
                    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
                        <jsp:useBean id="userDAO" class="com.matrimony.database.UserDAO" />
                        <jsp:useBean id="FriendBean" class="com.matrimony.database.FriendDAO" />
                        <jsp:useBean id="matrimony" class="com.matrimony.database.Matrimony" />
                        <jsp:useBean id="jstl" class="com.matrimony.model.JSLTFunctionUtil" />
                        <t:layout>
                            <jsp:attribute name="head">
                                <link rel="stylesheet" type="text/css" href="/matrimony/resources/css/home-style.css">
                                <script src="/matrimony/resources/js/chat/entity.js"></script>
                                <script src="/matrimony/resources/js/chat/chat-process.js"></script>
                                <script src="/matrimony/resources/js/chat/emoticon.js"></script>
                            </jsp:attribute>
                            <jsp:body>
                                <c:set var="alias" value="matrimony" />
                                <c:set var="peopleSuggestList" value="${matrimony.getSuggestUsers(sessionScope.user) }" />
                                <script>
                                    //                                 	======================SOME CODE INIT======================
                                    var sessionUserId ='${sessionScope.user.id}';
                                    $(document).ready(function() {
                                        $("#avatar").click(function() {
                                            $("#uploadAvatarPhoto").click();
                                        });
                                        $("#uploadAvatarPhoto").change(function() {
                                            $("#uploadPhotoForm").submit();
                                        });
                                        $(document).on('click','#btn-logout', function(e) {
                                            e.preventDefault();
                                            $("<form action='logout' method='POST'></form>").submit();
                                        });
                                        $(document).on('change','#cbx-pay-with', function(e) {
                                            var value = $(this).val();
                                            if (value =='credit')
                                                $('#credit-box').slideDown('slow');
                                            else
                                                $('#credit-box').slideUp('slow');
                                        })
                                        $(document).on('change','.checkboxPack', function(e) {
                                            if ($(this).val() == 1)
                                                $('#final-payment').html('$ 49.99');
                                            else
                                                $('#final-payment').html('$ 499.99');
                                        })
                                    });
                                </script>
                                <div id="hiddenDIV">
                                    <form id="uploadPhotoForm" action="changeAvatar" method="POST" enctype="multipart/form-data">
                                        <input id="uploadAvatarPhoto" type="file" name="file" accept="image/*" />
                                        <input type="hidden" type="text" name="test" value="love" />
                                    </form>
                                </div>
                                <div id="container" class='row'>
                                    <div id='left' class='col-lg-2'>
                                        <img id="avatar" alt='avatar' style='height: 170px; width: 170px;' src='${userAvatarFolder}/${sessionScope.user.avatarPhoto }' />
                                        <br />
                                        <br />
                                        <span id='name' class='col-lg-21'>${sessionScope.user.name }</span>
                                        <br />
                                        <br />
                                        <div class="list-group">
                                            <a class="list-group-item" href="#"><i class="fa fa-user"></i>&nbsp;Thông tin cá nhân</a>
                                            <a class="list-group-item" href="#"> <i class="fa fa-cog"></i>&nbsp;Cài đặt</a>
                                            <a class="list-group-item" id='btn-logout' href="#"><i class="fa fa-sign-out"></i>&nbsp;Thoát</a>
                                        </div>
                                    </div>
                                    <div id='center' class='col-lg-7' style='background-color: #ffffff; border: solid 2px #f4f4f4; border-radius: 6px;'>
                                        <c:choose>
                                            <c:when test="${userDAO.hasExpiries(sessionScope.user)}">
                                                <div class='container col-lg-12'>
                                                <br/>
                                                <div class="alert alert-danger" role="alert">Tài khoản của bạn đã hết hạn, vui lòng thanh toán để sử tiếp tục sử dụng</div>
                                                <form method='POST' action='payment' class='col-lg-6 form-horizontal'>
<!--                                                     <label>Chọn gói sử dụng</label> -->
                                                    <p><h3>Chọn gói sử dụng:</h3>
                                                    <div class='form-group' style='font-size: 17px;'>
                                                        <div class="checkbox">
                                                            <label>
                                                                <input class='checkboxPack' type="radio" name="pack" value="1" />
                                                                <span id="price"> 1 tháng <span id="currencyCode">$</span>&nbsp;49.99</span>
                                                            </label>
                                                        </div>
                                                        <div class="checkbox">
                                                            <label>
                                                                <input class='checkboxPack' type="radio" name="pack" value="12" />
                                                                <span id="price"> 12 tháng <span id="currencyCode">$</span>&nbsp;499.99</span> (Tiết kiệm 15%)</label>
                                                        </div>
                                                    </div>
                                                    
                                                    <ul class="list-group">
													  <li class="list-group-item list-group-item-info">Final payment<span id='final-payment' class="badge"></span></li>
													</ul>
                                                   
                                                    <div class='form-group'>
                                                        <select id="cbx-pay-with" name="payWith" class='selectpicker col-lg-12' data-style='btn-warning'>
                                                            <option disabled selected>Chọn loại thanh toán</option>
                                                            <option value='paypal'>Paypal</option>
                                                            <option value='credit'>Credit card</option>
                                                        </select>
                                                    </div>
													
													<div id='credit-box' style='display:none;'>
													<img class="img-responsive pull-right" src="http://i76.imgup.net/accepted_c22e0.png">
									                        <div class="row">
									                            <div class="col-xs-12">
									                                <div class="form-group">
									                                    <label>CARD NUMBER</label>
									                                    <div class="input-group">
									                                        <input type="tel" class="form-control" name="cardNumber" placeholder="Valid Card Number" />
									                                        <span class="input-group-addon"><i class="fa fa-credit-card"></i></span>
									                                    </div>
									                                </div>                            
									                            </div>
									                        </div>
									                        <div class="row">
									                            <div class="col-xs-7 col-md-7">
									                                <div class="form-group">
									                                    <label><span class="hidden-xs">EXPIRATION</span><span class="visible-xs-inline">EXP</span> DATE</label>
									                                    <input  type="tel" class="form-control" name="cardExpiry" placeholder="MM / YY" />
									                                </div>
									                            </div>
									                            <div class="col-xs-5 col-md-5 pull-right">
									                                <div class="form-group">
									                                    <label>CV CODE</label>
									                                    <input type="tel" class="form-control" name="cardCVC" placeholder="CVC"/>
									                                </div>
									                            </div>
									                        </div>
									                        <div class="row">
									                            <div class="col-xs-12">
									                                <div class="form-group">
									                                    <label>COUPON CODE</label>
									                                    <input type="text" class="form-control" name="couponCode" />
									                                </div>
									                            </div>                        
									                        </div>
									                        
									                        <div class="row" style="display:block;">
									                            <div class="col-xs-12">
									                                <p class="payment-errors">ERROR</p>
									                            </div>
									                        </div>
									                        </div>
													<input type='submit' class='btn btn-success col-lg-12' value='Pay now'/>
													
                                                </form>
                                        <div class='col-lg-6'>
                                                	 <p><h3>Features:</h3>
										                <ul>
										                    <li>As-you-type, input formatting</li>
										                    <li>Form field validation (also as you type)</li>
										                    <li>Graceful error feedback for declined card, etc</li>
										                    <li>AJAX form submission w/ visual feedback</li>
										                    <li>Creates a Stripe credit card token</li>
										                </ul>
										            </p>
										            <p>Be sure to replace the dummy API key with a valid Stripe API key.</p>
										            
										            <p>Built upon: Bootstrap, jQuery, 
										                <a href="http://jqueryvalidation.org/">jQuery Validation Plugin</a>, 
										                <a href="https://github.com/stripe/jquery.payment">jQuery.payment library</a>,
										                and <a href="https://stripe.com/docs/stripe.js">Stripe.js</a>
										            </p>
                                        </div>
                                                </div>
                                                
                                            </c:when>
                                            <c:otherwise>
                                                <span>Những người phù hợp với bạn</span>
                                                <div id='tblPeople'>
                                                    <c:forEach var="i" items="${peopleSuggestList }">
                                                        <div id='person-panel' class='row col-sm-12' data-user-id='${i.id}'>
                                                            <div class='col-sm-2'>
                                                                <img id='person-avatar' alt='person-avatar' src='/matrimony/resources/profile/avatar/${i.avatarPhoto }' style='height: 70px; width: 70px;' />
                                                                <br />
                                                                <br />
                                                                <span id='person-name'>${i.name }</span>
                                                            </div>
                                                            <div id='person-left' class='col-sm-5'>
                                                                <span id='person-name'><span id="label-basic">Name</span> ${i.name }</span>
                                                                <br />
                                                                <span id='person-age'><span id="label-basic">Age </span> ${jstl.yearUntilToDay(i.birthday) } tuổi</span>
                                                                <br />
                                                                <span id='person-gender'><span id="label-basic">Gender </span>${i.gender =='female' ?'Nữ':'Name'}</span>
                                                                <br />
                                                                <br />
                                                                <span><input id='btn-chat-inbox' type='button' class="btn-warning" value='Inbox'/></span>
                                                                <span id='person-btna'>1 friend</span>
                                                            </div>
                                                            <div id='person-right' class='col-sm-4'>
                                                                <span id='person-name'><span id="label-basic">Country </span>${i.countryside }</span>
                                                                <br />
                                                                <span id='person-city'><span id="label-basic">City </span>${i.hometown }</span>
                                                                <br />
                                                                <span id='person-status'><span id="label-basic">Marital </span> ${i.maritalStatus }</span>
                                                                <br />
                                                                <br />
                                                                <span id='person-btna'><button class="btn-info">Add friend</button></span>

                                                            </div>
                                                        </div>
                                                    </c:forEach>
                                                    <div class='row col-sm-12' style='text-align: center;'>
                                                        <h4>Trống</h4>
                                                    </div>
                                                </div>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                    <div id='right' class='col-lg-3'>
                                        <div class="panel panel-default">
                                            <div class="panel-heading">
                                                <h3 class="panel-title">Friends</h3>
                                            </div>
                                            <div class="panel-body">
                                            </div>
                                        </div>
                                    </div>
                                </div>


                                <!-- CHAT BOX START HERE -->

                                <div style='z-index:9999'>
                                    <div id='chat-container' class="container">
                                        <div class="row col-lg-3 css-chat-window" id="chat-window" style='display:none;'>
                                            <div class="col-lg-12">
                                                <div class="panel panel-default css-chat-panel">
                                                    <div class="panel-heading top-bar css-heading-chat">
                                                        <div class="col-lg-8">
                                                            <h3 class="panel-title">
	                              								<span class="glyphicon glyphicon-comment"></span><span id='name'></span>
	                           								</h3>
                                                        </div>
                                                        <div class="col-lg-4" style="text-align: right;">
                                                            <a href="#"><span id="minim_chat_window" class="glyphicon glyphicon-minus icon_minim"></span></a>
                                                            <a href="#"><span id="close_chat_window" class="glyphicon glyphicon-remove icon_close" data-id="chat-window"></span></a>
                                                        </div>
                                                    </div>
                                                    <div class="panel-body msg-container-base">
                                                        <div id='base-sent-message' class="row msg_container base-sent" style='display:none;'>
                                                            <div class="col-lg-10 css-chat-content">
                                                                <div class="messages msg_sent">
                                                                    <p></p>
                                                                    <time datetime="2009-11-13T20:00">Timothy • 51 min</time>
                                                                </div>
                                                            </div>
                                                            <div class="col-lg-2 css-avatar">
                                                                <img src="http://www.bitrebels.com/wp-content/uploads/2011/02/Original-Facebook-Geek-Profile-Avatar-1.jpg" class="img-responsive ">
                                                            </div>
                                                        </div>
                                                        <div id='base-receive-message' class="row msg_container base-receive" style='display:none;'>
                                                            <div class="col-lg-2 css-avatar">
                                                                <img src="http://www.bitrebels.com/wp-content/uploads/2011/02/Original-Facebook-Geek-Profile-Avatar-1.jpg" class="img-responsive ">
                                                            </div>
                                                            <div class="col-md-10 col-xs-10 css-chat-content">
                                                                <div class="messages msg_receive">
                                                                    <p></p>
                                                                    <time datetime="2009-11-13T20:00">Timothy • 51 min</time>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="panel-footer">
                                                        <!--                                                         <div class="input-group"> -->
                                                        <input id="txt-chat-msg" type="text" class="form-control input-sm chat_input" placeholder="Write your message here..." />
                                                        <!--                                                             <span class="input-group-btn"> -->
                                                        <!-- 	                          									 <button class="btn btn-primary btn-sm" id="btn-chat-send-msg">Send</button> -->
                                                        <!-- 	                           								</span> -->
                                                        <!--                                                         </div> -->
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </jsp:body>
                        </t:layout>