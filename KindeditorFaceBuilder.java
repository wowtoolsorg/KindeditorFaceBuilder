package org.wowtools.kindeditorfacebuilder;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class KindeditorFaceBuilder {

	public static void main(String[] args) throws IOException {
		builde("D:\\Genuitec\\MyEclipse 8.5\\work\\umfly\\WebRoot\\WEB-INF\\resource\\r\\js\\kindeditor\\plugins\\emoticons\\images",
				"e:\\temp", 64, 64);

	}

	public static void builde(String sourceImgPath, String tarPath, int faceWidth, int faceHeight) throws IOException {
		// 遍历原文件夹下的所有图片，调整宽度，按数字.gif的名字存到目标文件夹下
		File sp = new File(sourceImgPath);
		File[] allFile = sp.listFiles();
		int i = 0;
		for (File f : allFile) {
			if (f.isFile()) {
				String fileName = f.getName();
				String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
				if (fileType.equalsIgnoreCase("png") || fileType.equalsIgnoreCase("jpg")
						|| fileType.equalsIgnoreCase("gif")) {
					try {
						writeImgToTarPath(f, tarPath, i, faceWidth, faceHeight);
						i++;
					} catch (Exception e) {
					}
				}
			}
		}
		// 遍历目标文件夹下的图片，生成static.gif
		BufferedImage staticImg = new BufferedImage(24*i, 24, BufferedImage.TYPE_INT_BGR);
		Graphics graphics = staticImg.createGraphics();
		int start = 0;
		for (int j = 0; j < i; j++) {
			BufferedImage img = ImageIO.read(new File(tarPath + "\\" + j + ".gif"));
			graphics.drawImage(img, start, 0, 24, 24, null);
			start += 24;
		}
		ImageIO.write(staticImg, "gif", new File(tarPath + "\\static.gif"));
	}

	private static void writeImgToTarPath(File imgFile, String tarPath, int i, int faceWidth, int faceHeight)
			throws IOException {
		BufferedImage img = ImageIO.read(imgFile);
		BufferedImage newImg = new BufferedImage(faceWidth, faceHeight, BufferedImage.TYPE_INT_BGR);
		Graphics graphics = newImg.createGraphics();
		graphics.drawImage(img, 0, 0, faceWidth, faceHeight, null);
		ImageIO.write(newImg, "gif", new File(tarPath + "\\" + i + ".gif"));
	}

}
