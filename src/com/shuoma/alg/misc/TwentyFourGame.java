/**
 * Implement the card game 24
 * @author solma
 *
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import com.shuoma.helper.*;

public class TwentyFourGame {
	public static void main(String[] args) {
		new TwentyFourGame().main();
	}

	public class Hand {
		public HashMap<Double, Integer> cards;
		public Hand prev;
		
		public Hand(int[] array) {
			cards = new HashMap<Double, Integer>();
			for (int o : array)
				add((double) o);
		}

		public Hand(Hand other) {
			cards = new HashMap<Double, Integer>(other.cards);
		}

		public ArrayList<Double> toList() {
			ArrayList<Double> li = new ArrayList<Double>();
			for (Double d : cards.keySet())
				for (int i = 0; i < cards.get(d); i++)
					li.add(d);
			return li;
		}

		public int size() {
			return toList().size();
		}

		public void add(double num) {
			if (cards.containsKey(num))
				cards.put(num, cards.get(num) + 1);
			else
				cards.put(num, 1);
		}
		
		public void setPrev(Hand prev){
			this.prev=prev;
		}

		public void remove(double num) {
			try {
				if (!cards.containsKey(num))
					throw new Exception();
				else {
					int cnt = cards.get(num);
					cnt--;
					if (cnt == 0)
						cards.remove(num);
					else
						cards.put(num, cnt);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				System.err.println("no card has value of " + (int) num
						+ " in the hand");
				System.exit(-1);
			}
		}

		public ArrayList<Hand> getNeighborHands() {
			// compute all neighboring hands
			HashSet<Hand> neighbors = new HashSet<Hand>();
			char[] operators = { '+', '-', '*', '/' };

			ArrayList<Double> li = toList();
			//System.out.println("li is =" + li);
			int n = size();
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++) {
					if (j == i)
						continue;
					double op1 = li.get(i), op2 = li.get(j);
					for (char opr : operators) {
						double res = arithmetic(op1, op2, opr);
						Hand newHand = new Hand(this);
						// System.out.println("newHand is ="+newHand );
						newHand.remove(op1);
						newHand.remove(op2);
						newHand.add(res);
						neighbors.add(newHand);
					}
				}
			
//			for (Hand nh : neighbors)
//				System.out.println(nh);
			return new ArrayList<Hand>(neighbors);
		}
		
		public String getPath(){
			String path=toString();
			if(prev!=null) path=prev.getPath()+"-> "+path;
			return path;
		}

		@Override
		public boolean equals(Object other) {
			for (double d : cards.keySet()) {
				Hand oHand = (Hand) other;
				if (!oHand.cards.containsKey(d)
						|| oHand.cards.get(d) != cards.get(d))
					return false;
			}
			return true;
		}

		@Override
		public int hashCode() {
			int hashcode = 0;
			for (double d : cards.keySet()) {
				hashcode += d + 19 * cards.get(d);
			}
			return hashcode;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			for (double d : cards.keySet()) {
				if(d==(int) d) sb.append((int) d );
				else sb.append(String.format("%.2f", d) );
				sb.append(":" + cards.get(d) + " ");
			}
			return sb.toString();
		}
	}

	public void main() {
		//{3, 4, 5, 8};
		int[] randArray = CommonUtils.genRandomArray(4, 13, true, false);

		Hand hand = new Hand(randArray);
		System.out.println("hand is=" + hand);

		HashSet<Hand> visited=new HashSet<Hand>();
		solve(hand, visited);
	}
	
	public void solve(Hand hand, HashSet<Hand> visited){
		//System.out.println("cur hand is "+hand);
		visited.add(hand);
		
		if(hand.size()==1&&hand.cards.containsKey(24.0)){
			System.out.println(hand.getPath());
			visited.remove(hand); //clear flag for the target state, allow other solutions
		}
		
		for(Hand next: hand.getNeighborHands()){
			if(!visited.contains(next)){
				next.setPrev(hand);
				solve(next, visited);
			}
		}
	}

	public double arithmetic(double op1, double op2, char operator) {
		try {
			switch (operator) {
			case '+':
				return op1 + op2;
			case '-':
				return op1 - op2;
			case '*':
				return op1 * op2;
			case '/':
				return op1 / op2;
			default:
				throw new Exception();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.err.println("invalid operator");
		}
		return -1;
	}
}
