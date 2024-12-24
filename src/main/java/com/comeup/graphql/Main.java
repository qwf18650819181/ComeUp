package com.comeup.graphql;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述:
 *
 * @author: qiu wanzi
 * @date: 2024年12月23日 星期一
 * @version: 1.0
 */
public class Main {

    public static void main(String[] args) {


        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("query", "query findUserById($id: ID!) { findUserById(id: $id) { email name id } }");
        Map<String, String> variables = new HashMap<>();
        variables.put("id", "1");
        jsonMap.put("variables", variables);
        String json = null;
        try {
            json = mapper.writeValueAsString(jsonMap);
            System.out.println(json);
            System.out.println(JSON.toJSONString(jsonMap));

            Variable variable = new Variable();
            json = JSON.toJSONString(variable);
            System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }



        OkHttpClient client = new OkHttpClient();

        String json1 = "{"
                + "\"query\":\"query findUserById($id: ID!) {"
                + " findUserById(id: $id) {"
                + " email"
                + " name"
                + " id"
                + " }"
                + "}\","
                + "\"variables\":{\"id\":\"1\"}"
                + "}";

        Map<String, String> map = new HashMap<>();
        map.put("query", "query  findUserById($id: ID!) {\\r\\n  findUserById(id: $id) {\\r\\n    email\\r\\n    name\\r\\n    id\\r\\n  }\\r\\n}");
        map.put("variables", "{\"id\":\"1\"}");

        System.out.println(JSON.toJSONString(map));

        Variable variable = new Variable();
        String jsonString = JSON.toJSONString(variable);
        System.out.println(jsonString);


        RequestBody body = RequestBody.create(json, MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url("http://localhost:8082/graphql")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // 打印响应体
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static class Variable {
        String query = "query  findUserById($id: ID!) {\n" +
                "  findUserById(id: $id) {\n" +
                "    email\n" +
                "    name\n" +
                "    id\n" +
                "  }\n" +
                "}";
        Body variables = new Body();
        public static class Body {
            String id = "1";

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }

        public String getQuery() {
            return query;
        }

        public void setQuery(String query) {
            this.query = query;
        }

        public Body getVariables() {
            return variables;
        }

        public void setVariables(Body variables) {
            this.variables = variables;
        }
    }
}
