package com.mullya.app.service.impl;

import com.mullya.app.domain.Catalogue;
import com.mullya.app.repository.CatalogueRepository;
import com.mullya.app.service.CatalogueService;
import com.mullya.app.service.dto.CatalogueDTO;
import com.mullya.app.service.mapper.CatalogueMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Catalogue}.
 */
@Service
@Transactional
public class CatalogueServiceImpl implements CatalogueService {

    private final Logger log = LoggerFactory.getLogger(CatalogueServiceImpl.class);

    private final CatalogueRepository catalogueRepository;

    private final CatalogueMapper catalogueMapper;

    public CatalogueServiceImpl(CatalogueRepository catalogueRepository, CatalogueMapper catalogueMapper) {
        this.catalogueRepository = catalogueRepository;
        this.catalogueMapper = catalogueMapper;
    }

    @Override
    public CatalogueDTO save(CatalogueDTO catalogueDTO) {
        log.debug("Request to save Catalogue : {}", catalogueDTO);
        Catalogue catalogue = catalogueMapper.toEntity(catalogueDTO);
        catalogue = catalogueRepository.save(catalogue);
        return catalogueMapper.toDto(catalogue);
    }

    @Override
    public Optional<CatalogueDTO> partialUpdate(CatalogueDTO catalogueDTO) {
        log.debug("Request to partially update Catalogue : {}", catalogueDTO);

        return catalogueRepository
            .findById(catalogueDTO.getId())
            .map(existingCatalogue -> {
                catalogueMapper.partialUpdate(existingCatalogue, catalogueDTO);

                return existingCatalogue;
            })
            .map(catalogueRepository::save)
            .map(catalogueMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CatalogueDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Catalogues");
        return catalogueRepository.findAll(pageable).map(catalogueMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CatalogueDTO> findAll(Pageable pageable, Long parent) {
        log.debug("Request to get all Catalogues with parent {}", parent);
        if (parent == 0) {
            log.debug("calling findAllByParentIsNull");
            return catalogueRepository.findAllByParentIsNull(pageable).map(catalogueMapper::toDto);
        } else {
            log.debug("calling findAllByParent");
            return catalogueRepository.findAllByParent(pageable, parent).map(catalogueMapper::toDto);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CatalogueDTO> findOne(Long id) {
        log.debug("Request to get Catalogue : {}", id);
        return catalogueRepository.findById(id).map(catalogueMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Catalogue : {}", id);
        catalogueRepository.deleteById(id);
    }
}
