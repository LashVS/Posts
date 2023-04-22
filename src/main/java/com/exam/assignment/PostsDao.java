package com.exam.assignment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostsDao {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final String TABLE_POSTS_NAME = "posts";
    private static final String COLUMN_CREATOR_NAME = "creator_name";
    private static final String COLUMN_CREATOR_LAST_NAME = "creator_last_name";
    private static final String COLUMN_POST_CONTENT_NAME = "post_content";
    private static final String COLUMN_POST_CREATION_DATE_NAME = "post_creation_date";


    public static void savePost(Post post) throws SQLException {
        Connection conn = DatabaseUtil.getConnection();
        Statement statement = conn.createStatement();
        statement.execute(postInsertScript(post.getCreatorName(), post.getCreatorLastName(), post.getPostContent()));

        statement.close();
        conn.close();
    }

    public static List<Post> getPosts() throws SQLException {
        Connection conn = DatabaseUtil.getConnection();
        Statement statement = conn.createStatement();
        ResultSet results = statement.executeQuery(String.format("SELECT * FROM %s", TABLE_POSTS_NAME));
        List<Post> posts = new ArrayList<>();

        while(results.next()) {
            int id = results.getInt("id");
            String firstName = results.getString("creator_name");
            String lastName = results.getString("creator_last_name");
            String postContent = results.getString("post_content");
            LocalDateTime postCreationDate = LocalDateTime.parse(results.getString("post_creation_date"), formatter);

            posts.add(Post.of(id, firstName, lastName, postContent, postCreationDate));
        }

        return posts;
    }


    private static String postInsertScript(String firstName, String lastName, String postContent){
        return String.format("""
                INSERT INTO %s
                    (%s, %s, %s, %s)
                VALUES
                    ('%s', '%s', '%s', NOW())""", TABLE_POSTS_NAME, COLUMN_CREATOR_NAME, COLUMN_CREATOR_LAST_NAME,
                COLUMN_POST_CONTENT_NAME, COLUMN_POST_CREATION_DATE_NAME, firstName, lastName, postContent);
    }
}
