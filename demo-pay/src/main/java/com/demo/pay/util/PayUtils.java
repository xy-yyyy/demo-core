package com.demo.pay.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.extern.slf4j.Slf4j;

import java.awt.image.BufferedImage;
import java.util.Hashtable;
import java.util.Map;

/**
 * @Author: sunYF
 * @Description: 生成二维码图片用于当面付
 * @Date: Create in 13:45 2020/11/2
 */
@Slf4j
public class PayUtils {

		/**
		 * 根据url生成二位图片对象
		 *
		 * @param codeUrl url的代码
		 * @return {@link BufferedImage}
		 * @throws WriterException 写入异常
		 */
		public static BufferedImage getQrCodeImage(String codeUrl) throws WriterException {
				Map<EncodeHintType, Object> hints = new Hashtable(2);
				hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
				hints.put(EncodeHintType.CHARACTER_SET, "UTF8");
				int width = 256;
				int height = 256;
				BitMatrix bitMatrix = (new MultiFormatWriter()).encode(codeUrl, BarcodeFormat.QR_CODE, width, height, hints);
				BufferedImage image = new BufferedImage(width, height, 1);
				for (int x = 0; x < width; ++x) {
						for (int y = 0; y < width; ++y) {
								image.setRGB(x, y, bitMatrix.get(x, y) ? -16777216 : -1);
						}
				}
				return image;
		}
}