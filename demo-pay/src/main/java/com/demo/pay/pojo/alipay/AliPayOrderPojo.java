package com.demo.pay.pojo.alipay;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author: sunYF
 * @Description:
 * @Date: Create in 15:52 2021/2/1
 */
@Data
public class AliPayOrderPojo {
//商户订单号
		private String out_trade_no;
		//交易金额
		private BigDecimal total_amount;
		//支付宝订单号
		private String trade_no;
		//买家支付宝账号
		private String buyer_logon_id;
		//实收金额
		private BigDecimal receipt_amount;
		//买家付款的金额
		private BigDecimal buyer_pay_amount;
		//交易支付时间
		private LocalDateTime gmt_payment;
		//买家用户类型 CORPORATE:企业用户；PRIVATE:个人用户。
		private String buyer_user_type;
		//买家名称
		private String buyer_user_name;
}
