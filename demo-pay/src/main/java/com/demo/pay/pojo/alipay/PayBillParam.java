package com.demo.pay.pojo.alipay;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: sunYF
 * @Description:
 * @Date: Create in 14:12 2020/11/3
 */
@Data
@ApiModel(value = "支付宝查询账单请求参数", description = "支付宝查询账单请求参数")
public class PayBillParam {
		@ApiModelProperty(name = "billDate", value = "账单时间 日账单格式为yyyy-MM-dd，月账单格式为yyyy-MM")
		private String billDate;
}
