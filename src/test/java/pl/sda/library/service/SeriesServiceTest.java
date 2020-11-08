package pl.sda.library.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.sda.library.LibraryApplication;
import pl.sda.library.model.Author;
import pl.sda.library.model.Series;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LibraryApplication.class)
public class SeriesServiceTest {
    @Autowired
    private SeriesService seriesService;

    @Test
    public void should_return_true_when_series_added() {
        //when
        Series series=new Series(null,"Rambo","Doo jakiś opis");

        //given
        boolean result = seriesService.addSeries(series);

        //then
        assertTrue(result);
    }

    @Test
    public void should_return_size__plus_2_when_2_serieses_added() {
        //when
        final int SIZE = seriesService.getAllSerieses().size() + 2;
        seriesService.addSeries(new Series(null,"John Rambo","Doo"));
        seriesService.addSeries(new Series(null,"Jack Ryan","Browm"));

        //given
        int result = seriesService.getAllSerieses().size();

        //then
        assertEquals(SIZE, result);
    }
    @Test
    public void should_return_size__minus_1_when_one_series_deleted() {
        //when
        final int SIZE = seriesService.getAllSerieses().size() -1;
        seriesService.delSeries(2L);

        //given
        int result = seriesService.getAllSerieses().size();

        //then
        assertEquals(SIZE, result);
    }

}