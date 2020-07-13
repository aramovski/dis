import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Transaction {

    private List<Integer> Articles;

    public Transaction (String Articles) {
        this.Articles = new ArrayList<>();
        for(String s : Articles.split("\\s+"))
        {
            this.Articles.add(Integer.parseInt(s));
        }
    }

    public List<Integer> getArticles() {
        return this.Articles;
    }

    public boolean contains(String article) {

        return this.Articles.contains(article);
    }

    public boolean containsAll(List<Integer> articles) {

        return this.Articles.containsAll(articles);
    }

    @Override
    public String toString() {
        return this.Articles.toString();
    }
}
