package com.postit.postit.common.utils;

public class SlugDecoder {

    public static String decode(String slug){
        return slug.replace("-", " ");
    }
}
