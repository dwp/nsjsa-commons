package uk.gov.dwp.jsa.adaptors.dto;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PhoneNumbers {

    private Map<String, PhoneNumber> numbers = new HashMap();

    public PhoneNumbers(final Collection<PhoneNumber> numbers) {
        createHashMap(numbers);
    }

    private void createHashMap(final Collection<PhoneNumber> numbers) {
        this.numbers.clear();
        for (PhoneNumber number : numbers) {
            this.numbers.put(number.getType(), number);
        }
    }

    public String getNumber(final String type) {
        final PhoneNumber number = numbers.get(type);
        if (Objects.isNull(number)) {
            return null;
        }
        return number.getNumber();
    }

    public Collection<PhoneNumber> getNumbers() {
        return numbers.values();
    }

    public void setNumbers(final Collection<PhoneNumber> numbers) {
        this.numbers.clear();
        createHashMap(numbers);
    }
}
