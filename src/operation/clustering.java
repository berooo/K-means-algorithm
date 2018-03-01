package operation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import entity.category;
import entity.point;

public class clustering {

	private List<point> ls;
	private List<category> c;

	public clustering(List<point> ls) {
		this.ls = ls;
		c = new ArrayList<category>();
	}

	public void initial(int k) {

		Random r = new Random();
		for (int i = 0; i < k; i++) {

			int num = Math.abs(r.nextInt() % ls.size());
			point a = ls.get(num);
			category ca = new category();
			String name = "Z" + (i + 1);
			ca.setName(name);
			ca.add(a);
			ca.updateCenter();
			c.add(ca);

		}

	}

	public float Euclidean(point a, point b) {

		float[] aValue = a.getValue();
		float[] bValue = b.getValue();

		float sum = 0;
		for (int i = 0; i < aValue.length; i++) {
			sum += Math.pow((aValue[i] - bValue[i]), 2);
		}

		return (float) Math.sqrt(sum);

	}

	public int choose(List<category> ls, point a) {

		float min = Float.MAX_VALUE;
		int index = 0;

		for (int i = 0; i < ls.size(); i++) {
			float distance = Euclidean(ls.get(i).getCenter(), a);
			if (distance <= min) {
				min = distance;
				index = i;
			}
		}

		return index;
	}

	public void cluster(int k) {

		initial(k);

		for (int i = 0; i < ls.size(); i++) {
			point a = ls.get(i);
			int ind = choose(c, a);
			category ca = c.get(ind);
			if (!ca.checkExist(a)) {
				ca.add(a);
			}
		}

		float Convergence = Float.MAX_VALUE;

		point[] p1 = new point[c.size()];
		point[] p2 = new point[c.size()];

		while (Math.abs(Convergence) <= 0) {
			for (int i = 0; i < ls.size(); i++) {
				point a = ls.get(i);
				int ind = choose(c, a);
				category ca = c.get(ind);
				if (!ca.checkExist(a)) {
					ca.add(a);
				}
			}
			Convergence = 0;
			for (int i = 0; i < c.size(); i++) {
				p1[i] = c.get(i).getCenter();
				c.get(i).updateCenter();
				p2[i] = c.get(i).getCenter();
				Convergence += Euclidean(p1[i], p2[i]);
			}
		}
	}

	public void printResult() {
		for (int i = 0; i < c.size(); i++) {
			System.out.println(c.get(i).getName());
			List<point> l = c.get(i).getList();
			for (int j = 0; j < l.size(); j++) {
				point p = l.get(j);
				System.out.println(Arrays.toString(p.getValue()));
			}
			// point a=c.get(i).getCenter();
			// System.out.println("Center"+Arrays.toString(a.getValue()));

		}
	}
}
