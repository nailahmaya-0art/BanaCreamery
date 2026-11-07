package com.mycompany.banacreamery;

public class Member {
    private String idMember;
    private String namaMember;

    public Member(String idMember, String namaMember) {
        this.idMember = idMember;
        this.namaMember = namaMember;
    }

    public String getIdMember() { return idMember; }
    public String getNamaMember() { return namaMember; }
}