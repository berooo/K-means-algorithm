package entity;

import java.util.ArrayList;
import java.util.List;

public class category {
	private String name;
	private List<point> ls;
	private point center;

	
	public category() {
		// TODO Auto-generated constructor stub
		ls=new ArrayList<point>();
	}

	public void setName(String name) {
		this.name=name;
	}
	
	public String getName() {
		return name;
	}
	
	public void add(point p) {
		ls.add(p);
	}
	
	public List<point> getList(){
		return ls;
	}
	
	public void updateCenter() {
		
		if(ls.size()==1) {
			center=ls.get(0);
		}else {
			int d=ls.get(0).getDimension();
			float[] a = new float[d];
			for(int i=0;i<d;i++) {
				float sum=0;
				for(int j=0;j<ls.size();j++) {
					float[] b=ls.get(j).getValue();
					sum+=b[i];
				}
				a[i]=sum/ls.size();
			}
			center.setValue(a);
		}
	}
	
	public point getCenter() {
		return center;
	}
	
	public boolean checkExist(point a) {
		if(ls.contains(a)) {
			return true;
		}
		return false;
	}
}
