package com.demo.pay.config;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.CertAlipayRequest;
import com.alipay.api.DefaultAlipayClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Context;
import org.apache.tomcat.util.scan.StandardJarScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.*;

/**
 * @Author: sunYF
 * @Description:
 * @Date: Create in 15:11 2021/2/1
 */
@Configuration
@Slf4j
public class AliPayConfig {
		@Autowired
		AliPayCertConfig aliPayCertConfig;
		@Bean
		public TomcatServletWebServerFactory tomcatFactory() {
				return new TomcatServletWebServerFactory() {
						@Override
						protected void postProcessContext(Context context) {
								((StandardJarScanner) context.getJarScanner()).setScanManifest(false);
						}
				};
		}



		@Bean
		public AlipayClient alipayClient() throws AlipayApiException, IOException {
				CertAlipayRequest certAlipayRequest = new CertAlipayRequest();
				//设置网关地址
				certAlipayRequest.setServerUrl(aliPayCertConfig.getGatewayUrl());
//设置应用Id
				certAlipayRequest.setAppId(aliPayCertConfig.getAppId());
//设置应用私钥
				certAlipayRequest.setPrivateKey(aliPayCertConfig.getPrivateKey());
//设置请求格式，固定值json
				certAlipayRequest.setFormat(aliPayCertConfig.getFormat());
//设置字符集
				certAlipayRequest.setCharset(aliPayCertConfig.getCharset());
//设置签名类型
				certAlipayRequest.setSignType(aliPayCertConfig.getSignType());
//设置应用公钥证书路径
				certAlipayRequest.setCertPath(aliPayCertConfig.getAppCertPath());
				//设置支付宝根证书路径
				certAlipayRequest.setRootCertPath(aliPayCertConfig.getAlipayRootcertPath());
//设置支付宝公钥证书路径
				certAlipayRequest.setAlipayPublicCertPath(aliPayCertConfig.getAlipayCertPath());

				//构造client
				return new DefaultAlipayClient(certAlipayRequest);


		}


}
