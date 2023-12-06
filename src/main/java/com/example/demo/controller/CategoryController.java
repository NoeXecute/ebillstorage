package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import com.example.demo.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.CategoryService;

/**
 * カテゴリー処理 Controller
 */
@RestController
@CrossOrigin
@Validated
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/path/", method = RequestMethod.POST)
    public Result SetPath(@RequestBody @Valid String pathAddress) {
        categoryService.SetPath(pathAddress);
        return Result.ok(null);
    }

    @RequestMapping(value = "/getBillTypes", method = RequestMethod.GET)
    public Result GetBillTypes() {
        List<BillType> billTypes = categoryService.getBillTypes();
        return Result.ok(billTypes);
    }

    @RequestMapping(value = "/deleteBillType/{billTypeno}", method = RequestMethod.GET)
    public Result DeleteBillType(@RequestBody @PathVariable int billTypeno) {
        categoryService.deleteBillType(billTypeno);
        return Result.ok(null);
    }

    @RequestMapping(value = "/addBillType", method = RequestMethod.POST)
    public Result AddBillType(@RequestBody @Valid BillType billType) {
        if (categoryService.isBillTypeExists(billType.getBillType())) {
            // 如果已存在相同的账单类型，则返回错误信息
            return Result.error("相同の請求タイプは既に存在しています、重複して追加できません。");
        }
        categoryService.addBillType(billType);
        return Result.ok(null);
    }

    @RequestMapping(value="/editBillType",method=RequestMethod.POST)
    public Result editBillType(@RequestBody @Valid BillType billType){
        categoryService.editBillType(billType);
        return Result.ok(null);
    }

    @RequestMapping(value = "/getCustomers", method = RequestMethod.GET)
    public Result GetCustomers() {
        List<Customer> customers = categoryService.getCustomers();
        return Result.ok(customers);
    }

    @RequestMapping(value = "/deleteCustomer", method = RequestMethod.POST)
    public Result DeleteCustomer(@RequestBody Customer customer) {
        categoryService.deleteCustomer(customer);
        return Result.ok(null);
    }

    @RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
    public Result AddCustomer(@RequestBody @Valid Customer customer) {
        if (categoryService.isCustomerExists(customer)) {
            return Result.error("相同の請求タイプは既に存在しています、重複して追加できません。");
        }
        categoryService.addCustomer(customer);
        return Result.ok(null);
    }

    
    @RequestMapping(value="/editCustomer",method=RequestMethod.POST)
    public Result EditCustomer(@RequestBody @Valid Customer customer){
        categoryService.editCustomer(customer);
        return Result.ok(null);
    }

    @RequestMapping(value = "/getCustomerTypes", method = RequestMethod.GET)
    public Result GetCustomerTypes() {
        List<CustomerType> customerTypes = categoryService.getCustomerTypes();
        return Result.ok(customerTypes);
    }

    //Searchmanage
    @RequestMapping(value = "/getSearchManage", method = RequestMethod.GET)
    public Result GetSearchManage() {
        List<Searchmanage> searchManageList = categoryService.getSearchManage();
        return Result.ok(searchManageList);
    }
}
