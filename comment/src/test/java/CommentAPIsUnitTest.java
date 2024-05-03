import com.darbuka.comment.api.CommentController;
import com.darbuka.comment.model.Comment;
import com.darbuka.comment.service.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class CommentAPIsUnitTest {

    @Mock
    private CommentService commentService;

    @InjectMocks
    private CommentController commentController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllComments() {
        // Create sample comments
        Comment comment1 = new Comment();
        comment1.setId("1");
        comment1.setContent("Comment 1");

        Comment comment2 = new Comment();
        comment2.setId("2");
        comment2.setContent("Comment 2");

        List<Comment> comments = Arrays.asList(comment1, comment2);

        // Mock the behavior of commentService.getAllComments() method
        when(commentService.getAllComments()).thenReturn(comments);

        // Call the controller method
        List<Comment> result = commentController.getAllComments();

        // Verify the response
        assertEquals(comments.size(), result.size());
        assertTrue(result.contains(comment1));
        assertTrue(result.contains(comment2));
    }

    @Test
    public void testGetCommentById() {
        // Create a sample comment
        String id = "1";
        Comment comment = new Comment();
        comment.setId(id);
        comment.setContent("Comment 1");

        // Mock the behavior of commentService.getCommentById() method
        when(commentService.getCommentById(id)).thenReturn(comment);

        // Call the controller method
        ResponseEntity<Comment> responseEntity = commentController.getCommentById(id);

        // Verify the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(comment, responseEntity.getBody());
    }

    @Test
    public void testGetCommentById_NotFound() {
        // Mock the behavior of commentService.getCommentById() method to return null
        String id = "999"; // Assuming comment with ID '999' doesn't exist
        when(commentService.getCommentById(id)).thenReturn(null);

        // Call the controller method
        ResponseEntity<Comment> responseEntity = commentController.getCommentById(id);

        // Verify the response
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testCreateComment() {
        // Create a sample comment
        Comment comment = new Comment();
        comment.setAuthorId("user123");
        comment.setContent("Test comment");

        // Mock the behavior of commentService.createComment() method
        Comment createdComment = new Comment();
        createdComment.setId("123");
        createdComment.setAuthorId("user123");
        createdComment.setContent("Test comment");
        when(commentService.createComment(comment)).thenReturn(createdComment);

        // Call the controller method
        ResponseEntity<Comment> responseEntity = commentController.createComment(comment);

        // Verify the response
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(createdComment, responseEntity.getBody());
    }

    @Test
    public void testUpdateComment() {
        // Create a sample comment
        String id = "123";
        Comment comment = new Comment();
        comment.setAuthorId("user123");
        comment.setContent("Updated comment");

        // Mock the behavior of commentService.updateComment() method
        Comment updatedComment = new Comment();
        updatedComment.setId(id);
        updatedComment.setAuthorId("user123");
        updatedComment.setContent("Updated comment");
        when(commentService.updateComment(id, comment)).thenReturn(updatedComment);

        // Call the controller method
        ResponseEntity<Comment> responseEntity = commentController.updateComment(id, comment);

        // Verify the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedComment, responseEntity.getBody());
    }

    @Test
    public void testDeleteComment() {
        // Mock the behavior of commentService.deleteComment() method
        String id = "123";
        doNothing().when(commentService).deleteComment(id);

        // Call the controller method
        ResponseEntity<Void> responseEntity = commentController.deleteComment(id);

        // Verify the response
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
}