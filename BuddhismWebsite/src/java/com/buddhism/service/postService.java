package com.buddhism.service;

import com.buddhism.model.Administrator;
import com.buddhism.model.Post;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Trine
 */
public interface postService 
{
    public List getPage(final int offset, final int length);
    public void setPost(Post post) throws Exception;
    public Post setPost(Administrator postAuthor, String postTitle, String postContent, int postCategory, 
           boolean postUp) throws Exception;
    public Post setPost(Administrator postAuthor, String postTitle, String postContent, int postCategory,
           Date postDate, boolean postUp) throws Exception; //发表文章时也可以指定文章的时间
    public int getPostNumber(int type);
    public int getUpPostNumber();
    
    public void UpdatePost(int id, boolean update);
    public void UpdatePost(Post post);
    
    public List<Post> getPost(short postType, final int offset, final int length);
    public List<Post> getPost(Administrator administrator, short postType, int offset, int length);
    public List<Post> getPost();
    public Post getPost(int id);
    public List<Post> getUpPost(); //得到所有的置顶文章
    
    public List<Post> getPostForAdministrator(final Administrator administrator, final int offset, final int length);
    public List<Post> getPostFromTrash(final Administrator administrator, final int offset, final int length);
    
    public void deletePost(int id);
    public void removePost(int id);
    public void restorePost(int id);
    
    
    public void addPostClickTimes(int postId);  //add click time by 1
    public int getPostClickTimes(int postId);   //get click times
    
    public List<Post> getPostNameLike(String queryStr);
    
    public int getPostBetweenAnd(String start, String end);       
}