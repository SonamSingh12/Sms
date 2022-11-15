package com.matter.otp.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
public class SmsVo implements Serializable {

        private static final long serialVersionUID = -6311579055950705039L;

        private String number;
        private String message;
}
