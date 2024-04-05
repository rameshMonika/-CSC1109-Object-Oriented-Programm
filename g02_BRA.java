// Source code is decompiled from a .class file using FernFlower decompiler.
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class g02_BRA {
   private String branchId;
   private boolean isOverseas;
   private String country;
   private String branchName;
   private String address;
   private int phoneNum;
   private int atmAvailability;
   private ArrayList<String> queueNum;

   public g02_BRA(String var1, boolean var2, String var3, String var4, String var5, int var6, int var7) {
      this.branchId = var1;
      this.isOverseas = var2;
      this.country = var3;
      this.branchName = var4;
      this.address = var5;
      this.phoneNum = var6;
      this.atmAvailability = var7;
      this.queueNum = new ArrayList();
   }

   public String getBranchId() {
      return this.branchId;
   }

   public void setBranchId(String var1) {
      this.branchId = var1;
   }

   public String getBranchName() {
      return this.branchName;
   }

   public void setBranchName(String var1) {
      this.branchName = var1;
   }

   public String getAddress() {
      return this.address;
   }

   public void setAddress(String var1) {
      this.address = var1;
   }

   public int getPhoneNum() {
      return this.phoneNum;
   }

   public void setPhoneNum(int var1) {
      this.phoneNum = var1;
   }

   public int getAtmAvailability() {
      return this.atmAvailability;
   }

   public void setAtmAvailability(int var1) {
      this.atmAvailability = var1;
   }

   public ArrayList<String> getQueueNum() {
      return this.queueNum;
   }

   public void setQueueNum(ArrayList<String> var1) {
      this.queueNum = var1;
   }

   public static g02_BRA createBranch(String var0, boolean var1, String var2, String var3, String var4, int var5, int var6) {
      return new g02_BRA(var0, var1, var2, var3, var4, var5, var6);
   }

   public void updateBranchDetails() {
      // $FF: Couldn't be decompiled
   }

   public void generateQueueNumber(boolean var1, int var2) {
      int var3 = this.queueNum.isEmpty() ? 1 : this.queueNum.size() + 1;
      String var4 = (var1 ? "P" : "V") + var3 + "-" + var2;
      this.queueNum.add(var4);
      System.out.println("Generated queue number: " + var4);
   }

   public void removeQueue() {
      System.err.println("Queue to be removed: ");

      try {
         Scanner var1 = new Scanner(System.in);

         try {
            String var2 = var1.next();
            if (this.queueNum.contains(var2)) {
               this.queueNum.remove(var2);
               System.out.println("Queue removed. Current queue: " + String.valueOf(this.queueNum));
            } else {
               System.out.println("Invalid queue number, try again.");
               this.removeQueue();
            }
         } catch (Throwable var5) {
            try {
               var1.close();
            } catch (Throwable var4) {
               var5.addSuppressed(var4);
            }

            throw var5;
         }

         var1.close();
      } catch (NumberFormatException var6) {
         var6.printStackTrace();
      }

   }

   public void displayQueue() {
      ArrayList var1 = new ArrayList();
      ArrayList var2 = new ArrayList();
      Iterator var3 = this.queueNum.iterator();

      while(var3.hasNext()) {
         String var4 = (String)var3.next();
         char var5 = var4.charAt(0);
         if (var5 == 'V') {
            var1.add(var4);
         } else {
            var2.add(var4);
         }
      }

      PrintStream var10000 = System.out;
      String var10001 = String.valueOf(var2);
      var10000.println("Physical queues: " + var10001 + ", " + var2.size() + " in line");
      var10000 = System.out;
      var10001 = String.valueOf(var1);
      var10000.println("Virtual queues: " + var10001 + ", " + var1.size() + " in line");
   }

   public void displayBranchInfo() {
      System.out.println("Branch ID: " + this.branchId + "Overseas: " + this.isOverseas + "Country: " + this.country + "Branch Name: " + this.branchName + "Address: " + this.address + "Phone number: " + this.phoneNum + "ATMs Available: " + this.atmAvailability);
   }
}
