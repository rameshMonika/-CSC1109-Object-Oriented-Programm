// Source code is decompiled from a .class file using FernFlower decompiler.
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Security {
   private static final int PIN_LENGTH = 6;
   private static final int NRIC_LENGTH = 9;
   private static final int SALT_LENGTH = 16;

   public Security() {
   }

   public boolean authenticateUser(String var1, String var2) {
      try {
         String var3 = var2.substring(0, 64);
         String var4 = var2.substring(64);
         String var5 = this.hashWithSalt(var1, var4);
         if (!var3.equals(var5)) {
            throw new IllegalArgumentException("Invalid PIN");
         } else {
            return true;
         }
      } catch (IllegalArgumentException var6) {
         System.out.println("Authentication failed: " + var6.getMessage());
         return false;
      }
   }

   private String hashWithSalt(String var1, String var2) {
      try {
         String var3 = var1 + var2;
         MessageDigest var4 = MessageDigest.getInstance("SHA-256");
         byte[] var5 = var4.digest(var3.getBytes());
         StringBuilder var6 = new StringBuilder();
         byte[] var7 = var5;
         int var8 = var5.length;

         for(int var9 = 0; var9 < var8; ++var9) {
            byte var10 = var7[var9];
            String var11 = Integer.toHexString(255 & var10);
            if (var11.length() == 1) {
               var6.append('0');
            }

            var6.append(var11);
         }

         return var6.toString();
      } catch (NoSuchAlgorithmException var12) {
         throw new IllegalStateException("SHA-256 algorithm not found", var12);
      }
   }

   public boolean login(String var1) {
      try {
         System.out.println("Enter PIN:");
         Scanner var2 = new Scanner(System.in);
         String var3 = var2.next();
         if (!var3.isEmpty()) {
            return this.authenticateUser(var3, var1);
         } else {
            System.out.println("You did not enter a PIN");
            return false;
         }
      } catch (InputMismatchException var4) {
         System.out.println("Invalid input format. Please enter a valid PIN.");
         return false;
      } catch (NullPointerException var5) {
         System.out.println("Error: Null stored PIN.");
         return false;
      } catch (Exception var6) {
         System.out.println("An unexpected error occurred: " + var6.getMessage());
         return false;
      }
   }

   public String encrypt(String var1) {
      try {
         byte[] var2 = this.generateSalt();
         String var3 = var1 + this.bytesToHex(var2);
         MessageDigest var4 = MessageDigest.getInstance("SHA-256");
         byte[] var5 = var4.digest(var3.getBytes());
         String var10000 = this.bytesToHex(var5);
         return var10000 + this.bytesToHex(var2);
      } catch (NoSuchAlgorithmException var6) {
         System.err.println("Error: SHA-256 algorithm not found");
         return null;
      }
   }

   private byte[] generateSalt() {
      SecureRandom var1 = new SecureRandom();
      byte[] var2 = new byte[16];
      var1.nextBytes(var2);
      return var2;
   }

   private String bytesToHex(byte[] var1) {
      StringBuilder var2 = new StringBuilder();
      byte[] var3 = var1;
      int var4 = var1.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         byte var6 = var3[var5];
         String var7 = Integer.toHexString(255 & var6);
         if (var7.length() == 1) {
            var2.append('0');
         }

         var2.append(var7);
      }

      return var2.toString();
   }

   public boolean validateNewPin(String var1, String var2) {
      try {
         String var3 = var2.substring(64);
         String var4 = this.hashWithSalt(var1, var3);
         if (var4.equals(var2.substring(0, 64))) {
            throw new IllegalArgumentException("New PIN is identical to the old PIN");
         } else if (var1.length() != 6) {
            throw new IllegalArgumentException("New PIN length is not 6 characters");
         } else {
            return true;
         }
      } catch (IllegalArgumentException var5) {
         System.out.println("PIN validation failed: " + var5.getMessage());
         return false;
      }
   }

   public String resetPinMenu(String var1, String var2) {
      Scanner var3 = new Scanner(System.in);
      boolean var4 = false;

      String var5;
      do {
         while(!var4) {
            System.out.println("Reset PIN");
            System.out.println("=========================");
            System.out.println("Enter 0 to exit this menu");
            System.out.println("=========================");
            System.out.println("Verify NRIC:");
            var5 = var3.nextLine();
            if (var5.equals("0")) {
               System.out.println("Exiting PIN reset menu.");
               return null;
            }

            if (this.verifyNRIC(var5, var1)) {
               var4 = true;
               break;
            }
         }

         System.out.println("Enter a new PIN:");
         var5 = var3.nextLine();
         if (var5.equals("0")) {
            System.out.println("Exiting PIN reset menu.");
            return null;
         }
      } while(!this.validateNewPin(var5, var2));

      return var5;
   }

   public boolean verifyNRIC(String var1, String var2) {
      try {
         if (var1.length() != 9) {
            throw new IllegalArgumentException("Entered NRIC is not 9 characters long");
         } else if (!var1.equals(var2)) {
            throw new IllegalArgumentException("Entered NRIC is invalid");
         } else {
            return true;
         }
      } catch (IllegalArgumentException var4) {
         System.out.println("NRIC verification failed: " + var4.getMessage());
         return false;
      }
   }
}
