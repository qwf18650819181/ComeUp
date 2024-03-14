package com.comeup.exception;

import lombok.Data;

/**
 * 功能描述:
 *
 * @author: qiu wanzi
 * @date: 2024年3月14日 0014
 * @version: 1.0
 */
@Data
public class Response {

    Integer code = RPC.SUCCESS.code();
    Integer errorCode = RPCERROR.SUCCESS.code();
    Object responseBody;

    public Response(RPC rpc, RPCERROR rpcerror, Object responseBody) {
        this.code = rpc.code();
        this.errorCode = rpcerror.getCode();
        this.responseBody = responseBody;
    }

    public Response(Integer code, Object responseBody) {
        this.code = code;
        this.responseBody = responseBody;
    }

    public Response(Object responseBody) {
        this.responseBody = responseBody;
    }

    public Response() {
    }
}
