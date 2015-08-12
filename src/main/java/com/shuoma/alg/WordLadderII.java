package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.Backtracking;
import static com.shuoma.annotation.Tag.Algorithm.Recursion;
import static com.shuoma.annotation.Tag.DataStructure.StringT;
import static com.shuoma.annotation.Tag.Difficulty.D3;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

@Tag(algs = {Backtracking, Recursion}, dl = D3, dss = StringT, references = LeetCode)
public class WordLadderII {
  public static void main(String[] args) {
    new WordLadderII().main();
  }

  public void main() {
    HashSet<String> dict = new HashSet<>();
    // String[] words= new
    // String[]{"kid","tag","pup","ail","tun","woo","erg","luz","brr","gay","sip","kay","per","val","mes","ohs","now","boa","cet","pal","bar","die","war","hay","eco","pub","lob","rue","fry","lit","rex","jan","cot","bid","ali","pay","col","gum","ger","row","won","dan","rum","fad","tut","sag","yip","sui","ark","has","zip","fez","own","ump","dis","ads","max","jaw","out","btu","ana","gap","cry","led","abe","box","ore","pig","fie","toy","fat","cal","lie","noh","sew","ono","tam","flu","mgm","ply","awe","pry","tit","tie","yet","too","tax","jim","san","pan","map","ski","ova","wed","non","wac","nut","why","bye","lye","oct","old","fin","feb","chi","sap","owl","log","tod","dot","bow","fob","for","joe","ivy","fan","age","fax","hip","jib","mel","hus","sob","ifs","tab","ara","dab","jag","jar","arm","lot","tom","sax","tex","yum","pei","wen","wry","ire","irk","far","mew","wit","doe","gas","rte","ian","pot","ask","wag","hag","amy","nag","ron","soy","gin","don","tug","fay","vic","boo","nam","ave","buy","sop","but","orb","fen","paw","his","sub","bob","yea","oft","inn","rod","yam","pew","web","hod","hun","gyp","wei","wis","rob","gad","pie","mon","dog","bib","rub","ere","dig","era","cat","fox","bee","mod","day","apr","vie","nev","jam","pam","new","aye","ani","and","ibm","yap","can","pyx","tar","kin","fog","hum","pip","cup","dye","lyx","jog","nun","par","wan","fey","bus","oak","bad","ats","set","qom","vat","eat","pus","rev","axe","ion","six","ila","lao","mom","mas","pro","few","opt","poe","art","ash","oar","cap","lop","may","shy","rid","bat","sum","rim","fee","bmw","sky","maj","hue","thy","ava","rap","den","fla","auk","cox","ibo","hey","saw","vim","sec","ltd","you","its","tat","dew","eva","tog","ram","let","see","zit","maw","nix","ate","gig","rep","owe","ind","hog","eve","sam","zoo","any","dow","cod","bed","vet","ham","sis","hex","via","fir","nod","mao","aug","mum","hoe","bah","hal","keg","hew","zed","tow","gog","ass","dem","who","bet","gos","son","ear","spy","kit","boy","due","sen","oaf","mix","hep","fur","ada","bin","nil","mia","ewe","hit","fix","sad","rib","eye","hop","haw","wax","mid","tad","ken","wad","rye","pap","bog","gut","ito","woe","our","ado","sin","mad","ray","hon","roy","dip","hen","iva","lug","asp","hui","yak","bay","poi","yep","bun","try","lad","elm","nat","wyo","gym","dug","toe","dee","wig","sly","rip","geo","cog","pas","zen","odd","nan","lay","pod","fit","hem","joy","bum","rio","yon","dec","leg","put","sue","dim","pet","yaw","nub","bit","bur","sid","sun","oil","red","doc","moe","caw","eel","dix","cub","end","gem","off","yew","hug","pop","tub","sgt","lid","pun","ton","sol","din","yup","jab","pea","bug","gag","mil","jig","hub","low","did","tin","get","gte","sox","lei","mig","fig","lon","use","ban","flo","nov","jut","bag","mir","sty","lap","two","ins","con","ant","net","tux","ode","stu","mug","cad","nap","gun","fop","tot","sow","sal","sic","ted","wot","del","imp","cob","way","ann","tan","mci","job","wet","ism","err","him","all","pad","hah","hie","aim","ike","jed","ego","mac","baa","min","com","ill","was","cab","ago","ina","big","ilk","gal","tap","duh","ola","ran","lab","top","gob","hot","ora","tia","kip","han","met","hut","she","sac","fed","goo","tee","ell","not","act","gil","rut","ala","ape","rig","cid","god","duo","lin","aid","gel","awl","lag","elf","liz","ref","aha","fib","oho","tho","her","nor","ace","adz","fun","ned","coo","win","tao","coy","van","man","pit","guy","foe","hid","mai","sup","jay","hob","mow","jot","are","pol","arc","lax","aft","alb","len","air","pug","pox","vow","got","meg","zoe","amp","ale","bud","gee","pin","dun","pat","ten","mob"};
    String[] words =
        new String[] {"kid", "tag", "pup", "ail", "tun", "woo", "erg", "luz", "brr", "gay", "sip",
            "kay", "per", "val", "mes", "ohs", "now", "boa", "cet", "pal", "bar", "die", "war",
            "hay", "eco", "pub", "lob", "rue", "fry", "lit", "rex", "jan", "cot", "bid", "ali",
            "pay", "col", "gum", "ger", "row", "won", "dan", "rum", "fad", "tut", "sag", "yip",
            "sui", "ark", "has", "zip", "fez", "own", "ump", "dis", "ads", "max", "jaw", "out",
            "btu", "ana", "gap", "cry", "led", "abe", "box", "ore", "pig", "fie", "toy", "fat",
            "cal", "lie", "noh", "sew", "ono", "tam", "flu", "mgm", "ply", "awe", "pry", "tit",
            "tie", "yet", "too", "tax", "jim", "san", "pan", "map", "ski", "ova", "wed", "non",
            "wac", "nut", "why", "bye", "lye", "oct", "old", "fin", "feb", "chi", "sap", "owl",
            "log", "tod", "dot", "bow", "fob", "for", "joe", "ivy", "fan", "age", "fax", "hip",
            "jib", "mel", "hus", "sob", "ifs", "tab", "ara", "dab", "jag", "jar", "arm", "lot",
            "tom", "sax", "tex", "yum", "pei", "wen", "wry", "ire", "irk", "far", "mew", "wit",
            "doe", "gas", "rte", "ian", "pot", "ask", "wag", "hag", "amy", "nag", "ron", "soy",
            "gin", "don", "tug", "fay", "vic", "boo", "nam", "ave", "buy", "sop", "but", "orb",
            "fen", "paw", "his", "sub", "bob", "yea", "oft", "inn", "rod", "yam", "pew", "web",
            "hod", "hun", "gyp", "wei", "wis", "rob", "gad", "pie", "mon", "dog", "bib", "rub",
            "ere", "dig", "era", "cat", "fox", "bee", "mod", "day", "apr", "vie", "nev", "jam",
            "pam", "new", "aye", "ani", "and", "ibm", "yap", "can", "pyx", "tar", "kin", "fog",
            "hum", "pip", "cup", "dye", "lyx", "jog", "nun", "par", "wan", "fey", "bus", "oak",
            "bad", "ats", "set", "qom", "vat", "eat", "pus", "rev", "axe", "ion", "six", "ila",
            "lao", "mom", "mas", "pro", "few", "opt", "poe", "art", "ash", "oar", "cap", "lop",
            "may", "shy", "rid", "bat", "sum", "rim", "fee", "bmw", "sky", "maj", "hue", "thy",
            "ava", "rap", "den", "fla", "auk", "cox", "ibo", "hey", "saw", "vim", "sec", "ltd",
            "you", "its", "tat", "dew", "eva", "tog", "ram", "let", "see", "zit", "maw", "nix",
            "ate", "gig", "rep", "owe", "ind", "hog", "eve", "sam", "zoo", "any", "dow", "cod",
            "bed", "vet", "ham", "sis", "hex", "via", "fir", "nod", "mao", "aug", "mum", "hoe",
            "bah", "hal", "keg", "hew", "zed", "tow", "gog", "ass", "dem", "who", "bet", "gos",
            "son", "ear", "spy", "kit", "boy", "due", "sen", "oaf", "mix", "hep", "fur", "ada",
            "bin", "nil", "mia", "ewe", "hit", "fix", "sad", "rib", "eye", "hop", "haw", "wax",
            "mid", "tad", "ken", "wad", "rye", "pap", "bog", "gut", "ito", "woe", "our", "ado",
            "sin", "mad", "ray", "hon", "roy", "dip", "hen", "iva", "lug", "asp", "hui", "yak",
            "bay", "poi", "yep", "bun", "try", "lad", "elm", "nat", "wyo", "gym", "dug", "toe",
            "dee", "wig", "sly", "rip", "geo", "cog", "pas", "zen", "odd", "nan", "lay", "pod",
            "fit", "hem", "joy", "bum", "rio", "yon", "dec", "leg", "put", "sue", "dim", "pet",
            "yaw", "nub", "bit", "bur", "sid", "sun", "oil", "red", "doc", "moe", "caw", "eel",
            "dix", "cub", "end", "gem", "off", "yew", "hug", "pop", "tub", "sgt", "lid", "pun",
            "ton", "sol", "din", "yup", "jab", "pea", "bug", "gag", "mil", "jig", "hub", "low",
            "did", "tin", "get", "gte", "sox", "lei", "mig", "fig", "lon", "use", "ban", "flo",
            "nov", "jut", "bag", "mir", "sty", "lap", "two", "ins", "con", "ant", "net", "tux",
            "ode", "stu", "mug", "cad", "nap", "gun", "fop", "tot", "sow", "sal", "sic", "ted",
            "wot", "del", "imp", "cob", "way", "ann", "tan", "mci", "job", "wet", "ism", "err",
            "him", "all", "pad", "hah", "hie", "aim", "ike", "jed", "ego", "mac", "baa", "min",
            "com", "ill", "was", "cab", "ago", "ina", "big", "ilk", "gal", "tap", "duh", "ola",
            "ran", "lab", "top", "gob", "hot", "ora", "tia", "kip", "han", "met", "hut", "she",
            "sac", "fed", "goo", "tee", "ell", "not", "act", "gil", "rut", "ala", "ape", "rig",
            "cid", "god", "duo", "lin", "aid", "gel", "awl", "lag", "elf", "liz", "ref", "aha",
            "fib", "oho", "tho", "her", "nor", "ace", "adz", "fun", "ned", "coo", "win", "tao",
            "coy", "van", "man", "pit", "guy", "foe", "hid", "mai", "sup", "jay", "hob", "mow",
            "jot", "are", "pol", "arc", "lax", "aft", "alb", "len", "air", "pug", "pox", "vow",
            "got", "meg", "zoe", "amp", "ale", "bud", "gee", "pin", "dun", "pat", "ten", "mob"};
    for (int i = 0; i < words.length; i++)
      dict.add(words[i]);

    // String start= "hit", end="cog";
    String start = "cet", end = "ism";

    //ladderLength findAllPaths
    for (List<String> path : findAllPaths(start, end, dict))
      System.out.println(path);
  }


