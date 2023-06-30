package com.gby.video.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// VO 代表前段与后端交互数据实体
@Data
@AllArgsConstructor  //全参构造方法
@NoArgsConstructor   //无参构造方法
public class ResultVO {
    private final static Integer OK = 200;
    private final static Integer SYSTEM_ERROR = 503;
    private final static Integer NO_PERMISSIONS = 403;
    private Integer status;  //状态码
    private Object data;     //响应数据
    private String msg;      //响应信息

    public static ResultVO success(String msg) {
        return new ResultVO(OK, null, msg);
    }

    public static ResultVO error(String msg) {
        return new ResultVO(SYSTEM_ERROR, null, msg);
    }

    public ResultVO setData(Object data) {
        this.data = data;
        return this;
    }
}
