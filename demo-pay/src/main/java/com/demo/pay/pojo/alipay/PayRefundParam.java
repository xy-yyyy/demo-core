package com.demo.pay.pojo.alipay;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: sunYF
 * @Description:
 * @Date: Create in 11:41 2020/11/3
 */
@Data
@ApiModel(value = "支付宝退款请求参数", description = "支付宝退款请求参数")
public class PayRefundParam {
		@ApiModelProperty(name = "outTradeNo", value = "订单号（唯一）")
		private String outTradeNo;
		@ApiModelProperty(name = "totalAmount", value = "退款金额")
		private String totalAmount;
		@ApiModelProperty(name = "refundReason", value = "退款理由")
		private String 	refundReason;
}
