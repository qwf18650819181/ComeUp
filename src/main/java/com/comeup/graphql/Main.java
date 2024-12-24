package com.comeup.graphql;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.exception.ApolloException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.validation.constraints.NotNull;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述:
 *
 * @author: qiu wanzi
 * @date: 2024年12月23日 星期一
 * @version: 1.0
 */
public class Main {

    public static void main(String[] args) {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 7078)))  // 设置本地代理
                .addInterceptor(chain -> {
                    Request original = chain.request();

                    // 打印请求信息
                    System.out.println("Sending request to URL: " + original.url());
                    System.out.println("Request method: " + original.method());
                    if (original.body() != null) {
                        System.out.println("Request body: " + original.body().contentType());
                    }
                    original.headers().forEach(header -> System.out.println(header.getFirst() + ": " + header.getSecond()));

                    Request.Builder builder = original.newBuilder()
                            .method(original.method(), original.body());

                    Request request = builder.build();

                    Response response = chain.proceed(request);

                    // 打印响应信息
                    System.out.println("Received response for " + response.request().url() + " with status code " + response.code());

                    return response;
                })
                .build();

        ApolloClient apolloClient = ApolloClient.builder()
                .serverUrl("https://dokotoo-8790.myshopify.com/admin/api/2024-10/graphql.json")
                .okHttpClient(okHttpClient)
                .build();

        ProductQuery productQuery = ProductQuery.builder()
                .id("gid://shopify/Product/9229536002324")
                .build();


        apolloClient.query(productQuery).enqueue(new ApolloCall.Callback<ProductQuery.Data>() {
            @Override
            public void onResponse(com.apollographql.apollo.api.Response<ProductQuery.Data> response) {
                if (response.getData() != null && response.getData().product() != null) {
                    // 成功获取数据
                    System.out.println("Product created at: " + response.getData().product().title());
                } else if (response.hasErrors()) {
                    // 处理错误
                    System.out.println("Errors: " + response.getErrors());
                }
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                // 处理查询失败
                System.err.println("Error fetching product: " + e.getMessage());
            }
        });
//
//        ObjectMapper mapper = new ObjectMapper();
//
//        Map<String, Object> jsonMap = new HashMap<>();
//        jsonMap.put("query", "query findUserById($id: ID!) { findUserById(id: $id) { email name id } }");
//        Map<String, String> variables = new HashMap<>();
//        variables.put("id", "1");
//        jsonMap.put("variables", variables);
//        String json = null;
//        try {
//            json = mapper.writeValueAsString(jsonMap);
//            System.out.println(json);
//            System.out.println(JSON.toJSONString(jsonMap));
//
//            Variable variable = new Variable();
//            json = JSON.toJSONString(variable);
//            System.out.println(json);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }

//
//
//        OkHttpClient client = new OkHttpClient();
//
//        String json1 = "{"
//                + "\"query\":\"query findUserById($id: ID!) {"
//                + " findUserById(id: $id) {"
//                + " email"
//                + " name"
//                + " id"
//                + " }"
//                + "}\","
//                + "\"variables\":{\"id\":\"1\"}"
//                + "}";
//
//        Map<String, String> map = new HashMap<>();
//        map.put("query", "query  findUserById($id: ID!) {\\r\\n  findUserById(id: $id) {\\r\\n    email\\r\\n    name\\r\\n    id\\r\\n  }\\r\\n}");
//        map.put("variables", "{\"id\":\"1\"}");
//
//        System.out.println(JSON.toJSONString(map));
//
//        Variable variable = new Variable();
//        String jsonString = JSON.toJSONString(variable);
//        System.out.println(jsonString);
//
//
//        RequestBody body = RequestBody.create(json, MediaType.get("application/json; charset=utf-8"));
//        Request request = new Request.Builder()
//                .url("http://localhost:8082/graphql")
//                .post(body)
//                .build();
//
//        try (Response response = client.newCall(request).execute()) {
//            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
//
//            // 打印响应体
//            System.out.println(response.body().string());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


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
