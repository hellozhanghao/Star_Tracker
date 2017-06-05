package com.tinymos.demo.star_tracker;


import android.app.DownloadManager;

import java.io.IOException;
import java.text.Normalizer;

/**
 * Created by zhanghao on 2017/5/24.
 */

public class TestSkyMap {
    public static void main(String[] args) throws IOException {
        HttpResponse response;
        response = DownloadManager.Request.Post("http://www.example.com").bodyForm(Normalizer.Form.form().add("username", "John").add("password", "pass").build()).execute().returnResponse();

        System.out.println(response.toString());

        System.out.println("Hello");
    }
}