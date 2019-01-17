package util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import model.GoodModel;

public class POIReadExcelTool {

    public static void main(String[] args) throws Exception {
    	Vector<GoodModel> list = readXls("/home/mine/下载/加序号-京东价格表20190110-xiaoxin.xls");
        for(GoodModel stu : list){
            System.out.println(stu.getGoodNo());
            System.out.println(stu.getGoodName());
            System.out.println(stu.getGoodPrice());
        }
    }

    public static Vector<GoodModel> readXls(String filename) throws Exception {
        InputStream is = new FileInputStream(filename);

        HSSFWorkbook excel = new HSSFWorkbook(is);
        GoodModel stu = null;
        Vector<GoodModel> list = new Vector<GoodModel>();
        
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < excel.getNumberOfSheets(); numSheet++) {
            HSSFSheet sheet = excel.getSheetAt(numSheet);
            if (sheet == null)
                continue;
            // 循环行Row
            for (int rowNum = 1; rowNum < sheet.getLastRowNum(); rowNum++) {
                HSSFRow row = sheet.getRow(rowNum);
                if (row == null)
                    continue;
                stu = new GoodModel();
                /**console:
                 *         1
                        张三
                        1997-03-12
                        李四
                        1996-08-12
                 */
                HSSFCell cell0 = row.getCell(0);
                if (cell0 == null)
                    continue;
                stu.setIndex(getValue(cell0));
                HSSFCell cell1 = row.getCell(1);
                if (cell1 == null)
                	continue;
                stu.setGoodName(getValue(cell1));
                HSSFCell cell2 = row.getCell(2);
                if (cell2 == null)
                    continue;
                stu.setGoodNo(getValue(cell2));
                HSSFCell cell3 = row.getCell(3);
                if (cell3 == null)
                    continue;
                stu.setGoodPrice(getValue(cell3));
                HSSFCell cell6 = row.getCell(6);
                if (cell6 == null)
                    continue;
                stu.setAgreementPrice(getValue(cell6));
                HSSFCell cell7 = row.getCell(7);
                if (cell7 == null)
                    continue;
                stu.setSalePrice(getValue(cell7));
                list.add(stu);
            }
        }

        return list;
    }

    private static String getValue(HSSFCell cell) {
        if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            // 返回布尔类型 值
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            //返回数值类型的值
            return String.valueOf(cell.getNumericCellValue());
        } else {
            //返回字符串类型的值
            return cell.getStringCellValue();
        }
    }
}