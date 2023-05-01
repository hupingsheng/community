### 开发社区首页



```sql
CREATE TABLE `discuss_post` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '发布者',
  `title` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci COMMENT '内容',
  `type` int DEFAULT NULL COMMENT '0-普通; 1-置顶;',
  `status` int DEFAULT NULL COMMENT '0-正常; 1-精华; 2-拉黑;',
  `create_time` timestamp NULL DEFAULT NULL,
  `comment_count` int DEFAULT NULL COMMENT '帖子的评论总量',
  `score` double DEFAULT NULL COMMENT '分数，帖子排名用的',
  PRIMARY KEY (`id`),
  KEY `index_user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=281 DEFAULT CHARSET=utf8mb3;
```



- 分步实现
  - 显示前10个帖子
  - 开发分页组件，分页显示所有的帖子
