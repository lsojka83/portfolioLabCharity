package pl.coderslab.charity.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Status;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.StatusRepository;

@Component
public class StatusConverter implements Converter<String, Status> {

    @Autowired
    private StatusRepository statusRepository;


    @Override
    public Status convert(String id) {

        return statusRepository.findById(Long.parseLong(id)).get();
    }
}
