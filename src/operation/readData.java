package operation;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import entity.point;

public class readData {
	private static List<point> ls;
	private static String[] pointName;
	private static float[][] data;
	private static String filename;
	
	public static float[][] readDat(String file){
	     try{
	        filename=file;
	        FileReader fileReader=new FileReader(filename);
	        BufferedReader buf=new BufferedReader(fileReader);
	        int n=getFileLineCounts();
	        int m=getColumns()-1;
	        data=new float[n][m];
	//        pointName=new String[n];
	        String linetxt=null;

	        int i=0;

	        while((linetxt=buf.readLine())!=null) {

	            String[] linedata = linetxt.split(" ");
	            for (int j = 0; j < linedata.length-1; j++) {
	                data[i][j] = Integer.valueOf(linedata[j+1]);
	            }
	 //           pointName[i]=linedata[0];
	            i++;
	        }

	    }catch(Exception e){
	            e.printStackTrace();
	        }
	        return data;
	    }
	public static String[] readpointName(String file) {
		try{
	        filename=file;
	        FileReader fileReader=new FileReader(filename);
	        BufferedReader buf=new BufferedReader(fileReader);
	        int n=getFileLineCounts();
	        int m=getColumns();
	   //     data=new int[n][m];
	        pointName=new String[n];
	        String linetxt=null;

	        int i=0;

	        while((linetxt=buf.readLine())!=null) {

	            String[] linedata = linetxt.split(" ");
	            pointName[i]=linedata[0];
	            i++;
	        }

	    }catch(Exception e){
	            e.printStackTrace();
	        }
	        return pointName;
	    }
	
	public static List<point> getData(String filename){
		float[][] data=readDat(filename);
		String[] pointName=readpointName(filename);
		ls=new ArrayList<point>();
		for(int i=0;i<pointName.length;i++) {
			point a=new point();
			a.setName(pointName[i]);
			a.setValue(data[i]);
			ls.add(a);
		}
		return ls;
		
	}
	 public static int getFileLineCounts(){
	        int cnt=0;
	        InputStream is=null;
	        try{
	            is=new BufferedInputStream(new FileInputStream(filename));
	            byte[] c=new byte[1024];
	            int readChars=0;
	            while((readChars=is.read(c))!=-1){
	                for(int i=0;i<readChars;i++){
	                    if(c[i]=='\n'){
	                        ++cnt;
	                    }
	                }
	            }
	        }catch (Exception e){
	            cnt=-1;
	            e.printStackTrace();
	        }finally {
	            try{
	                is.close();
	            }catch (Exception e){
	                e.printStackTrace();
	            }
	        }
	        return cnt+1;
	    }

	    public static int getColumns(){
	        int i=0;
	        try{
	            FileReader fileReader=new FileReader(filename);
	            BufferedReader buf=new BufferedReader(fileReader);
	            String linetxt=null;

	            if((linetxt=buf.readLine())!=null) {
	                String[] linedata = linetxt.split(" ");
	                i=linedata.length;
	            }
	        }catch(Exception e){
	            i=-1;
	        }
	        return i;
	    }
}
