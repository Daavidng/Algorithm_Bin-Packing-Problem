package java_assignment;

import java.util.*;

public class firstFit {
	private static void firstFit(List<Integer> truck, Queue<Integer> queue) {

		Queue<Integer> q = new LinkedList<Integer>(queue);
		System.out.println("Parcel\tTruck no.\tRemaining Space");

		while (!q.isEmpty()) {
			
			boolean stored = false;
			Integer parcel = q.peek();

			for (int i = 0; i < truck.size(); ++i) {
				if (truck.get(i) >= parcel) {
					System.out.println(parcel + "\t" + (i + 1) + "\t\t" + (truck.get(i) - parcel));
					truck.set(i, truck.get(i) - parcel);
					stored = true;
					break;
				}
			}
			if (!stored)
				System.out.println("Cannot find truck for this parcel with size : " + parcel);
			q.remove();
		}
	}

	public static void main(String[] args) {

		List<Integer> truck = new LinkedList<>();
		Queue<Integer> parcel = new LinkedList<>();
		List<Integer> tempParcel = new ArrayList<>();
		int parcelNum = -1;
		int truckNum = -1;

		boolean invalidInput;
		do {
			Scanner inp = new Scanner(System.in);
			invalidInput = false;
			try {
				System.out.println("Enter Number of Parcels");
				parcelNum = inp.nextInt();
				System.out.println("Enter Number of Truck Available");
				truckNum = inp.nextInt();
				if (truckNum < 0 || parcelNum < 0) {
					throw new IllegalArgumentException();
				}
				for (int i = 0; i < parcelNum; i++) {
					System.out.println("Enter Size of Parcel " + (i + 1));
					int size = inp.nextInt();
					tempParcel.add(size);
				}

				for (int i = 0; i < truckNum; i++) {
					System.out.println("Enter Size of Truck " + (i + 1));
					int size = inp.nextInt();
					truck.add(size);
				}

				if (Collections.min(tempParcel) < 0 || Collections.min(truck) < 0) {
					throw new IllegalArgumentException();
				} else {
					parcel.addAll(tempParcel);
				}
			}

			catch (InputMismatchException e) {
				System.out.println("Invalid value, please insert Integer ONLY");
				invalidInput = true;
				System.out.println("Please enter a valid integer.");
				inp.nextLine();
			} catch (IllegalArgumentException e) {
				System.out.println("Parcel or Truck can Never be Negative, program exiting...");
			}

		} while (invalidInput);

		firstFit(truck, parcel);
	}
}