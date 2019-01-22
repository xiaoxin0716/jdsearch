package yapulan;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import model.GoodModel;
import model.JdModel;
import util.POIReadExcelTool;
import util.POIWriteExcelTool;
import util.URLFecter;

public class JdongMain {
    
    public static void main(String[] args) throws Exception {
        //初始化一个                                                                            
    	Vector<GoodModel> list = POIReadExcelTool.readXls("/home/mine/下载/加序号-京东价格表20190110-xiaoxin.xls");
    	//用于生成新的excel
    	//ExecutorService executor = Executors.newFixedThreadPool(100);
                     
    	for(GoodModel stu : list) {
        //    executor.execute(new Runnable() {
         //       public void run() {
                	 try {
                     	JdongMain.search(stu);
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }catch (Exception e) {
                    	 stu.setNowPrice("未找到");
                     }
        //        }
           // });
    	}
    	//executor.shutdown();
    	/*while(true){  
            if(executor.isTerminated()){  
                 System.out.println("所有的子线程都结束了！");  
                 break;  
            }  
            Thread.sleep(10000);    
        }*/
    	POIWriteExcelTool.writeXls(list, "/home/mine/加序号-京东价格表20190110-xiaoxin.xls");
    }

	protected static GoodModel search(GoodModel stu) throws Exception {
		HttpClient client = new DefaultHttpClient();
        //我们要爬取的一个地址，这里可以从数据库中抽取数据，然后利用循环，可以爬取一个URL队列
        String search = stu.getGoodName().replace(" ","");
        String url="https://search.jd.com/Search?keyword="+search+"&enc=utf-8&wq="+search+"&aqs=chrome.69i57&sourceid=chrome&ie=UTF-8";
        //抓取的数据
       System.out.println(url);
       
        List<JdModel> bookdatas = URLFecter.URLParser(client, url);
        //循环输出抓取的数据
        /**
         * 只查到一种结果，代表查找正确
         */
        if(bookdatas.size()==1) {
        	JdModel jdModel = searchUrl(bookdatas.get(0));
        	stu.setNowPrice(jdModel.getGoodOriginalPrice());
        	stu.setPromotionalPrice(bookdatas.get(0).getGoodPrice());
        	 
        }else if(bookdatas.size()>1){
	        System.out.println(search);
	        for (JdModel jd:bookdatas) {
	        	if(jd.getGoodName().startsWith("京东超市")) {
	        		if(jd.getGoodName().equals("京东超市小心机 坚果炒货 干果零食罐装免剥无壳 原味腰果仁195g")) {
	        			System.out.println("到了");
	        		}
	        		JdModel jdModel = searchUrl(jd);
	            	stu.setNowPrice(jdModel.getGoodOriginalPrice());
	            	stu.setPromotionalPrice(jd.getGoodPrice());
	        		System.out.println("=========================goodID:"+jd.getGoodID()+"\t"+"goodPrice:"+jd.getGoodPrice()+"\t"+"goodName:"+jd.getGoodName());
	        		break;
	        	}
	            //logger.info("bookID:"+jd.getBookID()+"\t"+"bookPrice:"+jd.getBookPrice()+"\t"+"bookName:"+jd.getBookName());
	        	
	        }
        }else {
        	stu.setNowPrice("未找到");
        }
        return stu;
	}
	
	protected static JdModel searchUrl(JdModel stu) throws Exception {
		HttpClient client = new DefaultHttpClient();
        //我们要爬取的一个地址，这里可以从数据库中抽取数据，然后利用循环，可以爬取一个URL队列
        String url="https:"+stu.getUrl();
        //抓取的数据
        System.out.println(url);
        stu = URLFecter.URLParserUrl(client, url, stu);
        //循环输出抓取的数据
        /**
         * 只查到一种结果，代表查找正确
         */
        return stu;
	}
}
