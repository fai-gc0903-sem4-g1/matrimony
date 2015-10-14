<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
                <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
                    <jsp:useBean id="userDAO" class="com.matrimony.database.UserDAO" />
                    <c:set var="prefer" value="${sessionScope.user.userPreferences.iterator().next()}"/>
                    
                    <t:homeLayout>
                        <jsp:attribute name="head">
                            <meta charset="utf-8" />
                            <title>Payment</title>
                            <style>
                                /*  bhoechie tab */
                                
                                div.bhoechie-tab-container {
                                    z-index: 10;
                                    background-color: #ffffff;
                                    padding: 0 !important;
                                    border-radius: 4px;
                                    -moz-border-radius: 4px;
                                    border: 1px solid #ddd;
                                    margin-top: 20px;
                                    margin-left: 50px;
                                    -webkit-box-shadow: 0 6px 12px rgba(0, 0, 0, .175);
                                    box-shadow: 0 6px 12px rgba(0, 0, 0, .175);
                                    -moz-box-shadow: 0 6px 12px rgba(0, 0, 0, .175);
                                    background-clip: padding-box;
                                    opacity: 0.97;
                                    filter: alpha(opacity=97);
                                }
                                div.bhoechie-tab-menu {
                                    padding-right: 0;
                                    padding-left: 0;
                                    padding-bottom: 0;
                                }
                                div.bhoechie-tab-menu div.list-group {
                                    margin-bottom: 0;
                                }
                                div.bhoechie-tab-menu div.list-group>a {
                                    margin-bottom: 0;
                                }
                                div.bhoechie-tab-menu div.list-group>a .glyphicon,
                                div.bhoechie-tab-menu div.list-group>a .fa {
                                    color: #59C4C5;
                                }
                                div.bhoechie-tab-menu div.list-group>a:first-child {
                                    border-top-right-radius: 0;
                                    -moz-border-top-right-radius: 0;
                                }
                                div.bhoechie-tab-menu div.list-group>a:last-child {
                                    border-bottom-right-radius: 0;
                                    -moz-border-bottom-right-radius: 0;
                                }
                                div.bhoechie-tab-menu div.list-group>a.active,
                                div.bhoechie-tab-menu div.list-group>a.active .glyphicon,
                                div.bhoechie-tab-menu div.list-group>a.active .fa {
                                    background-color: #59C4C5;
                                    background-image: #5A55A3;
                                    color: #ffffff;
                                }
                                div.bhoechie-tab-menu div.list-group>a.active:after {
                                    content: '';
                                    position: absolute;
                                    left: 100%;
                                    top: 50%;
                                    margin-top: -13px;
                                    border-left: 0;
                                    border-bottom: 13px solid transparent;
                                    border-top: 13px solid transparent;
                                    border-left: 10px solid #59C4C5;
                                }
                                div.bhoechie-tab-content {
                                    background-color: #ffffff;
                                    /* border: 1px solid #eeeeee; */
                                    
                                    padding-left: 20px;
                                    padding-top: 10px;
                                }
                                div.bhoechie-tab div.bhoechie-tab-content:not(.active) {
                                    display: none;
                                }
                                .list-group-item.active,
                                .list-group-item.active:focus,
                                .list-group-item.active:hover {
                                    border-color: #59C4C5;
                                }
                                /* end bhoechie tab */
                                
                                .form-horizontal .control-label {
                                    text-align: left;
                                }
                                .popover {
                                    background-color: #CB2027;
                                    color: #ffffff;
                                }
                                .popover.left .arrow:after {
                                    border-left-color: #CB2027;
                                }
                                .popover.bottom .arrow:after {
                                    border-bottom-color: #CB2027;
                                }
                                .st-reg-invalid {
                                    font-size: 22px;
                                    color: #CB2027;
                                    margin-right: 12px;
                                }
                                .glyphicon-exclamation-sign {
                                    color: #CB2027;
                                    display: none;
                                }
                                .st-caption-tab {
                                    margin-top: 0;
                                }
                            </style>
                            <script>
                                $(document).on('click', '.col-lg-2 btn-submit-update-basic-form', function(e) {
                                    $('#form-update-basic-info').submit();
                                })
                                $.noConflict();
                                $(document).ready(function() {
                                    $("div.bhoechie-tab-menu>div.list-group>a").click(function(e) {
                                        e.preventDefault();
                                        $(this).siblings('a.active').removeClass("active");
                                        $(this).addClass("active");
                                        var index = $(this).index();
                                        $("div.bhoechie-tab>div.bhoechie-tab-content").removeClass("active");
                                        $("div.bhoechie-tab>div.bhoechie-tab-content").eq(index).addClass("active");
                                    });

                                    //update basic
                                    $("#form-update-basic-info").submit(function(e) {
                                        e.preventDefault();
                                        var form = $(this);
                                        $.ajax({
                                            url: form.attr('action'),
                                            method: form.attr('method'),
                                            data: form.serialize(),
                                            async: false,
                                            success: function(obj) {
                                                if (obj.wellForm == true) {
                                                    alert('Da thay doi');
                                                } else {
                                                    for (var prop in obj) {
                                                        console.log(prop + ' ' + obj[prop] + '\n');
                                                        var t = $('#' + prop);
                                                        t.data('content', obj[prop]);
                                                        t.parents('.has-feedback').find('i').css('display', 'block');
                                                    }
                                                    $('.form-control').popover({
                                                        placement: 'left'
                                                    });
                                                }

                                            },
                                            error: function() {

                                            }
                                        });
                                    });

                                    $('#txtPassword').click(function() {
                                        $(this).val('');
                                    });

                                    $('#txtPassword').click(function() {
                                        $(this).val('');
                                    });
                                    $('#txtPassword').focusout(function() {
                                        if ($(this).val() == '') {
                                            $(this).val('12345678');
                                        }
                                    });

                                    // 									$('.btn-edit-basic').click(function(e){
                                    // 										var btn=$(this);
                                    // 										var group=btn.parents('.has-feedback');
                                    // 										var input=group.find('.form-element');
                                    // 										if(input.prop('')==true){
                                    // 											input.prop('', false);
                                    // 											btn.val("Thay đổi")
                                    // 										}else{
                                    // 											input.prop('', true);
                                    // 											btn.val('Sửa');
                                    // 										}
                                    // 									});


                                });
                            </script>
                        </jsp:attribute>
                        <jsp:attribute name="middle">
                            <div class='col-lg-12 row' style='padding: 0px;'>
                                <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 bhoechie-tab-menu">
                                    <div class="list-group">
                                        <a href="#" class="list-group-item active text-center">Cơ bản</a>
                                        <a href="#" class="list-group-item text-center">Ngoại hình</a>
                                        <a href="#" class="list-group-item text-center">Sở thích</a>
                                        <a href="#" class="list-group-item text-center">Học vấn</a>
                                        <a href="#" class="list-group-item text-center">Nghề nghiệp</a>
                                        <a href="#" class="list-group-item text-center">Người bạn thích</a>
                                        <a href="#" class="list-group-item text-center">Khác</a>
                                    </div>
                                </div>
                                <div class="col-lg-9 col-md-9 col-sm-9 col-xs-9 bhoechie-tab">
                                    <!-- flight section -->
                                    <div class="bhoechie-tab-content active">
                                        <h3 class="st-caption-tab">Cập nhập thông tin cá nhân</h3>
                                        <hr />
                                        <form id='form-update-basic-info' action="updateBasicProfile" method="post" class="form-horizontal">
                                            <!-- name -->
                                            <div class="form-group">
                                                <label class="col-lg-3 control-label">Họ</label>
                                                <div class="has-feedback col-lg-7">
                                                    <input type="text" class="form-control" id='txtLastName' data-toggle="popover" data-st='false' name='lastName' value='${sessionScope.user.lastName }' />
                                                    <i class="glyphicon glyphicon-exclamation-sign form-control-feedback"></i>
                                                    <!-- 											        <span class="input-group-btn"> -->
                                                    <!-- 												        <input type='button' class="btn btn-primary btn-edit-basic" type="button" value='Sửa'/> -->
                                                    <!-- 											        </span> -->
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="col-lg-3 control-label">Tên đệm</label>
                                                <div class="has-feedback col-lg-7">
                                                    <input type="text" id='txtMiddleName' class="form-control" data-toggle="popover" data-st='false' name='middleName' placeholder="Không bắt buộc" value='${sessionScope.user.middleName }' />
                                                    <i class="glyphicon glyphicon-exclamation-sign form-control-feedback"></i>
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="col-lg-3 control-label">Tên</label>
                                                <div class="has-feedback col-lg-7">
                                                    <input type="text" id='txtFirstName' class="form-control" data-toggle="popover" data-st='false' name='firstName' value='${sessionScope.user.firstName }'>
                                                    <i class="glyphicon glyphicon-exclamation-sign form-control-feedback"></i>
                                                </div>
                                                <input type='submit' class='btn btn-primary col-lg-2 btn-submit-update-basic-form' value='Thay đổi' />
                                            </div>
                                            <hr />
                                            <!-- email -->
                                            <div class="form-group">
                                                <label class="col-lg-3 control-label">Email</label>
                                                <div class="has-feedback col-lg-7">
                                                    <input type="email" id="txtEmail" class="form-control" data-toggle="popover" data-st='false' name='email' value='${sessionScope.user.email}'>
                                                    <i class="glyphicon glyphicon-exclamation-sign form-control-feedback"></i>
                                                </div>
                                                <input type='submit' class='btn btn-primary col-lg-2 btn-submit-update-basic-form' value='Thay đổi' />
                                            </div>
                                            <hr/>
                                            <!-- phone -->
                                            <div class="form-group">
                                                <label class="col-lg-3 control-label">Điện thoại</label>
                                                <div class="has-feedback col-lg-7">
                                                    <input type="text" id="txtPhone" class="form-control" data-toggle="popover" data-st='false' name='contactNumber' value='${sessionScope.user.contactNumber}'>
                                                    <i class="glyphicon glyphicon-exclamation-sign form-control-feedback"></i>
                                                </div>
                                                <input type='submit' class='btn btn-primary col-lg-2 btn-submit-update-basic-form' value='Thay đổi' />
                                            </div>
                                            <hr />
                                            <!-- nickname -->
                                            <div class="form-group">
                                                <label class="col-lg-3 control-label">Tên người dùng</label>
                                                <div class="has-feedback col-lg-7">
                                                    <input type="text" id='txtUsername' class="form-control" data-toggle="popover" data-st='false' name='username' placeholder="Tên người dùng" value='${sessionScope.user.username }'>
                                                    <i class="glyphicon glyphicon-exclamation-sign form-control-feedback"></i>
                                                </div>
                                                <input type='submit' class='btn btn-primary col-lg-2 btn-submit-update-basic-form' value='Thay đổi' />
                                            </div>
                                            <hr />
                                            <!-- password -->
                                            <div class="form-group">
                                                <label class="col-lg-3 control-label">Mật khẩu</label>
                                                <div class="has-feedback col-lg-7">
                                                    <input type="password" id='txtPassword' name='password' class="form-control" data-toggle="popover" value='12345678' data-st='false'>
                                                    <i class="glyphicon glyphicon-exclamation-sign form-control-feedback"></i>
                                                </div>
                                                <input id='btn-submit-uprofile' type='submit' class='btn btn-primary col-lg-2 btn-submit-update-basic-form' name='password' data-loading-text="Đang thay đổi..." value='Thay đổi' autocomplete="off" />

                                            </div>
                                            <hr />
                                            <!-- gender -->
                                            <div class="form-group">
                                                <label class="col-lg-3 control-label">Giới tính</label>
                                                <div class='radio has-feedback col-lg-7' style="font-size: 16px;">
                                                    <label>
                                                        <input type="radio" name="gender" value="FEMALE" ${sessionScope.user.gender=='FEMALE' ? 'checked': ''} /> Nữ</label>
                                                    &nbsp;
                                                    <label>
                                                        <input type="radio" name="gender" value="MALE" ${sessionScope.user.gender=='MALE' ? 'checked': ''}/> Nam</label>
                                                </div>
                                                <input id='btn-submit-uprofile' type='submit' class='btn btn-primary col-lg-2 btn-submit-update-basic-form' name='password' data-loading-text="Đang thay đổi..." value='Thay đổi' autocomplete="off" />
                                            </div>


                                        </form>

                                    </div>

                                    <!-- target section -->
                                    <div class="bhoechie-tab-content">
                                        <h3 class="st-caption-tab">Cập nhập ngoại hình</h3>
                                        <hr />
                                        <form id='form-update-basic-info' action="updateBasicProfile" method="post" class="form-horizontal">
                                            <!-- height -->
                                            <div class="form-group">
                                                <label class="col-lg-4 control-label">Chiều cao(cm)</label>
                                                <div class="has-feedback col-lg-6">
                                                    <input type="number" id="txtHeight" max='250' class="form-control" data-toggle="popover" data-st='false' name='height' value='${sessionScope.user.height}'>
                                                    <i class="glyphicon glyphicon-exclamation-sign form-control-feedback"></i>
                                                </div>
                                                <input type='submit' class='btn btn-primary col-lg-2 btn-submit-update-basic-form' value='Thay đổi' />
                                            </div>
                                            <hr />
                                             <!-- weight -->
                                            <div class="form-group">
                                                <label class="col-lg-4 control-label">Cân nặng(kg)</label>
                                                <div class="has-feedback col-lg-6">
                                                    <input type="number" id="txtWeight"  max='250' class="form-control" data-toggle="popover" data-st='false' name='weight' value='${sessionScope.user.weight}'>
                                                    <i class="glyphicon glyphicon-exclamation-sign form-control-feedback"></i>
                                                </div>
                                                <input type='submit' class='btn btn-primary col-lg-2 btn-submit-update-basic-form' value='Thay đổi' />
                                            </div>
                                            

                                        </form>
                                    </div>


                                    <!-- hobby section -->
                                    <div class="bhoechie-tab-content">
                                        <h3 class="st-caption-tab">Cập nhập thích</h3>
                                        <hr />
                                      
                                    </div>

                                    <!-- knowleged search -->
                                    <div class="bhoechie-tab-content">
                                        <h3 class="st-caption-tab">Học vấn</h3>
                                    </div>

                                    <!-- job search -->
                                    <div class="bhoechie-tab-content">
                                        <h3 class="st-caption-tab">Nghề nghiệp</h3>
                                    </div>
                                    
                                    
									<!-- people like -->
                                    <div class="bhoechie-tab-content">
                                        <h3 class="st-caption-tab">Người bạn thích</h3>
                                        <hr/>
                                         <form id='form-update-basic-info' action="updateBasicProfile" method="post" class="form-horizontal">
                                            <div class="form-group">
                                                <label class="col-lg-3 control-label">Bạn thích</label>
                                                <div class='radio has-feedback col-lg-7' style="font-size: 16px;">
                                                    <label>
                                                        <input type="radio" name="gender" value="FEMALE" ${sessionScope.user.gender=='FEMALE' ? 'checked': ''} /> Nữ</label>
                                                    &nbsp;
                                                    <label>
                                                        <input type="radio" name="gender" value="MALE" ${sessionScope.user.gender=='MALE' ? 'checked': ''}/> Nam</label>
                                                </div>
                                                <input id='btn-submit-uprofile' type='submit' class='btn btn-primary col-lg-2 btn-submit-update-basic-form' name='password' data-loading-text="Đang thay đổi..." value='Thay đổi' autocomplete="off" />
                                            </div>
											<hr/>
                                            <div class="form-group">
                                                <label class="col-lg-2 control-label">Tuổi</label>
                                                <label class="col-lg-1 control-label">Từ</label>
                                                <div class='col-lg-3'>
	                                                <select class='form-control'>
	                                                	<option>${fn:split(prefer.ageGap, '-')[0] }</option>
	                                                	<c:forEach var='i' begin="18" end="100">
	                                                		<option>${i}</option>
	                                                	</c:forEach>
	                                                </select>
                                                </div>
                                                <label class="col-lg-1 control-label">Đến</label>
                                                <div class='col-lg-3'>
	                                                <select class='form-control'>
	                                                	<option>${fn:split(prefer.ageGap, '-')[1] }</option>
	                                                	<c:forEach var='i' begin="18" end="100">
	                                                		<option>${i}</option>
	                                                	</c:forEach>
	                                                </select>
                                                </div>
                                                <input id='btn-submit-uprofile' type='submit' class='btn btn-primary col-lg-2 btn-submit-update-basic-form' name='password' data-loading-text="Đang thay đổi..." value='Thay đổi' autocomplete="off" />
                                            </div>
                                            <hr/>
                                            <div class="form-group">
                                                <label class="col-lg-2 control-label">Chiều cao(cm)</label>
                                                <label class="col-lg-1 control-label">Từ</label>
	                                              <div class="has-feedback col-lg-3">
                                                    <input type="text" id='txtFirstName' class="form-control" data-toggle="popover" data-st='false' name='firstName' value="${fn:split(prefer.heightGap,'-' )[0]}">
                                                    <i class="glyphicon glyphicon-exclamation-sign form-control-feedback"></i>
                                                </div>
                                                <label class="col-lg-1 control-label">Đến</label>
	                                               <div class="has-feedback col-lg-3">
                                                    <input type="text" id='txtFirstName' class="form-control" data-toggle="popover" data-st='false' name='firstName' value="${fn:split(prefer.heightGap,'-' )[1]}">
                                                    <i class="glyphicon glyphicon-exclamation-sign form-control-feedback"></i>
                                                </div>
                                                <input id='btn-submit-uprofile' type='submit' class='btn btn-primary col-lg-2 btn-submit-update-basic-form' name='password' data-loading-text="Đang thay đổi..." value='Thay đổi' autocomplete="off" />
                                            </div>
                                            <hr/>
                                            <div class="form-group">
                                                <label class="col-lg-2 control-label">Cân nặng(kg)</label>
                                                <label class="col-lg-1 control-label">Từ</label>
	                                              <div class="has-feedback col-lg-3">
                                                    <input type="text" id='txtFirstName' class="form-control" data-toggle="popover" data-st='false' name='firstName' value="${fn:split(prefer.weightGap,'-' )[0]}">
                                                    <i class="glyphicon glyphicon-exclamation-sign form-control-feedback"></i>
                                                </div>
                                                <label class="col-lg-1 control-label">Đến</label>
	                                               <div class="has-feedback col-lg-3">
                                                    <input type="text" id='txtFirstName' class="form-control" data-toggle="popover" data-st='false' name='firstName' value="${fn:split(prefer.weightGap,'-' )[1]}">
                                                    <i class="glyphicon glyphicon-exclamation-sign form-control-feedback"></i>
                                                </div>
                                                <input id='btn-submit-uprofile' type='submit' class='btn btn-primary col-lg-2 btn-submit-update-basic-form' name='password' data-loading-text="Đang thay đổi..." value='Thay đổi' autocomplete="off" />
                                            </div>
                                        	<hr/>
                                        	<div class="form-group">
                                                <label class="col-lg-3 control-label">Tôn giáo</label>
                                                <div class='col-lg-7'>
	                                                <select class='form-control'>
	                                                		<option>Không</option>
	                                                </select>
                                                </div>
                                                <input id='btn-submit-uprofile' type='submit' class='btn btn-primary col-lg-2 btn-submit-update-basic-form' name='password' data-loading-text="Đang thay đổi..." value='Thay đổi' autocomplete="off" />
                                            </div>
                                        </form>

                                    </div>
                                    <!-- others search -->
                                    <div class="bhoechie-tab-content">
                                        <h3 class="st-caption-tab">Ngôn ngữ</h3>
                                        <form action="#" method="post" class="form-horizontal">
                                            <!-- password -->
                                            <div class="form-group">
                                                <label class="col-lg-5 control-label">Ngôn ngữ hiển thị</label>
                                                <div class='col-lg-5'>
	                                                <select class='form-control'>
	                                                		<option>Tiếng việt</option>
	                                                		<option>English</option>
	                                                </select>
                                                </div>
                                                <input type='submit' class='btn btn-primary col-lg-2 btn-submit-update-basic-form' value='Thay đổi' />
                                            </div>
                                        </form>

                                    </div>
                                </div>
                            </div>
                        </jsp:attribute>
                    </t:homeLayout>