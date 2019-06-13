package Shared;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class RouteGraph {
    private Map<String, List<Edge>> nodes = new HashMap<>();

    public static void main(String[] args) {
        // add test code here
        RouteGraph rg = new RouteGraph();
        rg.addRoute(Route.Calgary_Helena);
        rg.addRoute(Route.Vancouver_Calgary);
        rg.addRoute(Route.Seattle_Vancouver);
        rg.addRoute(Route.Boston_NewYork);
        rg.addRoute(Route.Duluth_Omaha);
        rg.addRoute(Route.Denver_Omaha);
        rg.addRoute(Route.Denver_SantaFe);
        rg.addRoute(Route.SantaFe_OklahomaCity);

        int longestRoute = rg.findLongestRoute();

        System.out.println("Longest route: " + longestRoute);
    }

    public void addRoute(Route route) {
        String[] cities = route.name().split("_");
        String city1 = cities[0];
        String city2 = cities[1];

        Edge edge = new Edge(city1, city2, route.length);

        if (!nodes.containsKey(city1)) {
            nodes.put(city1, new ArrayList<>());
        }
        if (!nodes.containsKey(city2)) {
            nodes.put(city2, new ArrayList<>());
        }

        nodes.get(city1).add(edge);
        nodes.get(city2).add(edge);
    }

    public Map<String, List<Edge>> getNodes() {
        return nodes;
    }

    public int findLongestRoute() {

        AtomicInteger longest = new AtomicInteger(0);

        for (String city : getNodes().keySet()) {
            findLongestRoute(city, 0, longest);
        }

        return longest.get();
    }

    private void findLongestRoute(String city, int length, AtomicInteger longest) {

        List<Edge> edges = this.getNodes().get(city);

        for (Edge edge : edges) {
            if (edge.visited) {
                continue;
            } else {
                edge.visited = true;
            }

            String nextCity = edge.city1.equals(city) ? edge.city2 : edge.city1;

            findLongestRoute(nextCity, length + edge.length, longest);

            edge.visited = false;
        }

        if (length > longest.get()) {
            longest.set(length);
        }
    }

    public static class Edge {
        private String city1;
        private String city2;
        private int length;
        public boolean visited;

        Edge(String city1, String city2, int length) {
            this.city1 = city1;
            this.city2 = city2;
            this.length = length;
        }

        public String getCity1() {
            return city1;
        }

        public String getCity2() {
            return city2;
        }

        public int getLength() {
            return length;
        }
    }
}
