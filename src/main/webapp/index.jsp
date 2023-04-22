<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add New Post</title>
</head>
<body>

<h1>Add New Post</h1>

<form action="worker-servlet" method="POST">
    <input name="first_name" type="text" placeholder="First Name">
    <input name="last_name" type="text" placeholder="Last Name">
    <br>
    <textarea rows="10" cols="46" name="post_content" placeholder="Your Post Here..."></textarea>
    <br>
    <input type="submit">
</form>

<a href="worker-servlet"><h1>Show Posts</h1></a>

</body>
</html>