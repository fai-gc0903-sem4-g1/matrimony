<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:useBean id="userDAO" class="com.matrimony.database.UserDAO" />
<jsp:useBean id="FriendBean" class="com.matrimony.database.FriendDAO" />
<jsp:useBean id="matrimony" class="com.matrimony.database.Matrimony" />
<jsp:useBean id="jstl" class="com.matrimony.model.JSLTFunctionUtil" />
<c:set var="peopleSuggestList" value="${matrimony.getSuggestUsers(sessionScope.user) }" />
<!-- include you jquery ui theme -->
<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/themes/smoothness/jquery-ui.css" />
<t:homeLayout>
    <jsp:attribute name="head">
        <title>Payment confirm</title>
    </jsp:attribute>
    <jsp:attribute name="middle">
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
                        <span id='person-btna'><input type="button" class="btnAdd" onclick="addFriend('${i.id}')" value="Add Friend"/></span>
                        <span id='person-btna'><input type="button" class='showDialog' onclick="show('${i.id}')" value="Cancel" hidden="true"/></span>
                        </br>
<!--                        <div id="basicModal" style="display:none;"/>-->
                        <script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
                        <script>
                            // show the dialog on click of a button
                            function show(u){

                                /* select the div you want to be a dialog, in our case it is 'basicModal'
                                 you can add parameters such as width, height, title, etc. */
                                $("#person-btna").dialog({
                                    modal: true,
                                    title: "Are you sure?",
                                    buttons: {
                                        "YES": function () {
                                            removeRequest(u);
                                            $(this).dialog("close");
                                        },
                                        "NO": function () {
                                            $(this).dialog("close");
                                        }
                                    }
                                });

                            };
                        </script>
                    </div>
                </div>
            </c:forEach>
            <div class='row col-sm-12' style='text-align: center;'>
                <h4>Trống</h4>
            </div>
        </div>
    </jsp:attribute>
</t:homeLayout>