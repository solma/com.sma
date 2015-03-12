public class Regex{
  public static void main(String[] args){
     new Regex().mainloop();
  }
  void mainloop(){
   String address="923 S Carpenter, Chicago, IL 60607, USA";
   String publication1="(Tue, Jan 3 2012 10:30 AM to 2:30 PM) Main entrance to Loop-bound platform will be temporarily closed. Use Superior Street auxiliary entrance/exit to access Loop-bound trains.";
   String publication2="Main entrance to Loop-bound platform will be temporarily closed. Use Superior Street auxiliary entrance/exit to access Loop-bound trains.";
   System.out.println(publication2.length());
   //System.out.println(publication1.substring(0,publication1.lastIndexOf(" reported from ")) );
   //System.out.println(regex1(address));
  }

  String regex1(String s){
	//return s.replaceAll("\\s+"," ");
	return s.replaceAll(", Chicago, IL \\d{5}, USA","");
  }
 
}
