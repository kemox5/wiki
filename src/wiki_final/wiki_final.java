package wiki_final;

import java.io.*;
import java.net.*;

public class wiki_final {

    public static String get_html(String text) throws MalformedURLException {
        InputStream is;
        BufferedReader br;
        String line,html="";
        URL url;

        //url = new URL("https://en.wikipedia.org/wiki/Computer_science#Applied_computer_science");
        //url = new URL(" https://en.wikipedia.org/wiki/Egypt");
        //url = new URL("https://en.wikipedia.org/wiki/Jimmy_Carter");

        url = new URL("https://en.wikipedia.org/wiki/"+text);
        
        try{
            is = url.openStream();  // throws an IOException 
            br = new BufferedReader(new InputStreamReader(is));

            while ((line = br.readLine()) != null) {
                html=html+"\n"+line;
            }
        }catch(Exception e){
            print_error("Can't open url: "+url);
        }
        return html;
    }
    
    public static String get_ArabicName(String A){
        String B=A.substring(A.indexOf("<li class=\"interlanguage-link interwiki-ar"), A.indexOf("lang=\"ar\" hreflang=\"ar\" class=\"interlanguage-link-target\">"));
        String C=B.substring(B.indexOf("<a href=\""), B.length());
        String f=C.substring(C.indexOf(" title=\"")+8, C.length()-11);       
         return f;
    }
    
   public static String get_ArabicLink(String A){
        String B=A.substring(A.indexOf("<li class=\"interlanguage-link interwiki-ar"), A.indexOf("lang=\"ar\" hreflang=\"ar\" class=\"interlanguage-link-target\">"));
        String C=B.substring(B.indexOf("<a href=\"")+9, B.length());
        String f=C.substring(0, C.indexOf("\" title"));
        return f;
    }
   
   public static void print_error(String s){
                error_frame er=new error_frame();
                er.jLabel1.setText(s);
                er.show();
   }
   
   public static void wiki_main(String text) {
       try{
            result_frame Frame2=new result_frame(); 
            
            String html=get_html(text);

            String arabicName=get_ArabicName(html);
            String arabicLink=get_ArabicLink(html);

            Frame2.jLabel2.setText(arabicName);
            Frame2.jLabel4.setText(text);
            Frame2.ar_link=arabicLink;
            Frame2.en_link="https://en.wikipedia.org/wiki/"+text;

            Frame2.show();
         }catch (Exception e) {
            print_error("Something wrong.. please try again");
            System.out.println(e);
        }
   }
    
    public static void main(String[] args) {
        main_frame f=new main_frame();
        f.show();
    }
    
    
}
