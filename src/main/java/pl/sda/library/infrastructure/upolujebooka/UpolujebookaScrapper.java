package pl.sda.library.infrastructure.upolujebooka;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import pl.sda.library.domain.model.Book;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UpolujebookaScrapper {
    private final static String pageURL = "https://upolujebooka.pl";

    public static Book findBookFromUrl(String url){
        Book book = new Book();
        try{
            if (!url.isEmpty()) {
                StringBuilder pageContent = new ReadPageContent(url).invoke();
                Document documentURL = Jsoup.parse(pageContent.toString());
                book.setAuthors((documentURL.select("div.authors > h2 >a").textNodes().stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(","))));
                book.setCategories(documentURL.select("div.container > ol > li > span > a").eachText().stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(",")));
                book.setTitle(documentURL.select("div.col-lg-6.col-md-8.col-sm-8 > h1").first().childNode(0).toString().trim());
                book.setDescription(documentURL.select("div.description > p").text());
                book.setCover(pageURL + "/" + documentURL.select(" Div.DetailImage > img ").first().attr("src"));
            }
        }catch (Exception exception) {
            exception.printStackTrace();
            book = new Book();
        }
        return book;

    }

    private static List<String> getUniqueElements(Elements element) {
        List<String> foundElements = new ArrayList<>();
        HashSet<String> elementHashSet = new HashSet<>();
        //System.out.println("Autors list " + element.size());
        for (int i = 0; i< element.size(); i++){
            if (elementHashSet.add(element.get(i).text())) foundElements.add(element.get(i).text());
        }

        //System.out.println("Autors unique list " + foundElements.size());
        return foundElements;
    }

    public static List<String> finder(String phrase) {
        List<String> productsList = new ArrayList<>();
        //https://upolujebooka.pl/szukaj,dobry+omen.html#filtr
        String url = pageURL + "/szukaj," + phrase.replace(" ","+") + ".html?count=60";
        if (phrase.isEmpty()){
            throw new RuntimeException("Pole wyszukiwanie nie może zostać puste!");
        }
        try {
            Document page = Jsoup.connect(url).userAgent("Jsoup Scraper").get();
            productsList = connectElements(getTitles(page), getAuthors(page), getIsbn(page), getImageLink(page), getDescription(page), getItemType(page));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return productsList;
    }

    private static List<String> connectElements(Elements title, Elements authors, Elements isbn, Elements image, Elements description, Elements itemType) {

        List<String> foundElements = new ArrayList<>();
        //Integer minValue = Collections.min(List.of(title.size(),authors.size(), isbn.size(), image.size(), description.size(), itemType.size()));

        //heroku error with List.of!!!
        Integer minValue =title.size();

        //String authorsList = authors.stream().map(Element::text).collect(Collectors.joining(","));
        String typeOfItem;
        for (int i = 0; i < minValue; i++) {
            if (itemType.get(i).text().contains("mp3")) {
                typeOfItem = "AUDIOBOOK";
            }
            else if (itemType.get(i).text().contains("ebook")){
                typeOfItem = "EBOOK";
            }
            else  {
                typeOfItem = "BOOK";
            }
            foundElements.add(pageURL
                    + "::"+authors.get(i).text()
                    + "::"+title.get(i).text()
                    + "::"+description.get(i).attr("title").substring(description.get(i).attr("title").lastIndexOf("<br/>")+5).trim()
                    + "::"+ pageURL +"/"+image.get(i).attr("src")
                    + "::"+typeOfItem
                    + "::"+isbn.get(i).attr("content"));
        }
        return foundElements;
    }


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


    private static Elements getTitles(Document doc) throws IOException {
         return pagesTraverse(doc,"#OfferListingFull > div.polka > div > div > a > h2");
    }

    private static Elements getAuthors(Document doc) throws IOException {
        return pagesTraverse(doc, "#OfferListingFull > div.polka > div > div > div[itemscope] > div");
    }

    private static Elements getIsbn(Document doc) throws IOException {
        return pagesTraverse(doc,"#OfferListingFull > div.polka > div > div > div[itemscope] > meta[itemprop=isbn] ");
    }

    private static Elements getImageLink(Document doc) throws IOException {
        return pagesTraverse(doc,"#OfferListingFull > div.polka > div > div > a > img");
    }

    private static Elements getDescription(Document doc) throws IOException {
        return pagesTraverse(doc,"#OfferListingFull > div.polka > div > div > a ");
    }

    private static Elements getItemType (Document doc) throws IOException {
        return pagesTraverse(doc,"#OfferListingFull > div.polka > div > div > a > div");
    }


    private static class ReadPageContent {
        private String url;

        public ReadPageContent(String url) {
            this.url = url;
        }

        public StringBuilder invoke() throws IOException {
            InputStream inputStream = null;
            BufferedReader bufferedReader;
            StringBuilder pageContent = new StringBuilder();
            String line;
            URL inputUrl = new URL(url);

            try {
                inputStream = inputUrl.openStream();

            }catch (Exception exception) {
                throw new IOException();
            }
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));


            while ((line=bufferedReader.readLine()) !=null){
                pageContent.append(line);
            }
            return pageContent;
        }
    }
}
