package com.quem.arrisca.controller;

import com.quem.arrisca.model.ExcelFile;
import com.quem.arrisca.service.FileProcessorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/processing")
public class FileProcessorController {

    private FileProcessorService fileProcessorService;

    public FileProcessorController(FileProcessorService fileProcessorService) {
        this.fileProcessorService = fileProcessorService;
    }

    @GetMapping(value = "/countWords")
    public ResponseEntity wordCountInAListOfStringWithSeparator(@RequestParam("inputStr") String inputStr) throws Exception {
        try {
            List<String> wordList = Arrays.asList(inputStr.split("\\|"));
            Map<String, Long> count = this.fileProcessorService.countWordsInAListOfStringWithSeparator(wordList);
            return ResponseEntity.ok().body(count);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @PostMapping(value = "/uploadExcel")
    public ResponseEntity uploadExcel(@RequestParam("inputFile") MultipartFile inputFile) throws IOException {
        ExcelFile file = new ExcelFile(inputFile.getOriginalFilename(), inputFile.getBytes(),
                inputFile.getContentType());
        ExcelFile excelFile = fileProcessorService.saveFile(file);
        return new ResponseEntity<>(excelFile, HttpStatus.OK);
    }

    @GetMapping(value = "/queryFile")
    public void queryCSV() {
        fileProcessorService.queryFile("");
    }
}
