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
public class SonTestController {
	@RequestMapping(value = "example", method = RequestMethod.GET)
	public String example() {
		return "example";
	}
	
	@RequestMapping(value = "infinityOrb", method = RequestMethod.GET)
	public String infinityOrb() {
		return "infinityOrb";
	}
}
