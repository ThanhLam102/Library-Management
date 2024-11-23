package B14_MiniProject.services;

import B14_MiniProject.models.Book;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BookDataService {



    public List<Book> read(String path) throws IOException {

        List<Book> books = new ArrayList<>();

        //Path pathFileToRead = Paths.get("./resources/student-input.txt");
        Path pathFileToRead = Paths.get(path);

        List<String> allLines = Files.readAllLines(pathFileToRead);

        for (int i = 0; i < allLines.size(); ++i) {

            String line = allLines.get(i);
            String[] data = line.split(",");

            Book book
                    = new Book(Integer.parseInt(data[0]) ,Integer.parseInt(data[1].trim()), data[2].trim(), data[3].trim(), data[4].trim());

            books.add(book);
        }

        return books;

    }


    public void write(String path, List<Book> books) throws IOException {

        // List<Student> => List<String>
        List<String> list = new ArrayList<>();

        for (int i = 0; i < books.size(); ++i) {

            Book book = books.get(i);

            list.add(book.toString());
        }

        // print to file
        Path pathFileToWrite = Paths.get(path);

        Files.write(pathFileToWrite, list);

    }

}