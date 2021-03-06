package com.springapp.mvc;

import hex.gbm.GBM;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import water.Boot;
import water.H2O;
import water.Model;

import java.io.IOException;

@Controller
@RequestMapping("/")
public class HelloController {

  public void init() throws Exception {
    Boot.main(HelloController.class, new String[]{});
  }



  public static void main(String[] args) throws IOException {
    long start = System.currentTimeMillis();

    //TODO: it doesn't seem we need to connect to the cloud from here too
    H2O.main(new String[]{"-name", "myCloud", "-md5skip"});
    H2O.waitForCloudSize(1);
    System.out.println("H2oCloud initialized");
    //
    //File modelFile = new File("/Users/dbraga/Desktop/gbm_modelpraneet");
    //if (modelFile == null) {
    //  System.out.println("Model file is null");
    //}
    //else {
    //  System.out.println("Model file was found");
    //}

    //GBM.GBMModel model = (GBM.GBMModel) new Model2FileBinarySerializer().load(modelFile);
   //
   //

    GBM.GBMModel model= null;

    System.out.print("Size bef: " + H2O.KeySnapshot.globalSnapshot().fetchAll(water.Model.class).keySet().size());

    for (String key : H2O.KeySnapshot.globalSnapshot().fetchAll(water.Model.class).keySet()){
       model = (GBM.GBMModel) H2O.KeySnapshot.globalSnapshot().fetchAll(Model.class).get(key);
     }


     model.delete();

    System.out.print("Size after: " + H2O.KeySnapshot.globalSnapshot().fetchAll(water.Model.class).keySet().size());

   //
   // //double[] example = new double[] {1, 0.0, 1362.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 13.0}; // one example
   // //    double[] example = new double[] {0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}; // zero example
   double[] example = new double[] {1, 200.0, 6.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0};
   //
   //
   ////TODO: ask Praneet if we can use a different method besides model.score0(double[] data, float[] preds) because it has protected access hence it can't be used directly
   ////float[] predictions = model.score0(example,new float[]{});
   // //TODO: tried to replace with this but i'm not sure if it's working
   float[] test = new float[3];
   model.score0(example, test,9);
   //
   //
   long end = System.currentTimeMillis();
   //System.out.println("Prediction time: " + (end - start) + " milli seconds");
   //System.out.println("Size of predictions array: " + predictions.length);
   //for(float pred: predictions) {
      for(float pred: test) {
      System.out.println(pred + "-");
   }

   // System.out.println("Prediction time: " + (end - start) + " milli seconds");
    //System.out.println("Size of predictions array: " + predictions.length);
    //for(float pred: predictions) {
    //System.out.println(pred + "-");
    //}
    //    System.out.println("Example individual prediction: " + prediction);

  }

  @RequestMapping(method = RequestMethod.GET)
  public String printWelcome(ModelMap model) throws Exception {
    // Call init witch does the prediction
    init();
    model.addAttribute("message", "Hello world!");
    return "hello";
  }
}