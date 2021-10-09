import java.util.ArrayList;
import java.util.Scanner;

public class VaccinePortal
{
    public class Vaccine {
        private final String name;
        private final int doses, gap;
    
        public String getName() {
            return name;
        }
    
        public int getDoses() {
            return doses;
        }
    
        public int getGap() {
            return gap;
        }
    
        public Vaccine(String name, int doses, int gap) {
            this.name = name;
            this.doses = doses;
            this.gap = gap;
        }
        
        public void printVaccine() {
            System.out.println("Vaccine Name: " + name + ", Number of Doses: " + Integer.toString(doses) + ", Gap Between Doses: " + Integer.toString(gap));
        }
    }
    
    public class Hospital {
        private final String name;
        private final int pincode, ID;
        private ArrayList<Slot> slots;
    
        public String getName() {
            return name;
        }
    
        public int getPincode() {
            return pincode;
        }
    
        public int getID() {
            return ID;
        }
    
        public String getStringID() {
            return "0".repeat(Math.max(0, 6 - Integer.toString(ID).length())) + Integer.toString(ID);
        }
        
        public Hospital(String name, int pincode, int ID)
        {
            this.name = name;
            this.pincode = pincode;
            this.ID = ID;
            this.slots = new ArrayList<>();
        }
        
        public void printHospital() {
            int len = 6 - Integer.toString(ID).length();
            String id6 = "0".repeat(Math.max(0, len)) + Integer.toString(ID);
            System.out.println("Hospital Name: " + name + ", PinCode: " + Integer.toString(pincode) + ", Unique ID: " + id6);
        }
        
        public void addPrintSlot(Slot slot) {
            slots.add(slot);
            String id = "0".repeat(Math.max(0, 6 - Integer.toString(ID).length())) + Integer.toString(ID);
            System.out.println("Slot added by hospital " + id + " for Day: " +
                    Integer.toString(slot.getDay()) + ", Available Quantity: " +
                    Integer.toString(slot.getQuantity()) + " of Vaccine " + slot.vaccine.getName());
        }
        
        public void printAllSlots() {
            for (int i = 0; i < slots.size(); i++)
                if(slots.get(i).getQuantity() > 0) {
                    System.out.print(Integer.toString(i) + "-> ");
                    System.out.println(slots.get(i));
                }
        }
        
        public void printSlots(String vaccineName) {
            for (int i = 0; i < slots.size(); i++) {
                Slot slot = slots.get(i);
                if (slot.getVaccine().getName().equals(vaccineName) && slot.getQuantity() > 0) {
                    System.out.print(Integer.toString(i) + "-> ");
                    System.out.println(slot);
                }
            }
        }
        
        public void printAllSlotsWithoutIndices() {
            for (Slot slot : slots) {
                if (slot.getQuantity() > 0) System.out.println(slot);
            }
        }
        
        public Slot getSlot(int index) {
            return slots.get(index);
        }
    
        public boolean hasVaccine(String vaccine) {
            for (Slot sl : slots) {
                if (sl.getVaccine().getName().equals(vaccine) && sl.getQuantity() > 0) return true;
            }
            return false;
            
        }
    }

    public class Citizen {
        private final String name;
        private final int age;
        private final long ID;
        private int doses, lastDose, dueDate;
        // lastdose, dueDate is -1 if 0 zero doses are taken
        private Vaccine vaccine;
        // null if 0 doses taken
    
    
        public Citizen(String name, int age, long ID)
        {
            this.name = name;
            this.age = age;
            this.ID = ID;
            this.doses = 0;
            this.lastDose = this.dueDate = -1;
            this.vaccine = null;
        }
        
        public boolean vaccinate(Slot slot) {
            if (vaccine == null) {
                vaccine = slot.getVaccine();
            }
            else {
                if (doses == vaccine.getDoses()) {
                    System.out.println("You are already vaccinated.");
                    return false;
                }
                if (slot.getQuantity() == 0) {
                    System.out.println("Incorrect slot code entered.");
                    return false;
                }
                if (dueDate > slot.getDay()) {
                    System.out.println("Cannot get your next dose before due date: " +
                            Integer.toString(dueDate));
                    return false;
                }
                if (!vaccine.equals(slot.getVaccine())) {
                    System.out.println("Cannot mix vaccines.\nYou must get vaccinated by " + vaccine.getName());
                    return false;
                }
                
            }
            
            doses++;
            lastDose = slot.getDay();
            dueDate = lastDose + vaccine.getGap();
            return true;
        }
    
        public String getName()
        {
            return name;
        }
    
        public int getAge()
        {
            return age;
        }
    
        public long getID()
        {
            return ID;
        }
    
        public int getDoses()
        {
            return doses;
        }
    
        public int getLastDose()
        {
            return lastDose;
        }
    
        public int getDueDate()
        {
            return dueDate;
        }
    
        public Vaccine getVaccine()
        {
            return vaccine;
        }
        
