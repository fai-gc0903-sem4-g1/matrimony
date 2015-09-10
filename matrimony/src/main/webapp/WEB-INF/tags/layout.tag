<%@tag language="java" pageEncoding="UTF-8"
	description="Overall Page template"%>

<jsp:include page="/WEB-INF/bundle/jstl.jsp" />
<jsp:include page="/WEB-INF/bundle/bootstrap.jsp" />
<html>
<head>
<meta charset="utf-8" />
<title>This's layout page</title>
</head>
<body>
	<nav id="navbar" class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand"
					href="/ShoppingAssignment/ProductHandler?q=pbcate">Shopping NOW</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-left">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Loại sản phẩm<span class="caret"></span>
					</a>
						<ul class="dropdown-menu">
							<li><a href="/ShoppingAssignment/ProductHandler?q=pbcate">Tất
									cả sản phẩm</a></li>
							<c:forEach var="i" items="${pp.getAllCategory()}">
								<li><a
									href="/ShoppingAssignment/ProductHandler?q=pbcate&category=${i }">${i }</a></li>
							</c:forEach>
						</ul></li>
					<c:choose>
						<c:when test="${sessionScope.currentUser.roleName=='admin' }">
							<li><a href="/ShoppingAssignment/admin/manage-account.jsp">Quản
									lý người dùng</a></li>
							<li><a href="/ShoppingAssignment/admin/manage-product.jsp">Quản
									lý sản phẩm</a></li>
							<li><a href="/ShoppingAssignment/admin/manage-order.jsp">Quản
									lý Đơn đặt hàng</a></li>
						</c:when>
					</c:choose>


				</ul>

				<ul class="nav navbar-nav navbar-right">

					<c:choose>
						<c:when test="${empty sessionScope.currentUser}">
							<form class="navbar-form col-sm-4"
								action="/ShoppingAssignment/AccountHandler?q=login"
								method="post">

								<input class="form-control" name="username"
									value="${cookie.cUser.value}" type="text"
									placeholder="Tên người dùng" /> <input class="form-control"
									value="${cookie.cPass.value }" type="password" name="password"
									placeholder="Mật khẩu" />
								<div class="checkbox">
									<label> <input name="rememberMe" type="checkbox" />
										Duy trì đăng nhập 
								</div>
								<input class="btn btn-success" type="submit" value="Đăng nhập" />
								<a class="btn btn-info"
									href="/ShoppingAssignment/account/register.jsp">Đăng ký</a>
							</form>
						</c:when>
						<c:otherwise>
							<li><a href="/ShoppingAssignment/account/account-detail.jsp"><span
									class="glyphicon glyphicon-user"></span>
									${sessionScope.currentUser.email}</a></li>
							<li><a href="/ShoppingAssignment/account/account-detail.jsp">Thông
									tin và đơn hàng</a></li>
							<li><a href="/ShoppingAssignment/AccountHandler?q=logout">Thoát</a></li>
						</c:otherwise>
					</c:choose>
					<li><a href="/ShoppingAssignment/view-cart.jsp"
						data-toggle="tooltip" data-placement="top"
						title="Đến giỏ hàng của bạn"><span
							class="glyphicon glyphicon-shopping-cart"></span> Giỏ hàng <span
							class="badge" id="cartAmount">${empty sessionScope.cart ? '0': sessionScope.cart.size()}</span></a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->

		</div>
		<!-- /.container-fluid -->
	</nav>
	<div id="body">
		<jsp:doBody />
	</div>
</body>
</html>



