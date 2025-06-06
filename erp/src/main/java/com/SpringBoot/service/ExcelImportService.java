package com.SpringBoot.service;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.util.StringUtil;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.SpringBoot.bean.Customer;
import com.SpringBoot.bean.Goods;
import com.SpringBoot.bean.ImportError;
import com.SpringBoot.bean.ImportResult;
import com.SpringBoot.bean.Inport;
import com.SpringBoot.bean.Provider;
import com.SpringBoot.bean.Sales;  

@Service 
public class ExcelImportService {
	
	private static final Logger log = LogManager.getLogger(ExcelImportService.class); 
	
	@Autowired
	GoodsService goodsService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	InportService inportService;
	
	@Autowired
	ProviderService providerService;
	
	@Autowired
	SalesService salesService;
	
	@Autowired
	HttpSession httpSession;
	
 
    public ImportResult importGoodsExcel(MultipartFile file) throws Exception {
        List<Goods> successList = new ArrayList<>();
        List<ImportError> errorList = new ArrayList<>();
        
        try (InputStream is = file.getInputStream(); 
             Workbook workbook = WorkbookFactory.create(is))  {
            
            Sheet sheet = workbook.getSheetAt(0); 
            for (int i = 1; i <= sheet.getLastRowNum();  i++) { // 从第2行开始读取 
                try {
                    Row row = sheet.getRow(i); 
                    if (row == null || StringUtil.isBlank(getCellValue(row,  0)) ||  StringUtil.isBlank(getCellValue(row,  1))) continue;
                    
                    Goods goods = parseGoodsRow(row);
                    validateGoods(goods);
                    Integer providerId=providerService.selectProviderId("MEYINK");
                    // 保存到数据库 
                    goodsService.insert(providerId, "Toner Cartridge/硒鼓", goods.getProductcode(),goods.getDescription(),goods.getSize(),0); 
                    successList.add(goods); 
                } catch (Exception e) {
                    errorList.add(new  ImportError(i + 1, e.getMessage())); 
                }
            }
        }
        
        return new ImportResult(successList.size()  + errorList.size(),  
                              successList.size(),  errorList.size(),  errorList);
    }
 
    private Goods parseGoodsRow(Row row) {
        Goods goods = new Goods();
        goods.setProductcode(getCellValue(row,  0)); // 商品型号 
        goods.setSize(getCellValue(row,  1));        // 商品规格 
        goods.setDescription(getCellValue(row,  2));        // 适用机型 
        return goods;
    }
    
    public ImportResult importCustomerExcel(MultipartFile file) throws Exception {
        List<Customer> successList = new ArrayList<>();
        List<ImportError> errorList = new ArrayList<>();
        
        try (InputStream is = file.getInputStream(); 
             Workbook workbook = WorkbookFactory.create(is))  {
            
            Sheet sheet = workbook.getSheetAt(0); 
            for (int i = 1; i <= sheet.getLastRowNum();  i++) { // 从第2行开始读取 
                Row row = sheet.getRow(i); 
                if (row == null || StringUtil.isBlank(getCellValue(row,  0))) continue;
                
                try {
                	Customer customer = parseCustomerRow(row);
                    validateCustomer(customer);
                    
                    // 保存到数据库 
                    customerService.insert(customer.getCustomername(), customer.getAddress(), customer.getConnectionpersion(), customer.getPhone(), customer.getEmail());
                    successList.add(customer); 
                } catch (Exception e) {
                    errorList.add(new  ImportError(i + 1, e.getMessage())); 
                }
            }
        }
        
        return new ImportResult(successList.size()  + errorList.size(),  
                              successList.size(),  errorList.size(),  errorList);
    }
 
    private Customer parseCustomerRow(Row row) {
    	Customer customer = new Customer();
    	customer.setCustomername(getCellValue(row,  0));
    	customer.setAddress(getCellValue(row,  1));
    	customer.setConnectionpersion(getCellValue(row,  2));
    	customer.setPhone(getCellValue(row,  3));
    	customer.setEmail(getCellValue(row,  4));
        return customer;
    }
    
