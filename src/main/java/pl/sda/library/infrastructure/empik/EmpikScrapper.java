package pl.sda.library.infrastructure.empik;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import pl.sda.library.domain.model.EditionType;

import java.io.IOException;
import java.util.*;

@Component
public class EmpikScrapper {

    private final static String URL = "https://empik.com";
    private final static String EBOOK = "/audiobooki-i-ebooki/ebooki,3501,s?q=";
    private final static String BOOK = "/ksiazki,31,s?q=";
    private final static String AUDIOBOOK = "/audiobooki-i-ebooki/audiobooki,3502,s?q=";
    private final static Map<Enum, String> chanelMap = new Hashtable<>();


    public static List<String> finder(String phrase) {
        chanelMap.putIfAbsent(EditionType.AUDIOBOOK, "/audiobooki-i-ebooki/audiobooki,3502,s?q=");
        chanelMap.putIfAbsent(EditionType.BOOK, "/ksiazki,31,s?q=");
        chanelMap.putIfAbsent(EditionType.EBOOK, "/audiobooki-i-ebooki/ebooki,3501,s?q=");

        List<String> productsList = new ArrayList<>();
        //https://www.empik.com/szukaj/produkt?q=dobry&qtype=basicForm&resultsPP=45
        List<String> allChanels = new ArrayList<>();
        allChanels.addAll(List.of("AUDIOBOOK", "BOOK", "EBOOK"));
        for (Map.Entry<Enum, String> chanel: chanelMap.entrySet()) {
            List<String> chanelList = new ArrayList<>();
            String url = URL + chanel.getValue().toString() + phrase.replace(" ","%20");
            if (phrase.isEmpty()){
                throw new RuntimeException("Pole wyszukiwanie nie może zostać puste!");
            }
            try {
                Document page = Jsoup.connect(url).userAgent("Jsoup Scraper").get();
                chanelList = connectElements(chanel.getKey().toString(),getTitles(page), getAuthors(page), getImageLink(page), getCategory(page), getAvailability(page));//, getIsbn(page), getImageLink(page), getDescription(page), getItemType(page));
            } catch (IOException e) {
                e.printStackTrace();
            }
            productsList.addAll(chanelList);
        }
        return productsList;
    }

    private static List<String> connectElements(String chanel, Elements title, Elements authors, Elements image, Elements category, Elements availability){//, Elements isbn, Elements image, Elements description, Elements itemType) {

        List<String> foundElements = new ArrayList<>();
        Integer minValue = Collections.min(List.of(authors.size(), title.size(),image.size(), category.size(),availability.size()));//, isbn.size(), image.size(), description.size(), itemType.size() ));
        //heroku error with List.of!!!
        //Integer minValue = title.size();

        //String authorsList = authors.stream().map(Element::text).collect(Collectors.joining(","));
        String typeOfItem;
        for (int i = 0; i < minValue; i++) {
            foundElements.add(URL
                    + "::"+authors.get(i).text()
                    + "::"+title.get(i).attr("data-product-name")
//                    + "::"+description.get(i).attr("title").substring(description.get(i).attr("title").lastIndexOf("<br/>")+5).trim()
                    + "::"+image.get(i).attr("lazy-img")
                    + "::"+category.get(i).attr("data-analytics-category")
                    + "::"+chanel
                    + "::"+availability.get(i).text().trim()
//                    + "::"+isbn.get(i).attr("content")
                    + "");
        }
        return foundElements;
    }

    private static Elements pagesTraverse (Document doc, String cssQuery) throws IOException {
        Elements siteElements = new Elements();
        Elements pagination = doc.select(".pagination > a");
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
        return pagesTraverse(doc, "div.search-content > div > div > div > div > div.name > div.smartAuthorWrapper ");
    }

    private static Elements getTitles(Document doc) throws IOException {
        return pagesTraverse(doc,"div.search-content > div");
    }

    private static Elements getImageLink(Document doc) throws IOException {
        return pagesTraverse(doc,"div.search-content > div > div > div.productWrapper > a > img");
    }


    private static Elements getCategory(Document doc) throws IOException {
        return pagesTraverse(doc,"div.search-content > div > div > div.productWrapper > div > div > button");
    }


    private static Elements getAvailability(Document doc) throws IOException {
        return pagesTraverse(doc,"div.search-content > div > div > div.productWrapper > div > div.info > span >span.availability");
    }
//
//    private static Elements getDescription(Document doc) throws IOException {
//        return pagesTraverse(doc,"#OfferListingFull > div.polka > div > div > a ");
//    }
//
//    private static Elements getItemType (Document doc) throws IOException {
//        return pagesTraverse(doc,"#OfferListingFull > div.polka > div > div > a > div");

 /*   private Long idBook;
    private String authors; //autorzy: imie nazwisko            !!!!!!!!!!!
    private String title; //tytuł                                 !!!!!!!!!!
    private String cover; //link do okładki                     !!!!!!!!!
    private String categories; //kategorie np. Thriller, kryminał  !!!!!!!!!
    private String bookstore; //nazwa księgarni np. Empik
    private String userLogin;
    private String series; //cykl książek np 'Jack Reacher'   !!!!!!!!!!
    private String subtitle;//podtytuł jeżęli jest              !!!!!!!!!!
    private String description; //krótki opis książki           !!!!!!!!!!
    private EditionType editionType; //typ: EBOOK, AUDIOBOOK lub BOOK   !!!!!!!!!!!!
    private ReadingStatus readingStatus;
    private OwnershipStatus ownershipStatus;
    private LocalDate readFrom; //zaczęto czytać
    private LocalDate readTo; //skończono czytać
    private String info; //jakieś swoje zapiski
    private Boolean isRead;//czy przeczytana
    private String isbn;  //                    !!!!!!!!!!!!!!!!!!!!!!!!!!!
    private int volume;       //jak jest to część jakiejś serii  !!!!!!!!!!!!!!*/
}



