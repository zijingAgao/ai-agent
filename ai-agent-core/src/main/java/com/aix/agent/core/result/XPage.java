package com.aix.agent.core.result;

import lombok.Data;

@Data
public class XPage {
    private Integer page;
    private Integer size;
    private Long total;
    private Long totalPage;
}
