package pl.sda.library.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.sda.library.LibraryApplication;
import pl.sda.library.domain.model.Series;
import pl.sda.library.domain.model.exception.SeriesAlreadyExistException;
import pl.sda.library.domain.model.exception.SeriesDoesNotExistException;
import pl.sda.library.domain.service.SeriesService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LibraryApplication.class)
public class SeriesServiceTest {
    @Autowired
    private SeriesService seriesService;

    @Test
    public void should_return_id_bigger_than_zero_when_series_added() {
        //when
        Series series = new Series(null, "Rambo", "Doo jakiś opis");

        //given
        Long id = seriesService.addSeries(series);

        //then
        assertNotEquals(java.util.Optional.of(0L), id);
    }

    @Test
    public void should_throw_SeriesAlreadyExistException_when_series_already_exist() {
        //when
        Series series = new Series(null, "Commando", "Doo jakiś opis");
        seriesService.addSeries(series);

        //then
        assertThrows(SeriesAlreadyExistException.class, () -> seriesService.addSeries(series));
    }

    @Test
    public void should_return_changed_title_while_edit() {
        //when
        Series series = new Series(null, "Jack", "Doo jakiś opis");
        Long id = seriesService.addSeries(series);
        Series toEdit = seriesService.findSeries(id);
        toEdit.setTitle("Jack Ryan");

        //given
        Series afterEdit = seriesService.editSeries(toEdit, id);

        //then
        assertEquals("Jack Ryan", afterEdit.getTitle());
    }

    @Test
    public void should_throw_SeriesDoesNotExistException_when_series_doesnt_exist() {
        //when
        Series series = new Series(null, "Piraci", "Doo jakiś opis");
        Long id = seriesService.addSeries(series);
        Long notExistID = 0L;
        Series toEdit = seriesService.findSeries(id);
        toEdit.setTitle("Piraci z karaibów");
        toEdit.setId(0L);

        //then
        assertThrows(SeriesDoesNotExistException.class, () -> seriesService.editSeries(toEdit, notExistID));
    }

    @Test
    public void should_return_size__plus_2_when_2_serieses_added() {
        //when
        final int SIZE = seriesService.findAllSeries().size() + 2;
        seriesService.addSeries(new Series(null, "Niezniszczalni", "Doo"));
        seriesService.addSeries(new Series(null, "Powrót do przyszłości", "Browm"));

        //given
        int result = seriesService.findAllSeries().size();

        //then
        assertEquals(SIZE, result);
    }

    @Test
    public void should_return_size__minus_1_when_one_series_deleted() {
        //when
        final int SIZE = seriesService.findAllSeries().size() - 1;
        seriesService.deleteSeries(2L);

        //given
        int result = seriesService.findAllSeries().size();

        //then
        assertEquals(SIZE, result);
    }

}
