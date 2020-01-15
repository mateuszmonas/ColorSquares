package com.gmail.mateuszmonas.util;

import com.gmail.mateuszmonas.model.Field;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public final class GeneralUtil {

    public static <T> Optional<T> getRandomSetElement(Set<T> s) {
        if(s.size()==0) return Optional.empty();
        int r = ThreadLocalRandom.current().nextInt(1, s.size() + 1) - 1;
        int i = 0;
        T result = null;
        for (T element : s) {
            if (i == r) {
                result = element;
                break;
            }
            i++;
        }
        return Optional.ofNullable(result);

    }

}
