package com.example.networkmodule.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 富文本工具类
 * Date:2018/9/11
 * Time:10:55
 * author:lijun
 */

public class RichTextUtil {

    /**
     * 富文本拼接成标准HTML
     *
     * @param bigText
     * @return
     */
    public static String bigText2Html(String bigText) {
        String css = "<style type=\"text/css\"> img {" +
                "width:100%;" +
                "height:auto;" +
                "}" + "</style>";
        return "<html><head><meta charset='utf-8' />" + css + "</head><body> " + bigText + "</body></html>";

    }

    /**
     * WebView加载html文本时，图片太大不能适配屏幕
     *
     * @param htmltext
     * @return
     */
    public static String getHtmlFormatNewContent(String htmltext) {

        Document doc = Jsoup.parse(htmltext);
        Elements elements = doc.getElementsByTag("img");
        for (Element element : elements) {
            element.attr("width", "100%").attr("height", "auto");
        }

        return doc.toString();
    }
}
