package practiceProject.cmap.domain.cafe.converter;

import practiceProject.cmap.domain.cafe.entity.mapping.CafeThema;
import practiceProject.cmap.domain.thema.entity.Thema;

import java.util.List;
import java.util.stream.Collectors;

public class CafeThemaConverter {

    public static List<CafeThema> toCafeThemaList(List<Thema> themaList) {

        return themaList.stream()
                .map(thema ->
                        CafeThema.builder()
                                .thema(thema)
                                .build()
                ).collect(Collectors.toList());
    }
}
