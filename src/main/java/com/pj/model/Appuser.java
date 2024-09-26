package com.pj.model;

import lombok.Data;
import org.springframework.data.annotation.Id;


@Data
public class Appuser {
    @Id
    private String id;
    private String username;
    private String password;

    public Appuser() {
    }

    public Appuser(String username,  String password) {
        this.username = username;
        this.password = password;
    }
}
