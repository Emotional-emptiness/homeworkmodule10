import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserConverter {
    public static void main(String[] args) {
        String inputFile = "file.txt";
        String outputFile = "user.json";
        convertToJSON(inputFile, outputFile);
    }

    public static void convertToJSON(String inputFile, String outputFile) {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String header = reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\s+");
                String name = data[0];
                int age = Integer.parseInt(data[1]);
                User user = new User(name, age);
                users.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write("[");
            writer.newLine();
            for (int i = 0; i < users.size(); i++) {
                User user = users.get(i);
                writer.write("    {");
                writer.newLine();
                writer.write("        \"name\": \"" + user.getName() + "\",");
                writer.newLine();
                writer.write("        \"age\": " + user.getAge());
                writer.newLine();
                writer.write("    }");
                if (i < users.size() - 1) {
                    writer.write(",");
                }
                writer.newLine();
            }
            writer.write("]");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
