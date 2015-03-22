package com.shuoma;
import java.util.*;

public class WordLadder {
    public static void main(String[] args){
        new WordLadder().main();
    }
    
    public void main(){
        HashSet<String> dict=new HashSet<String>();
        String[] words= new String[]{"kid","tag","pup","ail","tun","woo","erg","luz","brr","gay","sip","kay","per","val","mes","ohs","now","boa","cet","pal","bar","die","war","hay","eco","pub","lob","rue","fry","lit","rex","jan","cot","bid","ali","pay","col","gum","ger","row","won","dan","rum","fad","tut","sag","yip","sui","ark","has","zip","fez","own","ump","dis","ads","max","jaw","out","btu","ana","gap","cry","led","abe","box","ore","pig","fie","toy","fat","cal","lie","noh","sew","ono","tam","flu","mgm","ply","awe","pry","tit","tie","yet","too","tax","jim","san","pan","map","ski","ova","wed","non","wac","nut","why","bye","lye","oct","old","fin","feb","chi","sap","owl","log","tod","dot","bow","fob","for","joe","ivy","fan","age","fax","hip","jib","mel","hus","sob","ifs","tab","ara","dab","jag","jar","arm","lot","tom","sax","tex","yum","pei","wen","wry","ire","irk","far","mew","wit","doe","gas","rte","ian","pot","ask","wag","hag","amy","nag","ron","soy","gin","don","tug","fay","vic","boo","nam","ave","buy","sop","but","orb","fen","paw","his","sub","bob","yea","oft","inn","rod","yam","pew","web","hod","hun","gyp","wei","wis","rob","gad","pie","mon","dog","bib","rub","ere","dig","era","cat","fox","bee","mod","day","apr","vie","nev","jam","pam","new","aye","ani","and","ibm","yap","can","pyx","tar","kin","fog","hum","pip","cup","dye","lyx","jog","nun","par","wan","fey","bus","oak","bad","ats","set","qom","vat","eat","pus","rev","axe","ion","six","ila","lao","mom","mas","pro","few","opt","poe","art","ash","oar","cap","lop","may","shy","rid","bat","sum","rim","fee","bmw","sky","maj","hue","thy","ava","rap","den","fla","auk","cox","ibo","hey","saw","vim","sec","ltd","you","its","tat","dew","eva","tog","ram","let","see","zit","maw","nix","ate","gig","rep","owe","ind","hog","eve","sam","zoo","any","dow","cod","bed","vet","ham","sis","hex","via","fir","nod","mao","aug","mum","hoe","bah","hal","keg","hew","zed","tow","gog","ass","dem","who","bet","gos","son","ear","spy","kit","boy","due","sen","oaf","mix","hep","fur","ada","bin","nil","mia","ewe","hit","fix","sad","rib","eye","hop","haw","wax","mid","tad","ken","wad","rye","pap","bog","gut","ito","woe","our","ado","sin","mad","ray","hon","roy","dip","hen","iva","lug","asp","hui","yak","bay","poi","yep","bun","try","lad","elm","nat","wyo","gym","dug","toe","dee","wig","sly","rip","geo","cog","pas","zen","odd","nan","lay","pod","fit","hem","joy","bum","rio","yon","dec","leg","put","sue","dim","pet","yaw","nub","bit","bur","sid","sun","oil","red","doc","moe","caw","eel","dix","cub","end","gem","off","yew","hug","pop","tub","sgt","lid","pun","ton","sol","din","yup","jab","pea","bug","gag","mil","jig","hub","low","did","tin","get","gte","sox","lei","mig","fig","lon","use","ban","flo","nov","jut","bag","mir","sty","lap","two","ins","con","ant","net","tux","ode","stu","mug","cad","nap","gun","fop","tot","sow","sal","sic","ted","wot","del","imp","cob","way","ann","tan","mci","job","wet","ism","err","him","all","pad","hah","hie","aim","ike","jed","ego","mac","baa","min","com","ill","was","cab","ago","ina","big","ilk","gal","tap","duh","ola","ran","lab","top","gob","hot","ora","tia","kip","han","met","hut","she","sac","fed","goo","tee","ell","not","act","gil","rut","ala","ape","rig","cid","god","duo","lin","aid","gel","awl","lag","elf","liz","ref","aha","fib","oho","tho","her","nor","ace","adz","fun","ned","coo","win","tao","coy","van","man","pit","guy","foe","hid","mai","sup","jay","hob","mow","jot","are","pol","arc","lax","aft","alb","len","air","pug","pox","vow","got","meg","zoe","amp","ale","bud","gee","pin","dun","pat","ten","mob"};
        for(int i=0;i<words.length;i++) dict.add(words[i]);
        String start="cet";
        String end="ism";
        System.out.println(ladderLength(start, end, dict));
    }
    
