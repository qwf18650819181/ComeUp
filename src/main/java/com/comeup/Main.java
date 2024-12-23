package com.comeup;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

/**
 * @auth: qwf
 * @date: 2024年1月9日 0009
 * @description:
 */
public class Main {

    public static void main(String[] args) throws Exception {

        JSON.parseObject("data", Map.class).get("location");





        amazonZip();
//
//        Random random = new Random(100);
//        for (int i = 0; i < 100; i++) {
//            int i1 = random.nextInt(100);
//            System.out.println(i1);
//        }
//
//
//        long l = DateUtil.betweenDay(new Date(), DateUtils.addMinutes(new Date(), 3), true);
//        System.out.println(l);
//
//
//        for (int i = 0; i < 100; i++) {
//            System.out.println(random.nextInt(100));
//        }
//
//
//        Person person = new Person();
//        if (Integer.valueOf(1) == null) {
//            System.out.println("MATERIAL_SUPPLIER_IS_TORT");
//        } else {
//            System.out.println("MATERIAL_SUPPLIER_IS_TORT\" OR str_attr=\"MATERIAL_SUPPLIER_NOT_TORT");
//        }


//        Map<String, Integer> map = new HashMap<>();
//
//        for (int i = 0; i < args.length; i++) {
//            map.merge(args[i], 1, Integer::sum);
//        }
//
//        System.out.println(JSON.toJSONString(map));
//
//
//        int n = 13;
//        System.out.println(Integer.toBinaryString(n));
//        n |= n >>> 1;
//        System.out.println(Integer.toBinaryString(n));
//        n |= n >>> 2;
//        System.out.println(Integer.toBinaryString(n));
//        n |= n >>> 4;
//        System.out.println(Integer.toBinaryString(n));
//        n |= n >>> 8;
//        System.out.println(Integer.toBinaryString(n));
//        n |= n >>> 16;
//        System.out.println(Integer.toBinaryString(n));
//        n = (n + 1) << 1;
//        System.out.println(Integer.toBinaryString(n));


    }

    /**
     * @param args:
     * @return null
     * @author qiu wanzi
     * @description TODO 弱引用
     * @date 2024年3月6日 0006
     */
    public static void main1(String[] args) {
        WeakReferenceCache<String, Object> cache = new WeakReferenceCache<>();
        Object value = new Object();
        cache.put("key", value);
        // 删除强引用 -> 自动回收
        value = null;
        // 强制垃圾收集
        System.gc();
        // 等待垃圾收集
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        // 检查缓存项是否被回收
        if (cache.get("key") == null) {
            System.out.println("Cache item was garbage collected.");
        } else {
            System.out.println("Cache item was not garbage collected.");
        }

        Map<String, Object> cache1 = new HashMap<>();
        Object value1 = new Object();
        cache1.put("key", value1);
        // 删除强引用 -> 自动回收
        value1 = null;
        // 强制垃圾收集
        System.gc();
        // 等待垃圾收集
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        // 检查缓存项是否被回收
        if (cache1.get("key") == null) {
            System.out.println("Cache item was garbage collected.");
        } else {
            System.out.println("Cache item was not garbage collected.");
        }
    }

    /**
     * Download and optionally decompress the document retrieved from the given url.
     *
     * @throws IOException              when there is an error reading the response
     * @throws IllegalArgumentException when the charset is missing
     */
    public static void amazonZip() throws IOException, IllegalArgumentException {
        String url=
                "https://tortuga-prod-na.s3-external-1.amazonaws.com/87076ec4-efd6-4169-b8d5-25be4f267952.amzn1.tortuga.4.na.T1GDTDZBL8I047?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20241030T010035Z&X-Amz-SignedHeaders=host&X-Amz-Expires=300&X-Amz-Credential=AKIA5U6MO6RACOWQRBEF%2F20241030%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Signature=c6b2789f87353e29abc94340b271525abc71a52c7b7f9b4cc9b757a1d144d7ed"
        ;
        String compressionAlgorithm = "GZIP";
        OkHttpClient httpclient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        Response response = httpclient.newCall(request).execute();
        if (!response.isSuccessful()) {
            System.out.println(
                    String.format("Call to download content was unsuccessful with response code: %d and message: %s",
                            response.code(), response.message()));
            return;
        }

        try (ResponseBody responseBody = response.body()) {
            MediaType mediaType = MediaType.parse(response.header("Content-Type"));

            Closeable closeThis = null;
            try {
                InputStream inputStream = responseBody.byteStream();
                closeThis = inputStream;

                if ("GZIP".equals(compressionAlgorithm)) {
                    inputStream = new GZIPInputStream(inputStream);
                    closeThis = inputStream;
                }

                // This example assumes that the download content has a charset in the content-type header, e.g.
                // text/plain; charset=UTF-8
                if ("text".equals(mediaType.type()) && "plain".equals(mediaType.subtype())) {
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.defaultCharset());
                    closeThis = inputStreamReader;

                    BufferedReader reader = new BufferedReader(inputStreamReader);
                    closeThis = reader;

                    String line;
                    do {
                        line = reader.readLine();
                        // Process line by line.
                    } while (line != null);
                    System.out.println(line);
                } else {
                    //Handle content with binary data/other media types here.
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.defaultCharset());
                    closeThis = inputStreamReader;

                    BufferedReader reader = new BufferedReader(inputStreamReader);
                    closeThis = reader;

                    String line;
                    StringBuilder stringBuilder = new StringBuilder();
                    do {
                        line = reader.readLine();
                        if (line != null) {
                            stringBuilder.append(line);
                        }
                        // Process line by line.
                    } while (line != null);
                    System.out.println(stringBuilder);
                }
            } finally {
                if (closeThis != null) {
                    closeThis.close();
                }
            }
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Person {

        Integer age;
    }

    public static class WeakReferenceCache<K, V> {

        private final Map<K, WeakReference<V>> cache = Collections.synchronizedMap(new HashMap<>());

        public void put(K key, V value) {
            cache.put(key, new WeakReference<>(value));
        }

        public V get(K key) {
            WeakReference<V> weakRef = cache.get(key);
            if (weakRef != null) {
                return weakRef.get();
            }
            return null;
        }
    }


}
