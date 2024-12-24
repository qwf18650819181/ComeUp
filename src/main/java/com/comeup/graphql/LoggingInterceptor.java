package com.comeup.graphql;

import com.apollographql.apollo.exception.ApolloException;
import com.apollographql.apollo.interceptor.ApolloInterceptor;
import com.apollographql.apollo.interceptor.ApolloInterceptorChain;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executor;

public class LoggingInterceptor implements ApolloInterceptor {
    @Override
    public void interceptAsync(@NotNull InterceptorRequest request, @NotNull ApolloInterceptorChain chain, @NotNull Executor executor, @NotNull CallBack callBack) {
        // 在请求发送前记录日志
        System.out.println("Sending request: " + request.operation.name().name());

        chain.proceedAsync(request, executor, new CallBack() {
            @Override
            public void onResponse(@NotNull InterceptorResponse response) {
                // 在收到响应后记录日志
                System.out.println("Received response: " + response);
                callBack.onResponse(response);
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                // 在请求失败时记录日志
                System.err.println("Request failed: " + e.getMessage());
                callBack.onFailure(e);
            }

            @Override
            public void onCompleted() {
                // 请求完成后的操作
                callBack.onCompleted();
            }

            @Override
            public void onFetch(FetchSourceType sourceType) {
                // 可以根据需要处理
                callBack.onFetch(sourceType);
            }
        });
    }

    @Override
    public void dispose() {
        // 清理资源，如果有必要
    }
}
