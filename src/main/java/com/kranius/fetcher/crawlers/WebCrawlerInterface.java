package com.kranius.fetcher.crawlers;

import com.kranius.fetcher.models.Produit;
import com.kranius.fetcher.models.Utilisateur;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

public interface WebCrawlerInterface {
    Produit makeProduct(URL url, Utilisateur utilisateur) throws IOException;

    boolean checkUrl(URL url);
}
