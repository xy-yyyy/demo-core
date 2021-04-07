package com.demo.pay.pojo.alipay;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: sunYF
 * @Description:
 * @Date: Create in 12:27 2020/11/3
 */
@Data
@ApiModel(value = "支付宝退款查询请求参数", description = "支付宝退款查询请求参数")
public class RefundQueryParam {
		@ApiModelProperty(name = "orderNo", value = "订单号（唯一）")
		private String orderNo;
		@ApiModelProperty(name = "refundOrderNo", value = "请求退款接口时，传入的退款请求号，如果在退款请求时未传入，则该值为创建交易时的外部订单号")
		private String refundOrderNo;
}