  public List<List<String>> ladderLength(String start, String end, HashSet<String> dict) {
    List<List<String>> res = new ArrayList<>();
    if (start == null || end == null)
      return res;

    ArrayList<String> path = new ArrayList<>();
    if (start.equals(end)) {
      path.add(start);
      path.add(end);
      res.add(path);
      return res;
    }

    Map<String, List<String>> prev = new HashMap<>();
    prev.put(end, new ArrayList<String>());
    prev.put(start, new ArrayList<String>());
    for (String s : dict) {
      prev.put(s, new ArrayList<String>());
    }

    Queue<String> queue = new LinkedList<>();
    queue.offer(start);
    Map<String, Integer> dis = new HashMap<>();
    dis.put(start, 0);

    List<String> lastLevel = new ArrayList<>();
    while (!queue.isEmpty()) {
      int level = 1;// queue.size();
      lastLevel.clear();
      for (int i = 0; i < level; i++) {
        String top = queue.poll();
        //if (dict.contains(top)) dict.remove(top);//remove used word from dict
        lastLevel.add(top);
      }
      if (lastLevel.contains(end))
        break;

      for (String curWord : lastLevel) {
        for (int i = 0; i < curWord.length(); ++i) {
          for (char j = 'a'; j <= 'z'; ++j) {
            char[] tmpchar = curWord.toCharArray();
            if (tmpchar[i] == j)
              continue;
            tmpchar[i] = j;
            String neighborWord = new String(tmpchar);

            if (dict.contains(neighborWord)) {//valid neighbor word
              if (!dis.containsKey(neighborWord)) {//new word
                queue.offer(neighborWord);
                dis.put(neighborWord, dis.get(curWord) + 1);
              }
              int newDist = dis.get(curWord) + 1;//update dis
              if (newDist <= dis.get(neighborWord)) {
                if (newDist < dis.get(neighborWord))
                  prev.get(neighborWord).clear();
                prev.get(neighborWord).add(curWord);
              }
            }
          }
        }
        //dis.remove(curWord);
      }

      //if (queue.contains(end)) break;
    }

    path.add(end);

    //        System.out.println("Build path........");
    //        ArrayList<String> keys=new ArrayList<String>(prev.keySet());
    //        Collections.sort(keys);
    //        for(String key: keys){
    //        	System.out.println(key+"  "+prev.get(key));
    //        }

    buildpath(start, end, prev, path, res);
    return res;
  }

