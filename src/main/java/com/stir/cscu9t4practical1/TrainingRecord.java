// An implementation of a Training Record as an ArrayList
package main.java.com.stir.cscu9t4practical1;


import java.util.*;


public class TrainingRecord {
    private List<Entry> tr;
    
    public TrainingRecord() {
        tr = new ArrayList<Entry>();
    } //constructor

//    // add a record to the list
//   public boolean checkEntry(Entry e){ //#4 attempt
//       ListIterator<Entry> iter = tr.listIterator(); //#4 attempt
//       while (iter.hasNext()) {
//           Entry current = iter.next();
//           if (e.getName().equals(current.getName()) && e.getDay() == current.getDay()
//                   && e.getMonth() == current.getMonth() && e.getYear() == current.getYear()) {
//               String result2 = "Entry already exists";
//               return false;
//           }
//           if (e.getName().isEmpty() || e.getDay() == 0 || e.getMonth() == 0 //#4 attempt
//                   || e.getYear() == 0 || e.getHour() == 0 || e.getMin() == 0
//                   || e.getSec() == 0 || e.getDistance() == 0) {
//               String result = "Some entries left blank";
//               return false;
//           }
//       }
//       tr.add(e);
//       return true;
//   } // addClass


    // add a record to the list
    public void addEntry(Entry e){
        tr.add(e);
    } // addClass


    // look up the entry of a given day and month
    public String lookupEntry (int d, int m, int y) {
        ListIterator<Entry> iter = tr.listIterator();
        String result = "No entries found";
        while (iter.hasNext()) {
            Entry current = iter.next();
            if (current.getDay()==d && current.getMonth()==m && current.getYear()==y)
                result = current.getEntry();
        }
        return result;
    } // lookupEntry

   // look up all of the entries of a given day and month //#2
   public String lookupAllEntries (int d, int m, int y) { //didn't make a new method for #2, just adjusted this one
       ListIterator<Entry> iter = tr.listIterator();
       String result = "";
       while (iter.hasNext()) {
          Entry current = iter.next();
          if (current.getDay()==d && current.getMonth()==m && current.getYear()==y) {
              result += current.getEntry(); //#2
          }
            }
       if (result.equals("")) { //#2
           result = "No entries found"; //#2
       }
       return result;
   } // lookupAllEntries
   
   // Count the number of entries
   public int getNumberOfEntries(){
       return tr.size();
   }
   // Clear all entries
   public void clearAllEntries(){
       tr.clear();
   }
   
} // TrainingRecord