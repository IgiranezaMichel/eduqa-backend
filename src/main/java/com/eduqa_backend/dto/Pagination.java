package com.eduqa_backend.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pagination <T>{
private int pageNumber;
private int totalPage;
private long size;
private List<T> data;
}
