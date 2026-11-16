package com.mycompany.banacreamery;

import java.io.Serializable;

public class Member implements Serializable {
    private static final long serialVersionUID = 1L;

    private String idMember;
    private String namaMember;

    public Member(String idMember, String namaMember) {
        this.idMember = idMember;
        this.namaMember = namaMember;
    }

    public String getIdMember() { return idMember; }
    public String getNamaMember() { return namaMember; }
}