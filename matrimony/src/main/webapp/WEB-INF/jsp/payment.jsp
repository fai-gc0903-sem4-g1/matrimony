<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
                    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
                        <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
        <t:homeLayout>
            <jsp:attribute name="head">
                <title>Payment confirm</title>
                <script>
                    $(document).ready(function() {
                        $(document).on('change', '#cbx-pay-with', function(e) {
                            var value = $(this).val();
                            if (value == 'credit')
                                $('#credit-box').slideDown('slow');
                            else
                                $('#credit-box').slideUp('slow');
                        })
                        $(document).on('change', '.checkboxPack', function(e) {
                            if ($(this).val() == 1)
                                $('#final-payment').html('$ 49.99');
                            else
                                $('#final-payment').html('$ 499.99');
                        })
                    });
                </script>
            </jsp:attribute>
            <jsp:attribute name="middle">
                <div class='container col-lg-12'>
                    <br/>
                    <c:choose>
                        <c:when test="${userDAO.hasExpiries(sessionScope.user)}">
                            <div class="alert alert-danger" role="alert">Tài khoản của bạn đã hết hạn vào lúc ${sessionScope.user.expiries }, vui lòng thanh toán để sử tiếp tục sử dụng</div>
                        </c:when>
                        <c:otherwise>
                            <div class="alert alert-info" role="alert">Tài khoản của bạn còn hạn đến ${sessionScope.user.expiries }</div>
                        </c:otherwise>
                    </c:choose>
                    <p>
                        <h3>Thanh toán sử dụng sản phẩm</h3>
                    </p>
                    <form method='POST' action='payment' class='col-lg-6 form-horizontal'>
                        <c:if test="${not empty paymentTimeOut }">
                            <div class="alert alert-warning" role="alert">Đường truyền quá yếu, xin kiểm tra lại</div>
                        </c:if>
                        <c:if test="${not empty paypalError }">
                            <div class="alert alert-warning" role="alert">Lỗi hệ thống, xin thử lại sau</div>
                        </c:if>
                        <c:if test="${not empty finalPaymentInvalid }">
                            <div class="alert alert-warning" role="alert">Gói sử dụng không được bỏ trống</div>
                        </c:if>
                        <c:if test="${not empty payWithInvalid }">
                            <div class="alert alert-warning" role="alert">Loại thanh toán không hợp lệ</div>
                        </c:if>
                        <c:if test="${not empty payWithNotNull }">
                            <div class="alert alert-warning" role="alert">Loại thanh toán không được bỏ trống</div>
                        </c:if>
                        <c:if test="${not empty creditNotSupport }">
                            <div class="alert alert-warning" role="alert">Thanh toán qua credit chưa hỗ trợ</div>
                        </c:if>
                        <p>
                            <h4>Chọn gói sử dụng:</h4>
                            <div class='form-group' style='font-size: 17px;'>
                                <div class="checkbox">
                                    <label>
                                        <input class='checkboxPack' type="radio" name="pack" value="1" />
                                        <span id="price"> 1 tháng <span id="currencyCode">$</span>&nbsp;49.99</span>
                                    </label>
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input class='checkboxPack' type="radio" name="pack" value="12" />
                                        <span id="price"> 12 tháng <span id="currencyCode">$</span>&nbsp;499.99</span> (Tiết kiệm 15%)</label>
                                </div>
                            </div>

                            <ul class="list-group">
                                <li class="list-group-item list-group-item-info">Final payment<span id='final-payment' class="badge"></span>
                                </li>
                            </ul>

                            <div class='form-group'>
                                <select id="cbx-pay-with" name="payWith" class='selectpicker col-lg-12' data-style='btn-warning'>
                                    <option disabled selected>Chọn loại thanh toán</option>
                                    <option value='paypal'>Paypal</option>
                                    <option value='credit'>Credit card</option>
                                </select>
                            </div>

                            <div id='credit-box' style='display:none;'>
                                <img class="img-responsive pull-right" src="http://i76.imgup.net/accepted_c22e0.png">
                                <div class="row">
                                    <div class="col-xs-12">
                                        <div class="form-group">
                                            <label>CARD NUMBER</label>
                                            <div class="input-group">
                                                <input type="tel" class="form-control" name="cardNumber" placeholder="Valid Card Number" />
                                                <span class="input-group-addon"><i class="fa fa-credit-card"></i></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-7 col-md-7">
                                        <div class="form-group">
                                            <label><span class="hidden-xs">EXPIRATION</span><span class="visible-xs-inline">EXP</span> DATE</label>
                                            <input type="tel" class="form-control" name="cardExpiry" placeholder="MM / YY" />
                                        </div>
                                    </div>
                                    <div class="col-xs-5 col-md-5 pull-right">
                                        <div class="form-group">
                                            <label>CV CODE</label>
                                            <input type="tel" class="form-control" name="cardCVC" placeholder="CVC" />
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-12">
                                        <div class="form-group">
                                            <label>COUPON CODE</label>
                                            <input type="text" class="form-control" name="couponCode" />
                                        </div>
                                    </div>
                                </div>

                                <div class="row" style="display:block;">
                                    <div class="col-xs-12">
                                        <p class="payment-errors">ERROR</p>
                                    </div>
                                </div>
                            </div>
                            <input type='submit' class='btn btn-success col-lg-12' value='Pay now' />

                    </form>
                    <div class='col-lg-6'>
                        <p>
                            <h4>Features:</h4>
                            <ul>
                                <li>As-you-type, input formatting</li>
                                <li>Form field validation (also as you type)</li>
                                <li>Graceful error feedback for declined card, etc</li>
                                <li>AJAX form submission w/ visual feedback</li>
                                <li>Creates a Stripe credit card token</li>
                            </ul>
                        </p>
                        <p>Be sure to replace the dummy API key with a valid Stripe API key.</p>

                        <p>Built upon: Bootstrap, jQuery,
                            <a href="http://jqueryvalidation.org/">jQuery Validation Plugin</a>,
                            <a href="https://github.com/stripe/jquery.payment">jQuery.payment library</a>, and <a href="https://stripe.com/docs/stripe.js">Stripe.js</a>
                        </p>
                    </div>
                </div>
            </jsp:attribute>
        </t:homeLayout>