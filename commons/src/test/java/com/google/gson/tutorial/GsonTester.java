package com.google.gson.tutorial;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import org.testng.annotations.Test;

import java.io.*;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;


/**
 * https://www.tutorialspoint.com/gson/index.htm
 */
public class GsonTester {
    @Test
    public void main() {
        String jsonString = "{\"name\":\"Mahesh\", \"age\":21,\"bd\":\"2018-04-11 11:39:11\"}";

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting()
                .setDateFormat("yyyy-MM-dd hh:mm:ss");

        Gson gson = builder.create();
        Student student = gson.fromJson(jsonString, Student.class);
        System.out.println(student);
        student.setBd(new Date());

        jsonString = gson.toJson(student);
        System.out.println(jsonString);
        System.out.println("isInMiddleSchool:" + student.isInMiddleSchool());


    }

    @Test
    public void serializationTest() {
        try {
            Student student = new Student();
            student.setAge(10);
            student.setName("Mahesh");
            writeJSON(student);
            Student student1 = readJSON();
            System.out.println(student1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void serializationArrayTest0() {
        Gson gson = new Gson();
        int[] marks = {100, 90, 85};
        String[] names = {"Ram", "Shyam", "Mohan"};

        //Serialization
        System.out.print("{");
        System.out.print("\"marks\":" + gson.toJson(marks) + ",");
        System.out.print("\"names\":" + gson.toJson(names));
        System.out.println("}");

        //De-serialization
        marks = gson.fromJson("[100,90,85]", int[].class);
        names = gson.fromJson("[\"Ram\",\"Shyam\",\"Mohan\"]", String[].class);
        System.out.println("marks:" + Arrays.toString(marks));
        System.out.println("names:" + Arrays.toString(names));


    }

    @Test
    public void serializationCollectionTest() {
        Gson gson = new Gson();
        Collection<Integer> marks = new ArrayList<Integer>();
        marks.add(100);
        marks.add(90);
        marks.add(85);

        //Serialization
        System.out.print("{");
        System.out.print("\"marks\":" + gson.toJson(marks));
        System.out.println("}");

        //De-serialization
        Type listType = new TypeToken<Collection<Integer>>() {
        }.getType();
        marks = gson.fromJson("[100,90,85]", listType);
        System.out.println("marks:" + marks);
    }

    @Test
    public void serializationGenericsTest() {
        // create a shape class of type circle.
        Shape<Circle> shape = new Shape<>();

        // Create a Circle object
        Circle circle = new Circle(5.0);

        //assign circle to shape
        shape.setShape(circle);
        Gson gson = new Gson();

        // Define a Type shapeType of type circle.
        Type shapeType = new TypeToken<Shape<Circle>>() {
        }.getType();

        //Serialize the json as ShapeType
        String jsonString = gson.toJson(shape, shapeType);
        System.out.println(jsonString);
        Shape shape1 = gson.fromJson(jsonString, Shape.class);

        System.out.println(shape1.get().getClass());
        System.out.println(shape1.get().toString());
        System.out.println(shape1.getArea());
        System.out.println("使用 shapeType，明确反序列化类型");
        Shape shape2 = gson.fromJson(jsonString, shapeType);
        System.out.println(shape2.get().getClass());
        System.out.println(shape2.get().toString());
        System.out.println(shape2.getArea());
    }


    @Test
    public void DataBinding() {
        Gson gson = new Gson();
        String name = "Mahesh Kumar";
        long rollNo = 1;
        boolean verified = false;
        int[] marks = {100, 90, 85};

        //Serialization
        System.out.println("{");
        System.out.println("name: " + gson.toJson(name) + ",");
        System.out.println("rollNo: " + gson.toJson(rollNo) + ",");
        System.out.println("verified: " + gson.toJson(verified) + ",");
        System.out.println("marks:" + gson.toJson(marks));
        System.out.println("}");

        //De-serialization
        name = gson.fromJson("\"Mahesh Kumar\"", String.class);
        rollNo = gson.fromJson("1", Long.class);
        verified = gson.fromJson("false", Boolean.class);
        marks = gson.fromJson("[100,90,85]", int[].class);

        System.out.println("name: " + name);
        System.out.println("rollNo: " + rollNo);
        System.out.println("verified: " + verified);
        System.out.println("marks:" + Arrays.toString(marks));
    }

    private void writeJSON(Student student) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        FileWriter writer = new FileWriter("student.json");
        writer.write(gson.toJson(student));
        writer.close();
    }

    private Student readJSON() throws FileNotFoundException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader("student.json"));

        Student student = gson.fromJson(bufferedReader, Student.class);
        return student;
    }

    @Test
    public void ObjectDateBinding() {
        Gson gson = new Gson();
        Student student = new Student();
        student.setAge(10);
        student.setName("Mahesh");

        String jsonString = gson.toJson(student);
        System.out.println(jsonString);

        Student student1 = gson.fromJson(jsonString, Student.class);
        System.out.println(student1);
    }


    @Test
    public void treeModel() {
        String jsonString =
                "{\"name\":\"Mahesh Kumar\", \"age\":21,\"verified\":false,\"marks\": [100,90,85]}";
        JsonParser parser = new JsonParser();
        JsonElement rootNode = parser.parse(jsonString);

        if (rootNode.isJsonObject()) {
            JsonObject details = rootNode.getAsJsonObject();
            JsonElement nameNode = details.get("name");
            System.out.println("Name: " + nameNode.getAsString());

            JsonElement ageNode = details.get("age");
            System.out.println("Age: " + ageNode.getAsInt());

            JsonElement verifiedNode = details.get("verified");
            System.out.println("Verified: " + (verifiedNode.getAsBoolean() ? "Yes" : "No"));
            JsonArray marks = details.getAsJsonArray("marks");

            for (int i = 0; i < marks.size(); i++) {
                JsonPrimitive value = marks.get(i).getAsJsonPrimitive();
                System.out.print(value.getAsInt() + " ");
            }

            System.out.println();
            JsonElement notexist = details.get("notexist");
            System.out.println(notexist);

        }
    }

