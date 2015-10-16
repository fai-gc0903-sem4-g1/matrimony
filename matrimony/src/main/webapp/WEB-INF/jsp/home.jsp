<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:useBean id="userDAO" class="com.matrimony.database.UserDAO" />
<jsp:useBean id="friendDAO" class="com.matrimony.database.FriendDAO" />
<jsp:useBean id="matrimony" class="com.matrimony.database.Matrimony" />
<jsp:useBean id="jstl" class="com.matrimony.model.JSLTFunctionUtil" />
<c:set var="peopleSugguestMap" value="${matrimony.suggest(sessionScope.user) }" />
<!-- include you jquery ui theme -->


<t:homeLayout>
    <jsp:attribute name="head">
        <title>Trang chủ</title>
        <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/themes/smoothness/jquery-ui.key.css" />
        <script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.key.min.js"></script>
        
        <link rel="stylesheet" href="/matrimony/resources/fancybox/jquery.fancybox.css" />
        <script src="/matrimony/resources/fancybox/jquery.fancybox.js"></script>
        <style>
        .st-name-suggester{
            font-weight: 600;
    		text-decoration: none;
    	}
        </style>
        <script>
        	$(document).ready(function(){
        		$(".st-avatar-suggester-link").fancybox({
        			openEffect	: 'elastic',
        			closeEffect	: 'elastic',
        		});
        		
        		$(document).on('click', '.btn-make-friend',function(e){
        			var btn=$(this);
        			$.ajax({
        				url: 'makeFriend',
                        method: 'POST',
                        data: {userBeInviteId:btn.data('suggest-user-id')},
                        async: false,
                        success: function(obj){
        					console.log('dasend');
        					console.log(obj);
        					if(obj=='SUCCESS'){
        						console.log('db saved');
        					}else{
        						alert('Không thể kết bạn');
        					}
        				},
        				error: function(){
        					alert('Kiểm tra lại đường truyền');
        				}
        			});
        		});
        	});
        </script>
    </jsp:attribute>
    <jsp:attribute name="middle">
    	<div>
    		<h4>Những người có thể bạn phù hợp với bạn</h4>
    	</div>
    
        <div id='tblPeople'>
        <div id="message" hidden="true">ARE YOU SURE??</div>
            <c:forEach var="i" items="${peopleSugguestMap}" varStatus="myIndex">
                <div id='person-panel' class='row col-sm-12' data-user-id='${i.key.id}'>
                    <div class='col-sm-3'>
                        <a class='st-avatar-suggester-link' href="/matrimony/resources/profile/avatar/${i.key.avatarPhoto}"><img id='person-avatar' alt='person-avatar' src='/matrimony/resources/profile/avatar/${i.key.avatarPhoto }' style='width: 100%;min-height: 130px;max-height: 200px;' /></a>
                        <br />
                        <a class='st-name-suggester' target="_blank" href='${i.key.username }' id='person-name'>${i.key.name }</a>
                    </div>
                    <div id='person-left' class='col-sm-4'>
                        <span id='person-age'><span id="label-basic">Tuổi </span> ${jstl.yearUntilToDay(i.key.birthday) } tuổi</span>
                        <br />
                        <span id='person-gender'><span id="label-basic">Giới tính </span>${i.key.gender =='FEMALE' ?'Nữ':'Nam'}</span>
                        <br />
                        <br />
                        <span><input id='btn-chat-inbox' type='button' class="btn btn-info" value='Gửi tin nhắn'/></span>
                        <span id='person-btna'>1 friend</span>
                        
                    </div>
                    <div id='person-right' class='col-sm-4'>
                        <span id='person-name'><span id="label-basic">Đất nước </span>${i.key.countryside }</span>
                        <br />
                        <span id='person-city'><span id="label-basic">Tỉnh/thành phố </span>${i.key.hometown }</span>
                        <br />
                        <span id='person-status'><span id="label-basic">Hôn nhân </span> ${i.key.maritalStatus }</span>
                        <br />
                        <br />
<%--                         <span id='person-btna'><input type="button" class="btnAdd btn btn-primary btn-make-friend" onclick="addFriend('${i.key.id}', '${myIndex.index}')" value="Add Friend"/></span> --%>
<span id='person-btna'><input type="button" class="btnAdd btn btn-primary btn-make-friend" data-suggest-user-id="${i.key.id }" value="Make Friend"/></span>
                        <span id='person-btna'><input type="button" class='showDialog' onclick="show('${i.key.id}', '${myIndex.index}')" value="Cancel" hidden="true"/></span>
                        <br/>
                    </div>
                </div>
            </c:forEach>
            <div class='row col-sm-12' style='text-align: center;'>
                <h4>Trống</h4>
            </div>
        </div>
    </jsp:attribute>
</t:homeLayout>
