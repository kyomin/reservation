package kr.or.connect.reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*
 * 		go to datail.jsp page about specific product
 */
@Controller
public class ProductController {
	@GetMapping(path="/detail")
	public String detail() {
		return "detail";
	}
	
	@GetMapping(path="/review")
	public String review() {
		return "review";
	}
}