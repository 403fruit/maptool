package net.rptools.maptool.client.functions.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.MalformedJsonException;
import java.math.BigDecimal;
import java.text.ParseException;
import net.rptools.maptool.language.I18N;
import net.rptools.parser.ParserException;

/**
 * Class used to convert between json and MT Script types.
 */
class JsonMTSTypeConversion {

  /** parser used to parse strings into {@link JsonElement} */
  private final JsonParser parser;

  /** An empty <code>String</code> as a {@link JsonPrimitive}. */
  public  static final JsonPrimitive EMPTY_STRING_ELEMENT = new JsonPrimitive("");


  /**
   * Creates a new <code>JsonMTSTypeConversion</code> object.
   * @param parser The json parser which will be used to convert a string into json.
   */
  JsonMTSTypeConversion(JsonParser parser) {
    this.parser = parser;
  }

  /**
   * Returns a valid MTScript type for the given object.
   * @param val the object to return a MTScript type for.
   * @return the MTScript type for the passed in object.
   */
  Object asScriptType(Object val) {
    if (val == null) {
      return "null";
    } else if (val instanceof JsonPrimitive) {
      JsonPrimitive jsonPrimitive = (JsonPrimitive) val;
      if (jsonPrimitive.isNumber()) {
        return jsonPrimitive.getAsBigDecimal();
      } else if (jsonPrimitive.isBoolean()) {
        return jsonPrimitive.getAsBoolean() ? "true" : "false";
      } else {
        return jsonPrimitive.getAsString();
      }
    } else if (val instanceof JsonElement) {
      return val;
    } else {
      return val.toString();
    }
  }

  /**
   * Returns a {@link JsonElement} version of the passed in object.
   * @param o the object tp convert to a {@link JsonElement}.
   * @return a {@link JsonElement} version of the object.
   */
  JsonElement asJsonElement(Object o) throws ParserException {
    if (o instanceof JsonElement) {
      return (JsonElement) o;
    }

    try {
      return parser.parse(o.toString());
    } catch (JsonSyntaxException jse) { // return String
      return new JsonPrimitive(o.toString());
    }
  }

  /**
   * Returns a {@link JsonElement} version of the passed in object. If the object is already a
   * {@link JsonElement} then it will return a cloned copy. As {@link JsonPrimitive}s are immutable
   * they may not be cloned.
   *
   * @param json the object tp convert to a {@link JsonElement}.
   * @return a {@link JsonElement} version of the object.
   */
  JsonElement asClonedJsonElement(Object json) throws ParserException {
    if (json instanceof JsonElement) {
      JsonElement jsonElement = (JsonElement) json;
      return jsonElement.deepCopy();
    } else {
      return asJsonElement(json);
    }
  }


  /**
   * Converts a <code>String</code> to a {@link JsonPrimitive}.
   * @param value the value to convert.
   * @return the converted value.
   */
  JsonPrimitive convertPrimitiveFromString(String value) {
    // Empty list element generates an empty string in the JSON array
    if (value.length() == 0) {
      return EMPTY_STRING_ELEMENT;
    } else {
      // Try to convert it to a number and if that works store it that way
      try {
        BigDecimal bd = new BigDecimal(value);
        return new JsonPrimitive(bd);
      } catch (NumberFormatException nfe) { // Not a number
        return new JsonPrimitive(value);
      }
    }
  }



}
