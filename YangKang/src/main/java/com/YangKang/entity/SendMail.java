package com.YangKang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendMail {
    private String recipient; // người nhận
    private String msgBody;  // message
    private String subject; // chủ đề
    private String attachment; // tập tin đính kèm
}
