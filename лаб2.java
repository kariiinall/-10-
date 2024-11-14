import java.util.ArrayList;
import java.util.List;

abstract class FileSystemComponent {
    protected String name;

    public FileSystemComponent(String name) {
        this.name = name;
    }

    public abstract void display(int depth);

    public void add(FileSystemComponent component) {
        throw new UnsupportedOperationException();
    }

    public void remove(FileSystemComponent component) {
        throw new UnsupportedOperationException();
    }

    public FileSystemComponent getChild(int index) {
        throw new UnsupportedOperationException();
    }
}

class File extends FileSystemComponent {
    public File(String name) {
        super(name);
    }

    @Override
    public void display(int depth) {
        System.out.println(" ".repeat(depth) + "- File: " + name);
    }
}

class Directory extends FileSystemComponent {
    private List<FileSystemComponent> children = new ArrayList<>();

    public Directory(String name) {
        super(name);
    }

    @Override
    public void add(FileSystemComponent component) {
        children.add(component);
    }

    @Override
    public void remove(FileSystemComponent component) {
        children.remove(component);
    }

    @Override
    public FileSystemComponent getChild(int index) {
        return children.get(index);
    }

    @Override
    public void display(int depth) {
        System.out.println(" ".repeat(depth) + "+ Directory: " + name);
        for (FileSystemComponent component : children) {
            component.display(depth + 2);
        }
    }
}

class FileSystemDemo {
    public static void main(String[] args) {
        Directory root = new Directory("Root");
        File file1 = new File("File1.txt");
        File file2 = new File("File2.txt");

        Directory subDir = new Directory("SubDirectory");
        File subFile1 = new File("SubFile1.txt");

        root.add(file1);
        root.add(file2);
        subDir.add(subFile1);
        root.add(subDir);

        root.display(1);
    }
}
/*
 В чем заключается суть паттерна Компоновщик?
Паттерн "Компоновщик" позволяет объединять объекты в древовидные структуры иерархии "часть-целое". Он позволяет клиентам работать с отдельными объектами и их группами одинаково, упрощая взаимодействие с составными структурами.
 В каких случаях удобно использовать паттерн Компоновщик?
Паттерн "Компоновщик" полезен, когда нужно создать структуру, где можно рассматривать отдельные объекты и их объединения одинаково, например, для построения иерархии файловой системы или организации графических элементов.
 Какие преимущества и недостатки имеет паттерн Компоновщик?
Преимущества: упрощает работу с иерархиями "часть-целое", позволяет клиентам единообразно взаимодействовать с объектами. Недостатки: возможная сложность кода из-за необходимости обеспечения единообразного интерфейса для компонентов с разными возможностями (например, листы и контейнеры).
 Какие другие паттерны структурного проектирования могут использоваться вместе с паттерном Компоновщик?
Паттерны Фасад и Легковес могут дополнять Компоновщик. Фасад может скрывать сложные структуры Компоновщика от клиента, а Легковес может уменьшить использование памяти, если иерархия Компоновщика содержит большое количество объектов с одинаковыми свойствами.
*/
