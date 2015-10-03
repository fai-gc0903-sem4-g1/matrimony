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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
        <link rel="stylesheet" type="text/css" href="/matrimony/resources/css/home-style.css">
        <script src="/matrimony/resources/js/chat/entity.js"></script>
        <script src="/matrimony/resources/js/chat/chat-process.js"></script>
        <script src="/matrimony/resources/js/chat/emoticon.js"></script>
        <script src="/matrimony/resources/js/chat/emoticon.js"></script>
        <script src="../../resources/js/friend.js" type="text/javascript"></script>
        <style>

            * {padding:0; margin:0;}

            p, h3 { 
                margin-bottom:15px;
            }

            div {
                padding:10px;
                width:600px;
                background:#fff;
            }

            .tabs li {
                list-style:none;
                display:inline;
            }

            .tabs a {
                padding:5px 10px;
                display:inline-block;
                background:#666;
                color:#fff;
                text-decoration:none;
            }

            .tabs a.active {
                background:#fff;
                color:#000;
            }


        </style>
        <script>
            $(document).ready(function () {
                $('ul.tabs').tabs;
                $('ul.tabs').each(function () {
                    // For each set of tabs, we want to keep track of
                    // which tab is active and it's associated content
                    var $active, $content, $links = $(this).find('a');
                    // If the location.hash matches one of the links, use that as the active tab.
                    // If no match is found, use the first link as the initial active tab.
                    $active = $($links.filter('[href="' + location.hash + '"]')[0] || $links[0]);
                    $active.addClass('active');
                    $content = $($active[0].hash);
                    // Hide the remaining content
                    $links.not($active).each(function () {
                        $(this.hash).hide();
                    });
                    // Bind the click event handler
                    $(this).on('click', 'a', function (e) {
                        // Make the old tab inactive.
                        $active.removeClass('active');
                        $content.hide();
                        // Update the variables with the new link and content
                        $active = $(this);
                        $content = $(this.hash);
                        // Make the tab active.
                        $active.addClass('active');
                        $content.show();
                        // Prevent the anchor's default click action
                        e.preventDefault();
                    });
                });
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <c:set var="alias" value="matrimony" />
        <c:set var="peopleSuggestList" value="${matrimony.getSuggestUsers(sessionScope.user) }" />
        <script>
            //                                 	======================SOME CODE INIT======================
            var sessionUserId = '${sessionScope.user.id}';
            $(document).ready(function () {
                $("#avatar").click(function () {
                    $("#uploadAvatarPhoto").click();
                });
                $("#uploadAvatarPhoto").change(function () {
                    $("#uploadPhotoForm").submit();
                });
                $(document).on('click', '#btn-logout', function (e) {
                    e.preventDefault();
                    $("<form action='logout' method='POST'></form>").submit();
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
        
        <!--        TAB PANNEL          -->
        <ul class='tabs'>
            <li><a href='#tab1' class='active'>Tab 1</a></li>
            <li><a href='#tab2'>Tab 2</a></li>
            <li><a href='#tab3'>Tab 3</a></li>
        </ul>
        <div id='request' style='display:block;'>
            <p>Hi, this is the first tab.</p>
        </div>
        <div id='friend'>
            <p>This is the 2nd tab.</p>
        </div>
        <div id='tab3'>
            <p>And this is the 3rd tab.</p>
        </div>
    </jsp:body>
</t:layout>