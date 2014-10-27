//package com.springapp.mvc;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import java.io.File;
//import java.net.URL;
//
///**
// * User: dbraga - Date: 10/25/14
// */
//@Controller
//@RequestMapping("/dami")
//public class DamiController {
//
//  @RequestMapping(method = RequestMethod.GET)
//  public String printWelcome(ModelMap model) throws Exception {
//    model.addAttribute("message", "Hello world!");
//    org.apache.commons.io.FileUtils.copyURLToFile(new URL("http://localhost:8081/models/1414292057110"), new File("downloaded_1414292057110"));
//    return "hello";
//  }
//}
