package OAST_2;

import java.util.ArrayList;
/*
Klasa, która opisuje jedno z zapotrzebowań
startNode i endNode opisuje węzeł startu i końca
pathList zawiera listę obiektów typu Path, opisująca dostępne dla tego zapotrzebowania ścieżki
demandValue to wartość, którą należy rozdzielić na wszystkie ścieżki
 */

public class Demand {
    private int startNode;
    private int endNode;
    private ArrayList<Path> pathList;
    private int demandValue;

    public int getDemandValue() {
        return demandValue;
    }

    public Demand(int startNode, int endNode, int demandValue) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.demandValue = demandValue;
    }

    public void setDemandValue(int demandValue) {
        this.demandValue = demandValue;
    }

    public Demand(int startNode, int endNode, ArrayList<Path> pathList) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.pathList = pathList;
    }

    @Override
    public String toString() {
        return "Demand{" +
                "startNode=" + startNode +
                ", endNode=" + endNode +
                ", pathList=" + pathList +
                ", demandValue=" + demandValue +
                '}';
    }

    public Demand() {
    }

    public int getStartNode() {
        return startNode;
    }

    public void setStartNode(int startNode) {
        this.startNode = startNode;
    }

    public int getEndNode() {
        return endNode;
    }

    public void setEndNode(int endNode) {
        this.endNode = endNode;
    }

    public ArrayList<Path> getPathList() {
        return pathList;
    }

    public void setPathList(ArrayList<Path> pathList) {
        this.pathList = pathList;
    }
}
