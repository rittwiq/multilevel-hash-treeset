import java.net.*;
import java.io.*;
class foo_2 {
	public static void main(String argv[]) throws Exception{
		URL site=new URL(argv[0]);
		BufferedReader in=new BufferedReader(new InputStreamReader(site.openStream()));
		String input_line;
		int startPosition,endPosition;
        		String subS="";
                  System.out.println(site.getRef());
		while((input_line=in.readLine())!=null) {
			if(input_line.indexOf("<a")!=(-1)) {
				startPosition = input_line.indexOf("href=\"//") + "href=\"//".length();
                  		endPosition = input_line.indexOf("/\"");
                  		if(startPosition<=endPosition){
                  			subS = input_line.substring(startPosition,endPosition);
                  			System.out.println("https://"+subS+"/");
                  			URL n_site=new URL("https://"+subS+"/");
                  			System.out.println(n_site.getRef());
                  		}
                  	}
		}
		in.close();
	}
}
