package com.company;

import com.company.tools.engine.Google;
import com.company.tools.engine.Rambler;
import com.company.tools.engine.Yandex;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11/8/16.
 */

public class Main {

    public static void main(String[] args) throws Exception {
        // UserAgen Linux of Windows
        final String LinuxUSER = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2227.0 Sa";
        final String WindowsUSER = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2227.0 Safari/537.36";
        // Search engines searching url
        String GOOGLE = "https://www.google.com/search?client=ubuntu&channel=fs&q=";
        String YANDEX = "https://yandex.ru/search/?text=hasan";
        String RAMBLER = "https://nova.rambler.ru/search?query=google";


        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Please enter what do you want to search: ");
        String filename = reader.readLine();

        List<Google> googles = new ArrayList<Google>();
        List<Yandex> yandexes = new ArrayList<Yandex>();
        List<Rambler> ramblers = new ArrayList<Rambler>();

        try {

            Document yandexDocument = Jsoup.connect(YANDEX + filename).userAgent(LinuxUSER).get();
            System.out.println("Searching data in Yandex...");
            Elements yandexLinks = yandexDocument.select("div.organic").select("h2.organic__title-wrapper");

            for (Element link : yandexLinks) {
                Yandex yandex = new Yandex();
                yandex.setName(yandexDocument.title());
                yandex.setTitle(link.select("a").text());
                yandex.setUrl(link.select("a").attr("href"));
                yandexes.add(yandex);
            }
            Document ramblerDocument = Jsoup.connect(RAMBLER + filename).userAgent(LinuxUSER).get();
            System.out.println("Yandex searching...");
            Elements ramblerElements = ramblerDocument.select("div.b-serp-list").select("div.b-serp-item");

            for (Element element : ramblerElements) {
                Rambler rambler = new Rambler();
                rambler.setTitle(element.select("a").text());
                rambler.setUrl(element.select("a").attr("href"));
                ramblers.add(rambler);
            }

            Document googleDocument = Jsoup.connect(GOOGLE + filename).userAgent(LinuxUSER).get();
            System.out.println("Google searching...");
            Elements googleLinks = googleDocument.select("div.g").select("h3.r");
            System.out.println("");
            for (Element link : googleLinks) {
                Google google = new Google();
                google.setName(googleDocument.title());
                google.setTitle(link.select("a").text());
                google.setUrl(link.select("a").attr("href"));
                googles.add(google);
            }

            System.out.println(yandexes.get(0).getName() + " first result's Title - " + yandexes.get(0).getTitle() + " URL: " + yandexes.get(0).getUrl());

            System.out.println(ramblers.get(0).getName() + " result's Title - " + ramblers.get(0).getTitle() + " URL: " + ramblers.get(0).getUrl());

            System.out.println(googles.get(0).getName() + " result's Title - " + googles.get(0).getTitle() + " URL: " + googles.get(0).getUrl());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}