    public ImportResult importInportExcel(MultipartFile file) throws Exception {
        List<Inport> successList = new ArrayList<>();
        List<ImportError> errorList = new ArrayList<>();
        
        String operateperson = (String) httpSession.getAttribute("username");
        try (InputStream is = file.getInputStream(); 
             Workbook workbook = WorkbookFactory.create(is))  {
            
            Sheet sheet = workbook.getSheetAt(0); 
            for (int i = 1; i <= sheet.getLastRowNum();  i++) { // 从第2行开始读取 
                Row row = sheet.getRow(i); 
                if (row == null || StringUtil.isBlank(getCellValue(row,  0))) continue;
                
                try {
                	Inport inport = parseInportRow(row);
                    validateInport(inport);
                    
                    // 保存到数据库 
                    inportService.insert(new Date(), operateperson, inport.getNumber(), inport.getRemark(), inport.getInportprice(), inport.getProviderid(), inport.getGoodsid());
                    successList.add(inport); 
                } catch (Exception e) {
                    errorList.add(new  ImportError(i + 1, e.getMessage())); 
                }
            }
        }
        
        return new ImportResult(successList.size()  + errorList.size(),  
                              successList.size(),  errorList.size(),  errorList);
    }
 
    private Inport parseInportRow(Row row) {
    	Inport inport = new Inport();
       // goods.setProductCode(getCellValue(row,  0)); // 商品型号 
        //goods.setGoodsName(getCellValue(row,  1));   // 商品名称 
        //goods.setSize(getCellValue(row,  2));        // 商品规格 
        //goods.setProviderId(getCellValue(row,  3)); // 供应商ID 
        return inport;
    }
    
    public ImportResult importProviderExcel(MultipartFile file) throws Exception {
        List<Provider> successList = new ArrayList<>();
        List<ImportError> errorList = new ArrayList<>();
        
        try (InputStream is = file.getInputStream(); 
             Workbook workbook = WorkbookFactory.create(is))  {
            
            Sheet sheet = workbook.getSheetAt(0); 
            for (int i = 1; i <= sheet.getLastRowNum();  i++) { // 从第2行开始读取 
                Row row = sheet.getRow(i); 
                if (row == null || StringUtil.isBlank(getCellValue(row,  0))) continue;
                
                try {
                	Provider provider = parseProviderRow(row);
                    validateProvider(provider);
                    
                    // 保存到数据库 
                    providerService.insert(provider.getProvidername(), provider.getAddress(), provider.getConnectionperson(),provider.getPhone());
                    successList.add(provider); 
                } catch (Exception e) {
                    errorList.add(new  ImportError(i + 1, e.getMessage())); 
                }
            }
        }
        
        return new ImportResult(successList.size()  + errorList.size(),  
                              successList.size(),  errorList.size(),  errorList);
    }
 
    private Provider parseProviderRow(Row row) {
    	Provider provider = new Provider();
         provider.setProvidername(getCellValue(row,  0)); 
         provider.setAddress(getCellValue(row,  1));   
         provider.setConnectionperson(getCellValue(row,  2));         
         provider.setPhone(getCellValue(row,  3)); 
        return provider;
    }
    
    public ImportResult importSalesExcel(MultipartFile file) throws Exception {
        List<Sales> successList = new ArrayList<>();
        List<ImportError> errorList = new ArrayList<>();
        String operateperson = (String) httpSession.getAttribute("username");
        try (InputStream is = file.getInputStream(); 
             Workbook workbook = WorkbookFactory.create(is))  {
            
            Sheet sheet = workbook.getSheetAt(0); 
            for (int i = 1; i <= sheet.getLastRowNum();  i++) { // 从第2行开始读取 
                Row row = sheet.getRow(i); 
                if (row == null || StringUtil.isBlank(getCellValue(row,  0))) continue;
                
                try {
                	Sales sales = parseSalesRow(row);
                	validateSales(sales);
                    
                    // 保存到数据库 
                	salesService.insert(sales.getId(),sales.getId(), sales.getPaytype(), new Date(), operateperson, sales.getNumber(), sales.getRemark(), 
                			sales.getSaleprice(), sales.getGoodsid());
                    successList.add(sales); 
                } catch (Exception e) {
                    errorList.add(new  ImportError(i + 1, e.getMessage())); 
                }
            }
        }
        
        return new ImportResult(successList.size()  + errorList.size(),  
                              successList.size(),  errorList.size(),  errorList);
    }
 
