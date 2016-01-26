package wifiqr;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class WifiQR {
	public static void geneateQrCode(String wifiInfos, boolean addLogo, String outputFilename) throws IOException, WriterException{

		Map<EncodeHintType,  Object> hints = new HashMap<EncodeHintType, Object>();
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		
		QRCodeWriter writer = new QRCodeWriter();
		BitMatrix bitMatrix = writer.encode(wifiInfos, BarcodeFormat.QR_CODE, 1024, 1024, hints);
		BufferedImage bufferedImage = new BufferedImage(bitMatrix.getWidth(), bitMatrix.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bufferedImage.createGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
		g.setColor(Color.BLACK);
		for (int i = 0; i < bitMatrix.getHeight(); i++) {
			for (int j = 0; j < bitMatrix.getWidth() ; j++) {
				if(bitMatrix.get(i, j)) g.fillRect(i, j, 1, 1);
			}
		}
		
		if(addLogo){
			int centerX = (int)(bufferedImage.getWidth()/2d);
			int centery = (int)(bufferedImage.getHeight()/2d);
			
			BufferedImage logo = ImageIO.read(WifiQR.class.getResourceAsStream("/logo.png"));
			int lpw=logo.getWidth();
			int lph=logo.getHeight();
			g.drawImage(logo, centerX-(lpw/2), centery-(lph/2), centerX+(lpw/2), centery+(lph/2), 0, 0, lpw, lph, Color.WHITE, null);
		}
		
		ImageIO.write(bufferedImage, "png", new FileOutputStream(outputFilename));
	}
}
