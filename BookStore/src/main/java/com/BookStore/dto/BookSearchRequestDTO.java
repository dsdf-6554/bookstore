package com.BookStore.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class BookSearchRequestDTO {

    // Getters and Setters
    private String title; // 书名
    private String category; // 分类
    private List<String> keywords; // 关键词
    private String description; // 内容（简介）

}