    private Sales parseSalesRow(Row row) {
    	 Sales sales = new Sales();
    	 sales.setId(Integer.valueOf(getCellValue(row,  0)));
    	 
         //sales.setProductCode(); // 商品型号 
         //sales.setGoodsName(getCellValue(row,  1));   // 商品名称 
         //sales.setSize(getCellValue(row,  2));        // 商品规格 
         //sales.setProviderId(getCellValue(row,  3)); // 供应商ID 
        return sales;
    }

    public void downloadGoodsTemplate(HttpServletResponse response) {
        // 声明为外部变量以便finally块访问 
        SXSSFWorkbook workbook = null;
        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); 
            response.setHeader("Content-Disposition",  "attachment; filename=goods_template_YYYYMMDD_v1.xlsx"); 
            
            // 创建SXSSFWorkbook（默认保留100行在内存中）
            workbook = new SXSSFWorkbook();
            Sheet sheet = workbook.createSheet(" Product Record产品数据");
            
            // Create cell style with text wrapping
            CellStyle wrapStyle = workbook.createCellStyle(); 
            wrapStyle.setWrapText(true); 
            
            // 创建表头  
            Row headerRow = sheet.createRow(0); 
            headerRow.setHeightInPoints(30);  // Increased height for wrapped text
            
            headerRow.createCell(0).setCellValue("Model\n产品型号");
            headerRow.getCell(0).setCellStyle(wrapStyle); 
            
            headerRow.createCell(1).setCellValue("Remark\n产品规格");
            headerRow.getCell(1).setCellStyle(wrapStyle); 
            
            headerRow.createCell(2).setCellValue("Compatible for\n产品机型");
            headerRow.getCell(2).setCellStyle(wrapStyle); 

            
            // Set column widths (in units of 1/256th of a character width)
            sheet.setColumnWidth(0,  30 * 256);  // Provider Name 
            sheet.setColumnWidth(1,  30 * 256);  // Provider Address 
            sheet.setColumnWidth(2,  30 * 256);  // Contact Person

