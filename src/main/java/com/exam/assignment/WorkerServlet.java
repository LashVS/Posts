package com.exam.assignment;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(name = "workerServlet", value = "/worker-servlet")
public class WorkerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String html = "";
        try {
            List<Post> posts = PostsDao.getPosts();
            for (Post post: posts){
                html = html
                        .concat(String.format("<h2>Author: %s %s </h2>", post.getCreatorName(), post.getCreatorLastName()))
                        .concat(String.format("<p><b>ID: %d </b></p>", post.getId()))
                        .concat(String.format("<p>%s</p>", post.getPostContent()))
                        .concat(String.format("<p><b>Created At: %s </b></p>",
                                post.getPostCreationDate().format(DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy hh:mm:ss a"))))
                        .concat("<hr>");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Sql Exception: " + e.getMessage());
        }

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println(html);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        Post post = Post.of(req.getParameter("first_name"), req.getParameter("last_name"), req.getParameter("post_content"));

        if (post.getCreatorName().isEmpty() || post.getCreatorLastName().isEmpty() || post.getPostContent().isEmpty()){
            out.println("<h1> Field(s) are empty </h1>");
            return;
        }

        try {
            PostsDao.savePost(post);
        } catch (SQLException e) {
            throw new RuntimeException("Sql Exception: " + e.getMessage());
        }

        out.println("<h1> Post Saved </h1>");
    }
}