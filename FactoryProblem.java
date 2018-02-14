/*  David Twyman, Andrew LeDawson
**  dtwyman@calpoly.edu, aledawson@calpoly.edu
**  CSC 349-03
**  Project 3
**  2-12-2018
*/

package com.csc349.project3;

import java.io.*;
import java.util.*;

public class FactoryProblem
{
   public static void main(String[] args)
   {
   	FactoryProblem fProblem = new FactoryProblem();
      Scanner scanner = new Scanner(System.in);
      System.out.println("Enter the data file name");
      String fname = scanner.next();
      File file = new File(fname);
      Factory factory;
      try {
         FileReader inputFile = new FileReader(file);
         BufferedReader lineBuffer = new BufferedReader(inputFile);
         factory = fProblem.scanFile(lineBuffer);
         station[][] resultPath = fProblem.findShortestTimes(factory);
         fProblem.printResult(resultPath, factory.n);
      } catch (FileNotFoundException fileError) {
         System.out.println("ERROR: File not found!");
      } catch (IllegalArgumentException argError) {
         System.out.println("ERROR: Could not process factory matricies!");
      } catch (IOException readError) {
         System.out.println("ERROR: Could not read file!");
      } catch (InputMismatchException readError) {
         System.out.println("ERROR: Could not parse file!");
      } catch (NoSuchElementException readError) {
         System.out.println("ERROR: Could not parse file!");
      }
   }


   /* Finds shortest times from each point from the bottom up */
   public station[][] findShortestTimes(Factory factory)
   {
      station[][] shortest = new station[2][factory.n];
      shortest[0][factory.n - 1] = new station(factory.cost[0][factory.n - 1] + factory.end1, 0);
      shortest[1][factory.n - 1] = new station(factory.cost[1][factory.n - 1] + factory.end2, 0);
      for (int i = factory.n - 2; i > 0; i--)
      {
         shortest[0][i] = getMin(factory.cost[0][i]+factory.time[0][i]+shortest[1][i+1].cost, factory.cost[0][i]+shortest[0][i+1].cost, 0);
         shortest[1][i] = getMin(factory.cost[1][i]+factory.time[1][i]+shortest[0][i+1].cost, factory.cost[1][i]+shortest[1][i+1].cost, 1);
      }
      shortest[0][0] = getMin(factory.cost[0][0]+factory.time[0][0]+shortest[1][1].cost+factory.start1, factory.cost[0][0]+shortest[0][1].cost+factory.start1, 0);
      shortest[1][0] = getMin(factory.cost[1][0]+factory.time[1][0]+shortest[0][1].cost+factory.start2, factory.cost[1][0]+shortest[1][1].cost+factory.start2, 1);
      return shortest;
   }

   /* 
   ** Returns a station with the minimum time for this station as
   ** well as the next step to get there
   */
   private station getMin(int changeTime, int stayTime, int next)
   {
      station newstation = new station();
      if (changeTime < stayTime)
      {
         newstation.cost = changeTime;
         if (next == 1)
         {
            newstation.next = 0;
         }
         else
         {
            newstation.next = 1;
         }
         return newstation;
      }
      else
      {
         newstation.cost = stayTime;
         newstation.next = next;
         return newstation;
      }
   }

   /* Scans the input file into the Factory */
   public Factory scanFile(BufferedReader lineBuffer) throws IOException
   {
      Scanner scanner = new Scanner(lineBuffer.readLine()).useDelimiter(" ");
      int n = scanner.nextInt();
      Factory factory = new Factory(n);
      Scanner scanner2 = new Scanner(lineBuffer.readLine()).useDelimiter(" ");
      factory.start1 = scanner2.nextInt();
      factory.start2 = scanner2.nextInt();
      Scanner scanner3 = new Scanner(lineBuffer.readLine()).useDelimiter(" ");
      factory.end1 = scanner3.nextInt();
      factory.end2 = scanner3.nextInt();
      for (int i = 0; i < 2; i++)
      {
         Scanner scanner4 = new Scanner(lineBuffer.readLine()).useDelimiter(" ");
         for (int j = 0; j < n; j++)
         {
            factory.cost[i][j] = scanner4.nextInt();
         }
      }
      for (int i = 0; i < 2; i++)
      {
         Scanner scanner5 = new Scanner(lineBuffer.readLine()).useDelimiter(" ");
         for (int j = 0; j < n - 1; j++)
         {
            factory.time[i][j] = scanner5.nextInt();
         }
      }
      return factory;
   }

   private void printResult(station[][] stations, int n)
   {
   	  int line = 0;
      if (stations[0][0].cost < stations[1][0].cost)
      {
         System.out.println("\nFastest time is: "+stations[0][0].cost+"\n");
         System.out.println("The optimal route is:\nstation 1, line 1");
         line = stations[0][0].next;
      }
      else
      {
         System.out.println("\nFastest time is: "+stations[1][0].cost+"\n");
         System.out.println("The optimal route is:\nstation 1, line 2");
         line = stations[1][0].next;
      }
      for (int i = 1; i < n; i++)
      {
         System.out.println("station " + (i+1) + ", line " + (line+1));
         line = stations[line][i].next;
      }
   }

   /*private void printAll(Factory factory)
   {
      System.out.println("n: " + factory.n);
      System.out.println("start1: " + factory.start1);
      System.out.println("start2: " + factory.start2);
      System.out.println("end1: " + factory.end1);
      System.out.println("end2: " + factory.end2);
      System.out.println("Costs: ");
      for (int i = 0; i < 2; i++)
      {
         for (int j = 0; j < factory.n; j++)
         {
            System.out.print(factory.cost[i][j]+" ");
         }
         System.out.println("");
      }
      System.out.println("Times: ");
      for (int i = 0; i < 2; i++)
      {
         for (int j = 0; j < factory.n-1; j++)
         {
            System.out.print(factory.time[i][j]+" ");
         }
         System.out.println("");
      }
   }

   private void printAllStations(station[][] stations, int n)
   {
      for (int i = 0; i < 2; i++)
      {
         for (int j = 0; j < n; j++)
         {
            System.out.print(stations[i][j].cost+" ");
         }
         System.out.println("");
      }
   }*/

   /* 
   ** Structure providing the minimum time to get to the end and whether the 
   ** product switches when going to the next station
   */
   private class station
   {
      int cost;
      int next;
      public station(int num, int change)
      {
         this.cost = num;
         this.next = change;
      }
      public station()
      {
         this.cost = 0;
         this.next = 0;
      }
   }

   /* Structure containing all of the input information */
   private class Factory
   {
      int start1;
      int start2;
      int end1;
      int end2;
      int[][] cost;
      int[][] time;
      int n;
      public Factory(int num_stations)
      {
         this.cost = new int[2][num_stations];
         this.time = new int[2][num_stations - 1];
         this.n = num_stations;
      }
   }
}
