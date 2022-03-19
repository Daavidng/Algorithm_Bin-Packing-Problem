package java_assignment;

import java.util.*;

public class nextFit {
	private static void nextFit(List<Integer> truck, Queue<Integer> queue) {

		Queue<Integer> q = new LinkedList<Integer>(queue);
		int j = 0;
		System.out.println("Parcel\tTruck no.\tRemaining Space");

		while (!q.isEmpty()) {
			
			boolean stored = false;
			Integer parcel = q.peek();
			int i = 0;
			
			while (j < truck.size() && i != truck.size()) {
				if (truck.get(j) >= parcel) {
					System.out.println(parcel + "\t" + (j + 1) + "\t\t" + (truck.get(j) - parcel));
					truck.set(j, truck.get(j) - parcel);
					stored = true;
					break;
				}
				i++;
				// the mod values will help in traversing the blocks from
				// starting block after we reach the end.
				j = (j + 1) % truck.size();
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

		nextFit(truck, parcel);
	}
}