    @Test
    public void stream() {
////create JsonReader object and pass it the json source or json text.
//    JsonReader reader = new JsonReader(new StringReader(jsonString));
//
////start reading json
//    reader.beginObject();
//
////get the next token
//    JsonToken token = reader.peek();
//
////check the type of the token
//    if (token.equals(JsonToken.NAME)) {
//      //get the current token
//      fieldname = reader.nextName();
//    }
        ////create JsonReader object and pass it the json source or json text.
        String jsonString =
                "{\"name\":\"Mahesh Kumar\", \"age\":21,\"verified\":false,\"marks\": [100,90,85]}";
        JsonReader reader = new JsonReader(new StringReader(jsonString));
        try {
            handleJsonObject(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleJsonObject(JsonReader reader) throws IOException {
        reader.beginObject();
        String fieldname = null;

        while (reader.hasNext()) {
            JsonToken token = reader.peek();

            if (token.equals(JsonToken.BEGIN_ARRAY)) {
                System.out.print("Marks [ ");
                handleJsonArray(reader);
                System.out.print("]");
            } else if (token.equals(JsonToken.END_OBJECT)) {
                reader.endObject();
                return;
            } else {
                if (token.equals(JsonToken.NAME)) {
                    //get the current token
                    fieldname = reader.nextName();
                }
                if ("name".equals(fieldname)) {
                    //move to next token
                    token = reader.peek();
                    System.out.println("Name: " + reader.nextString());
                }
                if ("age".equals(fieldname)) {
                    //move to next token
                    token = reader.peek();
                    System.out.println("Age:" + reader.nextInt());
                }
                if ("verified".equals(fieldname)) {
                    //move to next token
                    token = reader.peek();
                    System.out.println("Verified:" + reader.nextBoolean());
                }
            }
        }
    }

    private void handleJsonArray(JsonReader reader) throws IOException {
        reader.beginArray();

        while (true) {
            JsonToken token = reader.peek();

            if (token.equals(JsonToken.END_ARRAY)) {
                reader.endArray();
                break;
            } else if (token.equals(JsonToken.BEGIN_OBJECT)) {
                handleJsonObject(reader);
            } else if (token.equals(JsonToken.END_OBJECT)) {
                reader.endObject();
            } else {
                System.out.print(reader.nextInt() + " ");
            }
        }
    }


    @Test
    public void nullObject() {
        Gson gson = new Gson();

        Student student = new Student();
        student.setRollNo(1);
        String jsonString = gson.toJson(student);

        System.out.println(jsonString);
        student = gson.fromJson(jsonString, Student.class);
        System.out.println(student);


        System.out.println("\n\n\nserialize null .");
        GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls();
        builder.setPrettyPrinting();
        gson = builder.create();

        student = new Student();
        student.setRollNo(1);
        jsonString = gson.toJson(student);

        System.out.println(jsonString);
        student = gson.fromJson(jsonString, Student.class);
        System.out.println(student);
    }


    @Test
    public void versioningSupport() {
        GsonBuilder builder = new GsonBuilder();
        builder.setVersion(1.0);
        Gson gson = builder.create();

        Student student = new Student();
        student.setRollNo(1);
        student.setName("Mahesh Kumar");
        student.setVerified(true);

        String jsonString = gson.toJson(student);
        System.out.println(jsonString);

        gson = new Gson();
        jsonString = gson.toJson(student);
        System.out.println(jsonString);

    }

    /**
     * By default, GSON excludes transient and static fields from the serialization/deserialization process.
     * Let’s take a look at the following example.
     */
    @Test
    public void ExcludingfieldsfromSerialization() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        Student student = new Student();
        student.setRollNo(1);
        student.setName("Mahesh Kumar");
        student.setVerified(true);
        student.setId(1);
        Student.className = "VI";

        String jsonString = gson.toJson(student);
        System.out.println(jsonString);
    }


    /**
     * GsonBuilder provides control over excluding fields with particular modifier using excludeFieldsWithModifiers()
     * method from serialization/deserialization process. See the following example.
     */
    @Test
    public void ExcludingfieldsfromSerialization2() {
        GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithModifiers(Modifier.TRANSIENT);
        Gson gson = builder.create();

        Student student = new Student();
        student.setRollNo(1);
        student.setName("Mahesh Kumar");
        student.setVerified(true);
        student.setId(1);
        Student.className = "VI";

        String jsonString = gson.toJson(student);
        System.out.println(jsonString);
    }


    /**
     * Gson provides @Expose annotation to control the Json serialization/deserialization of a class based on its scope.
     * Consider the following class with a variable having @Expose support. In this class,
     * name and rollno variables are to be exposed for serialization.
     * Then we've used the GsonBuilder.excludeFieldsWithoutExposeAnnotation() method to
     * indicate that only exposed variables are to be serialized/deserialized. See the following example.
     */
    @Test
    public void ExcludingfieldsfromSerialization3() {
        GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation();
        Gson gson = builder.create();

        Student student = new Student();
        student.setRollNo(1);
        student.setName("Mahesh Kumar");
        student.setVerified(true);
        student.setId(1);
        Student.className = "VI";

        String jsonString = gson.toJson(student);
        System.out.println(jsonString);
    }
}  