package com.reto.challenge.util;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Scraping {

    private static final Logger log = LoggerFactory.getLogger(Scraping.class);

    private static final Random RANDOM = new Random(System.currentTimeMillis());

    private static final List<String> arrayAuthor = new ArrayList<>();

    private static final List<String> arrayMensaje = new ArrayList<>();

    private static final List<String> arrayTime = new ArrayList<>();

    public static void  getStatusConnectionCode(){
        String url = "https://jarroba.com/page/%s/";
        int maxPages = 2;
        for (int i=1; i<maxPages; i++){
            String urlPage = String.format(url, i);
            if (getStatusConnectionCode(urlPage) == 200) {

                Document document = getHtmlDocument(urlPage);

                Elements inputs = document.select("div.col-md-4.col-xs-12")
                        .not("div.col-md-offset-2.col-md-4.col-xs-12");

                forEachInput(inputs);

            }else{
                log.info("El Status Code no es OK es: "+getStatusConnectionCode(urlPage));
                break;
            }
        }
    }

    private static void forEachInput(Elements inputs) {
        for (Element elem : inputs) {
            arrayMensaje.add( elem.getElementsByClass("tituloPost").text());
            arrayAuthor.add(elem.getElementsByClass("autor").toString());
            arrayTime.add(elem.getElementsByClass("fecha").text());
        }
    }

    public static String author() {
        return arrayAuthor.get(RANDOM.nextInt(arrayAuthor.size()));
    }

    public static String message() { return arrayMensaje.get(RANDOM.nextInt(arrayMensaje.size())); }

    public static String timeStamp() { return arrayTime.get(RANDOM.nextInt(arrayTime.size())); }

    private static int getStatusConnectionCode(String url) {

        Response response = null;

        try {
            response = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).ignoreHttpErrors(true).execute();
        } catch (IOException ex) {
            log.info("Excepción al obtener el Status Code: " + ex.getMessage());
        }
        return response.statusCode();
    }

    private static Document getHtmlDocument(String url) {

        Document doc = null;

        try {
            doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).get();
        } catch (IOException ex) {
            log.info("Excepción al obtener el HTML de la página" + ex.getMessage());
        }

        return doc;

    }
}