            // 写入响应流 
            try (ServletOutputStream out = response.getOutputStream())  {
                workbook.write(out); 
                workbook.dispose();  // Clean up temporary files
            }
        } catch (Exception e) {
            throw new RuntimeException("生成模板失败", e);
        } finally {
            // 确保关闭workbook以删除临时文件 
            if (workbook != null) {
                try {
                    workbook.close(); 
                } catch (Exception e) {
                    // 关闭异常可记录日志，但不中断主流程 
                    log.error(" 关闭Workbook时出错", e);
                }
                // 显式调用dispose()删除临时文件（SXSSF特有）
                workbook.dispose(); 
            }
        }
    }
    
    public void downloadCustomerTemplate(HttpServletResponse response) {
        // 声明为外部变量以便finally块访问 
        SXSSFWorkbook workbook = null;
        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); 
            response.setHeader("Content-Disposition",  "attachment; filename=customer_template_YYYYMMDD_v1.xlsx"); 
            
            // 创建SXSSFWorkbook（默认保留100行在内存中）
            workbook = new SXSSFWorkbook();
            Sheet sheet = workbook.createSheet("Customer Record客户数据");
            
            // Create cell style with text wrapping
            CellStyle wrapStyle = workbook.createCellStyle(); 
            wrapStyle.setWrapText(true); 
            
            // 创建表头 
            Row headerRow = sheet.createRow(0); 
            headerRow.setHeightInPoints(30);  // Increased height for wrapped text
            
            headerRow.createCell(0).setCellValue("Client's Name\n客户名称");
            headerRow.getCell(0).setCellStyle(wrapStyle); 
            
            headerRow.createCell(1).setCellValue("Client's Address\n客户地址");
            headerRow.getCell(1).setCellStyle(wrapStyle); 
            
            headerRow.createCell(2).setCellValue("Connection Person's Name\n联系人");
            headerRow.getCell(2).setCellStyle(wrapStyle); 
            
            headerRow.createCell(3).setCellValue("Phone Number\n联系人电话");
            headerRow.getCell(3).setCellStyle(wrapStyle);
            
            headerRow.createCell(4).setCellValue("Email Address\n邮箱");
            headerRow.getCell(4).setCellStyle(wrapStyle); 
            
            // Set column widths (in units of 1/256th of a character width)
            sheet.setColumnWidth(0,  30 * 256); 
            sheet.setColumnWidth(1,  30 * 256);
            sheet.setColumnWidth(2,  30 * 256);
            sheet.setColumnWidth(3,  30 * 256);  
            sheet.setColumnWidth(4,  30 * 256); 

            // 写入响应流 
            try (ServletOutputStream out = response.getOutputStream())  {
                workbook.write(out); 
                workbook.dispose();  // Clean up temporary files
            }
        } catch (Exception e) {
            throw new RuntimeException("生成模板失败", e);
        } finally {
            // 确保关闭workbook以删除临时文件 
            if (workbook != null) {
                try {
                    workbook.close(); 
                } catch (Exception e) {
                    // 关闭异常可记录日志，但不中断主流程 
                    log.error(" 关闭Workbook时出错", e);
                }
                // 显式调用dispose()删除临时文件（SXSSF特有）
                workbook.dispose(); 
            }
        }
    }
    
    public void downloadInportTemplate(HttpServletResponse response) {
        // 声明为外部变量以便finally块访问 
        SXSSFWorkbook workbook = null;
        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); 
            response.setHeader("Content-Disposition",  "attachment; filename=inport_template_YYYYMMDD_v1.xlsx"); 
            
            // 创建SXSSFWorkbook（默认保留100行在内存中）
            workbook = new SXSSFWorkbook();
            Sheet sheet = workbook.createSheet(" 进货数据");
            
            // 创建表头 
            Row headerRow = sheet.createRow(0); 
            headerRow.createCell(0).setCellValue(" 供应商");
            headerRow.createCell(1).setCellValue(" 商品名称");
            headerRow.createCell(2).setCellValue(" 商品型号");
            headerRow.createCell(3).setCellValue(" 商品规格");
            headerRow.createCell(3).setCellValue(" 进货时间");
            headerRow.createCell(3).setCellValue(" 操作员");
            headerRow.createCell(3).setCellValue(" 进货数量");
            headerRow.createCell(3).setCellValue(" 进货价格(RMB)");
            headerRow.createCell(3).setCellValue(" 备注");
            
            // 写入响应流 
            workbook.write(response.getOutputStream()); 
            
        } catch (Exception e) {
            throw new RuntimeException("生成模板失败", e);
        } finally {
            // 确保关闭workbook以删除临时文件 
            if (workbook != null) {
                try {
                    workbook.close(); 
                } catch (Exception e) {
                    // 关闭异常可记录日志，但不中断主流程 
                    log.error(" 关闭Workbook时出错", e);
                }
                // 显式调用dispose()删除临时文件（SXSSF特有）
                workbook.dispose(); 
            }
        }
    }
    
    public void downloadProviderTemplate(HttpServletResponse response) {
        // 声明为外部变量以便finally块访问 
        SXSSFWorkbook workbook = null;
        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); 
            response.setHeader("Content-Disposition",  "attachment; filename=provider_template_YYYYMMDD_v1.xlsx"); 
            
            // 创建SXSSFWorkbook（默认保留100行在内存中）
            workbook = new SXSSFWorkbook();
            Sheet sheet = workbook.createSheet("Provider Record供应商数据");
            
            // Create cell style with text wrapping
            CellStyle wrapStyle = workbook.createCellStyle(); 
            wrapStyle.setWrapText(true); 
            
            // 创建表头 
            Row headerRow = sheet.createRow(0); 
            headerRow.setHeightInPoints(30);  // Increased height for wrapped text
            
            headerRow.createCell(0).setCellValue("Provider Name\n供应商名称");
            headerRow.getCell(0).setCellStyle(wrapStyle); 
            
            headerRow.createCell(1).setCellValue("Provider Address\n供应商地址");
            headerRow.getCell(1).setCellStyle(wrapStyle); 
            
            headerRow.createCell(2).setCellValue("Connection Person's Name\n联系人名称");
            headerRow.getCell(2).setCellStyle(wrapStyle); 
            
            headerRow.createCell(3).setCellValue("Phone Number\n电话");
            headerRow.getCell(3).setCellStyle(wrapStyle); 
            
            // Set column widths (in units of 1/256th of a character width)
            sheet.setColumnWidth(0,  30 * 256);  // Provider Name 
            sheet.setColumnWidth(1,  30 * 256);  // Provider Address 
            sheet.setColumnWidth(2,  30 * 256);  // Contact Person
            sheet.setColumnWidth(3,  30 * 256);  // Phone Number

            // 写入响应流 
            try (ServletOutputStream out = response.getOutputStream())  {
                workbook.write(out); 
                workbook.dispose();  // Clean up temporary files
            }
        } catch (Exception e) {
            throw new RuntimeException("生成模板失败", e);
        } finally {
            // 确保关闭workbook以删除临时文件 
            if (workbook != null) {
                try {
                    workbook.close(); 
                } catch (Exception e) {
                    // 关闭异常可记录日志，但不中断主流程 
                    log.error(" 关闭Workbook时出错", e);
                }
                // 显式调用dispose()删除临时文件（SXSSF特有）
                workbook.dispose(); 
            }
        }
    }
 
    public void downloadSalesTemplate(HttpServletResponse response) {
        // 声明为外部变量以便finally块访问 
        SXSSFWorkbook workbook = null;
        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); 
            response.setHeader("Content-Disposition",  "attachment; filename=sales_template_YYYYMMDD_v1.xlsx"); 
            
            // 创建SXSSFWorkbook（默认保留100行在内存中）
            workbook = new SXSSFWorkbook();
            Sheet sheet = workbook.createSheet(" 商品销售数据");
            
            // 创建表头 
            Row headerRow = sheet.createRow(0); 
            headerRow.createCell(0).setCellValue(" 客户名称");
            headerRow.createCell(1).setCellValue(" 商品名称");
            headerRow.createCell(2).setCellValue(" 商品型号");
            headerRow.createCell(3).setCellValue(" 商品规格");
            headerRow.createCell(4).setCellValue(" 支付类型");
            headerRow.createCell(5).setCellValue(" 销售时间");
            headerRow.createCell(6).setCellValue(" 销售员");
            headerRow.createCell(7).setCellValue(" 销售数量");
            headerRow.createCell(8).setCellValue(" 销售价格(PHP)");
            headerRow.createCell(8).setCellValue(" 备注");
            
            // 写入响应流 
            workbook.write(response.getOutputStream()); 
            
        } catch (Exception e) {
            throw new RuntimeException("生成模板失败", e);
        } finally {
            // 确保关闭workbook以删除临时文件 
            if (workbook != null) {
                try {
                    workbook.close(); 
                } catch (Exception e) {
                    // 关闭异常可记录日志，但不中断主流程 
                    log.error(" 关闭Workbook时出错", e);
                }
                // 显式调用dispose()删除临时文件（SXSSF特有）
                workbook.dispose(); 
            }
        }
    }
    
    private String getCellValue(Row row, int cellNum) {
        Cell cell = row.getCell(cellNum); 
        if (cell == null) return "";
        
        switch (cell.getCellType())  {
            case STRING: return cell.getStringCellValue(); 
            case NUMERIC: return String.valueOf((int)cell.getNumericCellValue()); 
            default: return "";
        }
    }
    
    private void validateGoods(Goods goods) throws Exception {
//      if (StringUtils.isEmpty(goods.getProductCode()))  {
//          throw new Exception("商品型号不能为空");
//      }
      // 添加其他验证逻辑...
    }
    
    private void validateCustomer(Customer customer) throws Exception {
//      if (StringUtils.isEmpty(goods.getProductCode()))  {
//          throw new Exception("商品型号不能为空");
//      }
      // 添加其他验证逻辑...
    }
    
    private void validateInport(Inport inport) throws Exception {
//      if (StringUtils.isEmpty(goods.getProductCode()))  {
//          throw new Exception("商品型号不能为空");
//      }
      // 添加其他验证逻辑...
    }
    
    private void validateProvider(Provider provider) throws Exception {
//      if (StringUtils.isEmpty(goods.getProductCode()))  {
//          throw new Exception("商品型号不能为空");
//      }
      // 添加其他验证逻辑...
    }
    
    private void validateSales(Sales sales) throws Exception {
//      if (StringUtils.isEmpty(goods.getProductCode()))  {
//          throw new Exception("商品型号不能为空");
//      }
      // 添加其他验证逻辑...
    }

}