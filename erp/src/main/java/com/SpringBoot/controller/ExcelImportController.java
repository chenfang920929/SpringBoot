package com.SpringBoot.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.*; 
import org.springframework.web.multipart.MultipartFile;

import com.SpringBoot.bean.ImportResult;
import com.SpringBoot.bean.Result;
import com.SpringBoot.service.ExcelImportService; 
 
@RestController 
@RequestMapping("excel")
public class ExcelImportController {
 
    private final ExcelImportService excelImportService;
 
    public ExcelImportController(ExcelImportService excelImportService) {
        this.excelImportService  = excelImportService;
    }
 
    @PostMapping("importgoods")
    public Result importgoods(@RequestParam("file") MultipartFile file) {
        try {
            ImportResult result = excelImportService.importGoodsExcel(file); 
            return Result.success(" 导入成功", result);
        } catch (Exception e) {
            return Result.fail(" 导入失败: " + e.getMessage()); 
        }
    }
    
    @PostMapping("importcustomer")
    public Result importcustomer(@RequestParam("file") MultipartFile file) {
        try {
            ImportResult result = excelImportService.importCustomerExcel(file); 
            return Result.success(" 导入成功", result);
        } catch (Exception e) {
            return Result.fail(" 导入失败: " + e.getMessage()); 
        }
    }
    
    @PostMapping("importinport")
    public Result importinport(@RequestParam("file") MultipartFile file) {
        try {
            ImportResult result = excelImportService.importInportExcel(file); 
            return Result.success(" 导入成功", result);
        } catch (Exception e) {
            return Result.fail(" 导入失败: " + e.getMessage()); 
        }
    }
    
    @PostMapping("importprovider")
    public Result importprovider(@RequestParam("file") MultipartFile file) {
        try {
            ImportResult result = excelImportService.importProviderExcel(file); 
            return Result.success(" 导入成功", result);
        } catch (Exception e) {
            return Result.fail(" 导入失败: " + e.getMessage()); 
        }
    }
    
    @PostMapping("importsales")
    public Result importsales(@RequestParam("file") MultipartFile file) {
        try {
            ImportResult result = excelImportService.importSalesExcel(file); 
            return Result.success(" 导入成功", result);
        } catch (Exception e) {
            return Result.fail(" 导入失败: " + e.getMessage()); 
        }
    }
 
    @GetMapping("goodstemplate")
    public void downloadGoodsTemplate(HttpServletResponse response) {
        excelImportService.downloadGoodsTemplate(response); 
    }
    
    @GetMapping("customertemplate")
    public void downloadCustomerTemplate(HttpServletResponse response) {
        excelImportService.downloadCustomerTemplate(response); 
    }
    
    @GetMapping("inporttemplate")
    public void downloadInportTemplate(HttpServletResponse response) {
        excelImportService.downloadInportTemplate(response); 
    }
    
    @GetMapping("providertemplate")
    public void downloadProviderTemplate(HttpServletResponse response) {
        excelImportService.downloadProviderTemplate(response); 
    }
    
    @GetMapping("salestemplate")
    public void downloadSalesTemplate(HttpServletResponse response) {
        excelImportService.downloadSalesTemplate(response); 
    }
}