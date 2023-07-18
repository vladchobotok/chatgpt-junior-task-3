package com.example.juniortask3;

import com.example.juniortask3.controller.SocialMediaController;
import com.example.juniortask3.entity.Post;
import com.example.juniortask3.entity.User;
import com.example.juniortask3.exception.ResourceNotFoundException;
import com.example.juniortask3.repository.PostRepository;
import com.example.juniortask3.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SocialMediaControllerTest {

    @InjectMocks
    private SocialMediaController socialMediaController;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PostRepository postRepository;

    @Test
    public void createUserTest() {
        User user = new User();
        user.setUsername("John");

        when(userRepository.save(Mockito.any())).thenReturn(user);

        User savedUser = socialMediaController.createUser(user);

        assertNotNull(savedUser);
        assertEquals("John", savedUser.getUsername());

        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void getAllUsersTest() {
        List<User> userList = new ArrayList<>();
        userList.add(new User("John"));
        userList.add(new User("Jane"));

        when(userRepository.findAll()).thenReturn(userList);

        List<User> result = socialMediaController.getAllUsers();

        assertNotNull(result);
        assertEquals(2, result.size());

        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void createPostTest() {
        User author = new User();
        author.setUsername("John");

        Post post = new Post();
        post.setTitle("My First Post");
        post.setBody("Hello, world!");
        post.setAuthor(author);

        when(postRepository.save(Mockito.any())).thenReturn(post);

        Post savedPost = socialMediaController.createPost(post);

        assertNotNull(savedPost);
        assertEquals("My First Post", savedPost.getTitle());
        assertEquals("Hello, world!", savedPost.getBody());
        assertEquals(author, savedPost.getAuthor());

        verify(postRepository, times(1)).save(post);
    }

    @Test
    public void getAllPostsTest() {
        User author = new User();
        author.setUsername("John");

        List<Post> postList = new ArrayList<>();
        postList.add(new Post("Post 1", "This is post 1", author));
        postList.add(new Post("Post 2", "This is post 2", author));

        when(postRepository.findAll()).thenReturn(postList);

        List<Post> result = socialMediaController.getAllPosts();

        assertNotNull(result);
        assertEquals(2, result.size());

        verify(postRepository, times(1)).findAll();
    }

    @Test
    public void followUserTest() {
        User follower = new User();
        follower.setUsername("John");

        User following = new User();
        following.setUsername("Jane");

        when(userRepository.findById(1L)).thenReturn(Optional.of(follower));
        when(userRepository.findById(2L)).thenReturn(Optional.of(following));

        User result = socialMediaController.followUser(1L, 2L);

        assertNotNull(result);
        assertTrue(result.getFollowing().contains(following));

        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).findById(2L);
        verify(userRepository, times(1)).save(follower);
    }

    @Test
    public void likePostTest() {
        User user = new User();
        user.setUsername("John");

        Post post = new Post();
        post.setTitle("My First Post");
        post.setBody("Hello, world!");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));

        User result = socialMediaController.likePost(1L, 1L);

        assertNotNull(result);
        assertTrue(post.getLikes().contains(user));

        verify(userRepository, times(1)).findById(1L);
        verify(postRepository, times(1)).findById(1L);
        verify(postRepository, times(1)).save(post);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void followUserResourceNotFoundExceptionTest() {
        User follower = new User();
        follower.setUsername("John");

        when(userRepository.findById(1L)).thenReturn(Optional.of(follower));
        when(userRepository.findById(2L)).thenReturn(Optional.empty()); // Simulating a non-existing user

        socialMediaController.followUser(1L, 2L);
    }
}
