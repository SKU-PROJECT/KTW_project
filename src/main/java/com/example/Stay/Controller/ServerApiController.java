package com.example.Stay.Controller;//package com.example.Stay.Controller;
//
//import com.example.Stay.Entity.Stay;
//import com.example.Stay.Repository.StayRepository;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.RequestEntity;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import java.net.URI;
//import java.nio.ByteBuffer;
//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//@Slf4j
//@RestController
//@RequestMapping("/api/server")
//public class ServerApiController {
//    @Autowired
//    private StayRepository stayRepository;
//    @GetMapping("/naver")
//    public ResponseEntity<String> naver() {
//        String query = "서울 호텔";
//        ByteBuffer buffer = StandardCharsets.UTF_8.encode(query);
//
//        URI uri = UriComponentsBuilder
//                .fromUriString("https://openapi.naver.com")
//                .path("/v1/search/local.json")
//                .queryParam("query", StandardCharsets.UTF_8.decode(buffer).toString())  // 직접 사용
//                .queryParam("display", 10)
//                .queryParam("start", 1)
//                .queryParam("sort", "random")
//                .encode()
//                .build()
//                .toUri();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("X-Naver-Client-Id", "pbVxvGhqyLESC5CiY1cZ"); // 네이버 Client ID
//        headers.set("X-Naver-Client-Secret", "RZBnMKzotf");  //네이버 Secret Key
//
//        RequestEntity<Void> req = RequestEntity
//                .get(uri)
//                .headers(headers)
//                .build();
//
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<String> result = restTemplate.exchange(req, String.class);
//
//        try {
//
//            ObjectMapper objectMapper = new ObjectMapper();    // 객체 매핑
//            JsonNode jsonNode = objectMapper.readTree(result.getBody());
//
//            JsonNode itemsNode = jsonNode.get("items");
//
//
//            if (itemsNode != null) {
//                Iterator<JsonNode> items = itemsNode.elements();
//
//                List<Stay> stays = new ArrayList<>();
//                while (items.hasNext()) {
//                    JsonNode item = items.next();
//
//                    // 제목열에 다른 문자가 포함이 되어 있을 경우 정규화 진행
//                    String title = item.get("title").asText().replaceAll("\\<.*?\\>", "");
//
//                    //DB에 같은 데이터가 있는지 확인
//                    if (!stayRepository.existsByTitle(title)) {
//                        Stay stay = new Stay();
//                        stay.setTitle(title);
//                        stay.setLink(item.get("link").asText());
//                        stay.setCategory(item.get("category").asText());
//                        stay.setDescription(item.get("description").asText());
//                        stay.setTelephone(item.get("telephone").asText());
//                        stay.setAddress(item.get("address").asText());
//                        stay.setRoad_address(item.get("road_address").asText());
//                        stay.setMapx(item.get("mapx").asText());
//                        stay.setMapy(item.get("mapy").asText());
//
//                        stays.add(stay);
//                    }
//                }
//                stayRepository.saveAll(stays);
//            }else {
//                log.warn("No 'items' found in the JSON response.");
//            }
//
//        } catch (JsonProcessingException e) { //예외처리
//            e.printStackTrace();
//            return ResponseEntity.status(500).body("Error processing JSON response");
//        }
//        return ResponseEntity.ok(result.getBody());
//    }
//}
