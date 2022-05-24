package com.kranius.fetcher.crawlers;

import java.net.URL;

public class CrawlerPicker {

    public static WebCrawlerInterface pickCrawlerFromUrl(URL url) {
        String[] hostname = url.getHost().split("\\.");
        int pos = hostname.length;
        String tld = hostname[pos-1];
        String domain = hostname[pos-2];
        String subdomain = pos > 2 ? hostname[pos-3] : "";

        WebCrawlerInterface crawler = switch (domain) {
            case "thomann" -> new WebCrawlerThomann();
            case "amazon" -> new WebCrawlerAmazon();
            case "leboncoin" -> new WebCrawlerLeboncoin();
            default -> null;
        };

        return crawler;
    }
}
