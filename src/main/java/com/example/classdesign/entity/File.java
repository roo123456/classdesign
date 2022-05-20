package com.example.classdesign.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class File {
    int fid;
    String fname;
    String fpath;
    String fdate;
}
