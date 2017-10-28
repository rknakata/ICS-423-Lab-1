//package bruteforcer;
/**
 * Bruteforcer class. Implement and call your password bruteforcer here.
 */
//import java.io.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
//import java.util.*;

public class Bruteforcer {
 public static void main(String[] args) {

  int iterations = 0;
  String currentPass = "          "; // has length 10 cuz 10 spaces
  for (int current = 0; current < 10; current++) { //permutations for length
   brute(32, 0, current, currentPass); //first char is 32 0 index and 0 length gets empty hash first
   System.out.println("Found hashes are in AnswersYAY.txt");
  }

 }

 public static String hash(String input) {
  byte[] rawMd5 = Md5Hasher.hash(input.getBytes());
  String stringifiedMd5 = Md5Hasher.convertToHexString(rawMd5);
  System.out.println("String = " + input + "Hash = " + stringifiedMd5);
  if (checkMatch(stringifiedMd5, "hashes.txt")) //found a match append it to the file
  {
   System.out.println(stringifiedMd5 + "match"); //delete
   BufferedWriter bw = null;
   FileWriter fw = null;

   try {


    File file = new File("AnswersYAY.txt");

    // if file doesnt exists, then create it
    if (!file.exists()) {
     file.createNewFile();
    }

    fw = new FileWriter(file.getAbsoluteFile(), true);
    bw = new BufferedWriter(fw);
    bw.write(stringifiedMd5 + " = " + input);
    bw.newLine(); //maybe delete
    System.out.println("Hash Found!!! " + input + " = " + stringifiedMd5);

   } catch (IOException e) {

    e.printStackTrace();

   } finally {

    try {

     if (bw != null)
      bw.close();

     if (fw != null)
      fw.close();

    } catch (IOException ex) {

     ex.printStackTrace();

    }
   }
  }
  else{
    System.out.println("ELSEFCUKER");
  }
  return stringifiedMd5;
 }

 public static boolean checkMatch(String plsCheck, String filename) {
  File file = new File(filename);

  try {
   Scanner scanner = new Scanner(file);

   while (scanner.hasNextLine()) {
    String line = scanner.nextLine();
    System.out.println(line + " vs. " + plsCheck );
    if (line.equals(plsCheck)) {
     return true;
    }
   }
  } catch (FileNotFoundException e) {
   //handle this
  }
  return false;
 }

 //http://www.asciichars.com/_site_media/ascii/ascii-chars-landscape.jpg
 //ascii values 32 - 126 are actual ascii characters
 public static void brute(int currentVal, int index, int length, String currentString) {
  if (length == 0) {
   hash(currentString);
  } else {
   for (int current = 32; current < 126; current++) {
    char[] currentStringChars = currentString.toCharArray();
    currentStringChars[length] = (char) current;
    currentString = String.valueOf(currentStringChars);
    //hash(currentString);
    brute(currentVal + 1, index, length - 1, currentString);
   }
  }
 }
}
