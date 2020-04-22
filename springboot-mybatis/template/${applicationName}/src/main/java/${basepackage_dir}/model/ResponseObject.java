package ${basepackage}.model;

import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ResponseObject<T> implements Serializable{

    private static final long serialVersionUID = 1347089211198777840L;

    /**
     * 通信的状态码
     */
    private int statusCode;

    /**
     * 通信的提示消息
     */
    private String message;

    /**
     * 通信的业务数据 swagger要显示返回数据 必须泛型T
     */
    private T data;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseObject<T> ok(T data){
        return ok("处理成功!",data);
    }

    public ResponseObject<T> ok(String message, T data){
        ResponseObject<T> responseObject = new ResponseObject();
        responseObject.setStatusCode(200);
        responseObject.setMessage(message);
        responseObject.setData(data);
        return responseObject;
    }


    public ResponseObject<T> error(){
        return error("处理失败...");
    }

    public ResponseObject<T> error(String errorMessage){
        ResponseObject responseObject = new ResponseObject();
        responseObject.setStatusCode(500);
        responseObject.setMessage(errorMessage);
        return responseObject;
    }

    public ResponseObject errorMsg(String msg){
        ResponseObject responseObject = new ResponseObject();
        responseObject.setStatusCode(500);
        responseObject.setMessage(msg);
        Map map = new HashMap();
        map.put("msg",msg);
        responseObject.setData(map);
        return responseObject;
    }

    public ResponseObject okMsg(String msg){
        ResponseObject responseObject = new ResponseObject();
        responseObject.setStatusCode(200);
        responseObject.setMessage(msg);
        Map map = new HashMap();
        map.put("msg",msg);
        responseObject.setData(map);
        return responseObject;
    }

    public ResponseObject notLogin(String msg){
        ResponseObject responseObject = new ResponseObject();
        responseObject.setStatusCode(401);
        responseObject.setMessage(msg);
        Map map = new HashMap();
        if(StringUtils.isEmpty(msg)){
            msg = "请重新登录";
        }
        map.put("msg",msg);
        responseObject.setData(map);
        return responseObject;
    }

    public ResponseObject unauthorized(String msg){
        ResponseObject responseObject = new ResponseObject();
        responseObject.setStatusCode(403);
        responseObject.setMessage(msg);
        Map map = new HashMap();
        if(StringUtils.isEmpty(msg)){
            msg = "未授权访问";
        }
        map.put("msg",msg);
        responseObject.setData(map);
        return responseObject;
    }


    @Override
    public String toString() {
        return "ResponseData{" +
                "setStatusCode=" + statusCode +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
