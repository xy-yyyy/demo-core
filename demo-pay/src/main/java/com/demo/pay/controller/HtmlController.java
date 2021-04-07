package com.demo.pay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: sunYF
 * @Description:
 * @Date: Create in 16:55 2020/11/2
 */
@Controller
@RequestMapping("/pay")
public class HtmlController {
		@RequestMapping("/webPayHtml")
		public String webPayHtml() {
				return "AppPay";
		}
		@RequestMapping("/appPayHtml")
		public String wapPayHtml() {
				return "AppPay";
		}
}
