import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        //количество несовершеннолетних (т.е. людей младше 18 лет).
        persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();
        //cписок фамилий призывников (т.е. мужчин от 18 и до 27 лет).
        persons.stream()
                .filter(x -> x.getAge() < 27)
                .filter(x -> x.getAge() >= 18)
                .map(person -> person.getFamily())
                .collect(Collectors.toList());
        //отсортированный по фамилии список потенциально работоспособных людей с высшим образованием
        persons.stream()
                .filter(age -> age.getAge() < 60 && age.getSex().name() == "WOMAN" || age.getAge() < 65 && age.getSex().name() == "MAN")
                .filter(age -> age.getAge() >= 18)
                .filter(educ -> educ.getEducation().name() == "HIGHER")
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
    }
}