    //TLE
    public int ladderLengthTLE(String start, String end, HashSet<String> dict) {
        boolean notContainEnd=false;
        if(!dict.contains(end)){
            dict.add(end);
            notContainEnd=true;
        }
               
        Stack<String> stack=new Stack<String>();
        
        stack.push(start);
        int minStep=0;
        boolean deadEnd;
        String cur;
        
        ArrayList<String> removed=new ArrayList<String>();
        while(!stack.empty()){
           deadEnd=true;
           minStep+=1;
           cur=stack.lastElement();
           System.out.println(cur+" "+minStep+stack.toString());
           
           removed.clear();
           for(String s: dict){
                if(!stack.contains(s)&&isOneLetterDiff(s,cur)) {
                    deadEnd=false;
                    if(s.equals(end)){
                        if(minStep==1&&notContainEnd) return 0;
                        else return minStep+1;
                    }
                    stack.push(s);
                    removed.add(s);  //this is a trick
                }          
           }
           for(String s: removed) dict.remove(s);
           if(deadEnd){ 
                stack.pop();
                minStep-=1;                
           }
        }
        //ladderLengthDFS(start, end, dict, minStep, new HashSet<String>() );
        return 0;
    }    
  
    public boolean isOneLetterDiff(String s, String start){
      int diff=0;
      if(s.equals(start)) return false;
      for(int i=0;i<s.length();i++){
         if(diff>1) return false;
         if(s.charAt(i)!=start.charAt(i)) diff+=1;
      }
      if(diff!=1) return false;
      return true;
    }
    
    public void ladderLengthDFS(String start, String end, HashSet<String> dict, int[]  minStep, HashSet<String> usedWords) {
        if(start.equals(end)) if(minStep[0]>usedWords.size()){ minStep[0]=usedWords.size();}
        
        for(String s: dict){
            if(!usedWords.contains(s)&& isOneLetterDiff(s, start)){
                usedWords.add(s);
                ladderLengthDFS(s, end, dict, minStep, usedWords);
                usedWords.remove(s);
            }
        }
    }
    
    
    //pass the larget set
    class Node{ String word;
       int l;
            boolean v;
            public Node(String w){
                    word = w;            
                    l = 0;
                    v = false;
            }
    }

    public int ladderLength(String start, String end, HashSet<String> dict) {
            if (!dict.contains(start) || !dict.contains(end))
                    return 0;

            HashMap<String, Node> map = new HashMap<String, Node>();

            for (String w : dict){
                    Node n = new Node(w);
                    map.put(w, n);
            }

            Queue<Node> queue = new LinkedList<Node>();
            //Queue<Node> queue2 = new LinkedList<Node>();

            Node n = map.get(start);
            n.l=1;
            queue.offer(n);
            n=map.get(end);
            n.l=-1;
            queue.offer(n);
            //Node plus = new Node("");
            //queue.offer(plus);
            //int length = 1;

            while (queue.peek() != null ){
                    Node c = queue.poll();

                    if (c.v) {
                        continue; // already visited
                    }

                    c.v = true;

                    String w = c.word;
                    for (int i = 0; i < w.length(); i++){
                            String f= w.substring(0,i);
                            String e=w.substring(i+1);
                            for (int j = (int)'a'; j <= (int)'z'; j++) {
                                    String newS = f + (char)j + e;

                                    if (dict.contains(newS)){
                                            Node node = map.get(newS);
                                            if( 0 == node.l ) {
                                                    if( c.l >= 0 ) node.l = c.l+1;
                                                    else node.l=c.l-1;
                                                    queue.offer(node);
                                            } else {
                                                    if( (node.l >0 && c.l <0) || (c.l>0 && node.l<0) ){
                                                            return Math.abs(node.l)+Math.abs(c.l);
                                                    }
                                            }
                                    }
                            }
                    }

            }

            return 0;
    }
}