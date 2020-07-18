package com.restful.mvc.services;

import com.restful.mvc.api.v1.Mapper.VendorDTOMapper;
import com.restful.mvc.api.v1.Mapper.VendorMapper;
import com.restful.mvc.api.v1.model.VendorDTO;
import com.restful.mvc.domain.Vendor;
import com.restful.mvc.repository.VendorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service

public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper = VendorMapper.INSTANCE;
    private final VendorDTOMapper vendorDTOMapper = VendorDTOMapper.INSTANCE;

    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper, VendorDTOMapper vendorDTOMapper) {
        this.vendorRepository = vendorRepository;

    }

    @Override
    public List<VendorDTO> getVendors() {
        List<VendorDTO> vendorDTOS = vendorRepository.findAll().stream().map(vendorMapper::vendorToVendorDTO)
                .collect(Collectors.toList());

        return vendorDTOS;
    }

    @Override
    public VendorDTO createVendor(VendorDTO vendorDTO) {
        Vendor vendor  = vendorDTOMapper.vendorDTOToVendor(vendorDTO);

        try{
            Vendor savedVendor = vendorRepository.save(vendor);
            return vendorMapper.vendorToVendorDTO(savedVendor);
        }
        catch (Exception e){
            log.error(e.getMessage());
            throw  new RuntimeException();
        }

    }

    @Override
    public void deleteVendorById(Long id) {

        try {
            vendorRepository.deleteById(id);
        }
        catch (NoSuchElementException e){
            log.error(e.getMessage());
            throw new RuntimeException("Vendor does not exist");
        }
    }

    @Override
    public VendorDTO getVendorById(Long id) {

        Optional<Vendor> vendor = vendorRepository.findById(id);

        if(!vendor.isPresent()){
            log.error("No such element found ");
            throw  new NoSuchElementException("No Such Vendor Found");
        }
        else {
            VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor.get());
            return vendorDTO;
        }


    }

    @Override
    public VendorDTO editVendor(Long id, VendorDTO vendorDTO) {

        Optional<Vendor> vendorOptional= vendorRepository.findById(id);
        if(!vendorOptional.isPresent()){
            log.error("No such element found ");
            throw  new NoSuchElementException("No Such Vendor Found");
        }
        else{
            Vendor vendor =  vendorOptional.get();
            vendor.setName(vendorDTO.getName());

            Vendor savedVendor = vendorRepository.save(vendor);
            return  vendorMapper.vendorToVendorDTO(savedVendor);
        }
    }
}
