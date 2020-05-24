package ${basepackage}.model;
import ${basepackage}.enums.ResponseCode;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.HashMap;

@ApiModel(value = "api接口通用返回对象", description = "所有数据经此包装")
public class ResponseObject<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "返回码", dataType = "int")
    private Integer statusCode;

    @ApiModelProperty(value = "返回信息", dataType = "String")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private T data;


    public ResponseObject() {
    }

    public ResponseObject(T data) {
        this.statusCode = ResponseCode.OK;
        this.message = ResponseCode.OK_MSG;
        this.data = data;
    }

    public ResponseObject(Integer code, String msg) {
        this.statusCode = code;
        this.message = msg;
        this.data = null;
    }

    public ResponseObject(Integer code, String msg, T data) {
        this.statusCode = code;
        this.message = msg;
        this.data = data;
    }

    public static ResponseObject ok() {
        return ok(ResponseCode.OK_MSG);
    }

    public static ResponseObject ok(String message) {
        ResponseObject ResponseObject = new ResponseObject();
        ResponseObject.setStatusCode(ResponseCode.OK);
        ResponseObject.setMessage(message);
        return ResponseObject;
    }

    public ResponseObject<T> ok(T data) {
        return ok(ResponseCode.OK_MSG, data);
    }

    public ResponseObject<T> ok(String message, T data) {
        ResponseObject<T> ResponseObject = new ResponseObject();
        ResponseObject.setStatusCode(ResponseCode.OK);
        ResponseObject.setMessage(message);
        ResponseObject.setData(data);
        return ResponseObject;
    }

    public static ResponseObject error() {
        return error(ResponseCode.ERROR_MSG);
    }

    public static ResponseObject error(String errorMessage) {
        ResponseObject ResponseObject = new ResponseObject();
        ResponseObject.setStatusCode(ResponseCode.ERROR);
        ResponseObject.setMessage(errorMessage);
        return ResponseObject;
    }

    public static ResponseObject error(Object data, String errorMessage) {
        ResponseObject responseObject = new ResponseObject();
        responseObject.setStatusCode(ResponseCode.ERROR);
        responseObject.setData(data);
        responseObject.setMessage(errorMessage);
        return responseObject;
    }

    /**
     * unLogin
     *
     * @return
     */
    public static ResponseObject unLogin() {
        return new ResponseObject(ResponseCode.NOT_LOGIN, ResponseCode.NOT_LOGIN_MSG);
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        if (null == data) {
            data = (T) new HashMap<String, String>();
        }
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseObject{" +
                "statusCode=" + statusCode +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
