package com.matter.otp.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="")
public class UserDo implements Serializable {

    private static final long serialVersionUID = -7122347682494567882L;
   //private transient DataObjectUtils dataObjectUtils = new DataObjectUtils();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    @Column(name="user_name")
    private String name;

    @Column(name="sur_name")
    private String lastName;

    @Column(name="last_name")
    private String motherLastName;

    @Column(name="phone_num")
    private String phone;

    @Column(name="email_id")
    private String email;

    @Column(name="birth_year")
    private String birthYear;

    @Column(name="zip_code")
    private String zipCode;

    @Column(name="affiliation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date affiliationDate;

    @Column(name="creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(name="affiliation_store")
    private String affiliationStore;

    @Column(name="user_status")
    private String status;

    @Column(name="sms_code")
    private String code;

    @Column(name="receive_alerts")
    private Boolean receiveNotifications;
}
