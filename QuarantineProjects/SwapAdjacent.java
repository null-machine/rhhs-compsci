import java.util.ArrayList;

public class SwapAdjacent {
	public static void main(String[] args) {
		// im not doing the scanner part soz
		
		ArrayList<String> list = new ArrayList<String>();
		list.add("apple");
		list.add("banana");
		list.add("carrot");
		list.add("dragonfruit");
		list.add("eggplant");
		list.add("fig");
		list.add("gapple");
		list = swapAdjacent(list);
		
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i) + " ");
		}
	}
	
	private static <T> ArrayList<T> swapAdjacent(ArrayList<T> list) {
		for (int i = 0; i + 1 < list.size(); i += 2) {
			T temp = list.get(i);
			list.set(i, list.get(i + 1));
			list.set(i + 1, temp);
		}
		return list;
	}
}
