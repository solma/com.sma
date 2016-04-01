package com.sma.thirdpartylibrary.gson;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.Map;

public class JsonElementToString {

  public static void main(String[] args) {
    JsonObject outer = new JsonObject();

    // String as a property
    String stringProperty = "primitiveProperty";
    outer.addProperty(stringProperty, "\"value\"");

    // JsonObject as a property
    JsonObject innerObject = new JsonObject();
    innerObject.addProperty("date", "\"2012\"");
    //payload.add(APP_USAGE_INTEREST_SEGMENT_NAME, getAppUsageArray());
    String nestedObject = "nestedObject";
    outer.add(nestedObject, innerObject);

    // JsonArray as a property
    String nestedArray = "nestedArray";
    JsonArray innerArray = new JsonArray();
    innerArray.add(new JsonPrimitive(1));
    innerArray.add(new JsonPrimitive(2));
    outer.add(nestedArray, innerArray);

    // JsonNull as a property
    outer.add("nullProperty", JsonNull.INSTANCE);

    // print out all properties
    for (Map.Entry<String, JsonElement> entry : outer.entrySet()) {
      JsonElement ele = entry.getValue();
      System.out.print(entry.getKey() + " : ");
      if (ele.isJsonPrimitive()) {
        System.out.println(ele.getAsString());
      } else {
        System.out.println(ele.toString());
      }
    }
  }
}
