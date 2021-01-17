package backend;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Station {

    private final String name;
    private final String line;
    private boolean crossingPoint;
    private final List<Station> neighbours;

    Station(String line, String name) {
        this.line = line;
        this.name = name;
        this.neighbours = new LinkedList<>();
    }

    String getName() {
        return name;
    }

    public List<Station> getNeighbours() {
        return neighbours;
    }

    public String getLine() {
        return line;
    }

    void connect(Station... stations) {
        for (Station s : stations) {
            if (!neighbours.contains(s)) neighbours.add(s);
        }
        if (neighbours.size() > 2) crossingPoint = true;
    }

    @SuppressWarnings("unchecked")
    LinkedList<Station> route(LinkedList<Station> route) {
        if (neighbours.contains(route.getLast())) {
            route.add(route.size() - 1, this);
            return route;
        } else {
            route.add(route.size() - 1, this);
            return neighbours.stream().filter(st -> !(route.contains(st) || st.equals(this))).map(st -> st.route((LinkedList<Station>) route.clone()))
                    .filter(Objects::nonNull).min(Comparator.comparingInt(LinkedList::size)).orElse(null);
        }
    }

    @Override
    public String toString() {
        String descr = name;
        if (crossingPoint) descr += " (Crossing Point)";
        return descr;
    }
}
