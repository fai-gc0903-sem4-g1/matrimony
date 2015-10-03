/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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


    $.get('allFriend', function (responseJson) {
        if (responseJson != null) {
            $("#friend").find("tr:gt(0)").remove();
            var table1 = $("#friend");
            $.each(responseJson, function (key, value) {
                var rowNew = $("<tr><td></td><td></td><td></td><td></td></tr>");
                rowNew.children().eq(0).text(value['id']);
                rowNew.children().eq(1).text(value['firstName']);
                rowNew.children().eq(2).append("<input id='btnAdd' type='button' value='Friend'/>");
                rowNew.children().eq(3).append("<input id='btnRemove' type='button' value='Remove' onClick='removeFriend(\"" + value['id'] + "\")'/>");
                rowNew.appendTo(table1);
            });
        }
    });
});
function addFriend(u)
{
    $.post('addFriend',
            {user: u},
    function () {
        $("#btnAdd").prop('value', 'Sended Request');
        $('#btnAdd').prop('disabled', true);
        alert("insert success");

    })
            .fail(function () { //on failure
                alert("Insertion failed.");
            });

}
function removeFriend(u)
{
    $.post('removeFriend',
            {user: u},
    function () {
        alert("Remove success");

    })
            .fail(function () { //on failure
                alert("Remove failed.");
            });

}
function acceptFriend(u)
{
    $.post('acceptFriend',
            {user: u},
    function () {
        $("#btnAdd").prop('value', 'Friend');
        $('#btnAdd').prop('disabled', true);
        alert("accpet success");

    })
            .fail(function () { //on failure
                alert("accept failed.");
            });

}
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


//            $(document).ready(function () {
//                $("#spinner").bind("ajaxSend", function () {
//                    $(this).show();
//                }).bind("ajaxStop", function () {
//                    $(this).hide();
//                }).bind("ajaxError", function () {
//            $(this).hide();
//                });
//
//            });


