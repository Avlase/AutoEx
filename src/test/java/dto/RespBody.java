package dto;

public class RespBody {
    private String responseCode;
    private String message;
    private String RspMsg;
    private int code;
    private String RspCodeBody;


    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getRspMsg() {
        return RspMsg;
    }
    public void setRspMsg(String rspMsg) {
        RspMsg = rspMsg;
    }
    public String getRspCodeBody() {
        return RspCodeBody;
    }
    public void setRspCodeBody(String rspCodeBody) {
        RspCodeBody = rspCodeBody;
    }


}
