package util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import model.GoodModel;

public class POIWriteExcelTool {

    public static void main(String[] args) throws Exception {
    	
    	Vector<GoodModel> list = POIReadExcelTool.readXls("/home/mine/下载/加序号-京东价格表20190110-xiaoxin.xls");
    	writeXls(list,"/home/mine/加序号-京东价格表20190110-xiaoxin.xls");
    }

    public static  void writeXls(Vector<GoodModel> list,String filename) throws Exception {

        //第一步创建workbook  
        HSSFWorkbook wb = new HSSFWorkbook();  
          
        //第二步创建sheet  
        HSSFSheet sheet = wb.createSheet("京东价格表20190110");  
          
        //第三步创建行row:添加表头0行  
        HSSFRow row = sheet.createRow(0);  
        HSSFCellStyle  style = wb.createCellStyle();      
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  //居中  
          
        //第四步创建单元格  
        HSSFCell cell = row.createCell(0); //第一个单元格  
        cell.setCellValue("序号");  
        cell.setCellStyle(style);  
        
        cell = row.createCell(1);         //第二个单元格     
        cell.setCellValue("商品名称");  
        cell.setCellStyle(style);  
        
        cell = row.createCell(2);         //第三个单元格     
        cell.setCellValue("货号");  
        cell.setCellStyle(style); 
        
        cell = row.createCell(3);         //第四个单元格     
        cell.setCellValue("京东价");  
        cell.setCellStyle(style); 
        
        cell = row.createCell(4);         //第五个单元格     
        cell.setCellValue("现价");  
        cell.setCellStyle(style); 
       
        cell = row.createCell(5);         //第六个单元格     
        cell.setCellValue("活动价");  
        cell.setCellStyle(style); 
        
        cell = row.createCell(6);         //第七个单元格     
        cell.setCellValue("协议价");  
        cell.setCellStyle(style); 
        
        cell = row.createCell(7);         //第八个单元格     
        cell.setCellValue("销售价(参考)");  
        cell.setCellStyle(style); 
        //第五步插入数据  
        
        cell = row.createCell(8);         //第九个单元格     
        cell.setCellValue("定价");  
        cell.setCellStyle(style); 
        //第五步插入数据  
       
        for (int i = 0; i < list.size(); i++) {  
            //创建行  
            row = sheet.createRow(i+1);  
            //创建单元格并且添加数据  
            row.createCell(0).setCellValue(list.get(i).getIndex().replace(".0", ""));  
            row.createCell(1).setCellValue(list.get(i).getGoodName());
            row.createCell(2).setCellValue(list.get(i).getGoodNo());
            row.createCell(3).setCellValue(list.get(i).getGoodPrice());
            row.createCell(4).setCellValue(list.get(i).getNowPrice());
            row.createCell(5).setCellValue(list.get(i).getPromotionalPrice());
            row.createCell(6).setCellValue(list.get(i).getAgreementPrice());
            row.createCell(7).setCellValue(list.get(i).getSalePrice());
            row.createCell(8).setCellValue("");
            
        }  
          
        //第六步将生成excel文件保存到指定路径下  
        try {  
            FileOutputStream fout = new FileOutputStream(filename);  
            wb.write(fout);  
            fout.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
          
        System.out.println("Excel文件生成成功...");  

    }
}