package com.example.networkmodule.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlUtil {
    /**
     * 富文本适配
     */
    public static String getHtmlContent(String htmltext) {
        Document doc = Jsoup.parse(htmltext);
        Elements elements = doc.getElementsByTag("img");
        for (Element element : elements) {
            if (element.className() != null && element.className().length() > 0)
                element.attr("width", "100%").attr("height", "auto");
        }
        return doc.toString();
    }

}
