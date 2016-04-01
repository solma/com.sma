package com.sma.thirdpartylibrary.gson;

import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputJson {

  public static void main(String[] args) {
    InputJson inputJson = new InputJson(genIdMap());
    Gson gson = new Gson();

    String jsonString = gson.toJson(inputJson);
    System.out.println(jsonString);

    jsonString = "{\"idMap\":{\"ie76c08d94bece584b0dd34a424d4eeef579201045\":{\"2014-11-13\":{\"com.motorola.MotGallery2\":{\"appResume\":[1415838244009],\"appPause\":[1415838254791]},\"com.facebook.katana\":{\"appPause\":[1415837719960,1415837311468],\"appResume\":[1415837349082,1415837267944]},\"com.whatsapp\":{\"appResume\":[1415838137291,1415838066518,1415837225582,1415838256578,1415838194725],\"appPause\":[1415838314383,1415837266088,1415838108454,1415838242224,1415838177887]}}}}}";
    InputJson deserialization = gson.fromJson(jsonString, InputJson.class);
    System.out.println(gson.toJson(deserialization));
  }

  static Map<String, Map<String, Map<String, TwoLists>>> genIdMap() {
    Map<String, Map<String, Map<String, TwoLists>>> map = new HashMap<>();
    map.put("imei", new HashMap<String, Map<String, TwoLists>>());
    map.get("imei").put("date", new HashMap<String, TwoLists>());
    map.get("imei").get("date").put("app1", new TwoLists(
        ImmutableList.of(1415837719960L, 1415837311468L),
        ImmutableList.of(1415837349082L, 1415837267944L)));
    return map;
  }

  Map<String, Map<String, Map<String, TwoLists>>> idMap;

//  public InputJson(){
//  }

  public InputJson(Map<String, Map<String, Map<String, TwoLists>>> map) {
    this.idMap = map;
  }

  static class TwoLists {
    List<Long> appPause;
    List<Long> appResume;

//    public TwoLists(){
//    }

    public TwoLists(List<Long> l1, List<Long> l2) {
      this.appPause = l1;
      this.appResume = l2;
    }

    @Override
    public String toString() {
      return appPause + "," + appResume;
    }
  }
}
