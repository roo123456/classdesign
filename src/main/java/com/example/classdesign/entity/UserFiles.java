package com.example.classdesign.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserFiles {
    int uid;
    int fid;
    int status;
    String fname;
    String fdate;
    String uname;
}
