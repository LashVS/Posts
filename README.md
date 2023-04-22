CREATE SCHEMA posts;

CREATE TABLE posts(
  id INT AUTO_INCREMENT PRIMARY KEY,
  creator_name VARCHAR(255),
  creator_last_name VARCHAR(255),
  post_content TEXT(65535),
  post_creation_date DATETIME
);
