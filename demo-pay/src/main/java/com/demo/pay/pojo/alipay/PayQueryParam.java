package com.demo.pay.pojo.alipay;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: sunYF
 * @Description:
 * @Date: Create in 13:32 2020/11/3
 */
@Data
@ApiModel(value = "支付宝查询订单请求参数", description = "支付宝查询订单请求参数")
public class PayQueryParam {
		@ApiModelProperty(name = "orderNo", value = "订单号（唯一）")
		private String orderNo;
		@ApiModelProperty(name = "tradeNo", value = "out_trade_no（外部订单号）和trade_no（支付宝交易号)二选一用 即可查询")
		private String tradeNo;

}
