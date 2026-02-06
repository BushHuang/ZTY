package com.xuehai.launcher.common.constants.error;

public enum ErrorCode {
    ERROR_DEFAULT(-1, "error"),
    ERROR_PARSER_JSON(-10000001, "网络回调数据解析失败！"),
    ERROR_DEAL_DATA(-10000002, "客户端处理数据异常！"),
    OK(200, "服务器成功返回用户请求的数据！"),
    CREATED(201, "用户新建或修改数据成功！"),
    ACCEPTED(202, "异步请求已经进入后台排队！"),
    NO_CONTENT(204, "用户删除数据成功！"),
    INVALID_REQUEST(400, "INVALID REQUEST 请求有错误！"),
    UNAUTHORIZED(401, "UNAUTHORIZED 用户没有权限！"),
    FORBIDDEN(403, "FORBIDDEN 访问是被禁止的！"),
    NOT_FOUND(404, "NOT FOUND！"),
    NOT_ACCEPTABLE(406, "Not Acceptable！"),
    GONE(410, "GONE 用户请求的资源被永久删除！"),
    UNPROCESABLE_ENTITY(422, "Unprocesable entity ！"),
    INTERNAL_SERVER_ERROR(500, "服务器发生错误！");

    private int code;
    private String msg;

    ErrorCode(int i, String str) {
        this.code = i;
        this.msg = str;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setMsg(String str) {
        this.msg = str;
    }
}
