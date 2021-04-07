package com.demo.pay.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: sunYF
 * @Description:
 * @Date: Create in 16:10 2021/2/1
 */

@Data
@Component
@ConfigurationProperties(prefix = "alipay")
public class AliPayCertConfig {
    /**
     * 应用ID
     */
    public String appId;
    // 商户私钥，您的PKCS8格式RSA2私钥
    public String privateKey;
    /**
     * 后台通知地址
     */
    public String notifyUrl;
    /**
     * 页面回跳地址
     */
    public String returnUrl;
    /**
     * 签名类型: RSA2
     */
    public String signType;
    /**
     * 编码格式 utf-8
     */
    public String charset;
    /**
     * 转换类型: json
     */
    public String format;
    /**
     * 网关地址: https://openapi.alipay.com/gateway.do
     */
    public String gatewayUrl;
    /**
     * 应用公钥
     */
    public String appCertPath;
    /**
     * 阿里公钥
     */
    public String alipayCertPath;
    /**
     * 阿里根证书
     */
    public String alipayRootcertPath;
}
