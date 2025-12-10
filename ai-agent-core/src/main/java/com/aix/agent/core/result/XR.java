package com.aix.agent.core.result;

import com.aix.agent.core.errorcode.IErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Agao
 * @date 2025/12/10 15:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class XR<T> {
    /**
     * 成功的code编码
     */
    private static final String SUCCESS = "0";

    private String code;
    private String msg;
    private T data;
    private XPage xpage;

    public static <T> XR<T> success() {
        return new XR<>(SUCCESS, "success", null, null);
    }

    public static <T> XR<T> success(T data) {
        return new XR<>(SUCCESS, "success", data, null);
    }

    public static <T> XR<T> success(T data, XPage xPage) {
        return new XR<>(SUCCESS, "success", data, xPage);
    }


    public static <T> XR<T> error(String code, String msg) {
        return new XR<>(code, msg, null, null);
    }

    public static <T> XR<T> error(IErrorCode iErrorCode) {
        return new XR<>(iErrorCode.getCode(), iErrorCode.getMsg(), null, null);
    }
}
