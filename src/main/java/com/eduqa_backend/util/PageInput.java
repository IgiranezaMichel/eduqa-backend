package com.eduqa_backend.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageInput{
private int pageNumber;
private int pageSize;
private String sortBy;
private String search;
}
