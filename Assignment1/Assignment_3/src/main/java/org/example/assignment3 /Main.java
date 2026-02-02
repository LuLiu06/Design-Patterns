package org.example.assignment3;

import org.example.assignment3.composite.Department;
import org.example.assignment3.leaf.Employee;

public class Main {

    public static void main(String[] args) {

        Department company = new Department("Company");

        Department it = new Department("IT");
        Department hr = new Department("HR");

        Employee alice = new Employee("Alice", 4000);
        Employee bob = new Employee("Bob", 3500);
        Employee clara = new Employee("Clara", 3000);

        it.add(alice);
        it.add(bob);
        hr.add(clara);

        company.add(it);
        company.add(hr);

        System.out.println("Total salary: " + company.getTotalSalary());
        System.out.println("\nOrganization structure (XML):");
        company.printXML("");
    }
}
