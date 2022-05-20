
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;


public class DemoGreedysearch {
    
    public static double distance = 0;
    public static Node aStar(Node start, Node target) {
        PriorityQueue<Node> closedList = new PriorityQueue<>();
        PriorityQueue<Node> openList = new PriorityQueue<>();

        start.f = start.calculateHeuristic(target);
        openList.add(start);

        while (!openList.isEmpty()) {
            Node n = openList.peek();
            if (n == target) {
                return n;
            }
            double check = 99999;

            for (Node.Edge edge : n.neighbors) {
                Node m = edge.node;
                double totalWeight = n.g + edge.weight;

                if (!openList.contains(m)) {
                    m.parent = n;
                    m.g=totalWeight;
                    m.f = m.g;
                    
                    openList.add(m);
                } else {
                    if (check < m.calculateHeuristic(target)) {
                        m.parent = n;
                        m.g = totalWeight;
                        check = m.calculateHeuristic(target);

                        if (closedList.contains(m)) {
                            closedList.remove(m);
                            openList.add(m);
                        }
                    }
                }
                distance = m.f;
            }

            openList.remove(n);
            closedList.add(n);
        }
        return null;
    }

    public static void printPath(Node target) {
        Node n = target;

        if (n == null) {
            return;
        }

        List<String> ids = new ArrayList<>();

        while (n.parent != null) {
            ids.add(n.id);
            n = n.parent;
        }
        ids.add(n.id);
        Collections.reverse(ids);

        for (String id : ids) {
            System.out.print(id + " ");
        }
        System.out.println("");
        System.out.println("Do dai duong di giua 2 diem:"+distance);
    }

    public static void main(String[] args) throws FileNotFoundException {
        String url1 = "C:\\Users\\levan\\Documents\\NetBeans Projects\\DemoTTCS\\src\\data1.txt";
        String url2 = "C:\\Users\\levan\\Documents\\NetBeans Projects\\DemoTTCS\\src\\data2.txt";
        // Đọc dữ liệu từ File với Scanner
        FileInputStream data1 = new FileInputStream(url1);
        FileInputStream data2 = new FileInputStream(url2);
        ArrayList<Node> listNode = new ArrayList<>();
        Scanner in1 =  new Scanner(data1);
        Scanner in2 = new Scanner(data2);
        while(in1.hasNextLine()){
            String nod = in1.nextLine();
            String nod1[] = nod.split(" ");
            Node node = new Node(Double.parseDouble(nod1[1]),nod1[0]);
            listNode.add(node);
        }
        int count = 0;
        while(in2.hasNextLine()){
            String line = in2.nextLine();
            String weight[] = line.split(" ");           
            for(int i=0; i<weight.length;i++){
               int g = Integer.parseInt(weight[i]);
               if(g>0){
                   listNode.get(count).addBranch(g, listNode.get(i));
               }
            }
            count++;
        }
        listNode.get(0).g=0;
        Node res = aStar(listNode.get(0),listNode.get(count-1));
        printPath(listNode.get(count-1));
    }
}