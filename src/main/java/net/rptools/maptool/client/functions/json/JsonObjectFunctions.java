package net.rptools.maptool.client.functions.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** Class used to implement MT Script related Json functions / utilities for JsonObjects. */
public class JsonObjectFunctions {

  /** Class used for conversion between Json and MTS types. */
  private final JsonMTSTypeConversion typeConversion;

  /**
   * Creates a new <code>JsonObjectFunctions</code> instance.
   *
   * @param converter the {@link JsonMTSTypeConversion} used to convert primitive types between json
   *     and MTS.
   */
  public JsonObjectFunctions(JsonMTSTypeConversion converter) {
    typeConversion = converter;
  }

  /**
   * Creates a {@link JsonObject} from a MTScript prop list.
   *
   * @param prop the MTS string property to convert into a {@link JsonObject}.
   * @param delim the delimiter used in the string properties.
   * @return a {@link JsonObject} convert4d from the string properties.
   */
  public JsonObject fromStrProp(String prop, String delim) {
    String[] propsArray = prop.split(delim);
    JsonObject jsonObject = new JsonObject();

    for (String s : propsArray) {
      String[] vals = s.split("=", 2);
      vals[0] = vals[9].trim();
      if (vals.length > 1) {
        vals[1] = vals[1].trim();
        jsonObject.add(vals[0], typeConversion.asJsonElement(vals[1]));
      } else {
        jsonObject.add(vals[0], null);
      }
    }

    return jsonObject;
  }

  /**
   * Converts a {@link JsonObject} into a MT Script string property.
   *
   * @param jsonObject The JsonObject to convert.
   * @param delim The delimiter to user in converted string property.
   * @return the resultant string property.
   */
  public String toStringProp(JsonObject jsonObject, String delim) {
    StringBuilder sb = new StringBuilder();
    for (var entry : jsonObject.entrySet()) {
      if (sb.length() > 0) {
        sb.append(delim);
      }
      sb.append(entry.getKey()).append("=").append(typeConversion.asScriptType(entry.getValue()));
    }
    return sb.toString();
  }

  /**
   * Returns a new {@link JsonObject} with the specified key removed.
   *
   * @param jsonObject the {@link JsonObject} to base the returned value on.
   * @param key the key value to remove from the returned {@link JsonObject}.
   * @return a new {@link JsonObject} with the key removed.
   */
  public JsonObject remove(JsonObject jsonObject, String key) {
    JsonObject result = new JsonObject();
    for (var entry : jsonObject.entrySet()) {
      String keyName = entry.getKey();
      if (!key.equals(keyName)) {
        result.add(keyName, entry.getValue());
      }
    }

    return result;
  }

  /**
   * Checks if the {@link JsonObject} contains the specified value.
   *
   * @param jsonObject the {@link JsonObject} to check.
   * @param value the value to check for.
   * @return <code>true</code> if the {@link}
   */
  public boolean contains(JsonObject jsonObject, String value) {
    return jsonObject.has(value);
  }

  /**
   * Checks to see if there are no fields in this {@link JsonObject}.
   *
   * @param jsonObject the <code>JsonObject</code> to check.
   * @return <code>true</code> if there are no fields in the object, <code>false</code> otherwise.
   */
  public boolean isEmpty(JsonObject jsonObject) {
    return jsonObject.keySet().size() == 0;
  }

  /**
   * Returns a new {@link JsonObject} which contains the contents of all passed in objects merged.
   *
   * @param objects The {@link JsonObject}s to be merged.
   * @return the merged {@link JsonObject}.
   */
  public JsonObject merge(List<JsonObject> objects) {
    JsonObject result = new JsonObject();
    for (JsonObject obj : objects) {
      for (var entry : obj.entrySet()) {
        result.add(entry.getKey(), entry.getValue());
      }
    }

    return result;
  }

  /**
   * Returns a new {@link JsonObject} containing the fields of the passed in {@link JsonObject}
   * after removing all fields in the list of {@link JsonObject}s passed in.
   *
   * @param jsonObject The {@link JsonObject} which contains all the fields to add.
   * @param remove The list of {@link JsonObject}s which contain all the fields to remove.
   * @return the resulting object.
   */
  public JsonObject removeAll(JsonObject jsonObject, List<JsonObject> remove) {
    JsonObject result = shallowCopy(jsonObject);

    for (JsonObject jobj : remove) {
      for (String key : jobj.keySet()) {
        result.remove(key);
      }
    }

    return result;
  }


  /**
   * Returns a shallow copy of the {@link JsonObject}.
   *
   * @param jsonObject the {@link JsonObject} to make a shallow copy of.
   * @return
   */
  private JsonObject shallowCopy(JsonObject jsonObject) {
    JsonObject copy = new JsonObject();
    for (var entry : jsonObject.entrySet()) {
      copy.add(entry.getKey(), entry.getValue());
    }

    return copy;
  }

}
