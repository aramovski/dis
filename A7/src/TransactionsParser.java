import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TransactionsParser {

    private Path _path;

    public TransactionsParser(String path) {
        _path = Paths.get(path);
    }

    public List<Transaction> getAllTransactions() {

        ArrayList<Transaction> Transactions = new ArrayList<Transaction>();
        try (Stream<String> stream = Files.lines(_path)) {
            stream.forEach(line ->
                    Transactions.add(new Transaction(line))
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Transactions;
    }
}

