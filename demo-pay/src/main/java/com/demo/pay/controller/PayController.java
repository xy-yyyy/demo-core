package com.demo.pay.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.*;
import com.alipay.api.request.*;
import com.alipay.api.response.AlipayDataDataserviceBillDownloadurlQueryResponse;
import com.alipay.api.response.AlipayTradeCloseResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.demo.pay.config.AliPayCertConfig;
import com.demo.pay.pojo.alipay.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @Author: sunYF
 * @Description: 调用支付宝沙箱 --支付方法
 * @Date: Create in 10:20 2020/11/2
 */
@Api(tags = {"支付宝相关接口"})
@RequestMapping("/pay")
@RestController
@Slf4j
public class PayController {
		// 获得初始化的AlipayClient
		@Autowired
		AlipayClient alipayClient;

		@Autowired
		AliPayCertConfig aliPayCertConfig;


		@ApiOperation(value = "WEB端支付入口", notes = "WEB端支付入口")
		@PostMapping("/webPay")
		public void pcPay(PayParam param, HttpServletResponse response) throws Exception {
				log.info("param,{}", param);
				//订单模型
				String productCode = "FAST_INSTANT_TRADE_PAY";
				AlipayTradePagePayModel model = new AlipayTradePagePayModel();
				model.setOutTradeNo(param.getOut_trade_no());
				model.setProductCode(productCode);
				model.setTimeoutExpress("2m");
				model.setSubject(param.getSubject());
				model.setBody(param.getBody());
				log.info("body,{}", param.getBody());
				model.setTotalAmount(param.getTotal_amount());
				/*model.setSubject("测试网页支付");
				model.setTotalAmount("5");
				model.setBody("网页支付测试，共5元");*/

				log.info("model,{}", model);
				// 设置请求参数
				AlipayTradePagePayRequest pagePayRequest = new AlipayTradePagePayRequest();
				pagePayRequest.setReturnUrl(aliPayCertConfig.getReturnUrl());
				pagePayRequest.setNotifyUrl(aliPayCertConfig.getNotifyUrl());
				pagePayRequest.setBizModel(model);


				log.info("bizModel,{}", pagePayRequest.getBizContent());


				// 请求
			/*	String result = alipayClient.pageExecute(pagePayRequest).getBody();
				response.setContentType("text/html; charset=utf-8");
				response.getWriter().write(result);
				response.getWriter().flush();
				response.getWriter().close();
				log.info("result,{}", result);*/
		}


		@ApiOperation(value = "APP端支付入口", notes = "APP端支付入口")
		@PostMapping("/appPay")
		public void appPay(PayParam param, HttpServletResponse response) throws Exception {
				log.info("param,{}", param);


				//订单模型
				String productCode = "QUICK_MSECURITY_PAY";
				AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
				model.setOutTradeNo(param.getOut_trade_no());
				model.setTimeoutExpress("2m");
				model.setSubject(param.getSubject());
				model.setBody(param.getBody());
				model.setTotalAmount(param.getTotal_amount());
				model.setPassbackParams("1");
				model.setSubject("手机支付测试");
				model.setTotalAmount("10");
				model.setBody("手机支付测试，共10元");
				model.setProductCode(productCode);


				AlipayTradeAppPayRequest appPayRequest = new AlipayTradeAppPayRequest();
				appPayRequest.setReturnUrl(aliPayCertConfig.getReturnUrl());
				appPayRequest.setNotifyUrl(aliPayCertConfig.getNotifyUrl());
				appPayRequest.setBizModel(model);
				String retStr = alipayClient.sdkExecute(appPayRequest).getBody();
				log.info("retStr,{}", retStr);
				System.out.println(retStr);
		}

		@ApiOperation(value = "退款", notes = "退款")
		@PostMapping("/refund")
		public String refund(@RequestBody PayRefundParam param) throws Exception {
				log.info("param,{}", param);

				AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();
				AlipayTradeRefundModel model = new AlipayTradeRefundModel();
				// 商户订单号
				model.setOutTradeNo(param.getOutTradeNo());
				// 退款金额
				model.setRefundAmount(param.getTotalAmount());
				// 退款原因
				model.setRefundReason(param.getRefundReason());
				// 退款订单号(同一个订单可以分多次部分退款，当分多次时必传)
				//model.setOutRequestNo(UUID.randomUUID().toString());
				alipayRequest.setBizModel(model);
				AlipayTradeRefundResponse alipayResponse = alipayClient.execute(alipayRequest);
				return alipayResponse.getBody();
		}

		@ApiOperation(value = "订单查询", notes = "订单查询")
		@GetMapping("/query")
		public String query(PayQueryParam param) {
				log.info("param,{}", param);

				if (StringUtils.isBlank(param.getOrderNo())) {
						throw new RuntimeException("订单号不能为空");
				}
				AlipayTradeQueryRequest alipay_request = new AlipayTradeQueryRequest();
				AlipayTradeQueryModel model = new AlipayTradeQueryModel();
				model.setOutTradeNo(param.getOrderNo());
				if (StringUtils.isNotEmpty(param.getTradeNo())) {
						model.setTradeNo(param.getTradeNo());
				}
				alipay_request.setBizModel(model);
				String form = "";
				try {
						AlipayTradeQueryResponse alipay_response = alipayClient.execute(alipay_request);
						form = alipay_response.getBody();
						log.info("from,{}", form);
						return form;
				} catch (AlipayApiException e) {
						e.printStackTrace();
						throw new RuntimeException("支付宝查询交易数据失败");
				}

		}

