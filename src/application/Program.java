package application;

import entities.Employee;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Program {
    public static void main(String[] args) throws IOException {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        File file = new File("C:\\OO\\Stream\\src\\in.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            List<Employee> list = new ArrayList<>();

            String line = br.readLine();
            while (line != null) {
                String[] fields = line.split(", ");
                list.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
                line = br.readLine();
            }

            System.out.print("Enter the salary: ");
            Double baseSalary = sc.nextDouble();

            //coleta todos os emails com o salário maior ao valor inserido acima
            List<String> email = list.stream()
                    .filter(e -> e.getSalary() > baseSalary)
                    .map(e -> e.getEmail())
                    .sorted()
                    .collect(Collectors.toList());

            //faz a junção do salário de todas as pessoas que começam com M
            Double sum = list.stream()
                    .filter(e -> e.getName().charAt(0) == 'M')
                    .map(e -> e.getSalary())
                    .reduce(0.0, (x, y) -> x + y);

            System.out.println("Email of people whose salary is more than " + String.format("%.2f", baseSalary) + ":");
            email.forEach(System.out::println);
            System.out.print("Sum of salary of people whose name starts with 'M': " + sum);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        sc.close();
    }
}
