package com.eduqa_backend.util;

import java.util.Base64;

public class ImageConverter {
public static String convertToBase64(byte[] image) {
    if(image!=null){
        return "data:image/png;base64,"+Base64.getEncoder().encodeToString(image);
    }
    else return null;
}
public static byte[] convertToByteArray(String image) {
    if(image!=null){
        return Base64.getDecoder().decode(image.replaceFirst("^data:image/.+;base64,", ""));
    }
    else return null;
}
}
