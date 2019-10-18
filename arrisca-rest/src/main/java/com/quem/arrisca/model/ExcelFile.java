package com.quem.arrisca.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "EXCEL_FILE")
public class ExcelFile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long fileId;
    private String fileName;
    @Lob
    private byte[] fileData;
    private String fileType;

    public ExcelFile() {
    }

    public ExcelFile(String fileName, byte[] fileData, String fileType) {
        this.fileName = fileName;
        this.fileData = fileData;
        this.fileType = fileType;
    }
}