		@ApiOperation(value = "退款查询", notes = "退款查询")
		@GetMapping("/refundQuery")
		public String refundQuery(RefundQueryParam param) throws AlipayApiException {
				log.info("param,{}", param);


				AlipayTradeFastpayRefundQueryRequest alipayRequest = new AlipayTradeFastpayRefundQueryRequest();
				AlipayTradeFastpayRefundQueryModel model = new AlipayTradeFastpayRefundQueryModel();
				model.setOutTradeNo(param.getOrderNo());
				if (StringUtils.isNotEmpty(param.getRefundOrderNo())) {
						log.info("----------");
						model.setOutRequestNo(param.getRefundOrderNo());
				}

				alipayRequest.setBizModel(model);
				return alipayClient.execute(alipayRequest).getBody();
		}

		@ApiOperation(value = "关闭交易", notes = "关闭交易")
		@PostMapping("/close")
		public String close(@RequestParam String orderNo) throws AlipayApiException {

				AlipayTradeCloseRequest alipayRequest = new AlipayTradeCloseRequest();
				AlipayTradeCloseModel model = new AlipayTradeCloseModel();
				model.setOutTradeNo(orderNo);
				alipayRequest.setBizModel(model);
				AlipayTradeCloseResponse alipayResponse = alipayClient.execute(alipayRequest);
				return alipayResponse.getBody();
		}

		@ApiOperation(value = "查询账单", notes = "查询账单")
		@GetMapping("/bill")
		public void queryBill(PayBillParam param) {
				log.info("billDate,{}", param.getBillDate());

				// 1. 查询对账单下载地址
				AlipayDataDataserviceBillDownloadurlQueryRequest request = new AlipayDataDataserviceBillDownloadurlQueryRequest();
				AlipayDataDataserviceBillDownloadurlQueryModel model = new AlipayDataDataserviceBillDownloadurlQueryModel();
				model.setBillType("trade");
				model.setBillDate(param.getBillDate());
				request.setBizModel(model);
				try {
						AlipayDataDataserviceBillDownloadurlQueryResponse response = alipayClient.execute(request);
						if (response.isSuccess()) {
								String billDownloadUrl = response.getBillDownloadUrl();
								System.out.println(billDownloadUrl);

								// 2. 下载对账单
								List<String> orderList = this.downloadBill(billDownloadUrl);
								System.out.println(orderList);
								// 3. 先比较支付宝的交易合计/退款合计笔数/实收金额是否和自己数据库中的数据一致，如果不一致证明有异常，再具体找出那些订单有异常
								// 查找支付宝支付成功而自己支付失败的记录和支付宝支付失败而自己认为支付成功的异常订单记录到数据库
						} else {
								// 失败
								String code = response.getCode();
								String msg = response.getMsg();
								String subCode = response.getSubCode();
								String subMsg = response.getSubMsg();
						}
				} catch (AlipayApiException | IOException e) {
						e.printStackTrace();
				}
		}

		/**
		 * 下载下来的是一个【账号_日期.csv.zip】文件(zip压缩文件名，里面有多个.csv文件)
		 * 账号_日期_业务明细 ： 支付宝业务明细查询
		 * 账号_日期_业务明细(汇总)：支付宝业务汇总查询
		 * 注意：如果数据量比较大，该方法可能需要更长的执行时间
		 */

		private List<String> downloadBill(String billDownLoadUrl) throws IOException {
				String ordersStr = "";
				CloseableHttpClient httpClient = HttpClients.createDefault();
				RequestConfig config = RequestConfig.custom()
								.setConnectTimeout(60000)
								.setConnectionRequestTimeout(60000)
								.setSocketTimeout(60000)
								.build();
				HttpGet httpRequest = new HttpGet(billDownLoadUrl);
				httpRequest.setConfig(config);
				CloseableHttpResponse response = null;
				byte[] data = null;
				try {
						response = httpClient.execute(httpRequest);
						HttpEntity entity = response.getEntity();
						data = EntityUtils.toByteArray(entity);
				} finally {
						response.close();
						httpClient.close();
				}
				ZipInputStream zipInputStream = new ZipInputStream(new ByteArrayInputStream(data), Charset.forName("GBK"));
				ZipEntry zipEntry = null;
				try {
						while ((zipEntry = zipInputStream.getNextEntry()) != null) {
								ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
								try {
										String name = zipEntry.getName();
										// 只要明细不要汇总
										if (name.contains("汇总")) {
												continue;
										}
										byte[] byteBuff = new byte[4096];
										int bytesRead = 0;
										while ((bytesRead = zipInputStream.read(byteBuff)) != -1) {
												byteArrayOutputStream.write(byteBuff, 0, bytesRead);
										}
										ordersStr = byteArrayOutputStream.toString("GBK");
								} finally {
										byteArrayOutputStream.close();
										zipInputStream.closeEntry();
								}
						}
				} finally {
						zipInputStream.close();
				}

				if (ordersStr.equals("")) {
						return null;
				}
				String[] bills = ordersStr.split("\r\n");
				List<String> billList = Arrays.asList(bills);
				billList = billList.parallelStream().map(item -> item.replace("\t", "")).collect(Collectors.toList());

				return billList;
		}
}
