package repository.jdbc;

import junit.framework.TestCase;
import model.Label;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import service.LabelService;

import java.util.List;

@RunWith(value = MockitoJUnitRunner.class)
public class LabelRepositoryImplTest extends TestCase {
    LabelService labelService = new LabelService();

    @Mock
    private Label label;

    @Mock
    private List<Label> listMock;

    @Mock
    private LabelRepositoryImpl labelRepository;

    @Before
    public void setUp() {
        labelService.setLabelRepository(labelRepository);
    }

    @Test
    public void testSave() {
        when(labelRepository.save(any())).thenReturn(label);
        when(label.getId()).thenReturn(1L);

        Label testLabel = new Label(1L, "test", 2L);
        assertEquals(testLabel.getId(), labelService.save(label).getId());
    }

    @Test
    public void testUpdate() {
        when(labelRepository.update(any())).thenReturn(label);
        when(label.getId()).thenReturn(1L);

        Label testLabel = new Label(1L, "test");
        assertEquals(testLabel.getId(), labelService.update(label).getId());
    }

    @Test
    public void testGetById() {
        when(labelRepository.getById(anyLong())).thenReturn(label);
        when(label.getId()).thenReturn(1L);
        when(label.getName()).thenReturn("test");
        when(label.getPostId()).thenReturn(2L);

        Label testLabel = new Label(1L, "test", 2L);
        assertEquals(testLabel.getId(), labelService.getById(1L).getId());
    }

    @Test
    public void testDeleteById() {
        doNothing().when(labelRepository).deleteById(anyLong());
        labelService.deleteById(1L);
        verify(labelRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testGetAll() {
        when(labelRepository.getAll()).thenReturn(listMock);
        when(listMock.get(anyInt())).thenReturn(label);

        labelService.getAll();
        listMock.get(1);
        verify(labelRepository).getAll();
        verify(listMock).get(1);
    }
}