package pl.coderslab.charity.controler;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.util.List;

@RestController
public class InstitutionRestController {
    private final InstitutionRepository institutionRepository;

    public InstitutionRestController(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    @GetMapping("/institutions")
    public List<Institution> getAllInstitutions()
    {
        return institutionRepository.findAll();
    }

    @GetMapping("/institutions/{pageNumber}")
    public List<Institution> getInstitutionsFromPage(@PathVariable int pageNumber)
    {
        int institutionPageSize = 10;
        int institutionPageNumber = pageNumber;

        PageRequest pageRequest = PageRequest.of(institutionPageNumber, institutionPageSize);
        return institutionRepository.findAll(pageRequest).getContent();
    }

    @GetMapping("/institutions/pages")
    public int getInstitutionPageCount()
    {
        int institutionPageSize = 10;

        PageRequest pageRequest = PageRequest.ofSize(institutionPageSize);
        return institutionRepository.findAll(pageRequest).getTotalPages();
    }
}