  public void buildpath(String start, String end, Map<String, List<String>> prev, List<String> path, List<List<String>> res) {
    List<String> pre = prev.get(end);
    if (end.equals(start)) {
      List<String> cpy = new ArrayList<>(path);
      Collections.reverse(cpy);
      res.add(cpy);
      return;
    }
    for (String s : pre) {
      path.add(s);
      buildpath(start, s, prev, path, res);
      path.remove(path.size() - 1);
    }
  }

  // second pass
  class WordNode implements Comparable<WordNode> {
    int dis;
    String word;
    List<WordNode> prev;

    public WordNode(String word, int dis) {
      this.dis = dis;
      this.word = word;
      prev = new LinkedList<>();
    }

    public void addPrev(WordNode prevWord) {
      prev.add(prevWord);
    }

    @Override
    public int compareTo(WordNode other) {
      return dis - other.dis;
    }
  }

  List<List<String>> findAllPaths(String start, String end, Set<String> dict) {
    PriorityQueue<WordNode> queue = new PriorityQueue<>();
    Map<String, WordNode> visited = new HashMap<>();
    WordNode startNode = new WordNode(start, 0);
    startNode.addPrev(null);
    queue.offer(startNode);
    visited.put(start, startNode);
    while (!queue.isEmpty()) {
      WordNode top = queue.poll();
      String topWord = top.word;
      //System.out.println(topWord);
      if (topWord.equals(end)) {
        break;
      }
      for (int i = 0; i < topWord.length(); i++) {
        for (int j = 0; j < 26; j++) {
          char replacement = (char) ('a' + j);
          if (replacement == topWord.charAt(i)) {
            continue;
          }
          StringBuilder neighborWordBuilder = new StringBuilder(topWord);
          neighborWordBuilder.setCharAt(i, replacement);
          String neighborWord = neighborWordBuilder.toString();

          if (dict.contains(neighborWord)) {
            if (visited.containsKey(neighborWord)) {
              WordNode nw = visited.get(neighborWord);
              if (nw.dis < top.dis + 1) {
                continue;
              }
              if (nw.dis > top.dis + 1) {
                nw.prev.clear();

              }
              // update the exisitng node
              nw.dis = top.dis + 1;
              nw.addPrev(top);
              queue.remove(nw);
              queue.offer(nw);
              continue;
            }
            // not visited before
            WordNode nw = new WordNode(neighborWord.toString(), top.dis + 1);
            nw.addPrev(top);
            queue.offer(nw);
            visited.put(nw.word, nw);
          }
        }
      }
    }

    System.out.println(visited.size());

    // there is no end;
    if (!visited.containsKey(end)) {
      return new LinkedList<>();
    }
    return buildPath(visited.get(end));
  }

  List<List<String>> buildPath(WordNode cur) {
    List<List<String>> paths = new LinkedList<>();
    if (cur == null) {
      paths.add(new LinkedList<String>());
      return paths;
    }
    for (WordNode prev : cur.prev) {
      for(List<String> path : buildPath(prev)) {
        path.add(cur.word);
        paths.add(path);
      }
    }
    return paths;
  }
}
