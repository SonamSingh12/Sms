package com.matter.otp.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
public class UserVo  implements Serializable {

    private static final long serialVersionUID = 2254747808301102086L;

    private String phone;
    private String firstName;
    private String lastName;
    private String imageBase64;
    private String email;
    private String dob;
    private String address;
    private boolean receiveNotifications;
}