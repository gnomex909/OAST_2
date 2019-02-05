package OAST_2;

import java.util.ArrayList;
/*
Klasa, która opisuje ściezki wewnątrz Demandów,
id to numer ścieżki.
assignedDemand to wartość przypisana w danym chromosomie, o ile będzie tutaj potrzebna, nie przemyślałem tej części... może należy zrobić inny obiekt na chromosomy.
linkList to lista tego przez które łącza przechodzi dana ścieżka, dość oczywiste
 */
public class Path {
    private int id;
    private int assignedDemand;
    private ArrayList<Link> linkList;

    public int getAssignedDemand() {
        return assignedDemand;
    }

    public void setAssignedDemand(int assignedDemand) {
        this.assignedDemand = assignedDemand;
    }

    public Path() {
    }

    @Override
    public String toString() {
        return "Path{" +
                "id=" + id +
                ", assignedDemand=" + assignedDemand +
                ", linkList=" + linkList +
                '}';
    }

    public Path(int id, ArrayList<Link> linkList) {
        this.id = id;
        this.linkList = linkList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Link> getLinkList() {
        return linkList;
    }

    public void setLinkList(ArrayList<Link> linkList) {
        this.linkList = linkList;
    }
}
