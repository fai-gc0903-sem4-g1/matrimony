<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script>
	$(document).on('click', '.panel-heading span.icon_minim', function(e) {
		var $this = $(this);
		if (!$this.hasClass('panel-collapsed')) {
			$this.parents('.panel').find('.panel-body').slideUp();
			$this.addClass('panel-collapsed');
			$this.removeClass('glyphicon-minus').addClass('glyphicon-plus');
		} else {
			$this.parents('.panel').find('.panel-body').slideDown();
			$this.removeClass('panel-collapsed');
			$this.removeClass('glyphicon-plus').addClass('glyphicon-minus');
		}
	});
	$(document).on(
			'focus',
			'.panel-footer input.chat_input',
			function(e) {
				var $this = $(this);
				if ($('#minim_chat_window').hasClass('panel-collapsed')) {
					$this.parents('.panel').find('.panel-body').slideDown();
					$('#minim_chat_window').removeClass('panel-collapsed');
					$('#minim_chat_window').removeClass('glyphicon-plus')
							.addClass('glyphicon-minus');
				}
			});
	$(document).on('click', '#new_chat', function(e) {
		var size = $(".chat-window:last-child").css("margin-left");
		size_total = parseInt(size) + 400;
		alert(size_total);
		var clone = $("#chat_window_1").clone().appendTo(".container");
		clone.css("margin-left", size_total);
	});
	$(document).on('click', '.icon_close', function(e) {
		//$(this).parent().parent().parent().parent().remove();
		$("#chat_window_1").remove();
	});
</script>

<script>
	$(document).ready(function() {
		var ws = new WebSocket('ws://localhost:8080/matrimony/chatserver');
		ws.onopen = function processOpen() {
			console.log('connected');
		}
		ws.onmessage = function processMessage(message) {
			console.log('data ' + message.data);
		}
		ws.onclose = function processClose() {
			console.log('closed');
		}
		ws.onerror = function processError() {
			console.log('has error');
		}

		$('#btnSendMsg').click(function() {
			sendMessage();
		});

		function sendMessage() {
			var msg = $('#txtMsg');
			var msgObj = {
				userSenderId : '${sessionScope.user.id}',
				userReceiverId : '120329100312',
				content : msg.val(),
				type : 'message',
				time : Date.now()
			};
			ws.send(JSON.stringify(msgObj));
		}
	});
</script>

<style>
body {
	height: 400px;
	position: fixed;
	bottom: 0;
}

.css-avatar, .css-chat-content {
	padding: 0;
}

.css-chat-panel {
	margin-bottom: 0px;
}

.chat-window {
	bottom: 0;
	position: fixed;
	float: right;
	margin-left: 10px;
}

.chat-window>div>.panel {
	border-radius: 5px 5px 0 0;
}

.icon_minim {
	padding: 2px 10px;
}

.msg_container_base {
	background: #e5e5e5;
	margin: 0;
	padding: 0 10px 10px;
	max-height: 300px;
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

img {
	display: block;
	width: 100%;
}

.css-avatar {
	position: relative;
}

.base_receive>.css-avatar:after {
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

.base_sent {
	justify-content: flex-end;
	align-items: flex-end;
}

.base_sent>.css-avatar:after {
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
	//
	not
	quite
	perfect
	but
	close
}

.msg_sent>time {
	float: right;
}

.msg_container_base::-webkit-scrollbar-track {
	-webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
	background-color: #F5F5F5;
}

.msg_container_base::-webkit-scrollbar {
	width: 12px;
	background-color: #F5F5F5;
}

.msg_container_base::-webkit-scrollbar-thumb {
	-webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, .3);
	background-color: #555;
}

.btn-group.dropup {
	position: fixed;
	left: 0px;
	bottom: 0;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row chat-window col-xs-5 col-md-3" id="chat_window_1" style="margin-left: 10px;">
			<div class="col-xs-12 col-md-12">
				<div class="panel panel-default css-chat-panel">
					<div class="panel-heading top-bar css-heading-chat">
						<div class="col-md-8 col-xs-8">
							<h3 class="panel-title">
								<span class="glyphicon glyphicon-comment"></span> Chat - Miguel
							</h3>
						</div>
						<div class="col-md-4 col-xs-4" style="text-align: right;">
							<a href="#"><span id="minim_chat_window"
								class="glyphicon glyphicon-minus icon_minim"></span></a> <a href="#"><span
								class="glyphicon glyphicon-remove icon_close"
								data-id="chat_window_1"></span></a>
						</div>
					</div>
					<div class="panel-body msg_container_base">
						<div class="row msg_container base_sent">
							<div class="col-md-10 col-xs-10 css-chat-content">
								<div class="messages msg_sent">
									<p>that mongodb thing looks good, huh? tiny master db, and
										huge document store</p>
									<time datetime="2009-11-13T20:00">Timothy • 51 min</time>
								</div>
							</div>
							<div class="col-md-2 col-xs-2 css-avatar">
								<img
									src="http://www.bitrebels.com/wp-content/uploads/2011/02/Original-Facebook-Geek-Profile-Avatar-1.jpg"
									class=" img-responsive ">
							</div>
						</div>
						<div class="row msg_container base_receive">
							<div class="col-md-2 col-xs-2 css-avatar">
								<img
									src="http://www.bitrebels.com/wp-content/uploads/2011/02/Original-Facebook-Geek-Profile-Avatar-1.jpg"
									class=" img-responsive ">
							</div>
							<div class="col-md-10 col-xs-10 css-chat-content">
								<div class="messages msg_receive">
									<p>that mongodb thing looks good, huh? tiny master db, and
										huge document store</p>
									<time datetime="2009-11-13T20:00">Timothy • 51 min</time>
								</div>
							</div>
						</div>
					</div>
					<div class="panel-footer">
						<div class="input-group">
							<input id="txtMsg" type="text"
								class="form-control input-sm chat_input"
								placeholder="Write your message here..." /> <span
								class="input-group-btn">
								<button class="btn btn-primary btn-sm" id="btnSendMsg">Send</button>
							</span>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="btn-group dropup">
			<button type="button" class="btn btn-default dropdown-toggle"
				data-toggle="dropdown">
				<span class="glyphicon glyphicon-cog"></span> <span class="sr-only">Toggle
					Dropdown</span>
			</button>
			<ul class="dropdown-menu" role="menu">
				<li><a href="#" id="new_chat"><span
						class="glyphicon glyphicon-plus"></span> Novo</a></li>
				<li><a href="#"><span class="glyphicon glyphicon-list"></span>
						Ver outras</a></li>
				<li><a href="#"><span class="glyphicon glyphicon-remove"></span>
						Fechar Tudo</a></li>
				<li class="divider"></li>
				<li><a href="#"><span class="glyphicon glyphicon-eye-close"></span>
						Invisivel</a></li>
			</ul>
		</div>
	</div>
</body>
</html>