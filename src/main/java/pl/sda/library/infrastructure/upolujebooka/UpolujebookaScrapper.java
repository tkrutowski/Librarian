package pl.sda.library.infrastructure.upolujebooka;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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
                String seria = null;
                if (documentURL.select("div.publisher").text().startsWith("Cykl: ")){
                    book.setSeries(documentURL
                            .select("div.publisher")
                            .text()
                            .substring(documentURL
                                    .select("div.publisher")
                                    .text()
                                    .indexOf(": ")+2,
                                    documentURL
                                            .select("div.publisher")
                                            .text()
                                            .indexOf(" (")
                            )
                    );
                    book.setBookInSeriesNo(Integer.valueOf(documentURL.select("div.publisher > span > a > b").text()));
                }
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
        return foundElements;
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
