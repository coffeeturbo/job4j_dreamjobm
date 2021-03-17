package dream.servlet;

import dream.model.Post;
import dream.store.MemStore;
import dream.store.PsqlStore;
import dream.store.Store;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@RunWith(PowerMockRunner.class)
@PrepareForTest(PsqlStore.class)
public class PostServletTest {

    @Test
    public void doGetPostsSuccess() throws ServletException, IOException {

        Store mockStore = MemStore.instOf();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

        when(request.getRequestDispatcher(any(String.class))).thenReturn(requestDispatcher);

        PostServlet postServlet = new PostServlet();
        PowerMockito.mockStatic(PsqlStore.class);
        Mockito.when(PsqlStore.instOf()).thenReturn(mockStore);

        postServlet.doGet(request, response);

        verify(request, times(1)).getRequestDispatcher("posts.jsp");

        Collection<Post> posts = PsqlStore.instOf().findAllPosts();

        MatcherAssert.assertThat(posts.size(), Matchers.is(3));
    }

    @Test
    public void doPostCreateSuccess() throws ServletException, IOException {

        Store mockStore = MemStore.instOf();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        String postName = "Viacheslav Savushkin";
        when(request.getParameter("name")).thenReturn(postName);
        when(request.getParameter("id")).thenReturn("200");

        PostServlet postServlet = new PostServlet();
        PowerMockito.mockStatic(PsqlStore.class);
        Mockito.when(PsqlStore.instOf()).thenReturn(mockStore);

        postServlet.doPost(request, response);
        verify(request, times(1)).getRequestDispatcher(request.getContextPath() + "/posts.do");
        Post addedPost = PsqlStore.instOf().findPostById(200);

        MatcherAssert.assertThat(addedPost.getName(), Matchers.is(postName));
        MatcherAssert.assertThat(addedPost.getId(), Matchers.is(200));
    }
}