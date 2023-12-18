package ma.anizar.frmw.service;

import ma.anizar.frmw.model.Region;
import ma.anizar.frmw.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegionService {

    private final RegionRepository regionRepository;

    @Autowired
    public RegionService(RegionRepository regionRepository){this.regionRepository=regionRepository;}

    public String addRegion(Region region) {

        try {
            regionRepository.save(region);
            return "Opération, effectuée avec succès.";
        }catch (Exception e){
            return "Erreur lors de l'ajout de la Competition";
        }
    }
}
