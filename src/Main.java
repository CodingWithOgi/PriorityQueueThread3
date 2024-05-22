import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {
    public static void main(String[] args) throws Exception {
        File file1 = new File("resources/guess.txt");
        File file2 = new File("resources/calvin_klein.txt");
        File file3 = new File("resources/trussardi.txt");

        Queue<Product> tempQueue = new ConcurrentLinkedQueue<>();

        Thread t1 = new Thread(() -> processFile(file1, tempQueue));
        Thread t2 = new Thread(() -> processFile(file2, tempQueue));
        Thread t3 = new Thread(() -> processFile(file3, tempQueue));

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        PriorityQueue<Product> pq = new PriorityQueue<>(tempQueue);

        File outputFile = new File("resources/output.txt");
        PrintWriter output = new PrintWriter(outputFile);
        for (int i = 0; i < 10 && !pq.isEmpty(); i++) {
            output.println(pq.poll().toString());
        }
        output.close();
    }

    private static void processFile(File file, Queue<Product> queue) {
        try (Scanner input = new Scanner(file)) {
            while (input.hasNext()) {
                String name = input.next();
                String type = input.next();
                double price = input.nextDouble();
                queue.add(new Product(name, type, price));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
