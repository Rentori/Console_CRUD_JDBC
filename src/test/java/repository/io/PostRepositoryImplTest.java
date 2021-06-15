package repository.io;

import junit.framework.TestCase;
import model.Post;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.runners.MockitoJUnitRunner;
import service.PostService;

import java.util.List;

@RunWith(value = MockitoJUnitRunner.class)
public class PostRepositoryImplTest extends TestCase {
    PostService postService = new PostService();

    @Mock
    private Post post;

    @Mock
    private List<Post> listMock;

    @Mock
    private PostRepositoryImpl postRepository;

    @Before
    public void setUp() {
       postService.setPostRepository(postRepository);
    }

    @Test
    public void testSave() {
        when(postRepository.save(any())).thenReturn(post);
        when(post.getId()).thenReturn(1l);
        when(post.getContent()).thenReturn("testcontent");

        Post testPost = new Post(1L, "testcontent");
        assertEquals(testPost.getId(), postService.save(any()).getId());
        assertEquals(testPost.getContent(), postService.save(any()).getContent());
    }

    @Test
    public void testUpdate() {
        when(postRepository.update(any())).thenReturn(post);
        when(post.getId()).thenReturn(1l);
        when(post.getContent()).thenReturn("testcontent");

        Post testPost = new Post(1L, "testcontent");
        assertEquals(testPost.getId(), postService.update(any()).getId());
        assertEquals(testPost.getContent(), postService.update(any()).getContent());
    }

    @Test
    public void testGetById() {
        when(postRepository.getById(anyLong())).thenReturn(post);
        when(post.getId()).thenReturn(1l);
        when(post.getContent()).thenReturn("testcontent");

        Post testPost = new Post(1L, "testcontent");
        assertEquals(testPost.getId(), postService.getById(anyLong()).getId());
        assertEquals(testPost.getContent(), postService.getById(anyLong()).getContent());
    }

    @Test
    public void testDeleteById() {
        doNothing().when(postRepository).deleteById(anyLong());
        postService.deleteById(1L);
        verify(postRepository).deleteById(1L);
    }

    @Test
    public void testGetAll() {
        when(postRepository.getAll()).thenReturn(listMock);
        when(listMock.size()).thenReturn(1);

        postService.getAll();
        verify(postRepository).getAll();

        listMock.size();
        verify(listMock).size();
    }
}