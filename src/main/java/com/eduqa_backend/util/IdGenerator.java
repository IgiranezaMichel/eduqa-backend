package com.eduqa_backend.util;

public class IdGenerator {
    public static String getNextId(String currentId) {
        int generatedId = Integer.parseInt(currentId);
        generatedId++;
       String formattedId = String.format("%05d", generatedId);
       return formattedId;
   }
}
