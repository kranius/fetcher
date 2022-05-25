package com.kranius.fetcher.crawlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.html.*;
import com.kranius.fetcher.http.SimpleWebClient;
import com.kranius.fetcher.models.Produit;
import com.kranius.fetcher.models.Utilisateur;
import com.kranius.fetcher.objectmappers.ThomannMapper;

import java.io.IOException;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.List;

public class WebCrawlerThomann implements WebCrawlerInterface {
    @Override
    public Produit makeProduct(URL url, Utilisateur user) throws IOException {

        Produit produit = new Produit();

        produit.setUrl(url.toString());
        produit.setLastRefreshed(ZonedDateTime.now());
        produit.setUtilisateur(user);

        SimpleWebClient webClient = SimpleWebClient.getClient();

        // seems to have problems with iframes
        HtmlPage page = webClient.getWebPage(url);

        DomNodeList<DomElement> scriptsTag = page.getElementsByTagName("script");

        for (int i=0; i<scriptsTag.size(); i++) {
            System.out.println(i + " = " + scriptsTag.get(i));
       }

        // we get the 62th script element which contains a call to `dataLayer.push(jsonblob)`
        DomElement tag = scriptsTag.get(62);
        String content = tag.getTextContent();

        // we extract only the text between parenthesis (the json) and deserialze it
        String json = content.substring(content.indexOf("(") + 1, content.indexOf(")"));
        ObjectMapper mapper = new ObjectMapper();
        ThomannMapper thomannMapper = mapper.readValue(json, ThomannMapper.class);


       // List<HtmlElement> referenceElement = page.getByXPath("//div[@class='']");
       // List<HtmlElement> priceElement = page.getByXPath("//div[@class='price']");

        List<HtmlElement> availabilityElement = page.getByXPath("//div[@class='fx-availability']");
        List<HtmlElement> titleElement = page.getByXPath("//div[@class='fx-product-headline']");

        System.out.println(json);


        // available
        produit.setAvailable(true);
        // reference
        produit.setReference(thomannMapper.getReference());
        // prix
        produit.setPrice(thomannMapper.getPrice());
        // name
        produit.setName(thomannMapper.getName());

        return produit;
    }

    @Override
    public boolean checkUrl(URL url) {
        return false;
    }
}
