package com.springapp.mvc;

import water.Boot;
import water.H2O;
import water.Key;
import water.fvec.NFSFileVec;

import java.io.File;

/**
 * User: dbraga - Date: 10/24/14
 */

// VERSION 1
public class TestCloud {

  public void init() throws Exception {
    System.out.println("Initialization of the H2oCloud");
    Boot.main(TestCloud.class, new String[]{});
    System.out.println("H2oCloud initialized");
    System.out.println("Proceeding with loading data to the cloud ... ");
    File file1 = new File(this.getClass().getClassLoader().getResource("cars.csv").toURI());
    // Or specify the actual path
    //File file1 = new File("/Users/dbraga/h2o/smalldata/cars.csv");
    Key fkey1 = NFSFileVec.make(file1);
    System.out.println("Hello H2O world! Your file has been loaded.");
  }

  public static void main(String[] args){
    H2O.main(new String[]{"-name", "myCloud", "-md5skip"});
  }

}


//
//// VERSION 2
//public class TestCloud {
//
//  public void init() throws Exception {
//    System.out.println("Initialization of the H2oCloud");
//
//    H2O.main(new String[]{"-name", "myCloud", "-md5skip"});
//
//    System.out.println("H2oCloud initialized");
//    System.out.println("Proceeding with loading data to the cloud ... ");
//    File file1 = new File(this.getClass().getClassLoader().getResource("cars.csv").toURI());
//    // Or specify the actual path
//    //File file1 = new File("/Users/dbraga/h2o/smalldata/cars.csv");
//    Key fkey1 = NFSFileVec.make(file1);
//    System.out.println("Hello H2O world! Your file has been loaded.");
//  }
//
//}


//// VERSION 3
//public class TestCloud {
//
//  public void init() throws Exception {
//    System.out.println("Initialization of the H2oCloud");
//
//    Boot.main(new String[]{"-name", "myCloud", "-md5skip"});
//
//    System.out.println("H2oCloud initialized");
//    System.out.println("Proceeding with loading data to the cloud ... ");
//    File file1 = new File(this.getClass().getClassLoader().getResource("cars.csv").toURI());
//    // Or specify the actual path
//    //File file1 = new File("/Users/dbraga/h2o/smalldata/cars.csv");
//    Key fkey1 = NFSFileVec.make(file1);
//    System.out.println("Hello H2O world! Your file has been loaded.");
//  }
//
//}
