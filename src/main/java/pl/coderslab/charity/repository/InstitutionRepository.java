package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.model.Pair;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, Long> {

//    public default List<Pair<Institution, Institution>> findAllInPaires() {
//        List<Pair<Institution, Institution>> pairList = new ArrayList<>();
//        List<Institution> institutions = findAll();
//        int size = 0;
//        if (institutions.size() == 0) {
//            return pairList;
//        }
//        if (institutions.size() == 1) {
//            size = 1;
//        }
//        if (institutions.size() > 1) {
//            if (institutions.size() % 2 == 0) {
//                size = institutions.size() / 2;
//            } else {
//                size = institutions.size() / 2 + 1;
//            }
//        }
//
//        for (int i = 0; i < size; i++) {
//            Pair<Institution, Institution> pair = new Pair<>();
//            if (i == 0) {
//                pair.setL(institutions.get(i));
//                try {
//                    pair.setR(institutions.get(i + 1));
//                } catch (Exception c) {
//                }
//            }
//            if (i > 0) {
//                pair.setL(institutions.get(i * 2));
//                try {
//                    pair.setR(institutions.get(i * 2 + 1));
//                } catch (Exception c) {
//                }
//            }
//            pairList.add(pair);
//        }
//        return pairList;
//    }

}