        public void printCitizen()
        {
            System.out.println("Citizen Name: " + name + ", Age: " + Integer.toString(age) + ", Unique ID: " + Long.toString(ID));
        }
        
        public void printStatus() {
            if (doses == 0) {
                System.out.println("Citizen REGISTERED");
            }
            else {
                if (doses == vaccine.getDoses()) System.out.println("FULLY VACCINATED");
                else System.out.println("PARTIALLY VACCINATED");
                System.out.println("Vaccine Given: " + vaccine.getName());
                System.out.println("Number of doses given: " + Integer.toString(doses));
                System.out.println("Next dose due date: " + Integer.toString(dueDate));
            }
        }
    }

    public class Slot {
        private final int day;
        private int quantity;
        private final Vaccine vaccine;
    
        public Slot(int day, int quantity, Vaccine vaccine)
        {
            this.day = day;
            this.quantity = quantity;
            this.vaccine = vaccine;
        }
    
        public int getDay()
        {
            return day;
        }
    
        public int getQuantity()
        {
            return quantity;
        }
    
        public Vaccine getVaccine()
        {
            return vaccine;
        }
        
        public void useSlot() {
            quantity--;
        }
    
        @Override
        public String toString()
        {
            return "Day: " + Integer.toString(day) +
                    " Available Quantity: " + Integer.toString(quantity) +
                    " Vaccine: " + vaccine.getName();
        }
    }
    
