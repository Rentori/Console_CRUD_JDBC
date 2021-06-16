package repository.jdbc;

import junit.framework.TestCase;
import model.Writer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.runners.MockitoJUnitRunner;
import service.WriterService;

import java.util.List;

@RunWith(value = MockitoJUnitRunner.class)
public class WriterRepositoryImplTest extends TestCase {
    WriterService writerService = new WriterService();

    @Mock
    Writer writer;

    @Mock
    WriterRepositoryImpl writerRepository;

    @Mock
    List<Writer> listMock;

    @Before
    public void setUp() {
        writerService.setWriterRepository(writerRepository);
    }

    @Test
    public void testSave() {
        when(writerRepository.save(any())).thenReturn(writer);
        when(writer.getId()).thenReturn(1L);
        when(writer.getFirstName()).thenReturn("testfirst");
        when(writer.getLastName()).thenReturn("testlast");

        Writer writertest = new Writer(1L, "testfirst", "testlast");
        assertEquals(writertest.getId(), writerService.save(any()).getId());
        assertEquals(writertest.getFirstName(), writerService.save(any()).getFirstName());
        assertEquals(writertest.getLastName(), writerService.save(any()).getLastName());
    }

    @Test
    public void testUpdate() {
        when(writerRepository.update(any())).thenReturn(writer);
        when(writer.getId()).thenReturn(1L);
        when(writer.getFirstName()).thenReturn("testfirst");
        when(writer.getLastName()).thenReturn("testlast");

        Writer writertest = new Writer(1L, "testfirst", "testlast");
        assertEquals(writertest.getId(), writerService.update(any()).getId());
        assertEquals(writertest.getFirstName(), writerService.update(any()).getFirstName());
        assertEquals(writertest.getLastName(), writerService.update(any()).getLastName());
    }

    @Test
    public void testGetById() {
        when(writerRepository.getById(anyLong())).thenReturn(writer);
        when(writer.getId()).thenReturn(1L);
        when(writer.getFirstName()).thenReturn("testfirst");
        when(writer.getLastName()).thenReturn("testlast");

        Writer writertest = new Writer(1L, "testfirst", "testlast");
        assertEquals(writertest.getId(), writerService.getById(anyLong()).getId());
        assertEquals(writertest.getFirstName(), writerService.getById(anyLong()).getFirstName());
        assertEquals(writertest.getLastName(), writerService.getById(anyLong()).getLastName());
    }

    @Test
    public void testDeleteById() {
        doNothing().when(writerRepository).deleteById(anyLong());
        writerService.deleteById(1L);
        verify(writerRepository).deleteById(1L);
    }

    @Test
    public void testGetAll() {
        when(writerRepository.getAll()).thenReturn(listMock);
        when(listMock.size()).thenReturn(1);

        writerService.getAll();
        verify(writerRepository).getAll();

        listMock.size();
        verify(listMock).size();
    }
}