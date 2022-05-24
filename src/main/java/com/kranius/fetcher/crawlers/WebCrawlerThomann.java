package com.kranius.fetcher.crawlers;

import com.gargoylesoftware.htmlunit.html.*;
import com.kranius.fetcher.http.SimpleWebClient;
import com.kranius.fetcher.models.Produit;
import com.kranius.fetcher.models.Utilisateur;

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

        HtmlPage page = webClient.getWebPage(url);

        DomNodeList<DomElement> scriptsTag = page.getElementsByTagName("script");
        scriptsTag.forEach(node -> {
            System.out.println(node);
        });

       // List<HtmlElement> referenceElement = page.getByXPath("//div[@class='']");
       // List<HtmlElement> priceElement = page.getByXPath("//div[@class='price']");

        List<HtmlElement> availabilityElement = page.getByXPath("//div[@class='fx-availability']");
        List<HtmlElement> titleElement = page.getByXPath("//div[@class='fx-product-headline']");


        /*
        if (!items.isEmpty()) {
            // Iterate over all elements
            for (HtmlElement item : items) {

                // Get the details from <p class="result-info"><a href=""></a></p>
                HtmlAnchor itemAnchor = ((HtmlAnchor) item.getFirstByXPath(".//p[@class='result-info']/a"));

                // Get the price from <a><span class="result-price"></span></a>
                HtmlElement spanPrice = ((HtmlElement) item.getFirstByXPath(".//a/span[@class='result-price']")) ;

                String itemName = itemAnchor.asText();
                String itemUrl =  itemAnchor.getHrefAttribute();

                // It is possible that an item doesn't have any price
                String itemPrice = spanPrice == null ? "0.0" : spanPrice.asText() ;

                System.out.println( String.format("Name : %s Url : %s Price : %s", itemName, itemPrice, itemUrl));

            }
        }
        else {
            System.out.println("No items found !");
        }
        */



        // available
        produit.setAvailable(true);
        // reference
        produit.setReference("sfsdfsdf");
        // prix
        produit.setPrice("123");
        // name
        produit.setName("babyface");

        return produit;
    }

    @Override
    public boolean checkUrl(URL url) {
        return false;
    }
}
