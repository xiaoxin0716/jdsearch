package parse;

import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import model.JdModel;
/*
 * 用于将上面传下来的html解析，获取我们需要的内容
 * 解析方式，采用Jsoup解析，有不明白Jsoup的可以上网搜索API文档
 * Jsoup是一款很简单的html解析器
 */
public class JdParse {
    public static List<JdModel> getData (String html) throws Exception{
        //获取的数据，存放在集合中
        List<JdModel> data = new ArrayList<JdModel>();
        //采用Jsoup解析
        Document doc = Jsoup.parse(html);
        //获取html标签中的内容
        Elements errorEle = doc.select("div[class=check-error]");
        if(!errorEle.isEmpty()) {
        	return data;
        }
        Elements elements=doc.select("ul[class=gl-warp clearfix]").select("li[class=gl-item]");
        for (Element ele:elements) {
            String goodID=ele.attr("data-sku");
            String goodPrice=ele.select("div[class=p-price]").select("strong").select("i").text();
            String goodName = ele.select("div[class=p-name]").select("em").text();;
            if(StringUtil.isBlank(goodName)) {
            	goodName=ele.select("div[class=p-name p-name-type-2]").select("em").text();
            }
            String url = ele.select("div[class=p-img]").select("a").attr("href");
            //创建一个对象，这里可以看出，使用Model的优势，直接进行封装
            JdModel jdModel=new JdModel();
            //对象的值
            jdModel.setGoodID(goodID);
            jdModel.setGoodName(goodName);
            jdModel.setGoodPrice(goodPrice);
            jdModel.setUrl(url);
            //将每一个对象的值，保存到List集合中
            data.add(jdModel);
        }
        //返回数据
        return data;
    }
    
    public static JdModel getUrlData (String html,JdModel stu) throws Exception{
        //获取的数据，存放在集合中
        //采用Jsoup解析
        Document doc = Jsoup.parse(html);
        //获取html标签中的内容
        String price =doc.select("div[class=summary-price-wrap]").select("div[class=dd]").select("span[class=pricing]").select("del").html();
        //返回数据
        stu.setGoodOriginalPrice(price);
        return stu;
    }
}
