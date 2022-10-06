package ru.script;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
       try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0])));
             PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(args[1])))
        ) {
            StringBuilder sb = new StringBuilder();
            List<String> firstList = new ArrayList<>();
            List<String> secondList = new ArrayList<>();
            int i = 1;
            for (String read = reader.readLine(); read != null; read = reader.readLine()) {
                sb.append(read);
                sb.insert(0, i++ + ")" + " " + "[");
                sb.insert(sb.length(), "]" + "(#" + read
                                .replace(" ", "-")
                                .replace("?", "")
                                .replace(".", "")
                                .replace(",", "")
                                .replace("()", "")
                                .toLowerCase(Locale.ROOT)
                        + ")");
                firstList.add(sb.toString());
                sb = new StringBuilder();
                sb.append("## ").append(read
                        .replace("?", "")
                        .replace(".", ""));
                secondList.add(sb.toString());
                sb = new StringBuilder();
            }
            firstList.addAll(secondList);
            writer.println("### " + args[1] + "\n");
            firstList.forEach(writer::println);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
