import java.util.ArrayList;

public class Main {

 private static final int MAX = 10000;
 private static final boolean canSet = true;

 public static void main(String[] args) throws Exception {
  int countAdd = 0;
  int countRemoveByIndex = 0;
  int countRemoveByT = 0;
  int countSet = 0;
  int countClear = 0;

  SimpleLinkedList<String> list = new SimpleLinkedList<String>();
  ArrayList<String> listShadow = new ArrayList<String>();
  for (int i = 0; i < MAX; i++) {
   int oper = (int) (9 * Math.random());
   switch (oper) {
   case (0):
   case (1):
   case (2):
   case (3):
   case (4):
   case (5): {
    list.add(Integer.toString(i));
    listShadow.add(Integer.toString(i));
    countAdd++;
    break;
   }
   case (6): {
    if (list.size() != 0) {
     int removeIndex = (int) ((list.size() - 1) * Math.random());
     System.out.println("remove index tested: " + removeIndex + "\nlist size: " + list.size());
     list.remove(removeIndex);
     listShadow.remove(removeIndex);
     countRemoveByIndex++;
    }
    break;
   }
   case (7): {
    if (i < MAX / 4) {
     list.clear();
     listShadow.clear();
     countClear++;
    }
    break;
   }
   case (8): {
    if (i != 0) {
     int removeT = (int) ((list.size() - 1) * Math.random());
     if(list.remove(Integer.toString(removeT))){
      countRemoveByT++;
     }
     listShadow.remove(Integer.toString(removeT));
    }
    break;
   }
   case (9): {
  /*  if (list.size() != 0 && canSet) {
     int removeIndex = (int) ((list.size() - 1) * Math.random());
     list.set(removeIndex, Integer.toString(i));
     listShadow.set(removeIndex, Integer.toString(i));
     countSet++;
    } */
    break;
   }
   }
  }

  int countCorrect = 0;
  for (int j = 0; j < list.size(); j++) {
   if (list.get(j).equals(listShadow.get(j))) {
    countCorrect++;
   }
  }
  System.out.println("Your data score: " + countCorrect + "/"
    + list.size());

  int countIndexOf = 0;
  for (int k = 0; k < MAX; k++) {
   if (list.indexOf(Integer.toString(k)) == listShadow.indexOf(Integer.toString(k))) {
    countIndexOf++;
   }
  }

  System.out.println("Your indexOf score: " + countIndexOf + "/" + MAX);

  System.out.println();
  System.out.println(countAdd + " add operations.");
  System.out.println(countRemoveByIndex + " remove by index operations.");
  System.out.println(countRemoveByT + " remove by T operations.");
  System.out.println(countClear + " clear operations.");
  System.out.println(countSet + " set operations.");
 }
}
