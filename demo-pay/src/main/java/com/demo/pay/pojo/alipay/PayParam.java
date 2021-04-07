package com.demo.pay.pojo.alipay;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: sunYF
 * @Description:
 * @Date: Create in 13:51 2020/11/2
 */
@ApiModel(value = "支付宝请求参数", description = "支付宝请求参数")
@Data
public class PayParam {
		@ApiModelProperty(name = "out_trade_no", value = "订单号（唯一）")
		private String out_trade_no;
		@ApiModelProperty(name = "total_amount", value = "金额")
		private String total_amount;
		@ApiModelProperty(name = "body", value = "说明")
		private String body;
		@ApiModelProperty(name = "subject", value = "主题")
		private String subject;

	/*	@ApiModelProperty(name = "timeoutExpress", value = "")
		private String timeoutExpress;*/
/*		@ApiModelProperty(name = "productCode", value = "自定义支付编码")
		private String productCode;*/
}