    public void runPortal() {
        ArrayList<Vaccine> vaccines = new ArrayList<>();
        ArrayList<Hospital> hospitals = new ArrayList<>();
        ArrayList<Citizen> citizens = new ArrayList<>();
    
        Scanner sc = new Scanner(System.in);
        System.out.println("\nCoVin Portal Initialized...\n_____________________________________");
        String menuOptions = "_____________________________________\n" +
                "1. Add Vaccine\n" +
                "2. Register Hospital\n" +
                "3. Register Citizen\n" +
                "4. Add Slot for Vaccination\n" +
                "5. Book Slot for Vaccination\n" +
                "6. List all slots for a hospital\n" +
                "7. Check Vaccination Status\n" +
                "8. Exit\n_____________________________________";
        while (true) {
            System.out.println(menuOptions);
            int q = sc.nextInt();
            sc.nextLine();
            if (q == 1) {
                System.out.print("Vaccine Name: ");
                String name = sc.nextLine().toLowerCase();
                System.out.print("Number of Doses: ");
                int doses = sc.nextInt();
                sc.nextLine();
                int gap = 0;
                if (doses > 1) {
                    System.out.print("Gap between doses: ");
                    gap = sc.nextInt();
                    sc.nextLine();
                }
                vaccines.add(new Vaccine(name, doses, gap));
                vaccines.get(vaccines.size() - 1).printVaccine();
                System.out.println("_____________________________________");
            }
            else if (q == 2) {
                System.out.print("Hospital Name: ");
                String name = sc.nextLine();
                System.out.print("Pincode: ");
                int pincode = sc.nextInt();
                sc.nextLine();
                hospitals.add(new Hospital(name, pincode, hospitals.size()));
                hospitals.get(hospitals.size() - 1).printHospital();
                System.out.println("_____________________________________");
            }
            else if (q == 3) {
                System.out.print("Citizen Name: ");
                String name = sc.nextLine();
                System.out.print("Age: ");
                int age = sc.nextInt();
                sc.nextLine();
                if (age < 18) {
                    System.out.println("Only above 18 are allowed.");
                    continue;
                }
                System.out.print("Unique ID: ");
                long ID = sc.nextLong();
                sc.nextLine();
                if(Long.toString(ID).length() < 12) {
                    System.out.println("ID must be 12 digit long.");
                    continue;
                }
                citizens.add(new Citizen(name, age, ID));
                citizens.get(citizens.size() - 1).printCitizen();
                System.out.println("_____________________________________");
            }
            else if (q == 4) {
                if (vaccines.size() == 0) {
                    System.out.println("There are no vaccines added yet.");
                    continue;
                }
                System.out.print("Enter Hospital ID: ");
                int ID = sc.nextInt();
                sc.nextLine();
                Hospital current = null;
                for (Hospital h : hospitals) {
                    if (h.getID() == ID) {
                        current = h;
                        break;
                    }
                }
                
                if (current == null) {
                    System.out.println("Incorrect ID entered / Unregistered hospital");
                    continue;
                }
                
                System.out.print("Enter number of slots to be added: ");
                int slots = sc.nextInt();
                sc.nextLine();
                
                for (int i = 0; i < slots; i++) {
                    System.out.print("Enter day number: ");
                    int day = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter quantity: ");
                    int quantity = sc.nextInt();
                    System.out.println("Select Vaccine");
                    for (int j = 0; j < vaccines.size(); j++) {
                        System.out.println(Integer.toString(j) + ". " + vaccines.get(j).getName());
                    }
                    int vaccine = sc.nextInt();
                    sc.nextLine();
                    current.addPrintSlot(new Slot(day, quantity, vaccines.get(vaccine)));
                }
            }
            else if (q == 5) {
                if (vaccines.size() == 0) {
                    System.out.println("There are no vaccines added yet.");
                    continue;
                }
                System.out.print("Enter Citizen Unique ID: ");
                long ID = sc.nextLong();
                sc.nextLine();
                Citizen cz = null;
                for (Citizen citizen : citizens) {
                    if (ID == citizen.getID()) {
                        cz = citizen;
                        break;
                    }
                }
                if (cz == null) {
                    System.out.println("Incorrect citizen ID / Unregistered citizen");
                    continue;
                }
                System.out.print("1. Search by area\n" +
                        "2. Search by Vaccine\n" +
                        "3. Exit\n" +
                        "Enter Option: ");
                int opt = sc.nextInt();
                sc.nextLine();
                if (opt == 1) {
                    System.out.print("Enter pincode: ");
                    int pincode = sc.nextInt();
                    sc.nextLine();
                    boolean available = false;
                    for (Hospital h : hospitals) {
                        if (h.getPincode() == pincode) {
                            available = true;
                            System.out.println(h.getStringID() + " " + h.getName());
                        }
                    }
                    if (!available) {
                        System.out.println("No hospital in this pincode.");
                        continue;
                    }
                    System.out.print("Enter hospital ID: ");
                    int HospitalID = sc.nextInt();
                    sc.nextLine();
                    Hospital chosenHospital = null;
                    for (Hospital h : hospitals) {
                        if (HospitalID == h.getID()) {
                            h.printAllSlots();
                            chosenHospital = h;
                            break;
                        }
                    }
                    System.out.print("Choose slot: ");
                    Slot slotChosen = chosenHospital.getSlot(sc.nextInt());
                    sc.nextLine();
                    if (cz.vaccinate(slotChosen)) {
                        slotChosen.useSlot();
                        System.out.println(cz.getName() + " vaccinated with " +
                                slotChosen.getVaccine().getName());
                    }
                }
                else if (opt == 2) {
                    System.out.print("Enter vaccine name: ");
                    String vaccineName = sc.nextLine().toLowerCase();
                    if (cz.getDoses() > 0 && !cz.getVaccine().getName().equals(vaccineName)) {
                        System.out.println("You can not mix vaccines.\nYou must take dose of " + cz.getVaccine().getName());
                        continue;
                    }
                    boolean available = false;
                    for (Hospital h : hospitals) {
                        if (h.hasVaccine(vaccineName)) {
                            System.out.println(h.getStringID() + " " + h.getName());
                            available = true;
                        }
                    }
                    if (!available) {
                        System.out.println("This vaccine is not available.");
                        continue;
                    }
                    
                    System.out.print("Enter hospital ID: ");
                    int hospitalID = sc.nextInt();
                    sc.nextLine();
                    Hospital chosenHospital = null;
                    boolean foundHospital = false;
                    for (Hospital h : hospitals) {
                        if (h.getID() == hospitalID && h.hasVaccine(vaccineName)) {
                            chosenHospital = h;
                            foundHospital = true;
                            break;
                        }
                    }
                    if (!foundHospital) {
                        System.out.println("Incorrect hospital ID entered.");
                        continue;
                    }
                    
                    chosenHospital.printSlots(vaccineName);
    
                    System.out.print("Chose slot: ");
                    Slot chosenSlot = chosenHospital.getSlot(sc.nextInt());
                    sc.nextLine();
                    if (cz.vaccinate(chosenSlot)) {
                        chosenSlot.useSlot();
                        System.out.println(cz.getName() + " Vaccinated with " + chosenSlot.getVaccine().getName());
                    }
                    
                        
                    
                }
                else if (opt > 3 || opt < 1){
                    System.out.println("Incorrect option chosen. Please chose again.");
                }
            }
            else if (q == 6) {
                System.out.print("Enter Hospital ID: ");
                int hospitalID = sc.nextInt();
                sc.nextLine();
                Hospital chosenHospital = null;
                for (Hospital h : hospitals) {
                    if (h.getID() == hospitalID) {
                        chosenHospital = h;
                        break;
                    }
                }
                
                if (chosenHospital == null) {
                    System.out.println("Incorrect hospital ID / Unregistered Hospital");
                    continue;
                }
                
                chosenHospital.printAllSlotsWithoutIndices();
            }
            else if (q == 7) {
                System.out.print("Enter citizen ID: ");
                long citizenID = sc.nextLong();
                sc.nextLine();
                Citizen cz = null;
                for (Citizen citizen : citizens) {
                    if (citizenID == citizen.getID()) {
                        cz = citizen;
                        break;
                    }
                }
                
                if (cz == null) {
                    System.out.println("Citizen not found / unregistered citizen");
                    continue;
                }
                
                cz.printStatus();
            }
            else if (q == 8) break;
            else System.out.println("Incorrect option chosen.");
        }
    }

    public static void main(String[] args) {
        VaccinePortal vp = new VaccinePortal();
        vp.runPortal();
    }
}
