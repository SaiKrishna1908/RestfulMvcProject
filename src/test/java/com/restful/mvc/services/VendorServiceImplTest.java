package com.restful.mvc.services;

import com.restful.mvc.api.v1.Mapper.VendorDTOMapper;
import com.restful.mvc.api.v1.Mapper.VendorMapper;
import com.restful.mvc.api.v1.model.VendorDTO;
import com.restful.mvc.domain.Vendor;
import com.restful.mvc.repository.VendorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class VendorServiceImplTest {

    public static final String FIRSTNAME = "johnny";
    public static final String LASTNAME = "Law";
    public static final String LASTNAME1 = "Gala";
    public static final String FIRSTNAME1 = "Susy";
    public static final String FIRSTNAME2 = "Marie";
    public static final String LASTNAME2 = "Curie";
    public static final long ID = 1L;

    @Mock
    private VendorRepository vendorRepository;

    private final VendorMapper vendorMapper = VendorMapper.INSTANCE;
    private final VendorDTOMapper vendorDTOMapper = VendorDTOMapper.INSTANCE;
    private final List<Vendor> vendors = new ArrayList<>();
    
    private VendorService vendorService;
    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
        vendorService = new VendorServiceImpl(vendorRepository, vendorMapper, vendorDTOMapper);

        Vendor one  = new Vendor();
        one.setName(FIRSTNAME);

        Vendor two = new Vendor();
        two.setName(FIRSTNAME1);

        Vendor three = new Vendor();
        three.setName(FIRSTNAME2);

        vendors.add(one);
        vendors.add(two);
        vendors.add(three);

    }
    @Test
    void getVendors() {
        //given
        List<Vendor> vendors = this.vendors;

        when(vendorRepository.findAll()).thenReturn(vendors);

        List<VendorDTO> vendorDTOS = vendorService.getVendors();
        assertEquals(this.vendors.size(), vendorDTOS.size());
    }

    @Test
    void createVendor() {
        //given
        VendorDTO vendor = vendorMapper.vendorToVendorDTO(this.vendors.get(1));
        this.vendors.get(1).setId(1L);

        //when
        when(vendorRepository.save(any())).thenReturn(this.vendors.get(1));

        //then
        VendorDTO vendorDTO = vendorService.createVendor(vendor);

        assertEquals(FIRSTNAME1 , vendorDTO.getName());
    }

    @Test
    void deleteVendorById() {

        //then
        vendorService.deleteVendorById(1L);
        verify(vendorRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void getVendorById() {
        //given

        Vendor vendor = vendors.get(2);
        vendor.setId(ID);
        //when
        when(vendorRepository.findById(anyLong())).thenReturn(Optional.of(vendor));

        VendorDTO vendorDTO = vendorService.getVendorById(1L);

        assertEquals(FIRSTNAME2, vendorDTO.getName());
    }

    @Test
    void editVendor() {

        //given
        Vendor vendor = vendors.get(0);
        vendor.setId(ID);

        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(FIRSTNAME2);

        //when
        when(vendorRepository.findById(anyLong())).thenReturn(Optional.of(vendor));

        assertEquals(FIRSTNAME2, vendorDTO.getName());

    }
}