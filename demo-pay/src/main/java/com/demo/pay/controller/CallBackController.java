package com.demo.pay.controller;

import com.demo.pay.pojo.alipay.AliPayOrderPojo;
import com.demo.pay.util.GsonUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author: sunYF
 * @Description:
 * @Date: Create in 12:32 2020/11/2
 */
@Api(tags = {"回调页面"})
@RequestMapping("/callBack")
@RestController
@Slf4j
public class CallBackController {

		// 将request中的参数转换成Map
		private static Map<String, String> convertRequestParamsToMap(HttpServletRequest request) {
				Map<String, String> retMap = new HashMap<String, String>();

				Set<Map.Entry<String, String[]>> entrySet = request.getParameterMap().entrySet();

				for (Map.Entry<String, String[]> entry : entrySet) {
						String name = entry.getKey();
						String[] values = entry.getValue();
						int valLen = values.length;

						if (valLen == 1) {
								retMap.put(name, values[0]);
						} else if (valLen > 1) {
								StringBuilder sb = new StringBuilder();
								for (String val : values) {
										sb.append(",").append(val);
								}
								retMap.put(name, sb.toString().substring(1));
						} else {
								retMap.put(name, "");
						}
				}

				return retMap;
		}




		/**
		 * 支付完成回调------异步返回商家，也就是notify_url
		 * @param request
		 * @return
		 */
		@RequestMapping("/notifyUrl")
		public Object notifyUrl(HttpServletRequest request) {
				// 商户订单号
				Map<String, String> params =convertRequestParamsToMap(request);
				AliPayOrderPojo pojo = GsonUtils.fromJson(GsonUtils.toJson(params), AliPayOrderPojo.class);
				//修改订单状态
				//TODO
				return pojo;
		}
		/**
		 * 支付完成回调------同步返回用户的页面，也就是return_url
		 * @param request
		 * @return
		 * @throws Exception
		 *//*

		@RequestMapping("returnUrl")
		public String returnUrl( HttpServletRequest request) throws Exception {
				//支付成功后的因袭可以通过 request.getParameterMap()获取到
				Map<String, String[]> parameterMap = request.getParameterMap();
				for (String key:parameterMap.keySet()
				) {
						System.out.println(key);
						System.out.println(parameterMap.get(key));
				}
				// 获取商户订单号
				String orderCode = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "GBK");
				//根据订单号修改订单状态为已付款
				orderService.updateOrderStatus(orderCode,Constants.ORDER_PAY_OK_TYPE);
				//return 重定向到我的订单界面或者其他页面
				return "redirect:/order/showOrderView";

		}
*/


}
