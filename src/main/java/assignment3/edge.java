package assignment3;
import java.util.Objects;

public class edge implements Comparable<edge> {
    public final String from;
    public final String to;
    public final int weight;

    public edge(String from, String to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public String other(String v) {
        return v.equals(from) ? to : from;
    }

    @Override
    public int compareTo(edge o) {
        return Integer.compare(this.weight, o.weight);
    }

    @Override
    public String toString() {
        return String.format("{\"from\": \"%s\", \"to\": \"%s\", \"weight\": %d}", from, to, weight);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof edge)) return false;
        edge that = (edge) o;
        return weight == that.weight &&
                Objects.equals(from, that.from) &&
                Objects.equals(to, that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, weight);
    }
}