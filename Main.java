import java.util.*;

class Employee {
    String id;
    String name;
    String depart;
    String gend;

    Employee(String id, String name, String depart, String gend) {
        this.id = id;
        this.name = name;
        this.depart = depart;
        this.gend = gend;
    }

    public String getId() {
        return id;
    }

    public String getDepart() {
        return depart;
    }

    public String getGend() {
        return gend;
    }
}

class EmployeeDatabase {
    private Map<String, List<Employee>> collections = new HashMap<>();

    public void createCollection(String collectionName) {
        collections.putIfAbsent(collectionName, new ArrayList<>());
    }

    public void indexData(String collectionName, String excludeColumn) {
        if (!collections.containsKey(collectionName)) {
            System.out.println("Collection '" + collectionName + "' does not exist.");
            return;
        }
        List<Employee> employees = Arrays.asList(
                new Employee("1", "Kumar", "ECE", "Male"),
                new Employee("2", "Raaj", "IT", "Male"),
                new Employee("3", "Karthi", "EEE", "Male"),
                new Employee("4", "Mahesh", "IT", "Male"),
                new Employee("5", "Pandi", "CSE", "Male"));
        for (Employee emp : employees) {
            if ("Depart".equalsIgnoreCase(excludeColumn)) {
                emp = new Employee(emp.getId(), emp.name, null, emp.getGend());
            } else if ("Gend".equalsIgnoreCase(excludeColumn)) {
                emp = new Employee(emp.getId(), emp.name, emp.getDepart(), null);
            }
            collections.get(collectionName).add(emp);
        }
    }

    public List<Employee> searchByColumn(String collectionName, String columnName, String columnValue) {
        if (!collections.containsKey(collectionName)) {
            System.out.println("Collection '" + collectionName + "' does not exist.");
            return Collections.emptyList();
        }
        List<Employee> result = new ArrayList<>();
        for (Employee emp : collections.get(collectionName)) {
            if ("Depart".equalsIgnoreCase(columnName) && columnValue.equals(emp.getDepart())) {
                result.add(emp);
            } else if ("Gend".equalsIgnoreCase(columnName) && columnValue.equals(emp.getGend())) {
                result.add(emp);
            }
        }
        return result;
    }

    public int getEmpCount(String collectionName) {
        if (!collections.containsKey(collectionName)) {
            System.out.println("Collection '" + collectionName + "' does not exist.");
            return 0;
        }
        return collections.get(collectionName).size();
    }

    public void delEmpById(String collectionName, String employeeId) {
        if (!collections.containsKey(collectionName)) {
            System.out.println("Collection '" + collectionName + "' does not exist.");
            return;
        }
        collections.get(collectionName).removeIf(emp -> emp.getId().equals(employeeId));
    }

    public Map<String, Integer> getDepFacet(String collectionName) {
        if (!collections.containsKey(collectionName)) {
            System.out.println("Collection '" + collectionName + "' does not exist.");
            return Collections.emptyMap();
        }
        Map<String, Integer> departCount = new HashMap<>();
        for (Employee emp : collections.get(collectionName)) {
            String dept = emp.getDepart();
            if (dept != null) {
                departCount.put(dept, departCount.getOrDefault(dept, 0) + 1);
            }
        }
        return departCount;
    }
}

public class Main {
    public static void main(String[] args) {
        EmployeeDatabase db = new EmployeeDatabase();
        String p_name_Collection = "Kumar";
        String p_phone_Collection = "1";
        db.createCollection(p_name_Collection);
        db.createCollection(p_phone_Collection);
        System.out.println("Employee count " + p_name_Collection + ": " + db.getEmpCount(p_name_Collection));
        db.indexData(p_name_Collection, "Depart");
        db.indexData(p_phone_Collection, "Gend");
        db.delEmpById(p_name_Collection, "2");
        System.out.println(
                "Employee count in " + p_name_Collection + " after deletion: " + db.getEmpCount(p_name_Collection));
        System.out.println("Search by Depart 'IT': " + db.searchByColumn(p_name_Collection, "Depart", "IT"));
        System.out.println("Search by Gend 'Male': " + db.searchByColumn(p_name_Collection, "Gend", "Male"));
        System.out.println("Search in " + p_phone_Collection + " by Depart 'IT': "
                + db.searchByColumn(p_phone_Collection, "Depart", "IT"));
        System.out.println("Depart facet " + p_name_Collection + ": " + db.getDepFacet(p_name_Collection));
        System.out.println("Depart facet " + p_phone_Collection + ": " + db.getDepFacet(p_phone_Collection));
    }
}
