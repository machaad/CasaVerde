package dto;

/**
 * Created by HeavyPollo on 9/29/15.
 */
public class ErrorDto {
    private String field;
    private String message;
    private String module;
    public ErrorDto() {
    }

    public ErrorDto(String field, String message,String module) {
        this.field = field;
        this.message = message;
        this.module = module;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }
}
