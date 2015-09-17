/**
 * 
 */
package com.matrimony.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author SON
 *
 */
@Controller
public class PaymentController {
	@RequestMapping(value = "payment", method = RequestMethod.GET)
	public String viewPayment() {
		return "payment";
	}
}
