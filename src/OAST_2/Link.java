package OAST_2;

/*
Link opisuje jedno z połączeń między węzłami.
startNode i endNode to odpowiednio węzeł startu i końca
capacity to wyliczona pojemność łącza
id to numer łącza, ten sam, który jest użyty w Path, w klasie Demand
 */
public class Link {
    private int startNode;
    private int endNode;
    private int capacity;
    private int id;
    private int usage = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Link() {
    }

    public int getUsage() {
        return usage;
    }

    public void setUsage(int usage) {
        this.usage = usage;
    }

    @Override
    public String toString() {
        return "Link{" +
                "startNode=" + startNode +
                ", endNode=" + endNode +
                ", capacity=" + capacity +
                ", id=" + id +
                '}';
    }

    public Link(int startNode, int endNode, int capacity, int id) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.capacity = capacity;
        this.id = id;
    }
    public void increaseUsage(int i){
        this.usage = this.usage+i;

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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
