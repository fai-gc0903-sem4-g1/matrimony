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
                            <jsp:body>
                                <c:set var="alias" value="matrimony" />
                                <c:set var="peopleSuggestList" value="${matrimony.getSuggestUsers(sessionScope.user) }" />
                                <style>
                                    body {
                                        background-color: #f9f9f9;
                                    }
                                    #container {
                                        padding: 0px 30px 0 30px;
                                    }
                                    #person-panel {
                                        border: solid 2px #f7f7f7;
                                        margin-left: 0px;
                                        margin-top: 10px;
                                        padding: 12px 0px;
                                    }
                                    #person-btna {
                                        color: #8DBAD9;
                                        cursor: pointer;
                                    }
                                    #label-basic {
                                        font-weight: bold;
                                        color: #116CBB;
                                    }
                                    #avatar {
                                        cursor: pointer;
                                    }
                                    #hiddenDIV {
                                        display: none;
                                    }
                                </style>
                                <script>
                                    $(document).ready(function() {
                                        $("#avatar").click(function() {
                                            $("#uploadAvatarPhoto").click();
                                        });
                                        $("#uploadAvatarPhoto").change(function() {
                                            $("#uploadPhotoForm").submit();
                                        });
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
                                        <span id='name' style='font-weight: bold;'>${sessionScope.user.name }</span>
                                        <br />
                                        <br />
                                        <ul class="list-group">
                                            <li class="list-group-item"><a href="#">My friend</a> <span class="badge">14</span>
                                            </li>
                                            <li class="list-group-item"><a href="#">Licence</a>
                                            </li>
                                            <li class="list-group-item">
                                                <a href="#">Last login<fmt:formatDate pattern="dd-MM-yyyy HH:mm:ss" value="${session.user.loginTime}" /></a>
                                            </li>
                                        </ul>
                                    </div>
                                    <div id='center' class='col-lg-7' style='background-color: #ffffff; border: solid 2px #f4f4f4; border-radius: 6px;'>
                                        <c:choose>
                                            <c:when test="${userDAO.hasExpiries(sessionScope.user)}">
                                                <span>tài khoản của bạn đã hết hạn, vui lòng thanh toán để sử tiếp tục sử dụng</span>
                                                <br/>
                                                <a href="payment">click vào đây để thanh toán</a>
                                            </c:when>
                                            <c:otherwise>
                                                Những người phù hợp với bạn
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
                                <style>
                                    /*          body { */
                                    /*          height: 400px; */
                                    /*          position: fixed; */
                                    /*          bottom: 0; */
                                    /*          } */
                                    
                                    .css-avatar,
                                    .css-chat-content {
                                        padding: 0;
                                    }
                                    .css-chat-panel {
                                        margin-bottom: 0px;
                                    }
                                    .css-chat-window {
                                        bottom: 0;
                                        position: fixed;
                                        float: right;
                                        right: 0;
                                        margin-right: 0px;
                                    }
                                    .css-chat-window>div>.panel {
                                        border-radius: 5px 5px 0 0;
                                    }
                                    .icon_minim {
                                        padding: 2px 10px;
                                    }
                                    .msg-container-base {
                                        background: #e5e5e5;
                                        margin: 0;
                                        padding: 0 10px 10px;
                                        height: 300px;
                                        overflow-y: auto;
                                        overflow-x: hidden;
                                    }
                                    .css-heading-chat {
                                        background: #666;
                                        color: white;
                                        padding: 10px;
                                        position: relative;
                                        overflow: hidden;
                                    }
                                    .msg_receive {
                                        padding-left: 0;
                                        margin-left: 0;
                                    }
                                    .msg_sent {
                                        padding-bottom: 20px !important;
                                        margin-right: 0;
                                    }
                                    .messages {
                                        background: white;
                                        padding: 10px;
                                        border-radius: 2px;
                                        box-shadow: 0 1px 2px rgba(0, 0, 0, 0.2);
                                        max-width: 100%;
                                    }
                                    .messages>p {
                                        font-size: 13px;
                                        margin: 0 0 0.2rem 0;
                                    }
                                    .messages>time {
                                        font-size: 11px;
                                        color: #ccc;
                                    }
                                    .msg_container {
                                        padding: 10px;
                                        overflow: hidden;
                                        display: flex;
                                    }
                                    .css-chat-avatar-img {
                                        display: block;
                                        width: 100%;
                                    }
                                    .css-avatar {
                                        position: relative;
                                    }
                                    .base-receive>.css-avatar:after {
                                        content: "";
                                        position: absolute;
                                        top: 0;
                                        right: 0;
                                        width: 0;
                                        height: 0;
                                        border: 5px solid #FFF;
                                        border-left-color: rgba(0, 0, 0, 0);
                                        border-bottom-color: rgba(0, 0, 0, 0);
                                    }
                                    .base-sent {
                                        justify-content: flex-end;
                                        align-items: flex-end;
                                    }
                                    .base-sent>.css-avatar:after {
                                        content: "";
                                        position: absolute;
                                        bottom: 0;
                                        left: 0;
                                        width: 0;
                                        height: 0;
                                        border: 5px solid white;
                                        border-right-color: transparent;
                                        border-top-color: transparent;
                                        box-shadow: 1px 1px 2px rgba(black, 0.2);
                                    }
                                    .msg_sent>time {
                                        float: right;
                                    }
                                    .msg-container-base::-webkit-scrollbar-track {
                                        -webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
                                        background-color: #F5F5F5;
                                    }
                                    .msg-container-base::-webkit-scrollbar {
                                        width: 12px;
                                        background-color: #F5F5F5;
                                    }
                                    .msg-container-base::-webkit-scrollbar-thumb {
                                        -webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, .3);
                                        background-color: #555;
                                    }
                                    .btn-group.dropup {
                                        position: fixed;
                                        left: 0px;
                                        bottom: 0;
                                    }
                                </style>
                                <script src="/matrimony/resources/js/entity.js"></script>
                                <script>
                                    //SETUP CLICK EVENT
                                    $(document).on('click', '#minim_chat_window', function(e) {
                                        showAndHideChat($(this));
                                    });

                                    $(document).on('click', '#close_chat_window', function(e) {
                                        closeChatWindow($(this));
                                    });

                                    $(document).on('click', '#btn-chat-inbox', function(e) {
                                        createChatWindow($(this).parents('#person-panel').data('user-id'));
                                    });

//                                     $(document).on('click', "#btn-chat-send-msg", function(e) {
//                                         sendMessage($(this).parents('#chat-window'));
//                                     });
                                    
                                    $(document).on('keypress', '#txt-chat-msg', function(e){
                                    	if(e.keyCode==13){
                                    		sendMessage($(this).parents('#chat-window'));
                                    		$(this).val('');
                                    	}
                                    });
                                    //CHAT PROCESS
                                    var ws = new WebSocket('ws://localhost:8080/matrimony/chatserver');
                                    ws.onopen = function() {
                                        processOpen();
                                    }
                                    ws.onmessage = function(obj) {
                                        processMessage(obj);
                                    }
                                    ws.onclose = function() {
                                        processClose();
                                    }
                                    ws.onerror = function() {
                                        processError();
                                    }

                                    function processOpen() {
                                        var senderId = '${sessionScope.user.id}';
                                        var messageObj = new Message(senderId, null, null, null);
                                        ws.send(JSON.stringify(messageObj));
                                        console.log('ws connected');
                                    };

                                    function processMessage(obj) {
                                            var msgObj = JSON.parse(obj.data);
                                            var senderId = msgObj.senderId;
                                            var hasChatWindow = false;
                                            var chatWindow = null;
                                            $('.css-chat-window').each(function(i) {
                                                if (i > 0) {
                                                    var temp = $(this);
                                                    if (senderId == temp.data('user-id')) {
                                                        chatWindow = temp;
                                                    }
                                                }
                                            });

                                            if (chatWindow == null) {
                                                chatWindow = createChatWindow(msgObj.senderId);
                                            }
                                             var receiverClone = $('#base-receive-message').clone().appendTo(chatWindow.find('.msg-container-base'));
                                             receiverClone.find('p').html(msgObj.content);
                                             receiverClone.css('display', 'block');
										};

                                            function processClose() {};

                                            function processError() {
                                                alert('websocket error');
                                            };

                                            function processOnpen() {};
                                            
                                            function sendMessage(chatWindow) {
                                                var receiverId = chatWindow.data('user-id');
                                                var senderId = '${sessionScope.user.id}';
                                                var content = chatWindow.find('#txt-chat-msg').val();
                                                var messageObj = new Message(senderId, receiverId, 'text-chat', content, Date.now());
                                                ws.send(JSON.stringify(messageObj));
                                                var sentClone = $('#base-sent-message').clone().appendTo(chatWindow.find('.msg-container-base'));
                                                sentClone.find('p').html(messageObj.content);
                                                sentClone.css('display', 'block');
                                            };

                                            //INITIALIZE CHAT GUI
                                            function showAndHideChat(obj) {
                                                if (!obj.hasClass('panel-collapsed')) {
                                                    obj.parents('.panel').find('.panel-body').slideUp();
                                                    obj.addClass('panel-collapsed');
                                                    obj.removeClass('glyphicon-minus').addClass('glyphicon-plus');
                                                } else {
                                                    obj.parents('.panel').find('.panel-body').slideDown();
                                                    obj.removeClass('panel-collapsed');
                                                    obj.removeClass('glyphicon-plus').addClass('glyphicon-minus');
                                                }
                                            };

                                            function closeChatWindow(obj) {
                                                obj.parents('#chat-window').remove();
                                            };
                                            var sizeTotal = 0;

                                            function createChatWindow(userId) {
                                                var margin = 0;
                                                var duplicate = false;
                                                $('.css-chat-window').each(function(i) {
                                                    if (i > 0) {
                                                        var chatWindow = $(this);
                                                        $('.css-chat-window').each(function(j) {
                                                            if (j != i) {
                                                                if (chatWindow.data('user-id') == userId) {
                                                                    duplicate = true;
                                                                }
                                                            }
                                                        });
                                                        margin = margin + parseInt(chatWindow.css('width'));
                                                    }
                                                });

                                                if (duplicate) {
                                                    alert('dang chat voi nguoi nay roi');
                                                } else {
                                                    var clone = $("#chat-window").clone().appendTo("#chat-container");
                                                    clone.css("margin-right", margin);
                                                    clone.data('user-id', userId);
                                                    clone.find('.panel-title').html(userId);
                                                    clone.css('display', 'block');
                                                    return clone;
                                                }
//                                                 return null;
                                            }

                                            //                                     $(document).on('focus', '.panel-footer input.chat_input', function(e) {
                                            //                                         var $this = $(this);
                                            //                                         if ($('#minim_chat_window').hasClass('panel-collapsed')) {
                                            //                                             $this.parents('.panel').find('.panel-body').slideDown();
                                            //                                             $('#minim_chat_window').removeClass('panel-collapsed');
                                            //                                             $('#minim_chat_window').removeClass('glyphicon-plus')
                                            //                                                 .addClass('glyphicon-minus');
                                            //                                         }
                                            //                                     });
                                </script>
                                <div style='z-index:9999'>
                                    <div id='chat-container' class="container">
                                        <div class="row col-lg-3 css-chat-window" id="chat-window" style='display:none;'>
                                            <div class="col-lg-12">
                                                <div class="panel panel-default css-chat-panel">
                                                    <div class="panel-heading top-bar css-heading-chat">
                                                        <div class="col-lg-8">
                                                            <h3 class="panel-title">
	                              	<span class="glyphicon glyphicon-comment"></span> Chat - Miguel
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
                                                                    <p>that mongodb thing looks good, huh? tiny master db, and huge document store
                                                                    </p>
                                                                    <time datetime="2009-11-13T20:00">Timothy • 51 min</time>
                                                                </div>
                                                            </div>
                                                            <div class="col-lg-2 css-avatar">
                                                                <img src="http://www.bitrebels.com/wp-content/uploads/2011/02/Original-Facebook-Geek-Profile-Avatar-1.jpg" class=" img-responsive ">
                                                            </div>
                                                        </div>



                                                        <div id='base-receive-message' class="row msg_container base-receive" style='display:none;'>
                                                            <div class="col-lg-2 css-avatar">
                                                                <img src="http://www.bitrebels.com/wp-content/uploads/2011/02/Original-Facebook-Geek-Profile-Avatar-1.jpg" class=" img-responsive ">
                                                            </div>
                                                            <div class="col-md-10 col-xs-10 css-chat-content">
                                                                <div class="messages msg_receive">
                                                                    <p>that mongodb thing looks good, huh? tiny master db, and huge document store
                                                                    </p>
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


                                        <div class="btn-group dropup">
                                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                                <span class="glyphicon glyphicon-cog"></span> <span class="sr-only">Toggle
	               Dropdown</span>
                                            </button>
                                            <ul class="dropdown-menu" role="menu">
                                                <li><a href="#" id="new_chat"><span
	                     class="glyphicon glyphicon-plus"></span> Novo</a>
                                                </li>
                                                <li><a href="#"><span class="glyphicon glyphicon-list"></span>
	                     Ver outras</a>
                                                </li>
                                                <li><a href="#"><span class="glyphicon glyphicon-remove"></span>
	                     Fechar Tudo</a>
                                                </li>
                                                <li class="divider"></li>
                                                <li><a href="#"><span class="glyphicon glyphicon-eye-close"></span>
	                     Invisivel</a>
                                                </li>
                                            </ul>
                                        </div>

                                    </div>
                                </div>
                            </jsp:body>
                        </t:layout>