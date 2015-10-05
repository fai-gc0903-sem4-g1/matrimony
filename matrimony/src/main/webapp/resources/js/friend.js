/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {

    $.get('allInvite', function (responseJson) {
        if (responseJson !== null) {
            var table = $("#invite");
            $.each(responseJson, function (key, value) {
                var rowNew = $("<div></div><div></div><div></div><div></div>");
                rowNew.eq(0).text(value['id']);
                rowNew.eq(1).text(value['firstName']);
                rowNew.eq(2).append("<input class='btnAccept' type='button' value='Accept Friend' onClick='acceptFriend(\"" + value['id'] + "\")'/>");
                rowNew.eq(3).append("<input id='btnRemoveInvite' type='button' value='Denied' onClick='removeInvite(\"" + value['id'] + "\")'/>");
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
                rowNew.eq(1).text(key);
                rowNew.eq(2).append("<input class='btnRemoveRequest' type='button' value='Remove Request' onClick='removeRequest(\"" + value['id'] + "\"," + key + ")'/>");
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
                rowNew.eq(2).append("<input type='button' value='Friend' disabled='true'/>");
                rowNew.eq(3).append("<input id='btnRemoveFriend' type='button' value='RemoveRequest' onClick='removeFriend(\"" + value['id'] + "\")'/>");
                rowNew.appendTo(table);
            });
        }
    });
});
function addFriend(u, k)
{
    $.post('addFriend',
            {user: u, },
            function () {
                $(".btnAdd").eq(k).prop('value', 'Sended Request');
                $(".btnAdd").eq(k).prop('disabled', true);
                $(".showDialog").eq(k).prop('hidden', false);

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

        $(".btnAdd").prop('value', 'Add Friend');
        $(".btnAdd").prop('disabled', false);
        $('.btnRemove').prop('hidden', true);
        alert("Remove success");

    })
            .fail(function () { //on failure
                alert("Remove failed.");
            });

}
function removeRequest(u, k)
{
    $.post('removeFriend',
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
}
function acceptFriend(u)
{
    $.post('acceptFriend',
            {user: u},
    function () {
        $(".btnAccept").prop('value', 'Friend');
        $(".btnAccept").prop('disabled', true);
        $('.btnRemoveFriend').prop('value', 'Remove Friend');
        alert("accpet success");

    })
            .fail(function () { //on failure
                alert("accept failed.");
            });

}
function showDialog(u) {

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

}
;
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
// show the dialog on click of a button
//function showDialog(){
// 
//    /* select the div you want to be a dialog, in our case it is 'basicModal'
//    you can add parameters such as width, height, title, etc. */
//    $("#basicModal").dialog(
////        modal: true,
////        title: "Are you sure?",
////        buttons: {
////            "YES": function() {
////                window.open("http://codeofaninja.com/", '_blank');
////            },
////            "NO": function() {
////                $( this ).dialog( "close" );
////            }
////        }
//    );
//     
//};

