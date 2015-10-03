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

<<<<<<< HEAD
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
                                

                                            <!-- CHAT BOX START HERE -->
                                <div>
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
=======
<t:layout>
    <jsp:attribute name="head">
        <link rel="stylesheet" type="text/css" href="/matrimony/resources/css/home-style.css">
        <script src="/matrimony/resources/js/chat/entity.js"></script>
        <script src="/matrimony/resources/js/chat/chat-process.js"></script>
        <script src="/matrimony/resources/js/chat/emoticon.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
        <script>
            $(document).ready(function () {
                var ssUserId = '${sessionScope.user.id}';
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
            $(document).ready(function () {
                $.get('listSuggest', function (responseJson) {

                    if (responseJson !== null) {
                        $("#suggest").find("tr:gt(0)").remove();
                        var table1 = $("#suggest");
                        $.each(responseJson, function (key, value) {
                            var rowNew = $("<div><div></div><div></div><div></div><div></div></div>");
                            rowNew.children().eq(0).text(value['id']);
                            rowNew.children().eq(1).text(value['firstName']);
                            rowNew.children().eq(2).append("<input id='btnAdd' type='button' value='Add Friend' onClick='addFriend(\"" + value['id'] + "\")'/>");
                            rowNew.children().eq(3).append("<input id='btnRemove' type='button' value='Remove' onClick='removeFriend(\"" + value['id'] + "\")'/>");
                            rowNew.appendTo(table1);
                        });
                    }
                });
            });
        </script>
        <script>
            $(document).ready(function () {
                $.get('allRequest', function (responseJson) {
                    if (responseJson != null) {
                        $("#request").find("tr:gt(0)").remove();
                        var table1 = $("#request");
                        $.each(responseJson, function (key, value) {
                            var rowNew = $("<tr><td></td><td></td><td></td><td></td></tr>");
                            rowNew.children().eq(0).text(value['id']);
                            rowNew.children().eq(1).text(value['firstName']);
                            rowNew.children().eq(2).append("<input id='btnAdd' type='button' value='Accept Friend' onClick='acceptFriend(\"" + value['id'] + "\")'/>");
                            rowNew.children().eq(3).append("<input id='btnRemove' type='button' value='Remove' onClick='removeFriend(\"" + value['id'] + "\")'/>");
                            rowNew.appendTo(table1);
                        });
                    }
                });
            });
        </script>

        <script>
            $(document).ajaxStart(function () {
                //show ajax indicator
                ajaxindicatorstart('loading data.. please wait..');
            }).ajaxStop(function () {
                //hide ajax indicator
                ajaxindicatorstop();
            });
            function ajaxindicatorstart(text)
            {
                if ($('body').find('#resultLoading').attr('id') !== 'resultLoading') {
                    $('body').append('<div id="resultLoading" style="display:none"><div><img src="web/ajax-loader.gif"><div>' + text + '</div></div><div class="bg"></div></div>');
                }

                $('#resultLoading').css({
                    'width': '100%',
                    'height': '100%',
                    'position': 'fixed',
                    'z-index': '10000000',
                    'top': '0',
                    'left': '0',
                    'right': '0',
                    'bottom': '0',
                    'margin': 'auto'
                });
                $('#resultLoading .bg').css({
                    'background': '#000000',
                    'opacity': '0.7',
                    'width': '100%',
                    'height': '100%',
                    'position': 'absolute',
                    'top': '0'
                });
                $('#resultLoading>div:first').css({'width': '250px',
                    'height': '75px',
                    'text-align': 'center', 'position': 'fixed',
                    'top': '0',
                    'left': '0',
                    'right': '0',
                    'bottom': '0',
                    'margin': 'auto',
                    'font-size': '16px',
                    'z-index': '10',
                    'color': '#ffffff'

                });
                $('#resultLoading .bg').height('100%');
                $('#resultLoading').fadeIn(300);
                $('body').css('cursor', 'wait');
            }


            function ajaxindicatorstop()
            {
                $('#resultLoading .bg').height('100%');
                $('#resultLoading').fadeOut(300);
                $('body').css('cursor', 'default');
            }

        </script>
        <jsp:invoke fragment="head"></jsp:invoke>
    </jsp:attribute>
    <jsp:body>
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
                <div id='right' class='col-lg-3' style="padding: 10px; width:350px; background:#fff">
                    <ul class='tabs'>
                        <li style="list-style: none; display: inline;"><a style=" padding:5px 10px;display:inline-block;background:#666;color:#fff;text-decoration:none;" href='#tab1' class='active'>Tab 1</a></li>
                        <li style="list-style: none; display: inline;"><a style=" padding:5px 10px;display:inline-block;background:#666;color:#fff;text-decoration:none;" href='#tab2'>Tab 2</a></li>
                        <li style="list-style: none; display: inline;"><a style=" padding:5px 10px;display:inline-block;background:#666;color:#fff;text-decoration:none;" href='#tab3'>Tab 3</a></li>
                    </ul>
                    <div id='tab1' style="display:block;padding: 10px; width:350px; background:#fff">
                        <div id="suggest"></div>
                    </div>
                    <div id='tab2' style="padding: 10px; width:350px; background:#fff">
                        <div id="request"></div>
                    </div>
                    <div id='tab3' style="padding: 10px; width:350px; background:#fff">
                        <p>And this is the 3rd tab.</p>
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
>>>>>>> branch 'master' of https://github.com/fai-gc0903-sem4-g1/matrimony.git
                                                </div>
                                            </div>
                                        </div>
                                    </div>
<<<<<<< HEAD
                                </div>
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                    <div id="hiddenDIV">
                                        <form id="uploadPhotoForm" action="changeAvatar" method="POST" enctype="multipart/form-data">
                                            <input id="uploadAvatarPhoto" type="file" name="file" accept="image/*" />
                                            <input type="hidden" type="text" name="test" value="love" />
                                        </form>
=======
                                    <div class="panel-footer">
                                        <!--                                                         <div class="input-group"> -->
                                        <input id="txt-chat-msg" type="text" class="form-control input-sm chat_input" placeholder="Write your message here..." />
                                        <!--                                                             <span class="input-group-btn"> -->
                                        <!-- 	                          									 <button class="btn btn-primary btn-sm" id="btn-chat-send-msg">Send</button> -->
                                        <!-- 	                           								</span> -->
                                        <!--                                                         </div> -->
>>>>>>> branch 'master' of https://github.com/fai-gc0903-sem4-g1/matrimony.git
                                    </div>
<<<<<<< HEAD
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
                                        </div>
                                        
                                        
                                        
                                        
                                        
                               
                                        
                                        
                                        
                                        
                                </jsp:body>
=======
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

    </jsp:body>
>>>>>>> branch 'master' of https://github.com/fai-gc0903-sem4-g1/matrimony.git


</t:layout>
