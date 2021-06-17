package com.rastkomitrovic.entity;

import javax.persistence.*;

@Entity
@Table(name = "hash_tag")
public class HashTag {

    @Id
    @Column(name = "hash_tag_name", unique = true)
    private String hashTagName;

    public HashTag() {

    }

    public HashTag(String hashTagName) {
        this.hashTagName = hashTagName;
    }

    public String getHashTagName() {
        return hashTagName;
    }

    public void setHashTagName(String hashTagName) {
        this.hashTagName = hashTagName;
    }

    @Override
    public String toString() {
        return "HashTag{" +
                "hashTagName='" + hashTagName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HashTag hashTag = (HashTag) o;
        return hashTagName == hashTag.hashTagName;
    }

}
