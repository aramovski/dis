import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Candidate {
    private List<Integer> Articles = new ArrayList<>();
    private AtomicInteger Support = new AtomicInteger();

    public Candidate(int Article) {
        this.Articles.add(Article);
    }

    public Candidate(Collection<Integer> Articles) {
            this.Articles.addAll(Articles);
    }

    public Candidate(Candidate Candidate1, Candidate Candidate2) {
        this.Articles.addAll(Candidate1.getArticles());
        this.Articles.addAll(Candidate2.getArticles());
    }

    public Candidate(Candidate ci, Integer integer) {
        this.Articles.addAll(ci.getArticles());
        this.Articles.add(integer);
    }

    public void IncrementSuport() {
        this.Support.getAndIncrement();
    }

    public List<Integer> getArticles() {
        return this.Articles;
    }

    public int getSupport() {
        return this.Support.get();
    }

    @Override
    public boolean equals(Object obj) {

        Candidate c = Candidate.class.cast(obj);
        return this.Articles.equals(c.getArticles());
    }

    @Override
    public String toString() {

        return "(Articles: " + this.Articles.toString() + " | Support: "  + this.Support.get()/100.0 + "%)";
    }

    @Override
    public int hashCode() {

        return Articles.toString().hashCode();
    }
}
