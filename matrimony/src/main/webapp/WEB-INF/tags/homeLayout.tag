<%@ tag language="java" pageEncoding="UTF-8" %>
    <%@attribute name="head" fragment="true" %>
        <%@attribute name="middle" fragment="true" %>
            <%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
                <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
                        <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
                            <jsp:useBean id="matrimony" class="com.matrimony.database.Matrimony" />
                            <c:set var="alias" value="matrimony" />
                            <c:set var="peopleSuggestList" value="${matrimony.getSuggestUsers(sessionScope.user) }" />

                            <t:layout>
                                <jsp:attribute name="head">
                                    <link rel="stylesheet" type="text/css" href="/matrimony/resources/css/home-style.css">
                                     <script src="/matrimony/resources/js/chat/entity.js"></script>
                                <script src="/matrimony/resources/js/chat/chat-process.js"></script>
                                <script src="/matrimony/resources/js/chat/emoticon.js"></script>
                                    <script>
                                        var sessionUserId = '${sessionScope.user.id}';
                                        $(document).ready(function() {
                                            $("#avatar").click(function() {
                                                $("#uploadAvatarPhoto").click();
                                            });
                                            $("#uploadAvatarPhoto").change(function() {
                                                $("#uploadPhotoForm").submit();
                                            });
                                            $(document).on('click', '#btn-logout', function(e) {
                                                e.preventDefault();
                                                $("<form action='logout' method='POST'></form>").submit();
                                            });
                                        });
                                    </script>
                                   <jsp:invoke fragment="head"></jsp:invoke>
                                </jsp:attribute>
                                <jsp:body>
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
                                                <a class="list-group-item" href="/matrimony/payment"> <i class="fa fa-cog"></i>&nbsp;Thanh toán</a>
                                                <a class="list-group-item" id='btn-logout' href="#"><i class="fa fa-sign-out"></i>&nbsp;Thoát</a>
                                            </div>
                                        </div>
                                        <div id='middle' class='col-lg-7' style='background-color: #ffffff; border: solid 2px #f4f4f4; border-radius: 6px;'>
                                            <jsp:invoke fragment="middle"></jsp:invoke>
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
                                    </div>

                                </jsp:body>


                            </t:layout>