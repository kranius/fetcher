package com.kranius.fetcher.http;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.net.URL;

public interface WebClientInterface {
    HtmlPage getWebPage(URL url) throws IOException;
}
