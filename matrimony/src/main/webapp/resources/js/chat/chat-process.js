//SETUP CLICK EVENT
$(document).on('click', '#minim_chat_window', function(e) {
	showAndHideChat($(this));
});

$(document).on('click', '#close_chat_window', function(e) {
	closeChatWindow($(this));
});

$(document).on('click', '#btn-chat-inbox', function(e) {
	var chatWindow=createChatWindow($(this).parents('#person-panel').data('user-id'));
	chatWindow.find('#txt-chat-msg').focus();
});

// $(document).on('click', "#btn-chat-send-msg", function(e) {
// sendMessage($(this).parents('#chat-window'));
// });

$(document).on('keypress', '#txt-chat-msg', function(e) {
	if (e.keyCode == 13) {
		sendMessage($(this).parents('#chat-window'));
		$(this).val('');
	}
});
// CHAT PROCESS
var ws = new WebSocket('ws://localhost/matrimony/chatserver');
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
	var senderId = sessionUserId;
	var msgObj = new Message(senderId, null, null, null);
	ws.send(JSON.stringify(msgObj));
	console.log('ws connected');
};
//receive message
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
	var str=emoticonDecode(msgObj.content);
	var receiverClone = $('#base-receive-message').clone().appendTo(chatWindow.find('.msg-container-base'));
	receiverClone.find('p').html(str);
	receiverClone.css('display', 'block');
};

function processClose() {
};

function processError() {
	alert('websocket error');
};

function processOnpen() {
};

//send message
function sendMessage(chatWindow) {
	var receiverId = chatWindow.data('user-id');
	var senderId = sessionUserId;
	var content = chatWindow.find('#txt-chat-msg').val();
	var msgObj = new Message(senderId, receiverId, 'text-chat', content, Date.now());
	ws.send(JSON.stringify(msgObj));
	var str=emoticonDecode(content);
	
	var sentClone = $('#base-sent-message').clone().appendTo(chatWindow.find('.msg-container-base'));
	sentClone.find('p').html(str);
	sentClone.css('display', 'block');

};

function emoticonDecode(str){
	var content=str;
	for (var kbd in emoticons) {
//		var reg=new RegExp(+"\\"+kbd+"\\",'g');
//		console.log(reg);
		content = content.replace(kbd,'<img src="http://localhost/matrimony/resources/emoticons/'+ emoticons[kbd] + '.gif" />')
	}
	return content;
}

// INITIALIZE CHAT GUI
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
		var uName;
		$.ajax({
			method:'POST',
			url:'/matrimony/sortUserProfile',
			data:{id:userId},
			success:function(obj){
				uName=obj.name;
			},
			error:function(){
				alert('server down');
			}
		});
		clone.data('user-id', userId);
		clone.find('#name').html(uName);
		clone.find('img').attr('src', obj.avatar);
		clone.css("margin-right", margin);
		clone.css('display', 'block');
		return clone;
	}
	
	// return null;
}

// $(document).on('focus', '.panel-footer input.chat_input', function(e) {
// var $this = $(this);
// if ($('#minim_chat_window').hasClass('panel-collapsed')) {
// $this.parents('.panel').find('.panel-body').slideDown();
// $('#minim_chat_window').removeClass('panel-collapsed');
// $('#minim_chat_window').removeClass('glyphicon-plus')
// .addClass('glyphicon-minus');
// }
// });
