package org.example;

import org.example.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://94.198.50.185:7081/api/users";
//        String sessionId = "your_session_id"; // полученный Session ID
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Cookie", "JSESSIONID=" + sessionId); // добавляем Session ID в заголовок Cookie
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
//        System.out.println(response.getBody());
//        String cookie = String.valueOf(response.getHeaders().get("Set-Cookie")).replaceAll("^.|.$","");
//        System.out.println(cookie);
//
//    }
//}
        final String URL = "http://94.198.50.185:7081/api/users";
        String result = "";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>("", headers);
        ResponseEntity responseEntity = restTemplate.exchange(URL, HttpMethod.GET, entity, String.class);

        System.out.println("Response entity header: " + responseEntity.getHeaders() + "\n");
        String cookie = String.valueOf(responseEntity.getHeaders().get("Set-Cookie")).replaceAll("^.|.$", "");
        System.out.println("Cookie: " + cookie + "\n");

        headers.set("Cookie", cookie);
        User user = new User(3L, "James", "Brown", (byte) 55);
        System.out.println(user);

        HttpEntity<User> entityUser = new HttpEntity<>(user, headers);
        responseEntity = restTemplate.exchange(URL, HttpMethod.POST, entityUser, String.class);
        String result1 = responseEntity.getBody().toString();
        result += responseEntity.getBody();
        System.out.println("Block-code 1: " + result1 + "\n");

        user.setName("Thomas");
        user.setLastName("Shelby");
        System.out.println(user);

        entityUser = new HttpEntity<>(user, headers);
        responseEntity = restTemplate.exchange(URL, HttpMethod.PUT, entityUser, String.class);
        String result2 = responseEntity.getBody().toString();
        result += responseEntity.getBody();
        System.out.println("Block-code 2: " + result2 + "\n");


        responseEntity = restTemplate.exchange(URL + "/" + user.getId(), HttpMethod.DELETE, entityUser, String.class);
        String result3 = responseEntity.getBody().toString();
        result += responseEntity.getBody();
        System.out.println("Block-code 3: " + result3 + "\n");

        System.out.println("Result code: " + result);
    }
}