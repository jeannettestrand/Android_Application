package ca.camosun.lab7_8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.text.DecimalFormat;
public class Converters {

    // Create collection objects to hold converter objects
    public static final List<Converters.ConverterItem> ITEMS = new ArrayList<>();
    public static final Map<String, Converters.ConverterItem> ITEM_MAP = new HashMap<>();

    // DecimalFormat Constant for use in setting double precision
    private static final DecimalFormat DF2 = new DecimalFormat(".##");

    // Constant used to display on UI in case of invalid input
    private static final String NA = "N/A";

    // Single static call to loadData() method to create Converter Objects
    static {
        addData();
    }

    //Static method which makes conversion objects, then uses collect() to put the objects in class containers.
    private static void addData() {
        collect(new ConverterItem("Area Converter", " ha > ac ", " ac > ha ",
                (value) -> {
                    try {
                        double v = Double.parseDouble(value);
                        return v < 0 ? NA : DF2.format(v * 2.47105);
                    } catch (NumberFormatException e) {
                        return NA;
                    }
                },
                (value) -> {
                    try {
                        double v = Double.parseDouble(value);
                        return v < 0 ? NA : DF2.format(v * 0.404686);
                    } catch (NumberFormatException e) {
                        return NA;
                    }
                }
        ));

        collect(new ConverterItem("Length Converter", " m > f ", " f > m ",
                (value) -> {
                    try {
                        double v = Double.parseDouble(value);
                        return v < 0 ? NA : DF2.format(v * 3.28084);
                    } catch (NumberFormatException e) {
                        return NA;
                    }
                },
                (value) -> {
                    try {
                        double v = Double.parseDouble(value);
                        return v < 0 ? NA : DF2.format(v * 0.305);
                    } catch (NumberFormatException e) {
                        return NA;
                    }
                }
        ));
        collect(new ConverterItem("Weight Converter", " lbs > kg ", " kg > lbs ",
                (value) -> {
                    try {
                        double v = Double.parseDouble(value);
                        return v < 0.0 ? NA : DF2.format(v * 0.453592);
                    } catch (NumberFormatException e) {
                        return NA;
                    }
                },
                (value) -> {
                    try {
                        double v = Double.parseDouble(value);
                        return v < 0 ? NA : DF2.format(v * 2.20462);
                    } catch (NumberFormatException e) {
                        return NA;
                    }
                }
        ));
        collect(new ConverterItem("Temperature Converter", " °C > F ", " F > °C ",
                (value) -> {
                    try {
                        double v = Double.parseDouble(value);
                        return DF2.format((v * 1.8) + 32);
                    } catch (NumberFormatException e) {
                        return NA;
                    }
                },
                (value) -> {
                    try {
                        double v = Double.parseDouble(value);
                        return DF2.format((v - 32.0) * 5.0 / 9.0);
                    } catch (NumberFormatException e) {
                        return NA;
                    }
                }
        ));
    }

    private static void collect(ConverterItem c){
        ITEMS.add(c);
        ITEM_MAP.put(c.name, c);
    }


    //  Nested Class, describes the properties and methods of the ConverterItem object.
    public static class ConverterItem {
        private String name;
        private String buttonA;
        private String buttonB;
        private ConversionMethod methodA;
        private ConversionMethod methodB;

        // Constructor assigns parameters to object properties and expressions
        public ConverterItem(String c, String bA, String bB, ConversionMethod mA, ConversionMethod mB) {
            name = c;
            buttonA = bA;
            buttonB = bB;
            methodA = mA;
            methodB = mB;
        }

        // Group of getter methods to provide access to private object properties
        @Override
        public String toString() { return name; }
        public String getName() { return name; }
        public String getButtonA() { return buttonA; }
        public String getButtonB() { return buttonB; }
        public ConversionMethod getMethodA() { return methodA; }
        public ConversionMethod getMethodB() { return methodB; }
    }

    // Interface provides signature required for ConverterItem Lambda expression assignment.
    public interface ConversionMethod {
        String result(String value);
    }
}