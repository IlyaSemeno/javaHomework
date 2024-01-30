import java.util.*;

public class LaptopStore {
    private Set<Laptop> laptops = new HashSet<>();

    public void addLaptop(Laptop laptop) {
        laptops.add(laptop);
    }

    public Set<Laptop> getLaptops() {
        return laptops;
    }

    public void filterLaptops(Map<String, Object> filters) {
        for (Laptop laptop : laptops) {
            boolean passFilter = true;
            for (Map.Entry<String, Object> entry : filters.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                switch (key) {
                    case "ram":
                        if (laptop.getRam() < (int) value) passFilter = false;
                        break;
                    case "storage":
                        if (laptop.getStorage() < (int) value) passFilter = false;
                        break;
                    case "os":
                        if (!laptop.getOs().equals(value)) passFilter = false;
                        break;
                    case "color":
                        if (!laptop.getColor().equals(value)) passFilter = false;
                        break;
                    default:
                        break;
                }
            }
            if (passFilter) {
                System.out.println("Laptop: " + laptop.getBrand() + ", RAM: " + laptop.getRam() + ", Storage: " +
                        laptop.getStorage() + ", OS: " + laptop.getOs() + ", Color: " + laptop.getColor());
            }
        }
    }

    public static void main(String[] args) {
        LaptopStore store = new LaptopStore();
        store.addLaptop(new Laptop("Lenovo", 8, 512, "Windows", "Black"));
        store.addLaptop(new Laptop("Dell", 16, 1024, "Linux", "Silver"));
        store.addLaptop(new Laptop("HP", 4, 256, "Windows", "Black"));
        store.addLaptop(new Laptop("Acer", 32, 2048, "MacOS", "Gray"));

        Scanner scanner = new Scanner(System.in);
        Map<String, Object> filters = new HashMap<>();
        System.out.println("Введите цифру, соответствующую необходимому критерию:");
        System.out.println("1 - ОЗУ\n2 - Объем ЖД\n3 - Операционная система\n4 - Цвет");
        int criterion = scanner.nextInt();
        switch (criterion) {
            case 1:
                System.out.println("Введите минимальное значение ОЗУ:");
                int minRam = scanner.nextInt();
                filters.put("ram", minRam);
                break;
            case 2:
                System.out.println("Введите минимальный объем ЖД:");
                int minStorage = scanner.nextInt();
                filters.put("storage", minStorage);
                break;
            case 3:
                System.out.println("Введите операционную систему:");
                String os = scanner.next();
                filters.put("os", os);
                break;
            case 4:
                System.out.println("Введите цвет:");
                String color = scanner.next();
                filters.put("color", color);
                break;
            default:
                System.out.println("Некорректный ввод.");
                return;
        }
        store.filterLaptops(filters);
    }
}
