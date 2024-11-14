import java.util.ArrayList;
import java.util.List;

abstract class OrganizationComponent {
    protected String name;

    public OrganizationComponent(String name) {
        this.name = name;
    }

    public abstract void display(int depth);
    public abstract int getEmployeeCount();
    public abstract double getBudget();
    public abstract OrganizationComponent findByName(String name);

    public void add(OrganizationComponent component) {
        throw new UnsupportedOperationException();
    }

    public void remove(OrganizationComponent component) {
        throw new UnsupportedOperationException();
    }
}

class Employee extends OrganizationComponent {
    private String position;
    private double salary;

    public Employee(String name, String position, double salary) {
        super(name);
        this.position = position;
        this.salary = salary;
    }

    @Override
    public void display(int depth) {
        System.out.println(" ".repeat(depth) + "- " + name + ", " + position + ", Зарплата: " + salary);
    }

    @Override
    public int getEmployeeCount() {
        return 1;
    }

    @Override
    public double getBudget() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public OrganizationComponent findByName(String name) {
        return this.name.equals(name) ? this : null;
    }
}

class Contractor extends OrganizationComponent {
    private String position;
    private double fixedPay;

    public Contractor(String name, String position, double fixedPay) {
        super(name);
        this.position = position;
        this.fixedPay = fixedPay;
    }

    @Override
    public void display(int depth) {
        System.out.println(" ".repeat(depth) + "- Контрактор: " + name + ", " + position + ", Фиксированная оплата: " + fixedPay);
    }

    @Override
    public int getEmployeeCount() {
        return 1;
    }

    @Override
    public double getBudget() {
        return 0;
    }

    @Override
    public OrganizationComponent findByName(String name) {
        return this.name.equals(name) ? this : null;
    }
}

class Department extends OrganizationComponent {
    private List<OrganizationComponent> components = new ArrayList<>();

    public Department(String name) {
        super(name);
    }

    public void add(OrganizationComponent component) {
        components.add(component);
    }

    public void remove(OrganizationComponent component) {
        components.remove(component);
    }

    @Override
    public void display(int depth) {
        System.out.println(" ".repeat(depth) + "Отдел: " + name);
        for (OrganizationComponent component : components) {
            component.display(depth + 2);
        }
    }

    @Override
    public int getEmployeeCount() {
        int count = 0;
        for (OrganizationComponent component : components) {
            count += component.getEmployeeCount();
        }
        return count;
    }

    @Override
    public double getBudget() {
        double totalBudget = 0;
        for (OrganizationComponent component : components) {
            totalBudget += component.getBudget();
        }
        return totalBudget;
    }

    @Override
    public OrganizationComponent findByName(String name) {
        if (this.name.equals(name)) {
            return this;
        }
        for (OrganizationComponent component : components) {
            OrganizationComponent found = component.findByName(name);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    public List<OrganizationComponent> getAllEmployees() {
        List<OrganizationComponent> employees = new ArrayList<>();
        for (OrganizationComponent component : components) {
            if (component instanceof Employee || component instanceof Contractor) {
                employees.add(component);
            } else if (component instanceof Department) {
                employees.addAll(((Department) component).getAllEmployees());
            }
        }
        return employees;
    }
}

class OrganizationHierarchyDemo {
    public static void main(String[] args) {
        Department headOffice = new Department("Головной офис");

        Employee ceo = new Employee("Айжан", "Генеральный директор", 250000);
        Employee cto = new Employee("Карина", "Технический директор", 200000);

        headOffice.add(ceo);
        headOffice.add(cto);

        Department hrDepartment = new Department("Отдел кадров");
        Employee hrManager = new Employee("Бота", "Менеджер по кадрам", 120000);
        hrDepartment.add(hrManager);

        Department techDepartment = new Department("Технический отдел");
        Employee dev1 = new Employee("Ерке", "Разработчик", 100000);
        Employee dev2 = new Employee("Расул", "Разработчик", 100000);
        Contractor contractor = new Contractor("Диас", "Технический консультант", 50000);

        techDepartment.add(dev1);
        techDepartment.add(dev2);
        techDepartment.add(contractor);

        headOffice.add(hrDepartment);
        headOffice.add(techDepartment);

        System.out.println("Структура организации:");
        headOffice.display(1);

        System.out.println("\nОбщее количество сотрудников: " + headOffice.getEmployeeCount());
        System.out.println("Общий бюджет: " + headOffice.getBudget() + " руб.");

        dev1.setSalary(110000);
        System.out.println("\nОбновленный бюджет после изменения зарплаты: " + headOffice.getBudget() + " руб.");

        String searchName = "Диас";
        OrganizationComponent found = headOffice.findByName(searchName);
        if (found != null) {
            System.out.println("\nНайден сотрудник: " + searchName);
            found.display(1);
        } else {
            System.out.println("\nСотрудник не найден: " + searchName);
        }

        System.out.println("\nВсе сотрудники в Техническом отделе:");
        for (OrganizationComponent employee : techDepartment.getAllEmployees()) {
            employee.display(2);
        }
    }
}
