import java.io.Serializable;

public class Node implements Comparable<Node> , Serializable{
int freq;
Character value;
Node left;
Node right;
public Node(int freq,Character value) {
	this.freq=freq;
	this.value=value;
	left=right=null;
}
@Override
public int compareTo(Node n) {
	if(this.freq>n.freq) return 1;
	return -1;
}
}
