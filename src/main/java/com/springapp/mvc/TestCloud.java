package com.springapp.mvc;

import hex.gbm.GBM;
import water.Boot;
import water.H2O;
import water.Key;
import water.UKV;
import water.fvec.Frame;
import water.fvec.NFSFileVec;
import water.fvec.ParseDataset2;
import water.fvec.Vec;
import water.serial.Model2FileBinarySerializer;

import java.io.File;
import java.io.IOException;

/**
 * User: dbraga - Date: 10/24/14
 */

// VERSION 1
public class TestCloud {

  private abstract static class PrepData { abstract Vec prep(Frame fr); }

  public void init() throws Exception {
    System.out.println("Initialization of the H2oCloud");
    Boot.main(TestCloud.class, new String[]{});
  }


  public static void main(String[] args) throws IOException {
    H2O.main(new String[]{"-name", "myCloud", "-md5skip"});
    H2O.waitForCloudSize(1);
    System.out.println("H2oCloud initialized");

    //TODO: stop here after initialization on real prod example. everything after this should be in predictor (training the model and storing it to disk)
    //############################################################################################################


    File file1 = new File("/Users/dbraga/Desktop/thoth/dense_train2");
    Key fkey1 = NFSFileVec.make(file1);
    Key dest1 = Key.make("thoth-train.hex");

    //    File file2 = new File("/Users/pmhatre/thoth-data/exp/latest/sept/dense_test2");
    File file2 = new File("/Users/dbraga/Desktop/thoth/dense_test2");
    Key fkey2 = NFSFileVec.make(file2);
    Key dest2 = Key.make("thoth-test.hex");
    Frame ftest = ParseDataset2.parse(dest2, new Key[]{fkey2});


    GBM gbm = new GBM();
    GBM.GBMModel model = null;
    gbm.source = ParseDataset2.parse(dest1, new Key[]{fkey1});
    gbm.response = new PrepData() { @Override
                                    Vec prep(Frame fr) { return fr.vecs()[0]; } }.prep(gbm.source);
    gbm.ntrees = 10;
    //    gbm.max_depth = 3;
    gbm.balance_classes = true;
    gbm.learn_rate = 0.1f;
    gbm.min_rows = 10;
    gbm.nbins = 20;
    //    gbm.cols =  new int[] {0,1,2,3,4,5,6,7,8,9,10};
    gbm.cols =  new int[] {1,2,3,4,5,6,7,8,9,10};

    gbm.invoke();
    model = UKV.get(gbm.dest());

    // Model serialization
    File modelFile = new File("/Users/dbraga/Desktop/thoth/gbm_model1000");
    new Model2FileBinarySerializer().save(model, modelFile);

  }

}
