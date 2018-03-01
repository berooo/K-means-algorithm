package operation;

import java.util.List;

import entity.point;
import operation.readData;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fileName=args[0];
		List<point> ls=readData.getData(fileName);
		int k=Integer.parseInt(args[1]);
		clustering c=new clustering(ls);
		c.cluster(k);
		c.printResult();
	}
	
}
