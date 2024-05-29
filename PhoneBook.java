import java.util.*;

public class PhoneBook {
    private HashMap<String, Set<String>> phoneBook;

    public PhoneBook() {
        phoneBook = new HashMap<>();
    }

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("Введите имя (или 'q' для завершения):");
                String name = scanner.nextLine().trim();
                if (name.equalsIgnoreCase("q")) {
                    break;
                }
                if (name.isEmpty()) {
                    throw new IllegalArgumentException("Имя не может быть пустым. Попробуйте снова.");
                }
                name = capitalizeName(name);

                System.out.println("Введите номер телефона (только цифры):");
                String phone = scanner.nextLine().trim();
                if (phone.isEmpty()) {
                    throw new IllegalArgumentException("Номер телефона не может быть пустым. Попробуйте снова.");
                }
                if (!phone.matches("[0-9]+")) {
                    throw new IllegalArgumentException("Номер телефона должен содержать только цифры. Попробуйте снова.");
                }

                phoneBook.addContact(name, phone);
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Произошла ошибка. Попробуйте снова.");
            }
        }
        phoneBook.printSortedContacts();
        scanner.close();
    }

    public static String capitalizeName(String name) {
        if (name == null || name.isEmpty()) {
            return name;
        }
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

    public void addContact(String name, String phone) {
        phoneBook.computeIfAbsent(name, k -> new HashSet<>()).add(phone);
    }

    public void printSortedContacts() {
        List<Map.Entry<String, Set<String>>> contacts = new ArrayList<>(phoneBook.entrySet());
        contacts.sort((entry1, entry2) -> Integer.compare(entry2.getValue().size(), entry1.getValue().size()));
        for (Map.Entry<String, Set<String>> entry : contacts) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}