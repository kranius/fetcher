package com.kranius.fetcher.http;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.net.URL;

public class SimpleWebClient implements WebClientInterface {

    private static volatile SimpleWebClient instance;
    private final WebClient webClient;

    @Override
    public HtmlPage getWebPage(URL url) throws IOException {
        return webClient.getPage(url.toString());
    }

    private SimpleWebClient() {
        this.webClient = new WebClient();
        this.webClient.getOptions().setCssEnabled(false);
        this.webClient.getOptions().setJavaScriptEnabled(false);
    }

    public static SimpleWebClient getClient() {
        SimpleWebClient result = instance;

        if (result != null) {
            return result;
        }

        synchronized (SimpleWebClient.class) {
            if (instance == null) {
                instance = new SimpleWebClient();
            }
            return instance;
        }
    }
}
