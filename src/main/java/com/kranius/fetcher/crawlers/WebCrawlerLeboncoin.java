package com.kranius.fetcher.crawlers;

import com.kranius.fetcher.models.Produit;
import com.kranius.fetcher.models.Utilisateur;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

public class WebCrawlerLeboncoin implements  WebCrawlerInterface {
    @Override
    public Produit makeProduct(URL url, Utilisateur user) throws IOException {
        return null;
    }

    @Override
    public boolean checkUrl(URL url) {
        return false;
    }
}
