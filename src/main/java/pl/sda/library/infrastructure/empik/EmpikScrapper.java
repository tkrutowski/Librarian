package pl.sda.library.infrastructure.empik;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class EmpikScrapper {

    private final static String URL = "https://empik.pl";

    public static List<String> finder(String phrase) {
        List<String> productsList = new ArrayList<>();
        //https://www.empik.com/szukaj/produkt?q=dobry&qtype=basicForm&resultsPP=45
        String url = URL + "/szukaj/produkt?q=" + phrase.replace(" ","%20") + "&qtype=basicForm&ac=true";
        if (phrase.isEmpty()){
            throw new RuntimeException("Pole wyszukiwanie nie może zostać puste!");
        }
        try {
            Document page = Jsoup.connect(url).userAgent("Jsoup Scraper").get();
            productsList = connectElements(getTitles(page), getAuthors(page));//, getIsbn(page), getImageLink(page), getDescription(page), getItemType(page));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return productsList;
    }

    private static List<String> connectElements(Elements title, Elements authors){//, Elements isbn, Elements image, Elements description, Elements itemType) {

        List<String> foundElements = new ArrayList<>();
        //Integer minValue = Collections.min(List.of(title.size(),authors.size()));//, isbn.size(), image.size(), description.size(), itemType.size() ));
        //heroku error with List.of!!!
        Integer minValue = title.size();

        //String authorsList = authors.stream().map(Element::text).collect(Collectors.joining(","));
        String typeOfItem;
        for (int i = 0; i < minValue; i++) {
            /*
            if (itemType.get(i).text().contains("mp3")) {
                typeOfItem = "AUDIOBOOK";
            }
            else if (itemType.get(i).text().contains("ebook")){
                typeOfItem = "EBOOK";
            }
            else  {
                typeOfItem = "BOOK";
            }
            */

            foundElements.add(URL
                    + "::"+authors.get(i).text()
                    + "::"+title.get(i).attr("data-product-name")
//                    + "::"+description.get(i).attr("title").substring(description.get(i).attr("title").lastIndexOf("<br/>")+5).trim()
//                    + "::"+URL+"/"+image.get(i).attr("src")
//                    + "::"+typeOfItem
//                    + "::"+isbn.get(i).attr("content")
                    + "");
        }
        return foundElements;
    }
    /*
    * private String bookstore; //nazwa księgarni np. Empik
    * private String authors; //autorzy: imie nazwisko            !!!!!!!!!!!
    * private String title; //tytuł                                 !!!!!!!!!!
    * private String description; //krótki opis książki           !!!!!!!!!!
    * private String cover; //link do okładki                     !!!!!!!!!
    * private EditionType editionType; //typ: EBOOK, AUDIOBOOK lub BOOK   !!!!!!!!!!!!
    * private String isbn;  //
    private String categories; //kategorie np. Thriller, kryminał  !!!!!!!!!
    private ReadingStatus readingStatus;
    private Long idBook;
    private String userLogin;
    private String series; //cykl książek np 'Jack Reacher'   !!!!!!!!!!
    private String subtitle;//podtytuł jeżęli jest              !!!!!!!!!!
    private OwnershipStatus ownershipStatus;
    private LocalDate readFrom; //zaczęto czytać
    private LocalDate readTo; //skończono czytać
    private String info; //jakieś swoje zapiski
    private boolean isRead;//czy przeczytana

     */

    private static Elements pagesTraverse (Document doc, String cssQuery) throws IOException {
        Elements siteElements = new Elements();
        Elements pagination = doc.select(".pagination > li > a");
        if (pagination.size() == 0) {
            siteElements = doc.select(cssQuery);
        }
        else {
            for (Element e:pagination) {
                String url = e.attr("abs:href");
                Document page = Jsoup.connect(url).get();
                Elements pageElements = doc.select(cssQuery);
                siteElements.addAll(pageElements);
            }
        }
        return siteElements;
    }


    private static Elements getAuthors(Document doc) throws IOException {
        return pagesTraverse(doc, "div.search-content > div > div > div > div > div.name > div.smartAuthorWrapper > a");
    }

    private static Elements getTitles(Document doc) throws IOException {
        return pagesTraverse(doc,"div.search-content > div");
    }
//
//    private static Elements getIsbn(Document doc) throws IOException {
//        return pagesTraverse(doc,"#OfferListingFull > div.polka > div > div > div[itemscope] > meta[itemprop=isbn] ");
//    }
//
//    private static Elements getImageLink(Document doc) throws IOException {
//        return pagesTraverse(doc,"#OfferListingFull > div.polka > div > div > a > img");
//    }
//
//    private static Elements getDescription(Document doc) throws IOException {
//        return pagesTraverse(doc,"#OfferListingFull > div.polka > div > div > a ");
//    }
//
//    private static Elements getItemType (Document doc) throws IOException {
//        return pagesTraverse(doc,"#OfferListingFull > div.polka > div > div > a > div");
    }



