/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {

    $.get('allInvite', function (responseJson) {
        if (responseJson !== null) {
            $("#invite").find("div:gt(0)").remove();
            var table = $("#invite");
            $.each(responseJson, function (key, value) {
                var rowNew = $("<div></div><div></div><div></div><div></div>");
                rowNew.eq(0).text(value['id']);
                rowNew.eq(1).text(value['firstName']);
                rowNew.eq(2).append("<input class='btnAccept' type='button' value='Accept Friend' onClick='acceptFriend(\"" + value['id'] + "\"," + key + ")'/>");
                rowNew.eq(3).append("<input id='Denied' type='button' value='Denied' onClick='removeInvite(\"" + value['id'] + "\")'/>");
                rowNew.appendTo(table);
            });
        }
    });


    $.get('allRequest', function (responseJson) {
        if (responseJson !== null) {
            $("#request").find("div:gt(0)").remove();
            var table = $("#request");
            $.each(responseJson, function (key, value) {
                var rowNew = $("<div></div><div></div><div></div><div></div>");
                rowNew.eq(0).text(value['id']);
                rowNew.eq(1).text(value['firstName']);
                rowNew.eq(2).append("<input class='btnRemoveRequest' type='button' value='Remove Request' onClick='showRequestTab(\"" + value['id'] + "\"," + key + ")'/>");
                rowNew.appendTo(table);
            });
        }
    });


    $.get('allFriend', function (responseJson) {
        if (responseJson !== null) {
            $("#friend").find("div:gt(0)").remove();
            var table = $("#friend");
            $.each(responseJson, function (key, value) {
                var rowNew = $("<div></div><div></div><div></div><div></div>");
                rowNew.eq(0).text(value['id']);
                rowNew.eq(1).text(value['firstName']);
                rowNew.eq(2).append("<input class='btnFriend' type='button' value='Friend' disabled='true'/>");
                rowNew.eq(3).append("<input class='btnRemoveFriend' type='button' value='Remove Friend' onClick='showFriendTab(\"" + value['id'] + "\"," + key + ")'/>");
                rowNew.appendTo(table);
            });
        }
    });
});


function removeFriendTab(u,k)
{
    $.post('remove',
            {user: u},
    function () {
        $(".btnRemoveFriend").eq(k).prop('value', 'Removed');
        $(".btnRemoveFriend").eq(k).prop('disabled', true);
        $(".btnFriend").eq(k).prop('hidden ', true);
        alert("Remove success");

    })
            .fail(function () { //on failure
                alert("Remove failed.");
            });

}

function showFriendTab(u, k) {
    $("#message").dialog({
        modal: true,
        title: "Are you sure?",
        buttons: {
            "YES": function () {
                removeFriendTab(u, k);
                $(this).dialog("close");
            },
            "NO": function () {
                $(this).dialog("close");
            }
        }
    });
};

function removeRequestTab(u, k)
{
    $.post('remove',
            {user: u}).
            done(function (data) {
                if (data === "success") {
                    alert("Remove success");
                    $(".btnRemoveRequest").eq(k).prop('value', 'Removed');
                    $(".btnRemoveRequest").eq(k).prop('disabled', true);
                } else if (data === "null") {
                    alert("Remove fail");
                }
            }).fail(function () {
        alert("Error");
    });
};

function showRequestTab(u, k) {
    $("#message").dialog({
        modal: true,
        title: "Are you sure?",
        buttons: {
            "YES": function () {
                removeRequestTab(u, k);
                $(this).dialog("close");
            },
            "NO": function () {
                $(this).dialog("close");
            }
        }
    });
};

function acceptFriend(u, k)
{
    $.post('acceptFriend',
            {user: u}).
            done(function (data) {
                if (data === "success") {
                    $(".btnAccept").eq(k).prop('value', 'Friend');
                    $(".btnAccept").eq(k).prop('disabled', true);
                    $('.btnRemoveFriend').prop('value', 'Remove Friend');
                }
            }).fail(function () {
        alert("Error");
    });
};

function removeRequest(u, k)
{
    $.post('remove',
            {user: u}).
            done(function (data) {
                if (data === "success") {
                    alert("Remove success");
                    $(".showDialog").eq(k).prop('hidden', true);
                    $(".btnAdd").eq(k).prop('value', 'Add Friend');
                    $(".btnAdd").eq(k).prop('disabled', false);
                } else if (data === "null") {
                    alert("Remove fail");
                }
            }).fail(function () {
        alert("Error");
    });
};


function addFriend(u, k)
{
    $.post('addFriend',
            {user: u},
    function () {
        $(".btnAdd").eq(k).prop('value', 'Sended Request');
        $(".btnAdd").eq(k).prop('disabled', true);
        $(".showDialog").eq(k).prop('hidden', false);

    })
            .fail(function () { //on failure
                alert("Insertion failed.");
            });
};

$(document).ready(function () {
    $('ul.tabs').tabs;
    $('ul.tabs').each(function () {
        var $active, $content, $links = $(this).find('a');
        $active = $($links.filter('[href="' + location.hash + '"]')[0] || $links[0]);
        $active.addClass('active');
        $content = $($active[0].hash);
        $links.not($active).each(function () {
            $(this.hash).hide();
        });
        $(this).on('click', 'a', function (e) {
            $active.removeClass('active');
            $content.hide();
            $active = $(this);
            $content = $(this.hash);
            $active.addClass('active');
            $content.show();
            e.preventDefault();
        });
    });
});

// show the dialog on click of a button
function show(u, k) {
    $("#message").dialog({
        modal: true,
        title: "Are you sure?",
        buttons: {
            "YES": function () {
                removeRequest(u, k);
                $(this).dialog("close");
            },
            "NO": function () {
                $(this).dialog("close");
            }
        }
    });
};

