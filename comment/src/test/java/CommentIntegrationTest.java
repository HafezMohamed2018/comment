import com.darbuka.comment.api.CommentController;
import com.darbuka.comment.model.Comment;
import com.darbuka.comment.service.CommentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(CommentController.class)
@AutoConfigureMockMvc
public class CommentIntegrationTest {
    @Configuration
    static class TestConfig {
        // Your configuration beans and methods go here
    }

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    static ObjectMapper objectMapper;

    @MockBean
    private CommentService commentService;

    private Comment comment;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        comment = new Comment();
        comment.setId("1");
        comment.setContent("Test comment");
    }

    @Test
    public void testGetAllComments() throws Exception {
        List<Comment> comments = Arrays.asList(comment);
        when(commentService.getAllComments()).thenReturn(comments);

        mockMvc.perform(get("/comments"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].content").value("Test comment"));
    }

    @Test
    public void testGetCommentById() throws Exception {
        when(commentService.getCommentById("1")).thenReturn(comment);

        mockMvc.perform(get("/comments/{id}", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content").value("Test comment"));
    }

    @Test
    public void testCreateComment() throws Exception {
        when(commentService.createComment(any(Comment.class))).thenReturn(comment);

        mockMvc.perform(post("/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comment)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content").value("Test comment"));
    }

    @Test
    public void testUpdateComment() throws Exception {
        when(commentService.updateComment(eq("1"), any(Comment.class))).thenReturn(comment);

        mockMvc.perform(put("/comments/{id}", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comment)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content").value("Test comment"));
    }

    @Test
    public void testDeleteComment() throws Exception {
        mockMvc.perform(delete("/comments/{id}", "1"))
                .andExpect(status().isNoContent());

        verify(commentService, times(1)).deleteComment("1");
    }
}