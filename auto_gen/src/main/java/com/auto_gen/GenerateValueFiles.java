package com.auto_gen;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * 
 * 生成对应分辨率下的 lay_x 文件目录，执行完毕后，刷新工程后，生成一个res
 * 
 * 处理方式：根据当前的原型设备的分辨率的宽高像素值，按照“比例关系”对用生成不同分辨率的宽高值
 * 比例关系：原型的宽/其他分辨率的宽， 原型的高/其他分辨率的高     
 * by 20170102
 */
public class GenerateValueFiles {

	private static String outputdir = "./auto_gen/res";

	public static void generateXmlFile(int baseW) {
		String WTemplate = "<dimen name=\"x{0}\">{1}pt</dimen>\n";
		
		StringBuffer sbForWidth = new StringBuffer();
		sbForWidth.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
		sbForWidth.append("<resources>\n");
		
		//--生成width
		for (int i = 0; i <= baseW; i++) {
			sbForWidth.append("\t" +WTemplate.replace("{0}", i + "").replace("{1}", i +""));
		}
		sbForWidth.append("</resources>");


		File dimenXFile = new File(outputdir,"dimen.xml");
		if (!dimenXFile.getParentFile().exists()) {
			dimenXFile.getParentFile().mkdir();
		}
		try {
			PrintWriter pw = new PrintWriter(new FileOutputStream(dimenXFile));
			pw.print(sbForWidth.toString());
			pw.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
		generateXmlFile(1280);
	}
}