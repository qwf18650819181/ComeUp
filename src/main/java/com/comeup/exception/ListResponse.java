package com.comeup.exception;

import lombok.Data;

import java.util.List;

/**
 * 功能描述:
 *
 * @author: qiu wanzi
 * @date: 2024年3月14日 0014
 * @version: 1.0
 */
@Data
public class ListResponse<T> {

    Integer code = RPC.SUCCESS.code();
    Integer errorCode = RPCERROR.SUCCESS.code();
    List<T> list;

    public ListResponse(RPC rpc, RPCERROR rpcerror, List<T> content) {
        this.code = rpc.code();
        this.errorCode = rpcerror.getCode();
        this.list = content;
    }

    public ListResponse(Integer code, List<T> content) {
        this.code = code;
        this.list = content;
    }

    public ListResponse(List<T> content) {
        this.list = content;
    }

    public ListResponse() {
    }


}
